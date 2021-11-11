package Controller;

import DAO.AccountDAO;
import DAO.AccountDTO;
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
import java.util.Arrays;
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
import javax.servlet.http.Part;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
//import org.jsoup.Jsoup;
//import org.jsoup.safety.Safelist;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CreateBlogServlet", urlPatterns = {"/CreateBlogServlet"})
public class CreateBlogServlet extends HttpServlet {

    private final String HOME_PAGE = "default";
    private final String CREATE_PAGE = "createPage";
    private final String ERROR_PAGE = "404.html";

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
        try {
            String url;
            BlogDAO blogDao = new BlogDAO();
            HttpSession session = request.getSession();
            AccountDTO account = (AccountDTO) session.getAttribute("USER");
            if (null == account || !account.getRole().equals("Student") && !account.getRole().equals("Mentor")) {
                response.sendRedirect(sc.getContextPath());
                return;
            } else {
                System.out.println("getAllBlogWithStatusAndAccountID not working?");
                ArrayList<BlogDTO> blogList = blogDao.getAllBlogWithStatusAndAccountID("PENDING", account.getAccountID());
                System.out.println(account.getAccountID());
                if (blogList.size() < 5) {

                    String title = request.getParameter("txtTitle");
                    String content = request.getParameter("txtContent");
                    String categoryID = request.getParameter("categoryBox");

                    String tags = request.getParameter("txtTags");

                    int studentID = 0;

                    if (null != account) {
                        studentID = account.getAccountID();
                    } else {
                        response.sendRedirect(ERROR_PAGE);
                        return;
                    }

                    String header = request.getContentType();
                    String base64Image = null;

                    boolean foundErr = false;

                    CategoryDAO catDao = new CategoryDAO();
                    ArrayList<CategoryDTO> catList = catDao.getAllCategory();

                    request.setAttribute("CATEGORY_LIST", catList);

                    if (null == title && null == content) {
                        url = roadmap.get(CREATE_PAGE);
                        RequestDispatcher rd = request.getRequestDispatcher(url);
                        rd.forward(request, response);
                    } else {
                        if (null != header && header.contains("multipart/form-data")) { // When Image Exists
                            Part part = request.getPart("fileAttachment");
                            System.out.println(part.getSize());
                            if (part.getSize() >= 0 && part.getSize() <= 4194304) {
                                ArrayList<String> allowedFileType = new ArrayList<>(Arrays.asList("jpg", "png", "jpeg"));
                                String fileName = part.getSubmittedFileName();
                                if ("".equals(fileName)) {

                                } else {
                                    String[] fileTypeSplit = fileName.split("\\.");
                                    String fileType = fileTypeSplit[fileTypeSplit.length - 1];

                                    if (!allowedFileType.contains(fileType)) {
                                        request.setAttribute("ERROR_UPLOAD", "You can only upload .jpg, .png, .jpeg");
                                        foundErr = true;
                                    } else {
                                        InputStream data = part.getInputStream();
                                        base64Image = ImageUtils.BytesToBase64(ImageUtils.InputStreamToBytes(data));
                                    }
                                }
//                        bytesImage = ImageUtils.InputStreamToBytes(data);
//                        base64Image = ImageUtils.BytesToBase64(bytesImage);
                            } else {
                                request.setAttribute("ERROR_UPLOAD", "Max file size is 4MB!");
                                foundErr = true;

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
                            RequestDispatcher rd = request.getRequestDispatcher(url);
                            rd.forward(request, response);
                        } else {
                            //4. Call DAO to insert to DB
                            Date postDate = new Date(Calendar.getInstance().getTime().getTime());
//                    String imageUrl = "UI/Icon/selfmademan.jpg";
                            String imageUrl = "UI/Icon/selfmademan.jpg";
                            if (null != base64Image) {
                                imageUrl = ImageUtils.uploadImage(base64Image);
                            }

                            content = Jsoup.clean(content, Safelist.relaxed());

                            BlogDTO blogDto = new BlogDTO(title, content, postDate, categoryID, "", tags, studentID, imageUrl);
                            int result = blogDao.createBlog(blogDto);
                            if (result > 0) {
                                url = roadmap.get(HOME_PAGE);
                                response.sendRedirect(url);
                            } else {
                                url = roadmap.get(CREATE_PAGE);
                                RequestDispatcher rd = request.getRequestDispatcher(url);
                                rd.forward(request, response);
                            }
                        }
                    }
                } else {
                    url = roadmap.get(CREATE_PAGE);
                    request.setAttribute("MAXBLOG", true);
                    RequestDispatcher rd = request.getRequestDispatcher(url);
                    rd.forward(request, response);
                }
            }

        } catch (SQLException ex) {
            String msg = ex.getMessage();
            log("CreateBlogServlet _ SQL " + msg);
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
