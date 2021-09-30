package Controller;

import DAO.AttachmentDAO;
import DAO.AttachmentDTO;
import DAO.BlogDAO;
import DAO.BlogDTO;
import Utils.ImageUtils;
import static Utils.ImageUtils.BytesToBase64;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
@WebServlet(name = "BlogDetailServlet", urlPatterns = {"/BlogDetailServlet"})
public class BlogDetailServlet extends HttpServlet {

    private final String HOME_PAGE = "default";
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
        String url = null;
        String txtBlogID = request.getParameter("txtBlogID");
        int blogID;
        if (null != txtBlogID) {
            blogID = Integer.parseInt(txtBlogID);
        } else {
            blogID = 2;
        }

        try {
            BlogDAO blogDao = new BlogDAO();
            BlogDTO blog = blogDao.getBlogFromBlogID(blogID);
            request.setAttribute("BLOG_DETAIL", blog);
            if (null == blog) {
                url = roadmap.get(HOME_PAGE);
                response.sendRedirect(url);
            } else {
                AttachmentDAO attDao = new AttachmentDAO();
                ArrayList<AttachmentDTO> attList = attDao.getAllAttachmentsFromBlogID(blog.getBlogID());
                if (attList.size() > 0) {
                    AttachmentDTO attachment = attList.get(0);
                    String base64Image = "";
                    System.out.println(attachment.toString());
                    if ("IMAGE/BINARY".equals(attachment.getType())) {
                        base64Image = ImageUtils.BytesToBase64(attachment.getDataBinary());
                    }

                    if ("IMAGE/BASE64".equals(attachment.getType())) {
                        base64Image = attachment.getDataText();
                    }

                    System.out.println(base64Image);

                    if (null != base64Image) {
                        request.setAttribute("IMAGE", base64Image);
                    }
                }
                url = roadmap.get(BLOGDETAIL_PAGE);
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
