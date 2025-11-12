/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Mutter;
import model.PostMutterLogic;
import model.User;
import java.util.UUID;

/**
 *
 * @author teacher
 */
@WebServlet(name = "Main", urlPatterns = {"/Main"})
public class Main extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Main</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Main at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        
        //つぶやき閲覧機能のために、（データベースの代わりとして）
        //アプリケーションスコープで「つぶやきリスト」インスタンスを扱う
        
        //ログイン前にこの処理は行うべき（ログインより前に、初回のつぶやきリスト生成は終えておくべき、ログイン時にこのチェックをする必要がない）
        ServletContext application = this.getServletContext();
        //アプリケーションスコープから「つぶやきリスト」を取り出す
        List<Mutter> mutterList = 
                (List<Mutter>)application.getAttribute("mutterList");
        
        //「つぶやきリスト」を取得できなかった場合
        //（サーバ起動後の1回目の実行時のみ）
        if(mutterList == null){
            //「つぶやきリスト」を生成して、アプリケーションスコープに保存
            mutterList = new ArrayList<>();
                        // ★★★ テストデータを１２こ作成し、リストに追加 ★★★
                        // mutterIdに対応
            Mutter mutter1 = new Mutter("f8d9b1a0-1c3b-4e5a-8f7d-2a6c9e4b3d1f","奥本", "データ１、つぶやきアプリへようこそ！",0);
            Mutter mutter2 = new Mutter("c7e6a9b1-2d4e-5f6b-9c8a-3b7d5e9f1a2c","奥本", "データ2、このアプリの動作確認中だよ。",5);
            Mutter mutter3 = new Mutter("a1b2c3d4-e5f6-7a8b-9c0d-1e2f3a4b5c6d","奥本", "データ3、離職者によるさこだ車両の不正内部告発",7);
            Mutter mutter4 = new Mutter("8e7d6c5b-4a3f-2e1d-0c9b-8a7f6e5d4c3b","奥本", "データ４、さこだ車両オイル交換サービス",2);
            Mutter mutter5 = new Mutter("3c4d5e6f-7a8b-9c0d-1e2f-3a4b5c6d7e8f","神田", "データ５、このアプリの動作確認中だよ。",1);
            Mutter mutter6 = new Mutter("f9e8d7c6-b5a4-9d8c-7b6a-5f4e3d2c1b0a","城山", "データ６、Applicationスコープに追加されました。",5);
            Mutter mutter7 = new Mutter("1a2b3c4d-5e6f-7a8b-9c0d-1e2f3a4b5c6d","長谷川", "データ７、たまたまだよ",8);
            Mutter mutter8 = new Mutter("6f5e4d3c-2b1a-0f9e-8d7c-6b5a4f3e2d1c","八反地", "データ８、たまにね",10);
            Mutter mutter9 = new Mutter("b1c2d3e4-f5a6-8b9c-d0e1-f2a3b4c5d6e7","渡辺", "データ９、今はまだたまねぎ",4);
            Mutter mutter10 = new Mutter("a8b7c6d5-e4f3-2a1b-0c9d-8e7f6a5b4c3d","荒木", "データ１０、明日は明日の風が吹く",2);
            Mutter mutter11 = new Mutter("d4e5f6a7-b8c9-0d1e-2f3a-4b5c6d7e8f90","安藤", "データ１１、幸せ風味",3);
            Mutter mutter12 = new Mutter("2c3b4a5d-6e7f-8c9a-b0d1-e2f3a4b5c6d7","江原", "データ１２、お書上げ",0);

            mutterList.add(mutter12);            
            mutterList.add(mutter11);
            mutterList.add(mutter10);
            mutterList.add(mutter9);            
            mutterList.add(mutter8);
            mutterList.add(mutter7);            
            mutterList.add(mutter6);            
            mutterList.add(mutter5);
            mutterList.add(mutter4);
            mutterList.add(mutter3);            
            mutterList.add(mutter2);
            mutterList.add(mutter1); 
            // ★★★ テストデータ追加完了 ★★★
            application.setAttribute("mutterList", mutterList);
        }
        
        //このアプリではセッションスコープから”loginUser”の名前で
        //インスタンスを取り出すことができればログインしていると定義している
        HttpSession session = request.getSession();
        User loginUser = (User)session.getAttribute("loginUser");
        
        if(loginUser == null){
            //ログインしていない、場合、TOP画面へリダイレクト
            response.sendRedirect("index.jsp");
        }else{
            //ログインしている、場合、メイン画面へフォワード
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
            dispatcher.forward(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        
        
        //フォームに入力された投稿内容を
        //リクエストパラメータから取り出す
        request.setCharacterEncoding("UTF-8");
        String text = request.getParameter("text");
        
        //投稿内容のチェック
        if(text != null && text.length()!= 0){
            //何かしら文字が入力されている場合（空欄でない場合）
            //「つぶやきリスト」をアプリケーションスコープを取り出す
            ServletContext application = this.getServletContext();
            List<Mutter> mutterList = 
                (List<Mutter>)application.getAttribute("mutterList");
            
            //「ログインしているユーザー情報」をセッションスコープから取り出す
            HttpSession session = request.getSession();
            User loginUser = (User)session.getAttribute("loginUser");
            
            
            // --- ここでUUIDを生成 ---
        // UUID.randomUUID() はUUID v4 (ランダムベース) を生成します。
        UUID newUuid = UUID.randomUUID();
        // UUIDオブジェクトをString型に変換
        String mutterId = newUuid.toString();
        // Tweetインスタンスを作成し、IDを格納
        //Tweet newTweet = new Tweet(input_text, tweetIdStr);
        
            List<Mutter> sessionMutterList = (List<Mutter>) session.getAttribute("sessionMutterList");
                if (sessionMutterList == null) {
                    sessionMutterList = new ArrayList<>();
                }
            //「ユーザー名」と「投稿内容」で「つぶやき」インスタンスを生成し、
            //セッションスコープ保存用リストsessionMutterListに格納する                
            Mutter mutter = new Mutter(loginUser.getName(), text, mutterId);
            sessionMutterList.add(mutter);
            session.setAttribute("sessionMutterList", sessionMutterList);
            //////過去バージョン///////////////////////////////////////////
            //今はokumotoセッションに保存してフォワードした後、Addlistがつぶやきを追加する
//            //「つぶやきリスト」に新しい「つぶやき」を追加する
//            PostMutterLogic postMutterLogic = new PostMutterLogic();
//            postMutterLogic.execute(mutter, mutterList);
//            
//            //アプリケーションスコープへ「つぶやきリスト」を保存
//            application.setAttribute("mutterList", mutterList);
/////////////////////////////////////////////////////////////////
        }else{
            //投稿内容が空欄の場合、リクエストスコープにエラーメッセージを保存して送信
            //セッションを破棄しないといけない
            HttpSession session = request.getSession();
            session.removeAttribute("sessionMutterList");
            request.setAttribute("errorMsg","つぶやきが入力されていません");
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("/Main");
            dispatcher.forward(request, response);            
        }
        
        //mainでなくAddListにフォワード
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/AddList");
        dispatcher.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
