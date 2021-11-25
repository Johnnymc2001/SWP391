/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.AccountDTO;
import DAO.BlogDAO;
import DAO.BlogDTO;
import DAO.CategoryDAO;
import DAO.CategoryDTO;
import Utils.ImageUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
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

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@WebServlet(name = "BlogEditServlet", urlPatterns = {"/BlogEditServlet"})
public class BlogEditServlet extends HttpServlet {

    private final String HOME_PAGE = "default";

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String txtBlogID = request.getParameter("txtBlogID");
//        System.out.println("current blogid : " + txtBlogID);
        ServletContext sc = request.getServletContext();
        HashMap<String, String> roadmap = (HashMap<String, String>) sc.getAttribute("ROADMAP");
        String header = request.getContentType();
        int blogID = 0;
        String url = "";
        String base64Image = null;
        boolean foundErr = false;
        String button = request.getParameter("btAction");
        System.out.println(button+"con de");
        HttpSession session = request.getSession(true);
        AccountDTO curUser = (AccountDTO) session.getAttribute("USER");

        if (null == curUser || !curUser.getRole().equals("Mentor") && !curUser.getRole().equals("Student")) {
            response.sendError(401);
            return;
        }

        CategoryDAO catDao = new CategoryDAO();

        if (null != curUser) {
//        System.out.println("button: " + button);
            if (null != txtBlogID) {
                blogID = Integer.parseInt(txtBlogID);
                BlogDAO blogDao = new BlogDAO();
                BlogDTO blogEdit = blogDao.getBlogFromBlogID(blogID);
                if (null == blogEdit) {
                    //dieu huong 404
                    response.sendError(404);
//                System.out.println("url tai blog edit servlet: " + url);
                } else {
                    if (blogEdit.getStudentID() == curUser.getAccountID()) {
                        if (button.equals("Edit")) {
                            request.setAttribute("BLOG_EDIT", blogEdit);

                            ArrayList<CategoryDTO> catList = catDao.getAllCategory();
                            request.setAttribute("CATEGORY_LIST", catList);
//                            System.out.println("blogEdit stdID: " + blogEdit.getStudentID());
                            url = roadmap.get("editPage");
                            RequestDispatcher rd = request.getRequestDispatcher(url);
                            rd.forward(request, response);
//                    System.out.println("url tai blog edit servlet: " + url);

                        } else if (button.equals("Update")) {
                            ArrayList<CategoryDTO> catList = catDao.getAllCategory();
                            request.setAttribute("CATEGORY_LIST", catList);

                            if (null != curUser) {
                                if (curUser.getAccountID() != blogEdit.getBlogID()) {
                                    request.setAttribute("INVALID_USER", "You can not edit this blog !!! ");
                                }
                            }

                            String title = request.getParameter("txtTitle");
                            String content = request.getParameter("txtContent");
//                            System.out.println("new content: " + content);
                            String categoryID = request.getParameter("categoryBox");
                            // IMAGE PARSING
//                    String tags = request.getParameter("txtTags");
                            if (null != header && header.contains("multipart/form-data")) { // When Image Exists
                                Part part = request.getPart("fileAttachment");
                                System.out.println(part.getSize());
                                if (part.getSize() >= 0 && part.getSize() < 4194304) {
                                    ArrayList<String> allowedFileType = new ArrayList<>(Arrays.asList("jpg", "png", "jpeg"));
                                    String fileName = part.getSubmittedFileName();
                                    String[] fileTypeSplit = fileName.split("\\.");
                                    String fileType = fileTypeSplit[fileTypeSplit.length - 1];

                                    if (fileName == "") {

                                    } else {
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

                            if (title.trim().length() < 6 || title.trim().length() > 60) {
                                foundErr = true;
                                request.setAttribute("ERROR_TITLE", "title is required from 6 to 60 characters");
                            }

                            if (content.trim().length() < 10) {
                                foundErr = true;
                                request.setAttribute("ERROR_CONTENT", "Content is required at least 10 characters");
                            }
                            if (!blogEdit.getStatus().equals("APPROVED") && !blogEdit.getStatus().equals("DISAPPROVED")) {
                                foundErr = true;
                                request.setAttribute("STATUS_ERROR", "You can not edit your blog due to blog status is not availible ");
                            }

                            if (foundErr) {
                                url = roadmap.get("editPage");
                                request.setAttribute("BLOG_EDIT", blogEdit);
//                            System.out.println("url tai blog edit servlet: " + url);
                                RequestDispatcher rd = request.getRequestDispatcher(url);
                                rd.forward(request, response);
                            } else {

                                content = Jsoup.clean(content, Safelist.relaxed());

                                //4. Call DAO to insert to DB
                                blogEdit.setContent(content);
                                blogEdit.setCategoryID(categoryID);
//                        blogEdit.setTags(tags);
                                blogEdit.setTitle(title);

                                if ("Mentor".equals(curUser.getRole()) && curUser.getCategoryID().equals(blogEdit.getCategoryID())) {

                                } else {
                                    blogEdit.setStatus("PENDING");
                                }

                                String imageUrl = "UI/Icon/selfmademan.jpg";

                                if (null != base64Image) {
                                    imageUrl = ImageUtils.uploadImageAndCrop(base64Image);
                                    blogEdit.setThumbnail(imageUrl);
                                }

                                boolean flag = blogDao.updateBlog(blogID, blogEdit);
                                System.out.println(flag);
                                url = "blog?txtBlogID=" + blogID;
                                response.sendRedirect(url);
//                            System.out.println("url tai blog edit servlet: " + url);
                            }
                            //ktrta rang buoc : id ton tai , title ,...  
                            // dieu huong sang blogDetail 
                            // loi : dieu huong jsp de hien loi
                        }
                    } else {
                        response.sendError(403);
                    }
                }
            } else {
                response.sendError(404);
            }
        } else {
            response.sendRedirect("home");
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
            Logger.getLogger(BlogEditServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(BlogEditServlet.class.getName()).log(Level.SEVERE, null, ex);
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
