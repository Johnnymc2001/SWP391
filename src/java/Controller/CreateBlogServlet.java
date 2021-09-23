package Controller;

import DAO.AttachmentDAO;
import DAO.AttachmentDTO;
import DAO.BlogDAO;
import DAO.BlogDTO;
import DAO.CreateBlogError;
import Utils.ImageUtils;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.SQLException;
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
import javax.servlet.http.Part;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CreateBlogServlet", urlPatterns = {"/CreateBlogServlet"})
public class CreateBlogServlet extends HttpServlet {

    private final String HOME_PAGE = "";
    private final String CREATE_PAGE = "createBlogPage";

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
        String url = roadmap.get(HOME_PAGE);
        String title = request.getParameter("txtTitle");
        String content = request.getParameter("txtContent");
        String categoryID = "DL";
        byte[] attachment = null;

        String tags = request.getParameter("txtTags");
        int studentID = 2;
        CreateBlogError errors = new CreateBlogError();
        boolean foundErr = false;
        String header = request.getContentType();

        byte[] bytesImage = null;

        if (header.contains("multipart/form-data")) { // When Image Exists
            Part part = request.getPart("fileAttachment");
            if (part != null) {
                InputStream data = part.getInputStream();
                bytesImage = ImageUtils.InputStreamToBytes(data);
            }
        }
        
        try {
            //1. Check all user error
            if (title.trim().length() < 6 || title.trim().length() > 20) {
                foundErr = true;
                errors.setTitleLengthErr("Username is required from 6 to 20 characters");
            }

            if (content.trim().length() < 6 || content.trim().length() > 30) {
                foundErr = true;
                errors.setContentLengthErr("Password is required from 6 to 30 characters");
            }

            //2. Process
            if (foundErr) {
                //3. Send errors to users
                request.setAttribute("CREATE_ERROR", errors);
                url = roadmap.get(CREATE_PAGE);
            } else {
                //4. Call DAO to insert to DB
                Date postDate = new Date(Calendar.getInstance().getTime().getTime());
                BlogDTO dto = new BlogDTO(title, content, postDate, categoryID, tags, studentID, attachment);
                BlogDAO dao = new BlogDAO();
                boolean result = dao.createBlog(dto);
                if (result) {
                    url = roadmap.get(HOME_PAGE);
                }
            }
        } catch (SQLException ex) {
            String msg = ex.getMessage();
            log("CreateBlogServlet _ SQL " + msg);
        } finally {
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
            Logger.getLogger(CreateBlogServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(CreateBlogServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CreateBlogServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(CreateBlogServlet.class.getName()).log(Level.SEVERE, null, ex);
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
