package store;

import model.Post;
import model.SearchHit;

import java.text.Normalizer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class InMemoryDB {

    // 名前ごとの投稿リスト
    private static final Map<String, CopyOnWriteArrayList<Post>> POSTS =
            new ConcurrentHashMap<>();

    private static final DateTimeFormatter FMT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            // =========================================================
    // 【? 静的初期化ブロックを追加】
    // アプリケーション起動時に一度だけ実行され、初期データを投入します。
    // =========================================================
    static {
        // --- 初期データの定義 ---
        
        // ユーザー '奥本' の初期投稿
//        addPost("奥本", "これは私の最初のテスト投稿です。");
//        addPost("奥本", "離職者によるさこだ車両の不正内部告発");
//        addPost("奥本", "さこだ車両オイル交換サービス！");
//        addPost("奥本", "サコダ車両");
//
//        // 別のユーザー 'guest' の初期投稿 (任意)
//        addPost("城山", "サコダ、どこつぶアプリへ！");
//        addPost("渡辺", "初期車両は、動作確認が簡単になりますね！");
//        addPost("海", "今日からサコダ車両で開発を頑張ります！");
//        addPost("海", "今日からさこだ車両で開発を頑張ります！");
//        addPost("神田","革ジャン");
        // -------------------------
    }
    // =========================================================
    
    
    // 内部用：指定名のリストを必ず返す
    private static CopyOnWriteArrayList<Post> ensure(String name) {
        return POSTS.computeIfAbsent(name, k -> new CopyOnWriteArrayList<>());
    }

    // =====================================================
    // 個人ページ用 API
    // =====================================================

    // 一覧取得
    public static List<Post> list(String name) {
        return new ArrayList<>(ensure(name));
    }

    // 投稿追加
    public static void addPost(String name, String content) {
        CopyOnWriteArrayList<Post> list = ensure(name);

        long newId = list.stream().mapToLong(Post::getId).max().orElse(0L) + 1;

        Post p = new Post();
        p.setId(newId);
        p.setAuthor(name);
        p.setContent(content);
        p.setLikes(0);
        p.setBads(0);
        p.setCreatedAt(LocalDateTime.now().format(FMT));

        // 新しい投稿を先頭に
        list.add(0, p);
    }

    // IDで1件取得
    public static Optional<Post> find(String name, long id) {
        return ensure(name).stream()
                .filter(p -> p.getId() == id)
                .findFirst();
    }

    // リアクション（イイネ / バット）
    public static void react(String name, long id, boolean like) {
        find(name, id).ifPresent(p -> {
            if (like) {
                p.setLikes(p.getLikes() + 1);
            } else {
                p.setBads(p.getBads() + 1);
            }
        });
    }

    // =====================================================
    // 全文検索 API（? 普通の検索 用）
    // =====================================================
    public static List<SearchHit> searchAll(String query) {
        String q = norm(query);
        List<SearchHit> out = new ArrayList<>();

        for (Map.Entry<String, CopyOnWriteArrayList<Post>> e : POSTS.entrySet()) {
            String name = e.getKey();
            for (Post p : e.getValue()) {
                String content = Optional.ofNullable(p.getContent()).orElse("");
                String nc = norm(content);
                int idx = nc.indexOf(q);
                if (idx >= 0) {
                    String snippet = makeSnippet(content, idx, q.length());
                    out.add(new SearchHit(
                            name,
                            p.getId(),
                            snippet,
                            p.getCreatedAt()
                    ));
                }
            }
        }

        // 新しい投稿順（ID降順）に並べる
        out.sort(Comparator.comparingLong(SearchHit::getPostId).reversed());
        return out;
    }

    // 正規化（全角半角・大文字小文字をゆるく統一）
    private static String norm(String s) {
        String t = Optional.ofNullable(s).orElse("");
        t = Normalizer.normalize(t, Normalizer.Form.NFKC);
        return t.toLowerCase(Locale.ROOT);
    }

    // 検索ヒット周辺だけ抜き出してスニペット生成
    private static String makeSnippet(String full, int hitIndex, int hitLen) {
        int total = full.length();
        int start = Math.max(0, hitIndex - 12);
        int end   = Math.min(total, hitIndex + hitLen + 12);
        String head = (start > 0) ? "…" : "";
        String tail = (end < total) ? "…" : "";
        return head + full.substring(start, end).replace("\n", " ") + tail;
    }
}
