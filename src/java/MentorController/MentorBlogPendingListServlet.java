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
import java.util.ArrayList;
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
@WebServlet(name = "MentorBlogPendingListServlet", urlPatterns = {"/MentorBlogPendingListServlet"})
public class MentorBlogPendingListServlet extends HttpServlet {

    public final String SUCCESS = "blogPendingListPage";

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

        try {
            response.setContentType("text/html;charset=UTF-8");
            ServletContext sc = request.getServletContext();
            HashMap<String, String> roadmap = (HashMap<String, String>) sc.getAttribute("ROADMAP");

            String url = SUCCESS;
            url = roadmap.get(url);

            String pageString = request.getParameter("page");
            BlogDAO blogDao = new BlogDAO();

            AccountDAO accDao = new AccountDAO();
            HttpSession session = request.getSession();
            AccountDTO account = (AccountDTO) session.getAttribute("USER");
            if (null == account || !account.getRole().equals("Mentor")) {
                response.sendError(401);
                return;
            }

            ArrayList<BlogDTO> blogList = blogDao.getAllBlogWithStatusFromCategoryId("PENDING", account.getCategoryID());
            ArrayList<BlogDTO> returnList = new ArrayList<>();

            int maxPageItem = 5;
            int page;

//            if (null != maxPageItemString) {
//                maxPageItem = Integer.parseInt(maxPageItemString);
//            } else {
//                maxPageItem = 5;
//            }
            if (null != pageString) {
                page = Integer.parseInt(pageString);
            } else {
                page = 1;
            }

            for (int i = (page - 1) * maxPageItem; i < blogList.size() && returnList.size() < maxPageItem; i++) {
                returnList.add(blogList.get(i));
            }

            request.setAttribute("PENDING_BLOG_LIST", returnList);
            request.setAttribute("PAGE_COUNT", (int) Math.ceil((double) blogList.size() / (double) maxPageItem));
            request.getRequestDispatcher(url).forward(request, response);
        } catch (SQLException ex) {
            ex.printStackTrace();
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
