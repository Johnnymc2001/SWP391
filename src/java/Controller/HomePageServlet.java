/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.BlogDAO;
import DAO.BlogDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.stream.Collectors;
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
@WebServlet(name = "HomePageServlet", urlPatterns = {"/HomePageServlet"})
public class HomePageServlet extends HttpServlet {

    private static final String HOME_PAGE = "homePage";
    String url;

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
            ServletContext sc = request.getServletContext();
            HashMap<String, String> roadmap = (HashMap<String, String>) sc.getAttribute("ROADMAP");
            url = roadmap.get(HOME_PAGE);

            response.setContentType("text/html;charset=UTF-8");

//            if (!list.isEmpty()) {                                                 // Rút gọn content nếu cần
//                list.forEach((blog) -> {
//                    blog.setContent(blog.getContent().substring(0, 60) + "...");
//                });
//            }
            BlogDAO blogDao = new BlogDAO();

            // Landing Blog
            BlogDTO landingBlog = blogDao.getBlogFromBlogID(70);
            request.setAttribute("LANDING_BLOG", landingBlog);

            // Get all blog with award, comment and rate support
            ArrayList<BlogDTO> blogList = blogDao.getBlogDetailed(6);

            // Most Award And Rate [Popular]
//            ArrayList<BlogDTO> mostAwardAndRate = blogDao.getAllApprovedBlogWithMostAwardAndHighestRating(5);
            ArrayList<BlogDTO> mostAwardAndRate = blogList;
            mostAwardAndRate.sort(Comparator.comparing(BlogDTO::getAwardCount).thenComparing(BlogDTO::getAvgRate));
            mostAwardAndRate = mostAwardAndRate.stream().limit(5).collect(Collectors.toCollection(ArrayList::new));

            request.setAttribute("MOST_AWARD_AND_RATE", mostAwardAndRate);

            // Most Award 
//            ArrayList<BlogDTO> mostAward = blogDao.getAllApprovedBlogWithMostAwardAndHighestRating(5);
            ArrayList<BlogDTO> mostAward = blogList;
            mostAward.sort(Comparator.comparing(BlogDTO::getAwardCount));
            mostAward = mostAward.stream().limit(5).collect(Collectors.toCollection(ArrayList::new));

            BlogDTO mostAwardFirst = mostAward.remove(0);

            request.setAttribute("MOST_AWARD_FIRST", mostAwardFirst);
            request.setAttribute("MOST_AWARD", mostAward);

            // Most Rate
//            ArrayList<BlogDTO> mostRate = blogDao.getAllApprovedBlogWithMostAwardAndHighestRating(5);
            ArrayList<BlogDTO> mostRate = blogList;
            mostRate.sort(Comparator.comparing(BlogDTO::getAvgRate));
            mostRate = mostRate.stream().limit(4).collect(Collectors.toCollection(ArrayList::new));

            request.setAttribute("MOST_RATE", mostRate);

            // Most Comment
//            ArrayList<BlogDTO> mostComment = blogDao.getAllApprovedBlogWithHighestComment(6);
            ArrayList<BlogDTO> mostComment = blogList;
            mostComment.sort(Comparator.comparing(BlogDTO::getCommentCount));
           
            mostComment = mostComment.stream().limit(6).collect(Collectors.toCollection(ArrayList::new));

            BlogDTO mostCommentFirst = mostComment.get(0);
            BlogDTO mostCommentSecond = mostComment.get(1);

            ArrayList<BlogDTO> mostCommentRow1 = new ArrayList<>();
            mostCommentRow1.add(mostComment.get(2));
            mostCommentRow1.add(mostComment.get(3));
            ArrayList<BlogDTO> mostCommentRow2 = new ArrayList<>();
            mostCommentRow2.add(mostComment.get(4));
            mostCommentRow2.add(mostComment.get(5));

            request.setAttribute("MOST_COMMENT", mostComment);
            request.setAttribute("MOST_COMMENT_FIRST", mostCommentFirst);
            request.setAttribute("MOST_COMMENT_SECOND", mostCommentSecond);
            request.setAttribute("MOST_COMMENT_ROW_1", mostCommentRow1);
            request.setAttribute("MOST_COMMENT_ROW_2", mostCommentRow2);

            // Recent [Recent]
            ArrayList<BlogDTO> recent = blogDao.getAllBlogWithStatus("APPROVED", 5);
            request.setAttribute("MOST_RECENT", recent);

            // Landing Post [Epic]
//            request.setAttribute("LANDING_BLOG", sc);
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
