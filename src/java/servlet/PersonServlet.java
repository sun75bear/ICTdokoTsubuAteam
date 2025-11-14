package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import model.Post;
import store.InMemoryDB;

@WebServlet("/person")
public class PersonServlet extends HttpServlet {

@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    //ここでは検索欄に入力した名前で全体候補を絞って、次のページに渡す
    request.setCharacterEncoding("UTF-8");

    String name = Optional.ofNullable(request.getParameter("name"))
                          .orElse("")
                          .trim();
    if (name.isEmpty()) {
        response.sendRedirect(request.getContextPath() + "/keyword.jsp");
        return;
    }

    // もともとの全投稿を取得
    List<Post> posts = InMemoryDB.list(name);

    // ★ 追加：検索キーワード q が来ていたら、そのキーワードを含む投稿だけに絞り込む
    // 
    String q = Optional.ofNullable(request.getParameter("q"))
                       .orElse("")
                       .trim();

    if (!q.isEmpty()) {
        List<Post> filtered = new ArrayList<>();
        for (Post p : posts) {
            String content = Optional.ofNullable(p.getContent()).orElse("");
            if (content.contains(q)) {   // 「サコダ車両」を含む投稿だけ通す
                filtered.add(p);
            }
        }
        posts = filtered;  // posts を絞り込んだリストに差し変え＝検索済のリストを準備
    }
    //検索結果であるpostsをリクエストスコープに格納して、person.jspにフォワードする
    request.setAttribute("posts", posts);

    RequestDispatcher rd =
            request.getRequestDispatcher("/WEB-INF/jsp/person.jsp");
    rd.forward(request, response);
}



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        //nameとactionを、requestスコープから取得して安全に格納する
        String name = Optional.ofNullable(request.getParameter("name"))
                              .orElse("")
                              .trim();
        String action = Optional.ofNullable(request.getParameter("action"))
                                .orElse("");
        //名前が空なら検索の先頭にフォワード
        if (name.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }
        //actionがaddならリクエストからcontentを取得して格納し、インメモリデータベースに名前とcontentを一組でaddする
        if ("add".equals(action)) {
            String content = Optional.ofNullable(request.getParameter("content"))
                                     .orElse("")
                                     .trim();
            if (content.isEmpty()) {
                HttpSession session = request.getSession();
                session.setAttribute("flash_error", "本文を入力してください。");
            } else {
                InMemoryDB.addPost(name, content);
            }
        //actionがreactなら、リクエストからtype（like or bad ）を取得して格納し、long型のidをリクエストパラメータから取得し、インメモリデータベースに名前・id・like or badで格納する
        //いいねをid付きでDBに入れる処理で、今後参考にするべき
        } else if ("react".equals(action)) {
            String type = request.getParameter("type"); // like / bad
            long id = Long.parseLong(request.getParameter("id"));
            InMemoryDB.react(name, id, "like".equals(type));
        }
        //person?name=に、nameを入れてpersonページにリダイレクトする
        String encoded = URLEncoder.encode(name, StandardCharsets.UTF_8);
        response.sendRedirect(request.getContextPath() + "/person?name=" + encoded);
    }
}
