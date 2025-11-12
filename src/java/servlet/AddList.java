/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.ServletContext;
import java.util.List;
import model.Mutter;

/**
 *
 * @author abi06
 */
@WebServlet(name = "AddList", urlPatterns = {"/AddList"})
public class AddList extends HttpServlet {

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
            out.println("<title>Servlet AddList</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddList at " + request.getContextPath() + "</h1>");
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
        //Addlistで送られてきたセッションの内容をアプリケーションリストに反映させて、sessionのattributeを削除し、Mainに変えす
        // ユーザーのセッションを取得
        HttpSession session = request.getSession();
        
        // 1. セッションデータからsessionMutterListを取得
        // (ログインユーザー情報も確認しておくと安全です)
        List<Mutter> sessionMutterList = (List<Mutter>) session.getAttribute("sessionMutterList");
        
        // セッションリストがnullまたは空の場合は何もしない
        if (sessionMutterList == null || sessionMutterList.isEmpty()) {
            response.sendRedirect("Main"); // Mainサーブレットへリダイレクト
            return;
        }
        // アプリケーションスコープを取得
        ServletContext application = this.getServletContext();
        
        // 2. Applicationスコープの該当リストを取得 (Mainサーブレットで初期化されていると仮定)
        // 例: 全ユーザーで共有するリスト
        List<Mutter> applicationMutterList = (List<Mutter>) application.getAttribute("mutterList");
        
        
        // 3. sessionMutterListを空になるまで全てApplicationスコープのリストに取り込む
        applicationMutterList.addAll(sessionMutterList);

        // 4. 空になったらsessionデータから"sessionMutterList"項目を削除
        session.removeAttribute("sessionMutterList");
        
        // 5. mainにリダイレクト
        response.sendRedirect("Main");
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
        processRequest(request, response);
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
