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
@WebServlet(name = "ForgotPasswordServlet", urlPatterns = {"/ForgotPasswordServlet"})
public class ForgotPasswordServlet extends HttpServlet {

    private final String FORGOT_PAGE = "forgotPasswordPage";
    private final String CHANGE_PASSWORD_PAGE = "changePasswordPage";

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
        String password = null == request.getParameter("password") ? null : request.getParameter("password");
        String confirmPassword = null == request.getParameter("confirmPassword") ? null : request.getParameter("confirmPassword");
        String url = "forgotPasswordPage";

        VerificationDAO veriDao = new VerificationDAO();
        AccountDAO accDao = new AccountDAO();

        try {
            if (null != verifyCode) { // Verify Code In Url
                url = CHANGE_PASSWORD_PAGE;
                if (null != action) {
                    if ("Change Password".equals(action)) {
                        VerificationDTO veriDto = veriDao.GetVerificationDTOUsingCode(verifyCode, "FORGOT_PASSWORD");
                        if (null != veriDto) {
                            AccountDTO accDto = accDao.getAccountFromEmail(veriDto.getEmail());
                            if (null != password && null != confirmPassword && password.equals(confirmPassword)) {
                                if (password.trim().matches("^([\\d\\w]{8,20})$")) { // Password match Regex
                                    request.setAttribute("TYPE", "SUCCESS");
                                    accDto.setPassword(password);
                                    accDao.updateAccount(accDto.getAccountID(), accDto);
                                    veriDao.RemoveVerification(accDto.getAccountID(), "FORGOT_PASSWORD");
                                } else { // Password doesn't match regex
                                    request.setAttribute("TYPE", "PASSWORD_INVALID");
                                }
                            } else {
                                request.setAttribute("TYPE", "PASSWORD_NOT_MATCH");
                            }
                        }

                    }
                } else {
                    VerificationDTO veriDto = veriDao.GetVerificationDTOUsingCode(verifyCode, "FORGOT_PASSWORD");
                    if (null == veriDto) {
                        url = CHANGE_PASSWORD_PAGE;
                        request.setAttribute("TYPE", "CODE_INVALID");
                    }
                }
            } else {
                url = FORGOT_PAGE;
                // Access the forgot password page
                if ("Request Password Change".equals(action)) {
                    if (null != email) {
                        AccountDTO accDto = accDao.getAccountFromEmail(email);
                        if (null != accDto) {
                            VerificationDTO veriDto = veriDao.GetVerificationDTOUsingEmail(email, "FORGOT_PASSWORD");
                            if (null != veriDto) { // Account already existed in verification
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
                                    MailUtils.sendForgotPassword(accDto.getAccountID());
                                }
                            } else { // Account doesn't existed in verification
                                request.setAttribute("TYPE", "SUCCESS");
                                veriDao.AddVerification(new VerificationDTO(accDto.getAccountID(), email, java.util.UUID.randomUUID().toString(), new Timestamp(System.currentTimeMillis()), "FORGOT_PASSWORD"));
                                MailUtils.sendForgotPassword(accDto.getAccountID());
                            }
                        } else {
                            request.setAttribute("TYPE", "EMAIL_NOT_EXISTED");
                        }
                    } else {
                        request.setAttribute("TYPE", "EMAIL_INPUT_MISSING");
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
