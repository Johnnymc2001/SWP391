/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

//import DAO.AccountDAO;
import DAO.AccountDAO;
import DAO.AccountDTO;
import DAO.AccountError;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@WebServlet(name = "CreateAccountServlet", urlPatterns = {"/CreateAccountServlet"})
public class CreateAccountServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     * @throws java.text.ParseException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        ServletContext sc = request.getServletContext();
        HashMap<String, String> roadmap = (HashMap<String, String>) sc.getAttribute("ROADMAP");
        String username = request.getParameter("username");
        String fullname = request.getParameter("fullname");
        String address = request.getParameter("address");
        String password = request.getParameter("password");

        String confirm_password = request.getParameter("confirm-password");

    

        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String birthdate = request.getParameter("birthdate");
        boolean foundError = false;
        AccountError error = new AccountError();
        String url = "loginPage";
        System.out.println("birthdate: " + birthdate);
          AccountDAO dao = new AccountDAO();
          
        try {
            AccountDTO user = dao.getAccountFromUsername("username");
            // KIEM TRA CAC LOI KHI TAO TAI KHOAN MOI
//             int i = dao.getAccountIDByUsername(username);
             System.out.println("AccountID: "+user);
            
            if (user!=null) {
                 foundError = true;
                 error.setUserNameExisted("Account Existed");
            }
                if (username == null || username.trim().length() < 6 || username.trim().length() > 20) {
                    foundError = true;
                    error.setUserNameLengthError("User name must be from 6-20 character");
                }
                if (password == null || password.trim().length() < 6 || password.trim().length() > 30) {
                    foundError = true;
                    error.setPasswordLengthError("Password must be from 6-30 character");
                } else if (confirm_password == null || !confirm_password.trim().equals(password.trim())) {
                    foundError = true;
                    error.setConfirmNotMatched("Confirm Password is Not Matched");
                }
                if (fullname == null || fullname.trim().length() < 6 || fullname.trim().length() > 20) {
                    foundError = true;
                    error.setFullNameLengthError("Full Name must be from 6-20 character");
                }
                if (email == null || error.checkValidEmail(email) == false) {
                    error.setEmailErrorFormat("Email is not valid");
                }
                if (phone == null || error.checkValidPhoneNumber(phone) == false) {
                    error.setPhoneErrorFormat("Phone is not valid");
                }

                if (foundError) {
                    request.setAttribute("ERROR", error);
                    request.setAttribute("PAGE", "REGISTER");
                } else {

//                   Date  date =new SimpleDateFormat("yyyy-MM-dd").parse(birthdate);  
                    java.sql.Date sqlDate;
                    sqlDate = java.sql.Date.valueOf(birthdate);
                    AccountDTO dto = new AccountDTO();
                    dto.setAddress(address);
                    dto.setUsername(username);
                    dto.setPassword(password);
                    dto.setFullname(fullname);
                    dto.setPhone(phone);
                    dto.setBirthday(sqlDate);

//               AccountDAO.createAccount(dto);
                  
                    dao.createAccount(dto);

                    url = "home";
                }

            System.out.println("username duplicate : " +error.getUserNameExisted());

        } finally {
            url=roadmap.get(url);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CreateAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(CreateAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CreateAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(CreateAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
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
