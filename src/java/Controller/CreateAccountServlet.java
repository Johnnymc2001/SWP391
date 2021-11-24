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
import DAO.VerificationDAO;
import DAO.VerificationDTO;
import Utils.MailUtils;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
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
        String url;
        java.sql.Date birthDateValue;
        java.sql.Date presentDate = new Date(System.currentTimeMillis());
        birthDateValue = java.sql.Date.valueOf(birthdate);


        try {
            AccountDAO dao = new AccountDAO();
            AccountDTO user = dao.getAccountFromUsername(username);
            // KIEM TRA CAC LOI KHI TAO TAI KHOAN MOI
//             int i = dao.getAccountIDByUsername(username);
            System.out.println("Account: " + user);
            request.setAttribute("PAGE", "REGISTER");
            if (user != null) {
                request.setAttribute("ERROR", error);
                error.setUserNameExisted("Account Existed");
                System.out.println("username duplicate : " + user.getUsername());
                url = "registerPage";
                url = roadmap.get(url);
                request.getRequestDispatcher(url).forward(request, response);
            }
            if (username == null || username.trim().length() < 6 || username.trim().length() > 20 || username.matches("[a-zA-Z]+[0-9]*") == false) {
                foundError = true;
                error.setUserNameLengthError("User name must be from 6-20 character and must contain 1 character at the begining");

            }
            if (!password.trim().matches("^[\\d\\w\\@\\-\\_\\#\\$\\%\\^\\&\\*\\!\\(\\)]{8,20}$")) {
                foundError = true;
                error.setPasswordLengthError("Password must be from 8 to 20 characters");
            } else if (confirm_password == null || !confirm_password.trim().equals(password.trim())) {
                foundError = true;
                error.setConfirmNotMatched("Confirm Password is Not Matched");
            }
            if (fullname == null || fullname.matches("[\\sA-Za-z]{2,30}") == false) {
                foundError = true;
                error.setFullNameLengthError("Full Name must be from 2 - 30 characters (No Number Or Special Character!)");
            }

            if (null == address || !address.matches("[\\s\\d\\w\\\\.\\\\,]{4,100}")) {
                foundError = true;
                request.setAttribute("ERROR_ADDRESS", "Address must be from 4 - 100 characters");
            }

            if (!email.matches("([\\w\\d\\_\\-])+@[\\w]+\\.[\\w\\.]+")) {
                request.setAttribute("ERROR_EMAIL", "Email Address is in incorrect format");
                foundError = true;
            } else {
                if (null != dao.getAccountFromEmail(email)) {
                    request.setAttribute("ERROR_EMAIL", "Email is existed!");
                    foundError = true;

                }
            }
            if (birthDateValue.after(presentDate)) {
                foundError = true;
                request.setAttribute("ERROR_BIRTHDATE", "Birthdate is not valid");
            }
            if (phone == null || error.checkValidPhoneNumber(phone) == false) {
                error.setPhoneErrorFormat("Phone is not valid");
                foundError = true;
            }

            if (foundError) {
                request.setAttribute("ERROR", error);
                url = "registerPage";
                url = roadmap.get(url);
                request.getRequestDispatcher(url).forward(request, response);
                request.setAttribute("PAGE", "REGISTER");

            } else {
//                   Date  date =new SimpleDateFormat("yyyy-MM-dd").parse(birthdate);  

                AccountDTO dto = new AccountDTO(username, password, fullname, address, birthDateValue, email, phone);

//               AccountDAO.createAccount(dto);
                VerificationDAO veriDao = new VerificationDAO();

                int key = dao.createAccount(dto);

                if (key != 0) {
                    veriDao.AddVerification(new VerificationDTO(key, email, java.util.UUID.randomUUID().toString(), new Timestamp(System.currentTimeMillis()), "VERIFICATION"));
                    MailUtils.sendVerification(key);
                }

                url = "home";
//                url = roadmap.get(url);
                response.sendRedirect("loginPage");
            }
        } catch (IOException | SQLException | ServletException ex) {
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
        try {
            processRequest(request, response);

        } catch (SQLException ex) {
            Logger.getLogger(CreateAccountServlet.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (ParseException ex) {
            Logger.getLogger(CreateAccountServlet.class
                    .getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CreateAccountServlet.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (ParseException ex) {
            Logger.getLogger(CreateAccountServlet.class
                    .getName()).log(Level.SEVERE, null, ex);
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
