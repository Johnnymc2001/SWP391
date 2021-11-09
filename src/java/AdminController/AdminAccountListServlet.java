/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminController;

import DAO.AccountDAO;
import DAO.AccountDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.stream.Collectors;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author JohnnyMC
 */
@WebServlet(name = "AdminAccountListServlet", urlPatterns = {"/AdminAccountListServlet"})
public class AdminAccountListServlet extends HttpServlet {

    public final String SUCCESS = "adminAccountListPage";

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

            HttpSession session = request.getSession();
            AccountDTO account = (AccountDTO) session.getAttribute("USER");
            if (null == account || !account.getRole().equals("Admin")) {
            response.sendError(401);
                return;
            }
//            String maxPageItemString = request.getParameter("maxPageItem");
            String pageString = request.getParameter("page");
            String searchValue = request.getParameter("txtSearchValue");
            String searchAccountType = request.getParameter("txtAccountType");

            AccountDAO accDao = new AccountDAO();

            int maxPageItem = 10;
            int page;

//            if (null != maxPageItemString) {
//                maxPageItem = Integer.parseInt(maxPageItemString);
//            } else {
//                maxPageItem = 5;
//            }
            if (null != pageString) {
                page = Integer.parseInt(pageString);
            } else {
                page = 1;
            }

            ArrayList<AccountDTO> list = new ArrayList<>();
            ArrayList<AccountDTO> listAccount = null;

            if (null != searchAccountType && !"All".equals(searchAccountType)) {
                listAccount = accDao.getAllAccountFromRole(searchAccountType);
            } else {
                listAccount = accDao.getAllAccount();
            }

            if (null != searchValue) {
                listAccount.removeIf(acc -> !acc.getUsername().contains(searchValue));
            }

            for (int i = (page - 1) * maxPageItem; i < listAccount.size() && list.size() < maxPageItem; i++) {
                list.add(listAccount.get(i));
            }

            request.setAttribute("LIST", list);
            request.setAttribute("PAGE_COUNT", (int) Math.ceil((double) listAccount.size() / (double) maxPageItem));
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
