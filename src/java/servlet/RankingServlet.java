package servlet;

import model.UserRanking;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.*;

@WebServlet("/ranking")
public class RankingServlet extends HttpServlet {
    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        //データベース参照先を原本から変更するから、ここではただfowardするだけ
//        // アプリケーションスコープからランキングデータを取得
//        ServletContext context = getServletContext();
//        List<UserRanking> list = (List<UserRanking>) context.getAttribute("rankingList");
//
//
//        // リクエストスコープに渡してJSPへ
//        request.setAttribute("rankingList", list);
        request.getRequestDispatcher("/WEB-INF/jsp/ranking.jsp").forward(request, response);
    }
}

//データベース連携
/*@WebServlet("/ranking")
public class RankingServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

try (Connection con = dataSource.getConnection()) {
    String sql = "SELECT name, count FROM user_ranking";
    try (PreparedStatement stmt = con.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
            list.add(new UserRanking(rs.getString("name"), rs.getInt("count")));
        }
    }
}
        // JSPに渡す
        request.setAttribute("rankingList", list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/ranking.jsp");
        dispatcher.forward(request, response);
    }
}*/

