package Controller;

import DAO.AccountDTO;
import DAO.AwardDAO;
import DAO.AwardDTO;
import DAO.AwardListDAO;
import DAO.AwardListDTO;
import DAO.BlogCommentDAO;
import DAO.BlogCommentDTO;
import DAO.BlogDAO;
import DAO.BlogDTO;
import DAO.BlogRatingDAO;
import DAO.BlogRatingDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
public class BlogDetailServlet extends HttpServlet {

    private final String HOME_PAGE = "home";
    private final String BLOGDETAIL_PAGE = "blogPage";

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
        String url;
        String txtBlogID = request.getParameter("txtBlogID");
        int blogID;
        HttpSession session = request.getSession();
        AccountDTO account = (AccountDTO) session.getAttribute("USER");

        if (null != txtBlogID) {
            blogID = Integer.parseInt(txtBlogID);
        } else {
            blogID = 0;
        }
        if (blogID != 0) {
            try {
                BlogDAO blogDao = new BlogDAO();
                BlogDTO blog = blogDao.getBlogFromBlogID(blogID);
                AccountDTO author = blog.getAccount();
                AwardDAO awardDao = new AwardDAO();
                AwardListDAO awardListDao = new AwardListDAO();
                ArrayList<String> awawdNames = new ArrayList<>();

                //Check pending and user constraint:
                if ((null==account||!(account.getAccountID() == author.getAccountID())) && (("PENDING".equals(blog.getStatus())) || ("DISAPPROVED".equals(blog.getStatus())))) {
                    System.out.println("Pending blog access denied");
                    response.sendError(404);
                    return;
                }

                //----------------------
                //Remove expired rewards:
                System.out.println("Blog ID: " + blogID);
                if (awardListDao.deleteExpiredAwardFromBlogId(blogID)) {
                    System.out.println("Expired Award Cleared");
                }
                //---------------------
                for (AwardDTO award : awardDao.getAllAward()) {
                    for (AwardListDTO awardList : awardListDao.getAwardListFromBlogId(blogID)) {
                        if (award.getAwardID() == awardList.getAwardID()) {
                            awawdNames.add(award.getAwardName());
                            System.out.println(award.getAwardName());
                        }
                    }
                }

                request.setAttribute("AWARDSNAME", awawdNames);
                request.setAttribute("BLOG", blog);
                request.setAttribute("AUTHOR", author);

                // Check if already rated
                if (null != account) {
                    BlogRatingDAO blogRatingDAO = new BlogRatingDAO();
                    BlogRatingDTO blogRatingDTO = blogRatingDAO.getBlogRatingFromBlogIDAndOwnerID(blogID, account.getAccountID());

                    if (null != blogRatingDTO) {
                        request.setAttribute("ALREADY_RATED", "true");
                    }
                }

                if (blog == null) {
                    url = roadmap.get(HOME_PAGE);
                    response.sendRedirect(url);
                } else {

                    int AwardQTY = new AwardListDAO().getAwardListFromBlogId(blogID).size();
                    request.setAttribute("BLOGAWARDS", AwardQTY);

                    int CommentQTY = new BlogCommentDAO().getAllBlogCommentFromBlogID(blogID).size();
                    request.setAttribute("COMMENTQTY", CommentQTY);

                    url = roadmap.get(BLOGDETAIL_PAGE);
                    RequestDispatcher rd = request.getRequestDispatcher(url);
                    rd.forward(request, response);
                }
            } catch (SQLException ex) {
                String msg = ex.getMessage();
                log("CreateBlogServlet _ SQL " + msg);
            } finally {

            }
        } else {
            url = roadmap.get(HOME_PAGE);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(BlogDetailServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(BlogDetailServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(BlogDetailServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(BlogDetailServlet.class.getName()).log(Level.SEVERE, null, ex);
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
