/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MentorController;

import DAO.AccountDTO;
import DAO.AwardDAO;
import DAO.AwardListDAO;
import DAO.AwardListDTO;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(name = "MentorAwardServlet", urlPatterns = {"/MentorAwardServlet"})
public class MentorAwardServlet extends HttpServlet {

    private final String BLOG_DETAIL = "blog";
    private final String AWARD_PAGE = "awardPage";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        ServletContext sc = request.getServletContext();
        HashMap<String, String> roadmap = (HashMap<String, String>) sc.getAttribute("ROADMAP");
        String url;
        String txtBlogID = request.getParameter("txtBlogID");
        String txtAwardID = request.getParameter("txtAwardID");
        int blogID;
        if (null != txtBlogID) {
            blogID = Integer.parseInt(txtBlogID);
        } else {
            blogID = 2;
        }
        int awardID;
        if (null == txtAwardID) {
            url = roadmap.get(AWARD_PAGE);
            AwardDAO Adao = new AwardDAO();
            request.setAttribute("ALL_AWARD", Adao.getAllAward());
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            return;
        } else {

//            try {
                awardID = Integer.parseInt(txtAwardID);
//            } catch (NumberFormatException ex) {
//
//            }
        }
        try {
            AccountDTO account = (AccountDTO) request.getSession().getAttribute("USER");
            AwardListDAO ALdao = new AwardListDAO();
            Date date = new Date(Calendar.getInstance().getTime().getTime());
            ALdao.createAwardList(new AwardListDTO(blogID, awardID, date, account.getAccountID()));
            url = roadmap.get(BLOG_DETAIL);

            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } catch (SQLException ex) {
            String msg = ex.getMessage();
            log("AwardServlet _ SQL " + msg);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(MentorAwardServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(MentorAwardServlet.class.getName()).log(Level.SEVERE, null, ex);
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
