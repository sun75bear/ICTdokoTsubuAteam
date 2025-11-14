package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import model.Mutter;

import model.Post;

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
    //"nameで全検索してる"
    //今回はここをapplicationスコープからの全選択を行う
    // 本文全文検索
    //アプリケーションスコープのインスタンスを順番に取得し、qとの一致があれば、一致したインスタンスをList<Mutter> textHit = new AllayList<>で作ったリストに格納
    ServletContext application = getServletContext();
    List<Mutter> mutterList = (List<Mutter>) application.getAttribute("mutterList");
    List<Mutter> nameHit = new ArrayList<>();
    for(Mutter mutter : mutterList){
        //名前に一致があればnameHitリストに格納
        //完全一致検索、リスト名はnameHitに変更して動かしてみる
        if (mutter.getUsername().equals(name)){
            nameHit.add(mutter);
        }
    }
//     ★ 追加：検索キーワード q がrequestに入って来てたら、そのキーワードを含む投稿だけに絞り込む

    String q = Optional.ofNullable(request.getParameter("q"))
                       .orElse("")
                       .trim();

    if (!q.isEmpty()) {
        List<Mutter> filtered = new ArrayList<>();
        //名前で検索したものをテキストでフィルターする
        for (Mutter m : nameHit) {
//            String content = Optional.ofNullable(p.getContent()).orElse("");
            if (m.getText().contains(q)) {   // 「サコダ車両」を含む投稿だけ通す
                filtered.add(m);
            }
        }
        nameHit = filtered;  // posts を絞り込んだリストに差し変え＝キーワード検索済のリストを準備
    }
    //検索結果であるnameHitをリクエストスコープに格納して、person.jspにフォワードする
    request.setAttribute("nameHit", nameHit);

    RequestDispatcher rd =
            request.getRequestDispatcher("/WEB-INF/jsp/person.jsp");
    rd.forward(request, response);
}



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //postで/personはないのでは？不要なら消す
        request.setCharacterEncoding("UTF-8");
        //nameとactionを、requestスコープから取得して安全に格納する
        String name = Optional.ofNullable(request.getParameter("name"))
                              .orElse("")
                              .trim();
        String action = Optional.ofNullable(request.getParameter("action"))
                                .orElse("");
        //名前が空なら検索の先頭にフォワード
        if (name.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "keyword");
            return;
        }
        //actionがaddならリクエストからcontentを取得して格納し、インメモリデータベースに名前とcontentを一組でaddする
//        if ("add".equals(action)) {
//            String content = Optional.ofNullable(request.getParameter("content"))
//                                     .orElse("")
//                                     .trim();
//            if (content.isEmpty()) {
//                HttpSession session = request.getSession();
//                session.setAttribute("flash_error", "本文を入力してください。");
//            }
//            } else {
//                //アプリケーションにnameとcontentのペアで格納する、ツイート処理は不要と判断してコメントアウト
//                InMemoryDB.addPost(name, content);
//            }
        //actionがreactなら、リクエストからtype（like or bad ）を取得して格納し、long型のidをリクエストパラメータから取得し、インメモリデータベースに名前・id・like or badで格納する
        //いいねをid付きでDBに入れる処理で、今後参考にするべき
        //コメントアウトで封じておく
//        } else if ("react".equals(action)) {
//            String type = request.getParameter("type"); // like / bad
//            long id = Long.parseLong(request.getParameter("id"));
//            InMemoryDB.react(name, id, "like".equals(type));
//        }
        //person?name=に、nameを入れてpersonページにリダイレクトする
        String encoded = URLEncoder.encode(name, StandardCharsets.UTF_8);
        response.sendRedirect(request.getContextPath() + "/person?name=" + encoded);
    }
}
