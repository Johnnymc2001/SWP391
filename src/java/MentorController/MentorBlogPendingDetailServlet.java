/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MentorController;

import DAO.AccountDAO;
import DAO.AccountDTO;
import DAO.BlogDAO;
import DAO.BlogDTO;
import DAO.NotificationDAO;
import DAO.NotificationDTO;
import java.io.IOException;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author JohnnyMC
 */
@WebServlet(name = "MentorBlogPendingDetailServlet", urlPatterns = {"/MentorBlogPendingDetailServlet"})
public class MentorBlogPendingDetailServlet extends HttpServlet {

    public final String PENDING_EDIT = "blogPendingDetailPage";
    public final String PENDING_LIST = "blogPendingList";
    
    public final String DEFAULT_THUMBNAIL = "UI/Icon/selfmademan.jpg";

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
        String url = PENDING_EDIT;
        response.setContentType("text/html;charset=UTF-8");
        ServletContext sc = request.getServletContext();
        HashMap<String, String> roadmap = (HashMap<String, String>) sc.getAttribute("ROADMAP");

        AccountDAO accDao = new AccountDAO();
//        request.setCharacterEncoding("UTF-8"); 
        HttpSession session = request.getSession();
        AccountDTO account = (AccountDTO) session.getAttribute("USER");
        if (null == account || !account.getRole().equals("Mentor")) {
            response.sendRedirect(sc.getContextPath());
            return;
        }

        BlogDAO blogDao = new BlogDAO();
        NotificationDAO notiDao = new NotificationDAO();

        BlogDTO blog = null;

        String blogIDString = request.getParameter("blogid");
        String action = request.getParameter("submitAction");
        String note = null != request.getParameter("note") ? request.getParameter("note") : "";
        String image = null != request.getParameter("txtImageUrl") && !"".equals(request.getParameter("txtImageUrl").trim()) ? request.getParameter("txtImageUrl") : "";;
        
        System.out.println("Khong co ok con de"+blogIDString);
        int blogID = 0;

        if (null != blogIDString) {
            try {
                blogID = Integer.parseInt(blogIDString);
            } catch (NumberFormatException ex) {
                blogID = 0;
            }
        }
        if (blogID != 0) {
            try {
                blog = blogDao.getBlogFromBlogID(blogID);

                if (null != blog) {
                    if (blog.getCategoryID().equals(account.getCategoryID()) || blog.getStatus().equals("PENDING")) {
                        boolean foundErr = false;

                        if (null == action) {
                            url = roadmap.get(PENDING_EDIT);
                            request.setAttribute("BLOG", blog);
                            RequestDispatcher rd = request.getRequestDispatcher(url);
                            rd.forward(request, response);

                        } else if ("Update".equals(action)) {
                            String title = null == request.getParameter("title") ? blog.getTitle() : request.getParameter("title");
                            String content = null == request.getParameter("content") ? blog.getTitle() : request.getParameter("content");

                            if (title.trim().length() < 6 || title.trim().length() > 60) {
                                foundErr = true;
                                request.setAttribute("ERROR_TITLE", "title is required from 6 to 60 characters");
                            }

                            if (content.trim().length() < 10) {
                                foundErr = true;
                                request.setAttribute("ERROR_CONTENT", "Content is required at least 10 characters");
                            }

                            if (foundErr) {
                                url = roadmap.get(PENDING_EDIT);
                                RequestDispatcher rd = request.getRequestDispatcher(url);
                                rd.forward(request, response);
                            } else {
                                blog.setTitle(title);
                                blog.setContent(content);
                                if ("".equals(image)) {
                                    blog.setThumbnail(DEFAULT_THUMBNAIL);
                                }
                                System.out.println(content);

                                boolean result = blogDao.updateBlog(blogID, blog);

                                if (result) {
                                    request.setAttribute("MESSAGE", "Blog Successfully Updated!");
                                } else {
                                    request.setAttribute("MESSAGE", "Something wrong, please try again!");
                                }

                                request.setAttribute("BLOG", blog);

                                url = roadmap.get(PENDING_EDIT);

                                RequestDispatcher rd = request.getRequestDispatcher(url);
                                rd.forward(request, response);
                            }

                        } else if ("Approve".equals(action)) {
                            request.setAttribute("MESSAGE", "Blog Approved");
                            blogDao.approveBlog(blogID, note);
                            notiDao.createNotification(new NotificationDTO(blog.getStudentID(), false, "BLOG_APPROVED", "Your blog is approved, congratz!", new Date(Calendar.getInstance().getTime().getTime()), "blog?txtblogID=" + blog.getBlogID()));
                            url = roadmap.get(PENDING_LIST);
                            RequestDispatcher rd = request.getRequestDispatcher(url);
                            rd.forward(request, response);
                        } else if ("Disapprove".equals(action)) {
                            request.setAttribute("MESSAGE", "Blog Disapproved");
                            url = roadmap.get(PENDING_LIST);
                            notiDao.createNotification(new NotificationDTO(blog.getStudentID(), false, "BLOG_DISAPPROVED", "Your blog have been disapproved!", new Date(Calendar.getInstance().getTime().getTime()), "blog?txtblogID=" + blog.getBlogID()));
                            blogDao.disapproveBlog(blogID, note);
                            RequestDispatcher rd = request.getRequestDispatcher(url);
                            rd.forward(request, response);
                        } else {
                            url = roadmap.get(PENDING_EDIT);
                            request.setAttribute("BLOG", blog);
                            RequestDispatcher rd = request.getRequestDispatcher(url);
                            rd.forward(request, response);
                        }
                    } else {
                        url = roadmap.get(PENDING_LIST);
                        RequestDispatcher rd = request.getRequestDispatcher(url);
                        rd.forward(request, response);
                    }
                } else {
                    url = roadmap.get(PENDING_LIST);
                    RequestDispatcher rd = request.getRequestDispatcher(url);
                    rd.forward(request, response);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            url = roadmap.get(PENDING_LIST);
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
