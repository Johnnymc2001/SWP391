/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.AccountDAO;
import DAO.AccountDTO;
import DAO.LoginErrorDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private static final String HOME_PAGE = "";

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

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String Remember = request.getParameter("chkRemember");
        String url = "loginPage";
//        System.out.println(username);
//        System.out.println(password);

        boolean foundError = false;
        try {
            AccountDAO dao = new AccountDAO();
            AccountDTO curUser = dao.checkLogin(username, password);
            if (username == null || password == null) {
                foundError = true;
                request.setAttribute("LOGIN_NULL", "User Name or Password Is Empty!");
            } else {

                if (curUser == null) {
                    foundError = true;
                    request.setAttribute("LOGIN_ERROR", "Your Username or Password is incorrect!");
                } else {
                    if (curUser.getStatus().equals("UNAVAILABLE")) {
                        foundError = true;
                        request.setAttribute("LOGIN_FAIL", "Your Account is not AVAILABLE anymore ");
                    }
                    if (curUser.getStatus().equals("PENDING")) {
                        foundError = true;
                        request.setAttribute("LOGIN_FAIL", "You account is not verified! Please check your mailbox or <a href=\"verify?email=" + curUser.getEmail() + "\">send again?</a>");
                    }
                }
            }
            if (foundError) {
                url = roadmap.get(url);
                request.getRequestDispatcher(url).forward(request, response);
            } else {
                if (null != Remember && Remember.equals("true")) {
                    Cookie cookie = new Cookie(username, password);
                    cookie.setMaxAge(60 * 10 * 7);

                    response.addCookie(cookie);
                }

//              url = roadmap.get("home");
                url = "home";
                HttpSession session = request.getSession(true);
                session.setAttribute("USER", curUser);
                response.sendRedirect(url);
            }

            System.out.println(url);

        } catch (IOException | SQLException | ServletException ex) {

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
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
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
