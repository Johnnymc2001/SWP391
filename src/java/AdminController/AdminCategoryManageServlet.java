/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminController;

import DAO.BlogDAO;
import DAO.CategoryDAO;
import DAO.CategoryDTO;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "AdminCategoryManageServlet", urlPatterns = {"/admin/AdminCategoryManageServlet"})
public class AdminCategoryManageServlet extends HttpServlet {

    private final String SUCCESS = "admin/categoryManagePage";

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
        ServletContext sc = request.getServletContext();
        HashMap<String, String> roadmap = (HashMap<String, String>) sc.getAttribute("ROADMAP");

        try {
            response.setContentType("text/html;charset=UTF-8");

            CategoryDAO catDao = new CategoryDAO();
            BlogDAO blogDao = new BlogDAO();

            String action = request.getParameter("submitAction");

            if ("Edit".equals(action)) {
                String categoryid = request.getParameter("categoryid");
                String categoryName = request.getParameter("categoryname");

                catDao.updateCategory(categoryid, new CategoryDTO(categoryid, categoryName));
                request.setAttribute("MESSAGE", "Category Updated!");
            } else if ("Add".equals(action)) {
                String categoryid = request.getParameter("categoryid");
                String categoryName = request.getParameter("categoryname");

                if (catDao.createCategory(new CategoryDTO(categoryid, categoryName))) {
                    request.setAttribute("MESSAGE", "Category Added!");
                } else {
                    request.setAttribute("MESSAGE", "CategoryID maybe already existed!!");
                }
            } else if ("Delete".equals(action)) {
                String categoryid = request.getParameter("categoryid");
                String categoryName = request.getParameter("categoryname");

                if (catDao.deleteCategory(categoryid)) {
                    request.setAttribute("MESSAGE", "Category Deleted!");
                } else {
                    request.setAttribute("MESSAGE", "Unexpected Error!");
                }
            }

            ArrayList<CategoryDTO> catList = catDao.getAllCategory();
            HashMap<CategoryDTO, Integer> list = new HashMap<>();

            for (CategoryDTO dto : catList) {
                list.put(dto, blogDao.getAllBlogFromCategoryId(dto.getCategoryID()).size());
            }

            request.setAttribute("LIST", list);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            String url = roadmap.get(SUCCESS);
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
