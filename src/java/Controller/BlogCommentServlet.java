package Controller;

import DAO.AccountDTO;
import DAO.BlogCommentDAO;
import DAO.BlogCommentDTO;
import DAO.BlogDAO;
import DAO.BlogDTO;
import static Utils.ImageUtils.BytesToBase64;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
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
 * @author Admin
 */
@WebServlet(name = "BlogDetailServlet", urlPatterns = {"/BlogDetailServlet"})
public class BlogCommentServlet extends HttpServlet {

    private final String HOME_PAGE = "default";
    private final String COMMENT_PAGE = "commentPage";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     * @throws javax.naming.NamingException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, NamingException {
        response.setContentType("text/html;charset=UTF-8");

        ServletContext sc = request.getServletContext();
        HashMap<String, String> roadmap = (HashMap<String, String>) sc.getAttribute("ROADMAP");
        String url = null;
        
        String content = request.getParameter("content");
//        String blogID = request.getParameter("blogID");
        AccountDTO curUser= (AccountDTO) request.getSession().getAttribute("USER");
        int ownerID = curUser.getAccountID();
        BlogCommentDAO dao = new BlogCommentDAO();

        try {

            if (content.length() < 15) {
                request.setAttribute("CONTENT_ERROR", "Your content need to exceed 15 character");
            } else {
                java.sql.Date sqlDate;
                Date commentDate = new Date(Calendar.getInstance().getTime().getTime());
                BlogCommentDTO dto = new BlogCommentDTO(ownerID, commentDate, content, ownerID);
                dao.createBlogComment(dto);
            }
            BlogCommentDAO commentDao = new BlogCommentDAO();
            ArrayList<BlogCommentDTO> commentList = commentDao.getAllBlogComment();
            if (null == commentList) {
                url = roadmap.get(HOME_PAGE);
                response.sendRedirect(url);
            } else {
                request.setAttribute("COMMENT_LIST", commentList);
                url = roadmap.get(COMMENT_PAGE);
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            }
        } catch (SQLException ex) {
            String msg = ex.getMessage();
            log("CreateBlogServlet _ SQL " + msg);
        } finally {

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
            Logger.getLogger(BlogCommentServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(BlogCommentServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(BlogCommentServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(BlogCommentServlet.class.getName()).log(Level.SEVERE, null, ex);
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
