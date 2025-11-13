package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Mutter;
import model.User;

@WebServlet(name = "Osusume", urlPatterns = {"/Osusume"})
public class Osusume extends HttpServlet {

    @Override
    public void init() throws ServletException {
        ServletContext application = getServletContext();
        List<Mutter> mutterList = new ArrayList<>();

        // ★★★ テストデータを12件追加 ★★★
        mutterList.add(new Mutter(1,"奥本", "データ１、つぶやきアプリへようこそ！",0));
        mutterList.add(new Mutter(2,"奥本", "データ2、このアプリの動作確認中だよ。",5));
        mutterList.add(new Mutter(3,"奥本", "データ3、離職者によるさこだ車両の不正内部告発",7));
        mutterList.add(new Mutter(4,"奥本", "データ４、さこだ車両オイル交換サービス",2));
        mutterList.add(new Mutter(5,"神田", "データ５、このアプリの動作確認中だよ。",1));
        mutterList.add(new Mutter(6,"城山", "データ６、Applicationスコープに追加されました。",5));
        mutterList.add(new Mutter(7,"長谷川", "データ７、たまたまだよ",8));
        mutterList.add(new Mutter(8,"八反地", "データ８、たまにね",10));
        mutterList.add(new Mutter(9,"渡辺", "データ９、今はまだたまねぎ",4));
        mutterList.add(new Mutter(10,"荒木", "データ１０、明日は明日の風が吹く",2));
        mutterList.add(new Mutter(11,"安藤", "データ１１、幸せ風味",3));
        mutterList.add(new Mutter(12,"江原", "データ１２、お書上げ",0));
        // ★★★ テストデータ追加完了 ★★★

        application.setAttribute("mutterList", mutterList);
        System.out.println("Init complete: mutterList size = " + mutterList.size());
    }

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
