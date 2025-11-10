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
import model.Mutter;
import model.Mutter;
import model.User;
import model.PostMutterLogic;
import model.User;
import model.User;

/**
 *
 * @author abi06
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
        //アプリケーションスコープのつぶやきリストを取得して、後ろのリストが空かどうかの処理に使う
        ServletContext application = this.getServletContext();
        List<Mutter> mutterList = (List<Mutter>)application.getAttribute("mutterList");
        //リストが無かった場合には、新規作成してアプリケーションスコープへsetAttributeする作業を行う（初期化？）
        if (mutterList == null){
            mutterList = new ArrayList<>();
            // ★★★ テストデータを3つ作成し、リストに追加 ★★★
            Mutter mutter1 = new Mutter("テストユーザー1", "テストデータその1：つぶやきアプリへようこそ！");
            Mutter mutter2 = new Mutter("テストユーザー2", "テストデータその2：このアプリの動作確認中だよ。");
            Mutter mutter3 = new Mutter("ゲスト", "テストデータその3：Applicationスコープに追加されました。");

            mutterList.add(mutter3);            
            mutterList.add(mutter2);
            mutterList.add(mutter1); 
            // ★★★ テストデータ追加完了 ★★★
            
            application.setAttribute("mutterList",mutterList);
        }
        //ログインしているかどうかを確認するために、セッションスコープからユーザー情報を取得
        //現状は、Mainを直打ちしてもMainサーブレットに入れてしまうから、この確認は必須
        HttpSession session = request.getSession();
        User loginUser = (User)session.getAttribute("loginUser");
        
        //ログインしていたらフォワード、ログインしていなかったらトップへリダイレクト
        if (loginUser != null){               
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("WEB-INF/jsp/mainpage.jsp");
        dispatcher.forward(request, response);
        }else{
            response.sendRedirect("index.jsp");
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
        //POSTからtextを取得
        request.setCharacterEncoding("UTF-8");
        String text = request.getParameter("text");
        //textがnullまたは空白でないなら処理を行う
        if (text != null && text.length() != 0){
            //ログインユーザー情報をセッションから取得し、userNameを取得
            HttpSession session = request.getSession();
            User loginUser = (User) session.getAttribute("loginUser");
            String userName = loginUser.getName();
            //つぶやきリストをアプリケーションから取得
            ServletContext application = this.getServletContext();
            List<Mutter> mutterList = (List<Mutter>)application.getAttribute("mutterList");
            //リストに追加する要素を構成
            Mutter mutter = new Mutter(userName,text);        
            //リストに追加する部分はPostMutterLogicのexecuteメソッドに処理させる
            PostMutterLogic postMutterLogic = new PostMutterLogic();
            postMutterLogic.execute(mutter,mutterList);
        }else{
            //テキストなしのエラーメッセージをリクエストスコープに保存する
            request.setAttribute("errorMsg", "つぶやきが入力されていません");
        }
        //mainpageにフォワードする
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("WEB-INF/jsp/mainpage.jsp");
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
