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
@WebServlet(name = "AdminAccountDetailServlet", urlPatterns = {"/admin/AdminAccountDetailServlet"})
public class AdminAccountDetailServlet extends HttpServlet {

    public final String SUCCESS = "admin/accountDetailPage";

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

            int accountID = Integer.parseInt(request.getParameter("accountid"));
            String action = request.getParameter("submitAction");

            AccountDAO accDao = new AccountDAO();

            System.out.println(action);
            if ("Update".equals(action)) {
                AccountDTO dto = accDao.getAccountFromAcoountID(accountID);

                System.out.println("GO HERE");
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String fullname = request.getParameter("fullname");
                String birthday = request.getParameter("birthday");
                String address = request.getParameter("address");
                Date sqlDate = Date.valueOf(birthday);
                String email = request.getParameter("email");
                String phone = request.getParameter("phone");
                String role = request.getParameter("role");
                String categoryID = "Mentor".equals(role) ? request.getParameter("category") : null;
                String status = dto.getStatus();

                boolean foundError = false;

                if (!username.trim().matches("[a-zA-Z0-9]{6,20}")) {
                    request.setAttribute("ERROR_USERNAME", "Username must be from 6 to 20 characters and only contains character and numbers!");
                    foundError = true;
                } else {
                    if (!dto.getUsername().equals(username)) {
                        int id = accDao.getAccountIDByUsername(username);
                        if (id != 0) {
                            request.setAttribute("ERROR_USERNAME", "Username is existed!");
                            foundError = true;
                        }
                    }
                }

                if (!password.trim().matches("(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}")) {
                    request.setAttribute("ERROR_PASSWORD", "Password must be from 8 to 20 characters and contains at least 1 uppercase, 1 lowercase, 1 number and 1 special characters!!");
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
                }

                if (!phone.matches("([0-9]){8,12}")) {
                    request.setAttribute("ERROR_PHONE", "Phone must be more than 8 number and less than 12 number!");
                    foundError = true;
                }

                if (!foundError) {
                    AccountDTO newDto = new AccountDTO(username, password, fullname, address, sqlDate, email, phone, role, categoryID, status);
                    accDao.updateAccount(accountID, newDto);
                    request.setAttribute("MESSAGE", "Update Successfully!");
                }

            } else if ("Enable".equals(action)) {
                accDao.activateAccount(accountID);
                request.setAttribute("MESSAGE", "Account Activated!");
            } else if ("Disable".equals(action)) {
                accDao.deactivateAccount(accountID);
                request.setAttribute("MESSAGE", "Account Dectivated!");
            }

            AccountDTO dto = accDao.getAccountFromAcoountID(accountID);

            CategoryDAO catDao = new CategoryDAO();
            ArrayList<CategoryDTO> catList = catDao.getAllCategory();
            ArrayList<String> roleList = new ArrayList<String>() {
                {
                    add("Student");
                    add("Mentor");
                    add("Admin");
                }
            };

            request.setAttribute("CATEGORY_LIST", catList);
            request.setAttribute("ROLE_LIST", roleList);
            request.setAttribute("ACCOUNT", dto);

            String url = roadmap.get(SUCCESS);
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
