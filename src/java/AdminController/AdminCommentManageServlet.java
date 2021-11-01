/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminController;

import DAO.AccountDAO;
import DAO.AccountDTO;
import DAO.BlogCommentDAO;
import DAO.BlogCommentDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author JohnnyMC
 */
@WebServlet(name = "AdminCommentManageServlet", urlPatterns = {"/AdminCommentManageServlet"})
public class AdminCommentManageServlet extends HttpServlet {

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
        ServletContext sc = request.getServletContext();
        HashMap<String, String> roadmap = (HashMap<String, String>) sc.getAttribute("ROADMAP");

        AccountDAO accDao = new AccountDAO();
        HttpSession session = request.getSession();
        AccountDTO account = (AccountDTO) session.getAttribute("USER");
        if (null == account || !account.getRole().equals("Admin")) {
            response.sendRedirect(sc.getContextPath());
            return;
        }

        String url = "401.html";

        String commentId = request.getParameter("commentID");
        String action = request.getParameter("action");
        BlogCommentDAO dao = new BlogCommentDAO();

        if (null != commentId) {
            try {
                int commentID = Integer.parseInt(commentId);
                BlogCommentDTO dto = dao.getBlogCommentFromCommentID(commentID);
                if (dto != null) {
                    if (null != action) {
                        if ("Delete".equals(action)) {
                            dao.deleteBlogComment(commentID);
                            url = roadmap.get("comment");
                            url += "?txtBlogID=" + dto.getBlogID();
                        }
                    }
                }
            } catch (Exception ex) {
                
            } finally {
               
                request.getRequestDispatcher(url).forward(request, response);
            }
        } else {
            url = roadmap.get(url);
            request.getRequestDispatcher(url).forward(request, response);
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
