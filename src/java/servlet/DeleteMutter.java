package servlet;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.Mutter; // ← 自作クラスのインポートを忘れずに！

@WebServlet("/DeleteMutter")
public class DeleteMutter extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //選択されたツイートの"mutterId"を、削除するmutterIdとして取得する
        String mutterIdToDelete = request.getParameter("mutterId");
        //アプリケーションスコープから現在のmutterListを取得
        List<Mutter> mutterList = (List<Mutter>) getServletContext().getAttribute("mutterList");
        //Mutterから、mutterIdToDeleteに一致するmutterIdを持つものをremoveする
        mutterList.removeIf(mutter -> mutterIdToDelete.equals(mutter.getMutterId()));
        //マイページへリダイレクトする
        response.sendRedirect(request.getContextPath() + "/MyPage");
    }
}
