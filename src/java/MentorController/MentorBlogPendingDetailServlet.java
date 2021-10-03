/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MentorController;

import DAO.AccountDAO;
import DAO.AccountDTO;
import DAO.BlogDAO;
import DAO.BlogDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
@WebServlet(name = "MentorBlogPendingDetailServlet", urlPatterns = {"/mentor/MentorBlogPendingDetailServlet"})
public class MentorBlogPendingDetailServlet extends HttpServlet {

    public final String EDIT = "mentor/blogPendingDetailPage";
    public final String SUCCESS = "mentor/blogPendingList";

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
        String url = EDIT;
        response.setContentType("text/html;charset=UTF-8");
        ServletContext sc = request.getServletContext();
        HashMap<String, String> roadmap = (HashMap<String, String>) sc.getAttribute("ROADMAP");

        HttpSession session = request.getSession();
//        AccountDTO account = (AccountDTO) session.getAttribute("USER");

        AccountDAO accDao = new AccountDAO();
        int accountID = 5;
        AccountDTO account = null;

        try {
            account = accDao.getAccountFromAcoountID(accountID);
        } catch (SQLException ex) {
        }

        BlogDAO blogDao = new BlogDAO();
        BlogDTO blog = null;

        String blogIDString = request.getParameter("blogid");
        String action = request.getParameter("submitAction");
        int blogID = 0;

        if (null != blogIDString) {
            try {
                blogID = Integer.parseInt(blogIDString);
            } catch (NumberFormatException ex) {
                blogID = 0;
            }
        }
        if (blogID != 0) {
            try {
                blog = blogDao.getBlogFromBlogID(blogID);
                if (null != blog) {
                    if (blog.getCategoryID().equals(account.getCategoryID())) {
                        if (null == action) {

                        } else if ("Update".equals(account)) {
                            String title = null == request.getParameter("title") ? blog.getTitle() : request.getParameter("title");
                            String content = null == request.getParameter("title") ? blog.getTitle() : request.getParameter("title");
                            
                            // Verify Title & Content
                            // Update
                            
                        }
                    }
                } else {
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
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
