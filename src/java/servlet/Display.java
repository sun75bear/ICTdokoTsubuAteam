/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.VisitCounter;

/**
 *
 * @author abi02
 */
@WebServlet(name = "Display", urlPatterns = {"/Display"})
public class Display extends HttpServlet {

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
      out.println("<title>Servlet Display</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>Servlet Display at " + request.getContextPath() + "</h1>");
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

    
        // アクセス回数をカウント
        VisitCounter.getInstance().increment();
        // 総アクセス数をリクエスト属性にセット
        request.setAttribute("totalVisits", VisitCounter.getInstance().getTotal());

    
    //************************************************************************************************
        String viewType = request.getParameter("viewType");

        if (viewType == null) {
            // 初回アクセス → 表示タイプ選択フォームJSPにフォワード
            request.getRequestDispatcher("WEB-INF/jsp/displayForm.jsp").forward(request, response);
        } else {
            // ボタン押下後 → 選択されたviewTypeに応じて各表示JSPにフォワード
            switch (viewType) {
                case "recommend":
//                    request.getRequestDispatcher("WEB-INF/jsp/osusume.jsp").forward(request, response);
                    response.sendRedirect(request.getContextPath() + "/Osusume");
                    break;
                case "ranking":
//                    request.getRequestDispatcher("WEB-INF/jsp/ranking.jsp").forward(request, response);
                    response.sendRedirect(request.getContextPath() + "/ranking");
                    break;
                case "keyword":
                    request.getRequestDispatcher("WEB-INF/jsp/keyword.jsp").forward(request, response);
//                    response.sendRedirect(request.getContextPath() + "/keyword");
                    break;
                default:
                    response.getWriter().println("不正な表示タイプです。");
            }
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