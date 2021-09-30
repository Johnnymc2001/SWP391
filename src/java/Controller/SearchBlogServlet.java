/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.BlogDTO;
import DAO.BlogDAO;
import DAO.CategoryDAO;
import DAO.CategoryDTO;
import java.io.IOException;
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
@WebServlet(name = "SearchBlogServlet", urlPatterns = {"/SearchBlogServlet"})
public class SearchBlogServlet extends HttpServlet {

    private final String HOME_PAGE = "";
    private final String SEARCH_PAGE = "searchPage";

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
//        HashMap<String, String> roadmap = (HashMap<String, String>) sc.getAttribute("ROADMAP");
//        String url = roadmap.get(SEARCH_PAGE);
        String searchValue = request.getParameter("txtSearchValue");
        String searchCategory = request.getParameter("txtSearchCategory");
        ArrayList<BlogDTO> blogList = new ArrayList<BlogDTO>();
        HashMap<String, String> roadmap = (HashMap<String, String>) sc.getAttribute("ROADMAP");

        String url = roadmap.get(SEARCH_PAGE);
        BlogDAO dao = new BlogDAO();
        CategoryDAO catDao = new CategoryDAO();

        try {
            ArrayList<CategoryDTO> catList = catDao.getAllCategory();
            if (null != searchValue && searchValue != "") {
                if (searchCategory.equals("All") || searchCategory.equals("")) {
                    blogList = dao.getAllBlogLikeTitle(searchValue);
                } else {
                    blogList = dao.getAllBlogLikeTitleAndFromCategoryID(searchValue, searchCategory);
                }
            }

//                if (!list.isEmpty()) {                                                 // Rút gọn content nếu cần
//                    list.forEach((blog) -> {
//                        blog.setContent(blog.getContent().substring(0, 60) + "...");
//                    });
//                }
            request.setAttribute("CAT_LIST", catList);
            request.setAttribute("BLOG_LIST", blogList);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
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
