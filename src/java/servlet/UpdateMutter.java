package servlet;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.Mutter;

@WebServlet("/UpdateMutter")
public class UpdateMutter extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // ★ ここで文字コードを指定（日本語対応）
        request.setCharacterEncoding("UTF-8");
        //リクエストスコープからmutterIDとtextを取得
        String updateMutterId = request.getParameter("mutterId");
        String editText = request.getParameter("text");
        //アプリケーションスコープのmutterListの中身のうち、IDが合致するもののtextを変更する
        List<Mutter> mutterList = (List<Mutter>) getServletContext().getAttribute("mutterList");
        for (Mutter mutter : mutterList) {
            if (mutter.getMutterId().equals(updateMutterId)) {
                mutter.setText(editText);
                break;
            }
        }

        response.sendRedirect("Main");
    }
}
