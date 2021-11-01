package Controller;

import DAO.AccountDAO;
import DAO.AccountDTO;
import DAO.BlogCommentDAO;
import DAO.BlogCommentDTO;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
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

/**
 *
 * @author Admin
 */
@WebServlet(name = "BlogCommentServlet", urlPatterns = {"/BlogCommentServlet"})
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
        String blogIdString = request.getParameter("txtBlogID");
        int blogID = 0;
        if (blogIdString != null) {
            try {
                blogID = Integer.parseInt(blogIdString);
            } catch (NumberFormatException ex) {
                blogID = 0;
            }
        }

//        int blogID = 21;
        int ownerID;
        AccountDTO curUser = (AccountDTO) request.getSession().getAttribute("USER");

        if (null != curUser) {
            ownerID = curUser.getAccountID();
        } else {
            ownerID = 0;
        }

        BlogCommentDAO dao = new BlogCommentDAO();
        if (blogID == 0) {
            response.sendRedirect(sc.getContextPath());
        } else {
            try {
                if (null != content) {
                    if (content.trim().length() < 4) {
                        request.setAttribute("ERROR_COMMENT", "Your comment need at least 4 character");
                    } else {
                        ArrayList<BlogCommentDTO> listCommentSelf = new ArrayList<>();
                        listCommentSelf = dao.getAllBlogCommentFromAccountID(curUser.getAccountID());
                        
                        if (listCommentSelf.size() > 0) {
                            BlogCommentDTO mostRecentComment = listCommentSelf.get(0);
                            long lastCommentTime = mostRecentComment.getTime().getTime() / 1000;
                            long now = new Timestamp(System.currentTimeMillis()).getTime() / 1000;
                            
                            int time = (int) (now - lastCommentTime);
                            if (time > 10) {
                                // Last comment is more than 10s, ok!
                                Timestamp commentDate = new Timestamp(System.currentTimeMillis());
                                BlogCommentDTO dto = new BlogCommentDTO(blogID, commentDate, content, ownerID);
                                dao.createBlogComment(dto);
                            } else {
                                request.setAttribute("MESSAGE", "You are comment too fast! Please wait 10 second!");
                            }
                            
                        } else {
                            // User hadn't has any comment yet!
                            Timestamp commentDate = new Timestamp(System.currentTimeMillis());
                            BlogCommentDTO dto = new BlogCommentDTO(blogID, commentDate, content, ownerID);
                            dao.createBlogComment(dto);
                        }

                    }
                }

                BlogCommentDAO commentDao = new BlogCommentDAO();
                ArrayList<BlogCommentDTO> commentList = commentDao.getAllBlogCommentFromBlogID(blogID);
                AccountDAO accountDao = new AccountDAO();

                HashMap<BlogCommentDTO, AccountDTO> CommentToAccountMap = new HashMap<>();
                for (BlogCommentDTO comment : commentList) {
                    CommentToAccountMap.put(comment, accountDao.getAccountFromAcoountID(comment.getOwnerID()));
                }

//                if (null == commentList) {
//                    url = roadmap.get(HOME_PAGE);
//                    response.sendRedirect(url);
//                } else {
//
//                }
                request.setAttribute("COMMENTQTY", commentList.size());
                request.setAttribute("COMMENT_MAP", CommentToAccountMap);
                url = roadmap.get(COMMENT_PAGE);
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);

            } catch (SQLException ex) {
                String msg = ex.getMessage();
                log("CreateBlogServlet _ SQL " + msg);
            } finally {

            }
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
