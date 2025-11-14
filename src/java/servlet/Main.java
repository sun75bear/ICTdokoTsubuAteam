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
        
//        //つぶやき閲覧機能のために、（データベースの代わりとして）
//        //アプリケーションスコープで「つぶやきリスト」インスタンスを扱う、アプリ起動時に必ずロードするからここは不要
//        
//        //ログイン前にこの処理は行うべき（ログインより前に、初回のつぶやきリスト生成は終えておくべき、ログイン時にこのチェックをする必要がない）
//        ServletContext application = this.getServletContext();
//        //アプリケーションスコープから「つぶやきリスト」を取り出す
//        List<Mutter> mutterList = 
//                (List<Mutter>)application.getAttribute("mutterList");
//        
//        //「つぶやきリスト」を取得できなかった場合
//        //（サーバ起動後の1回目の実行時のみ）
//        if(mutterList == null){
//            //「つぶやきリスト」を生成して、アプリケーションスコープに保存
//            mutterList = new ArrayList<>();
//            application.setAttribute("mutterList", mutterList);
//        }
        
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
        //セッションスコープを操作する為にsessionインスタンスを作成
        HttpSession session = request.getSession();        
        
        //投稿内容のチェック
         //何かしら文字が入力されている場合（空欄でない場合）処理が進む
        //isBlank()メソッドで聞くことにした、text.length()!=0は、空白文字がすり抜けてしまう
        if(text != null && !text.isBlank()){
            //セッションからloginUserインスタンスを取得
            User loginUser = (User)session.getAttribute("loginUser");           
            // --- ここでUUIDを生成 ---
            // UUID.randomUUID() はUUID v4 (ランダムベース) を生成します。
            UUID newUuid = UUID.randomUUID();
            // UUIDオブジェクトをIDとして扱いやすいString型に変換
            String mutterId = newUuid.toString();
            //つぶやきをmutterに格納
            Mutter mutter = new Mutter(loginUser.getName(), text, mutterId);
            //セッションにmutterを、sessionMutterListの中に入れる形で保存
            List<Mutter> sessionMutterList = (List<Mutter>) session.getAttribute("sessionMutterList");
                if (sessionMutterList == null) {
                    sessionMutterList = new ArrayList<>();
                }             
            sessionMutterList.add(mutter);
            session.setAttribute("sessionMutterList", sessionMutterList);
            //セッションにsessionMutterListが入ったら、AddListにフォワード
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("/AddList");
            dispatcher.forward(request, response);
        }else{
            //投稿内容が空欄の場合、リクエストスコープにエラーメッセージを保存して送信
            ///MainでdoGetサーブレットを用いようとすると、おそらくdoPostが働い手無限ループになる
            //絶対パスでmain.jspをforward先に指定する
            request.setAttribute("errorMsg","つぶやきが入力されていません");
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
            dispatcher.forward(request, response);            
        }        
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
