package model;

public class SearchHit {
    private final String name;       // 投稿者 (個人ページ名)
    private final long postId;       // 投稿ID
    private final String snippet;    // スニペット (抜粋テキスト)
    private final String createdAt;  // 投稿作成日時 (日付情報)

    public SearchHit(String name, long postId, String snippet, String createdAt) {
        this.name = name;
        this.postId = postId;
        this.snippet = snippet;
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public long getPostId() {
        return postId;
    }

    public String getSnippet() {
        return snippet;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
