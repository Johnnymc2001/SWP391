/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.AccountDAO;
import DAO.AccountDTO;
import DAO.BlogDAO;
import DAO.BlogDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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
@WebServlet(name = "StudentProfileServlet", urlPatterns = {"/StudentProfileServlet"})
public class StudentProfileServlet extends HttpServlet {

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

        String txtID = request.getParameter("userID");
        String username = request.getParameter("username");
        String fullname = request.getParameter("fullname");
        String address = request.getParameter("address");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String birthdate = request.getParameter("birthdate");
        String button = request.getParameter("btAction");
        String url = "";
        int id = 0;

        BlogDAO blogdao = new BlogDAO();
        AccountDAO accDao = new AccountDAO();

        AccountDTO curUser = (AccountDTO) request.getSession().getAttribute("USER");
        AccountDTO profileAccount = null;

        try {
            if (null != txtID && !txtID.equals("")) {
                id = Integer.parseInt(txtID);
            }
            // Parse Profile Account From Parameter Or Current Account
            if (id != 0) {
                profileAccount = accDao.getAccountFromAcoountID(id);
            } else if (null != curUser) {
                profileAccount = curUser;
                System.out.println("Get Self Profile!");
            }

            if (null != profileAccount) {
                if (button != null) {
                    if (button.equals("UpdateProfile") && profileAccount.getAccountID() == curUser.getAccountID()) {
                        java.sql.Date sqlDate;
                        sqlDate = java.sql.Date.valueOf(birthdate);
//                            account = new AccountDTO(username, password, fullname, address, sqlDate, email, phone);
                        profileAccount.setUsername(username);
                        profileAccount.setPassword(password);
                        profileAccount.setFullname(fullname);
                        profileAccount.setBirthday(sqlDate);
                        profileAccount.setEmail(email);
                        profileAccount.setPhone(phone);
                        profileAccount.setAddress(address);

                        accDao.updateAccount(profileAccount.getAccountID(), profileAccount);
                        request.setAttribute("ACCOUNT", profileAccount);
                        url = roadmap.get("profilePage");

                    }
                } else {
                    request.setAttribute("ACCOUNT", profileAccount);
                    url = roadmap.get("profilePage");

                }

                ArrayList<BlogDTO> bloglist = blogdao.getAllBlogFromAccountId(profileAccount.getAccountID());
                request.setAttribute("CURBLOG", bloglist);
                request.setAttribute("ACCOUNT", profileAccount);
//                    System.out.println("bloglist: " + bloglist);
//                    System.out.println("account :" + account.toString());

            } else {
                url = roadmap.get("home");
            }

        } catch (Exception ex) {
        } finally {
//            url = roadmap.get("profilePage");
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
