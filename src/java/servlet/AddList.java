package servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.ServletContext;
import java.util.List;
import java.util.ArrayList; 
// Mutterクラスはmodelパッケージにあると仮定してインポートします。
import model.Mutter; 
import model.PostMutterLogic;

/**
 * ユーザーのセッションリストをアプリケーションスコープのリストに反映させるServlet
 */
@WebServlet(name = "AddList", urlPatterns = {"/AddList"})
public class AddList extends HttpServlet {

    /**
     * doGet/doPostの共通処理として、セッションのつぶやきをアプリケーションスコープに移動し、
     * メイン画面へリダイレクトします。
     * 以前のdoGetのロジックをここに移動し、doGet/doPostから呼び出します。
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // ユーザーのセッションを取得
        HttpSession session = request.getSession();
        
        // 1. セッションデータからsessionMutterListを取得
        // @SuppressWarnings("unchecked") は、型安全ではないキャストを許可するアノテーションです。
        @SuppressWarnings("unchecked")
        List<Mutter> sessionMutterList = (List<Mutter>) session.getAttribute("sessionMutterList");
        
        // セッションリストがnullまたは空の場合は、単にメイン画面へリダイレクトして終了
        if (sessionMutterList == null || sessionMutterList.isEmpty()) {
            response.sendRedirect("/Main"); 
            return;
        }
        
        // データ保存用に、アプリケーションスコープを取得
        ServletContext application = this.getServletContext();
        
        // 2. Applicationスコープの該当リストを取得
        @SuppressWarnings("unchecked")
        List<Mutter> applicationMutterList = (List<Mutter>) application.getAttribute("mutterList");
        
        // 3. sessionMutterListの全ての要素をApplicationスコープのリストの先頭側に取り込む
        // 注: リストを逆順に処理することで、addAll()と同じ順序（新しいものが先頭）で要素が追加されます。
        if (applicationMutterList != null) {
            PostMutterLogic postMutterLogic = new PostMutterLogic();
            // リストの末尾から先頭に向かってループを回す
            // こうすることで、applicationMutterListに追加される際、
            // sessionMutterListでの順番が保たれます。
            for (int i = sessionMutterList.size() - 1; i >= 0; i--) {
                Mutter mutter = sessionMutterList.get(i);
                // PostMutterLogicで常にリストの先頭に追加
                postMutterLogic.execute(mutter,applicationMutterList);
            }

            // 4. アプリケーションスコープへの移動が完了したら、sessionデータを削除
            session.removeAttribute("sessionMutterList");

        } else {
             // 初期化漏れを開発者に通知
             System.err.println("Error: applicationMutterList (mutterList) is null. Please ensure it is initialized in Main Servlet or a ServletContextListener.");
        }
        
        // 5. mainにリダイレクト
        // サーブレット上から呼ぶと、/Mainとやるだけではうまくいかない、予めContextPathを取得しておく
        final String contextPath = request.getContextPath();

        // 修正されたリダイレクト: ContextPathを付加
        response.sendRedirect(contextPath + "/Main");
        //response.sendRedirect("/Main");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // ロジックをprocessRequestに移動したので、ここから呼び出す
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // ロジックをprocessRequestに移動したので、ここから呼び出す
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     */
    @Override
    public String getServletInfo() {
        return "Transfers session data to application scope list";
    }// </editor-fold>

}