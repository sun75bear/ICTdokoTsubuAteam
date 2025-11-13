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
/**
 *
 * @author abi02
 */
@WebServlet(name = "Akui", urlPatterns = {"/Akui"})
public class Akui extends HttpServlet {
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
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<title>超特急！怪しい振込画面</title>");
    out.println("<style>");
    out.println("body { background-color: black; color: lime; font-family: 'Comic Sans MS'; }");
    out.println(".warning { color: red; font-weight: bold; font-size: 24px; }");
    out.println("</style>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1 class='warning'>? 超特急送金システム v0.0.1 ?</h1>");
    out.println("<marquee behavior='scroll' direction='left'>安全！安心！即金！</marquee>");
    out.println("<p>※あなただけのご祝儀です</p>");
    out.println("<form method='post' action='Akui'>");
    out.println("口座番号: <input type='text' name='account' placeholder='例: 123-456-789'><br><br>");
    out.println("振込金額: <input type='text' name='amount' placeholder='例: 1000000'><br><br>");
    out.println("<input type='submit' value='送金' style='background:red; color:white;'>");
    out.println("</form>");

    String account = request.getParameter("account");
    String amount = request.getParameter("amount");
    if (account != null && amount != null) {
        out.println("<hr>");
        out.println("<h2>入力内容確認</h2>");
        out.println("口座番号: " + account + "<br>");
        out.println("振込金額: " + amount + " 円<br>");
        out.println("<p class='warning'>??これはネタです。送金されることはありません??</p>");
        out.println("<p>…でも入力したあなたはすでに騙されています。</p>");
    }
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