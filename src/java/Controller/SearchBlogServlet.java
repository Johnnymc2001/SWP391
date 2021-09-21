/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.BlogDTO;
import DAO.BlogDAO;
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
    private final String SEARCH_PAGE = "search.jsp";

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
        String searchType = request.getParameter("txtSearchType");
        ArrayList<BlogDTO> list = new ArrayList<BlogDTO>();

        try {
            if (searchValue == "" || searchValue == null) {
                if (searchValue != "" || searchValue != null) {
                    System.out.println(searchValue);
                    if (searchType != "" || searchType != null) {
                        System.out.println(searchType);
                        if (searchType.equals("title")) {
                            list = BlogDAO.searchBlogUsingTitle(searchValue);
                        } else if (searchType.equals("category")) {
                            list = BlogDAO.getAllBlogFromCategoryId(searchValue);
                        }
                    } else {

                    }

                } else {

                }

                if (!list.isEmpty()) {                                                 // Rút gọn content nếu cần
                    list.forEach((blog) -> {
                        blog.setContent(blog.getContent().substring(0, 60) + "...");
                    });
                }
                request.setAttribute("SEARCH_RESULT", list);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            request.getRequestDispatcher(SEARCH_PAGE).forward(request, response);
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
