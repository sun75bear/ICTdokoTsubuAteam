package servlet;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.Mutter;

@WebServlet("/EditMutter")
public class EditMutter extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //mutterIDでのリファクタリングが必要
        // 文字コードの指定（日本語対応）
        request.setCharacterEncoding("UTF-8");

        // 投稿IDの取得(mutterId)
        String editMutterId = request.getParameter("mutterId");
        System.out.println("編集対象ID: " + editMutterId); // ログ出力

        // アプリケーションスコープから投稿リストを取得
        List<Mutter> mutterList = (List<Mutter>) getServletContext().getAttribute("mutterList");

        if (mutterList == null) {
            System.out.println("mutterListがnullです！");
            response.sendRedirect("Main"); // 投稿リストがない場合はメインへ戻す
            return;
        }

        // 編集対象の投稿を探す、String同士の比較だから==ではなく.equals()で比較する
        Mutter target = null;
        for (Mutter mutter : mutterList) {
            if (mutter.getMutterId().equals(editMutterId)) {
                target = mutter;
                break;
            }
        }

        if (target == null) {
            System.out.println("指定されたIDの投稿が見つかりませんでした！");
            response.sendRedirect("MyPage"); // 投稿が見つからない場合はマイページへリダイレクトして処理を終了
            return;
        }

        // 編集対象をリクエストスコープに保存
        request.setAttribute("editMutter", target);

        // editMutterの編集画面へフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/edit.jsp");
        dispatcher.forward(request, response);
    }
}
