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
import Utils.MailUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.sql.Timestamp;
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
        String action = null == request.getParameter("action") ? null : request.getParameter("action");
        String email = null == request.getParameter("email") ? null : request.getParameter("email");
        String url = "verifyPage";

        VerificationDAO veriDao = new VerificationDAO();
        AccountDAO accDao = new AccountDAO();

        try {
            if (null != action) {
                if ("Send Verify Link".equals(action)) {
                    if (email != null) { // Email Inputted
                        AccountDTO accDto = accDao.getAccountFromEmail(email);
                        if (accDto != null) {   // ACcount exited in database
                            if ("PENDING".equals(accDto.getStatus())) { // Account is in pending mode
                                VerificationDTO veriDto = veriDao.GetVerificationDTOUsingEmail(email);
                                if (null != veriDto) {  // Account existed in verfication list

                                    Timestamp now = new Timestamp(System.currentTimeMillis());
                                    long cooldownLeft = (600 - (now.getTime() - veriDto.getTime().getTime()) / 1000);
                                    System.out.println("Cooldown Left : " + cooldownLeft);
                                    if (cooldownLeft > 0) {
                                        request.setAttribute("TYPE", "EMAIL_COOLDOWN");
//                                    String times = DateTime(cooldownLeft);
                                        int hours = (int) (cooldownLeft / 3600);
                                        int minutes = (int) ((cooldownLeft % 3600) / 60);
                                        int seconds = (int) (cooldownLeft % 60);

                                        String timeString = String.format("%02dh:%02dm:%02ds", hours, minutes, seconds);
                                        request.setAttribute("TIMELEFT", timeString);
                                    } else {
                                        request.setAttribute("TYPE", "SUCCESS");
                                        // Email not on cooldown
                                        veriDto.setTime(now);
                                        veriDao.UpdateVerification(veriDto);
                                        MailUtils.sendVerification(accDto.getAccountID());
                                    }
                                } else {// Account not exited in verfication list
                                    request.setAttribute("TYPE", "SUCCESS");
                                    veriDao.AddVerification(new VerificationDTO(accDto.getAccountID(), email, java.util.UUID.randomUUID().toString(), new Timestamp(System.currentTimeMillis())));
                                    MailUtils.sendVerification(accDto.getAccountID());
                                }
                            } else {
                                request.setAttribute("TYPE", "EMAIL_ALREADY_VERIFIED");
                                // Account is verified
                            }
                        } else {
                            request.setAttribute("TYPE", "EMAIL_NOT_EXISTED");
                            // Email doens't exist in database
                        }
                    } else {
                        // Email input is null!
                        request.setAttribute("TYPE", "EMAIL_INPUT_MISSING");
//                            request.setAttribute("MESSASGE", now);
                    }
                } else {
                    // Another action
                }
            } else {
                if (null != verifyCode) {

                    VerificationDTO dto = veriDao.GetAccountIdUsingCode(verifyCode);
                    if (dto != null) {
                        veriDao.RemoveVerification(dto.getAccountID());
                        AccountDTO acc = accDao.getAccountFromAcoountID(dto.getAccountID());
                        acc.setStatus("AVAILABLE");
                        accDao.updateAccount(dto.getAccountID(), acc);
                        request.setAttribute("TYPE", "SUCCESS");
                    } else {
                        request.setAttribute("TYPE", "INVALID");
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
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
