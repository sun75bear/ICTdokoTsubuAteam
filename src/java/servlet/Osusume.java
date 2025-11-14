package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.User;

@WebServlet(name = "Osusume", urlPatterns = {"/Osusume"})
public class Osusume extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginUser");

        if (loginUser == null) {
            // ログインしていない場合はTOPへ
            response.sendRedirect("index.jsp");
        } else {
            // ログイン済みならおすすめ記事画面へ
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("WEB-INF/jsp/osusume.jsp");
            dispatcher.forward(request, response);
        }
    }
}
