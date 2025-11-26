/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import jakarta.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Mutter;

/**
 *
 * @author abi06
 */
@WebServlet(name = "LikeServlet", urlPatterns = {"/LikeServlet"})
public class LikeServlet extends HttpServlet {

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
            out.println("<title>Servlet LikeServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LikeServlet at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // 1. リクエストパラメータ（Mutter ID）の取得
        request.setCharacterEncoding("UTF-8");
        // JSから渡されるIDパラメータ名が 'id' であると仮定
        String mutterId = request.getParameter("id"); 
        
        if (mutterId == null || mutterId.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Mutter IDが指定されていません。");
            return;
        }

        ServletContext application = getServletContext();
        
        // アプリケーションスコープ上のリストに対する排他制御を開始
        synchronized (application) {
            
            // 2. アプリケーションスコープから投稿リストを取得
            @SuppressWarnings("unchecked")
            List<Mutter> mutterList = (List<Mutter>) application.getAttribute("mutterList");
            
            if (mutterList == null) {
                System.err.println("mutterListがアプリケーションスコープにありません。");
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "データリストが利用できません。");
                return;
            }

            // 3. 編集対象の投稿を検索し、いいねをインクリメント
            Mutter target = null;
            for (Mutter mutter : mutterList) {
                if (mutter.getMutterId().equals(mutterId)) {
                    target = mutter;
                    break;
                }
            }

            int newLikes = -1;
            
            if (target != null) {
                // いいね数を1増やす
                int currentLikes = target.getGood();
                newLikes = currentLikes + 1;
                target.setGood(newLikes);
                System.out.println("Mutter ID: " + mutterId + " のいいねを " + newLikes + " に更新しました。");
            } else {
                System.out.println("指定されたID (" + mutterId + ") の投稿が見つかりませんでした。");
            }

            // 4. JSONで新しいいいね数をクライアントに返却
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            
            if (target != null) {
                // 成功時: 新しいいいね数をJSONで返す
                out.print("{\"likes\": " + newLikes + "}");
            } else {
                // 失敗時: エラーメッセージを返すか、現在のいいね数をそのまま返す（ここではエラーレスポンス）
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.print("{\"error\": \"Mutter not found\"}");
            }
            out.flush();
        } // synchronized ブロック終了
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
