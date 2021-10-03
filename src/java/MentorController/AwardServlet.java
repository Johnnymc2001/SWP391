/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MentorController;

import DAO.AwardDAO;
import DAO.AwardDTO;
import DAO.AwardListDAO;
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

/**
 *
 * @author Admin
 */
@WebServlet(name = "AwardServlet", urlPatterns = {"/AwardServlet"})
public class AwardServlet extends HttpServlet {

    private final String BLOGDETAIL_PAGE = "blogPage";

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
        if (null != txtBlogID) {
            awardID = Integer.parseInt(txtAwardID);
        } else {
            awardID = 1;
        }
        try {
//            AwardListDAO ALdao = new AwardListDAO();
//            AwardListDTO ALdto = 
//            AwardDAO Adao = new AwardDAO();
//            AwardDTO Adto = Adao.getAwardFromAwardID(awardID);
//            if (null != ALdao.getAwardListFromBlogId(blogID)) {
//                AwardDTO ALdto = ALdao.createAwardList(Adto);
//            }
        } finally {

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
