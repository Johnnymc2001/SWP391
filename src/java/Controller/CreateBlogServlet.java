package Controller;

import DAO.BlogDAO;
import DAO.BlogDTO;
import DAO.CategoryDAO;
import DAO.CategoryDTO;
import Utils.ImageUtils;
import java.io.IOException;
import java.io.InputStream;
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
import javax.servlet.http.Part;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CreateBlogServlet", urlPatterns = {"/CreateBlogServlet"})
public class CreateBlogServlet extends HttpServlet {

    private final String HOME_PAGE = "default";
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
        String categoryID = request.getParameter("categoryBox");

        String tags = request.getParameter("txtTags");
        int studentID = 2;
        boolean foundErr = false;
        String header = request.getContentType();

        byte[] bytesImage = null;

        CategoryDAO catDao = new CategoryDAO();
        ArrayList<CategoryDTO> catList = catDao.getAllCategory();

        request.setAttribute("CATEGORY_LIST", catList);

        try {
            if (null == title) {
                url = roadmap.get(CREATE_PAGE);
            } else {
                System.out.println(header);
                if (null != header && header.contains("multipart/form-data")) { // When Image Exists
                    Part part = request.getPart("fileAttachment");
                    System.out.println(part+" Ok con de");
                    if (part.getSize()>0) {
                        InputStream data = part.getInputStream();
                        bytesImage = ImageUtils.InputStreamToBytes(data);
                    }
                }
                //1. Check all user error
                if (title.trim().length() < 6 || title.trim().length() > 60) {
                    foundErr = true;
                    request.setAttribute("ERROR_TITLE", "title is required from 6 to 60 characters");
                }

                if (content.trim().length() < 10) {
                    foundErr = true;
                    request.setAttribute("ERROR_CONTENT", "Content is required at least 10 characters");
                }
                if (foundErr) {
                    url = roadmap.get(CREATE_PAGE);
                } else {
                    //4. Call DAO to insert to DB
                    Date postDate = new Date(Calendar.getInstance().getTime().getTime());
                    BlogDTO dto = new BlogDTO(title, content, postDate, categoryID, tags, studentID, bytesImage);
                    BlogDAO dao = new BlogDAO();
                    boolean result = dao.createBlog(dto);
                    if (result) {
                        request.setAttribute("MESSAGE", "Your have been created, waiting for mentor to approve...");
                        url = roadmap.get(HOME_PAGE);
                        response.sendRedirect(url);
                    } else {
                        url = roadmap.get(CREATE_PAGE);
                    }
                }
            }
            
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
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
