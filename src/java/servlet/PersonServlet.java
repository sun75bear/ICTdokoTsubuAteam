// servlet/PersonServlet.java
package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.*;
//import store.InMemoryDB;
//import model.Post;

@WebServlet("/person")
public class PersonServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //処理内容がブラックボックスなので、今はただ表示させるだけ        
//        String name = Optional.ofNullable(request.getParameter("name")).orElse("").trim();
//        if (name.isBlank()) {
//            response.sendRedirect(request.getContextPath() + "/index.jsp");
//            return;
//        }
//        request.setAttribute("posts", InMemoryDB.list(name)); // ★ なければ自動作成
        request.getRequestDispatcher("/WEB-INF/jsp/person.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //処理内容がブラックボックスなので、今はただ表示させるだけ
//        request.setCharacterEncoding("UTF-8");
//        String name   = Optional.ofNullable(request.getParameter("name")).orElse("").trim();
//        String action = Optional.ofNullable(request.getParameter("action")).orElse("");
//
//        if (name.isBlank()) {
//            response.sendRedirect(request.getContextPath() + "/index.jsp");
//            return;
//        }
//
//        switch (action) {
//            case "add": {
//                String content = Optional.ofNullable(request.getParameter("content")).orElse("").trim();
//                if (content.isBlank()) {
//                    request.getSession().setAttribute("flash_error", "本文を入力してください。");
//                } else {
//                    InMemoryDB.addPost(name, content);
//                }
//                break;
//            }
//            case "react": {
//                String type = Optional.ofNullable(request.getParameter("type")).orElse("");
//                long id = Long.parseLong(request.getParameter("id"));
//                InMemoryDB.find(name, id).ifPresent(p -> {
//                    if ("like".equals(type)) p.incLikes();
//                    if ("bad".equals(type))  p.incBads();
//                });
//                break;
//            }
//            default:
//                // no-op
//        }
//        response.sendRedirect(request.getContextPath() + "/person?name=" +
//                java.net.URLEncoder.encode(name, "UTF-8"));
        //なにもせずpersonにフォワードするだけ
        request.getRequestDispatcher("/WEB-INF/jsp/person.jsp").forward(request, response);      
    }
}
