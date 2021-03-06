/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.AccountDTO;
import DAO.BlogDAO;
import DAO.BlogRatingDAO;
import DAO.BlogRatingDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
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
 * @author JohnnyMC
 */
@WebServlet(name = "BlogRatingServlet", urlPatterns = {"/BlogRatingServlet"})
public class BlogRatingServlet extends HttpServlet {

    private final String BLOG_DETAIL = "blog";

    /**
     *
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
        try {

            ServletContext sc = request.getServletContext();
            HashMap<String, String> roadmap = (HashMap<String, String>) sc.getAttribute("ROADMAP");
            String url = roadmap.get(BLOG_DETAIL);
            String txtBlogID = request.getParameter("txtBlogID");
            String txtRate = request.getParameter("txtRate");
            int blogID = Integer.parseInt(txtBlogID);

            AccountDTO account = (AccountDTO) request.getSession().getAttribute("USER");

            BlogDAO blogDAO = new BlogDAO();
            BlogRatingDAO blogRatingDAO = new BlogRatingDAO();
            BlogRatingDTO dto = blogRatingDAO.getBlogRatingFromBlogIDAndOwnerID(blogID, account.getAccountID());

            if (null == account || !account.getRole().equals("Student") && !account.getRole().equals("Mentor")) {
                response.sendError(401);
                return;
            }
            if (null != txtRate) {
                double rate = Integer.parseInt(txtRate);

                if (rate >= 1 && rate <= 5) {
                    Date date = new Date(Calendar.getInstance().getTime().getTime());

                    BlogRatingDTO newDto = new BlogRatingDTO(blogID, date, rate, account.getAccountID());

                    if (null == dto) {
                        blogRatingDAO.createBlogRating(newDto);
                    } else {
                        blogRatingDAO.updateBlogRating(dto.getRatingID(), newDto);
                    }

                    int rating = blogDAO.getAverageRatingFromBlogID(blogID);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("utf-8");

                    String json = "{\"rating\":\"" + rating + "\"}";

                    PrintWriter out = response.getWriter();
                    out.write(json);
                    out.flush();
                }

            } else if (null != dto) {
                request.setAttribute("RATING", dto.getRate());
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
                return;
            } else {
                response.sendRedirect("blog?txtBlogID=" + blogID);
                return;
            }
        } catch (SQLException ex) {
            String msg = ex.getMessage();
            log("BlogRatingServlet _ SQL " + msg);
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
