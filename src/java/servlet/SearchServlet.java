// servlet/SearchServlet.java
package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        String keyword = Optional.ofNullable(request.getParameter("keyword"))
//                                 .orElse("")
//                                 .trim();
//
//        if (keyword.isBlank()) {
//            request.setAttribute("error", "キーワードを入力してください。");
//            request.getRequestDispatcher("Display").forward(request, response);
//            return;
//        }

//        // ★ 名簿チェックや結果ページへの分岐をやめて、必ず個人ページへ
//        response.sendRedirect(request.getContextPath() + "/person?name=" +
//                java.net.URLEncoder.encode(keyword, "UTF-8"));
        //現在は何もせず、displayページへ戻る
        response.sendRedirect("Display");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //現在は何もせず、displayページへ戻る
        response.sendRedirect("Display");
    }
}

