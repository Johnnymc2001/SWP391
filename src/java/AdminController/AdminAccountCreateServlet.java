/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminController;

import DAO.AccountDAO;
import DAO.AccountDTO;
import DAO.AccountError;
import DAO.CategoryDAO;
import DAO.CategoryDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
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
@WebServlet(name = "AdminAccountCreateServlet", urlPatterns = {"/AdminAccountCreateServlet"})
public class AdminAccountCreateServlet extends HttpServlet {

    public final String CREATE_PAGE = "adminAccountCreatePage";
    public final String LIST_ACCOUNT = "adminAccountList";

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

        String username = null == request.getParameter("username") ? "" : request.getParameter("username");
        String password = null == request.getParameter("password") ? "" : request.getParameter("password");
        String fullname = null == request.getParameter("fullname") ? "" : request.getParameter("fullname");    
        Date birthdate = null;      
        String confirm_password = null == request.getParameter("confirm_password") ? "" : request.getParameter("confirm_password");
        String address = null == request.getParameter("address") ? "" : request.getParameter("address");
        String email = null == request.getParameter("email") ? "" : request.getParameter("email");
        String phone = null == request.getParameter("phone") ? "" : request.getParameter("phone");
        String role = null == request.getParameter("role") ? "" : request.getParameter("role");
        String categoryID = "Mentor".equals(role) ? request.getParameter("category") : null;

        String action = request.getParameter("submitAction");

        boolean foundError = false;
        
                
        try {
            if (null != request.getParameter("birthday")){
                birthdate = Date.valueOf(request.getParameter("birthday"));
            }          
        } catch (Exception ex) {
            foundError = true;
            request.setAttribute("ERROR_BIRTHDAY", "Birthday is not valid!");
        }

        String url = CREATE_PAGE;
        AccountDAO accDao = new AccountDAO();
        CategoryDAO catDao = new CategoryDAO();
        ArrayList<CategoryDTO> catList = null;
        try {
            catList = catDao.getAllCategory();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        ArrayList<String> roleList = new ArrayList<String>() {
            {
                add("Student");
                add("Mentor");
                add("Admin");
            }
        };
        try {
            if (null == action) {

                request.setAttribute("CATEGORY_LIST", catList);
                request.setAttribute("ROLE_LIST", roleList);

            } else if (action.equals("Create")) {

                if (!username.trim().matches("[a-zA-Z0-9]{6,20}")) {
                    request.setAttribute("ERROR_USERNAME", "Username must be from 6 to 20 characters and only contains character and numbers!");
                    foundError = true;
                } else {
                    if (null != accDao.getAccountFromUsername(username)) {
                        request.setAttribute("ERROR_USERNAME", "Username is existed!");
                        foundError = true;

                    }
                }
//                if (!password.trim().matches("[A-Za-z\\d@$!%*?&]{8,20}")) {
//                    request.setAttribute("ERROR_PASSWORD", "Password must be from 8 to 20 characters and contains at least 1 uppercase, 1 lowercase, 1 number and 1 special characters!!");
//                    foundError = true;

//                } else 
                if (!confirm_password.equals(password)) {
                    request.setAttribute("ERROR_CONFIRM_PASSWORD", "Password must be the same!");

                    foundError = true;
                }
                if (!fullname.matches("([\\ a-zA-Z]){4,}")) {
                    request.setAttribute("ERROR_FULLNAME", "Fullname must be more than 4 characters and must not contains any special characters and number!");
                    foundError = true;
                }
                if (!address.matches("([\\ a-zA-Z0-9]){4,}")) {
                    request.setAttribute("ERROR_ADDRESS", "Address must be more than 4 characters and must not contains any special characters and number!");
                    foundError = true;
                }
                if (!email.matches("([\\w\\d\\_\\-])+@[\\w]+\\.[\\w\\.]+")) {
                    request.setAttribute("ERROR_EMAIL", "Email Address is in incorrect format");
                    foundError = true;
                } else {
                    if (null != accDao.getAccountFromEmail(email)) {
                        request.setAttribute("ERROR_EMAIL", "Email is existed!");
                        foundError = true;

                    }
                }

                if (null == birthdate) {
                    request.setAttribute("ERROR_BIRTHDAY", "Birthday not valid!");
                    foundError = true;
                }
                if (!phone.matches("([0-9]){8,12}")) {
                    request.setAttribute("ERROR_PHONE", "Phone must be more than 8 number and less than 12 number!");
                    foundError = true;
                }
                if (!roleList.contains(role) || !(null != role)) {
                    request.setAttribute("ERROR_ROLE", "Role not found!");
                    foundError = true;
                }
//                if ("Mentor".equals(role)) {
//                    if (!catList.contains(categoryID) || !(null != categoryID)) {
//                        request.setAttribute("ERROR_CATEGORY", "Category not found!");
//                        foundError = true;
//                    }
//                }
                if (!foundError) {
                    AccountDTO newDto = new AccountDTO(username, password, fullname, address, birthdate, email, phone, role, categoryID, "AVAILABLE");
                    accDao.createAccount(newDto);
                    url = LIST_ACCOUNT;
                }
            }
            request.setAttribute("CATEGORY_LIST", catList);
            request.setAttribute("ROLE_LIST", roleList);
        } catch (SQLException ex) {

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
