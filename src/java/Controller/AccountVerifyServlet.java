/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.AccountDAO;
import DAO.AccountDTO;
import DAO.VerificationDAO;
import DAO.VerificationDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author JohnnyMC
 */
@WebServlet(name = "AccountVerifyServlet", urlPatterns = {"/AccountVerifyServlet"})
public class AccountVerifyServlet extends HttpServlet {

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
        
        String verifyCode = null == request.getParameter("code") ? null : request.getParameter("code");
        String url = "home";
        
        VerificationDAO veriDao = new VerificationDAO();
        AccountDAO accDao = new AccountDAO();
        
        try {
            if (null != verifyCode) {
                
                VerificationDTO dto = veriDao.GetAccountIdUsingCode(verifyCode);
                if (dto != null) {
                    veriDao.RemoveVerification(dto.getAccountID());
                    AccountDTO acc = accDao.getAccountFromAcoountID(dto.getAccountID());
                    acc.setStatus("AVAILABLE");
                    accDao.updateAccount(dto.getAccountID(), acc);
                } else {
                    request.setAttribute("MESSAGE", "Your verification link isn't correct, please use another one!");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            roadmap.get(url);
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
