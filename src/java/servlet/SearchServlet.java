// servlet/SearchServlet.java
package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 文字化け対策（EncodingFilterがあるなら省略可）
        request.setCharacterEncoding("UTF-8");

        // mode=name（? 名前の検索） / mode=general（? 普通の検索）
        String mode = Optional.ofNullable(request.getParameter("mode"))
                .orElse("name")
                .toLowerCase(Locale.ROOT);

        // ? 普通の検索：本文（全投稿）から検索
        if ("general".equals(mode)) {
            String q = Optional.ofNullable(request.getParameter("q")).orElse("").trim();
            if (q.isBlank()) {
                request.setAttribute("error", "キーワードを入力してください。");
                request.getRequestDispatcher("keyword").forward(request, response);
                return;
            }

            // 本文全文検索（InMemoryDB に実装済みの searchAll を使用）
            java.util.List<model.SearchHit> hits = store.InMemoryDB.searchAll(q);

            request.setAttribute("keyword", q);
            request.setAttribute("hits", hits); // result.jsp で表示
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
            rd.forward(request, response);
            return;
        }

        // ? 名前の検索：名前の検索に奥本と入力すると、person?name=奥本にリダイレクトされる
        //「フォームからkeywordの値を取得し、もし値がnullや未定義でも安全に処理できるように空文字列 ("") で置き換え、さらに前後の空白を削除した結果」を格納
        String keyword = Optional.ofNullable(request.getParameter("keyword")).orElse("").trim();
        if (keyword.isBlank()) {
            request.setAttribute("error", "名前を入力してください。");
            request.getRequestDispatcher("keyword").forward(request, response);
            return;
        }
        String encoded = URLEncoder.encode(keyword, StandardCharsets.UTF_8);
        response.sendRedirect(request.getContextPath() + "/person?name=" + encoded);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "keyword");
    }
}
