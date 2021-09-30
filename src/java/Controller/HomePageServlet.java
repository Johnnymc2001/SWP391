/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.AttachmentDAO;
import DAO.AttachmentDTO;
import DAO.BlogDAO;
import DAO.BlogDTO;
import DAO.CategoryDAO;
import DAO.CategoryDTO;
import Utils.ImageUtils;
import static Utils.ImageUtils.BytesToBase64;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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
//            ArrayList<BlogDTO> blogList = blogDao.getAllAvailableBlog();

            CategoryDAO catDao = new CategoryDAO();
            ArrayList<CategoryDTO> catList = catDao.getAllCategory();

            HashMap<CategoryDTO, ArrayList<BlogDTO>> catToBlogMap = new HashMap<>();
            HashMap<BlogDTO, String> blogToImageMap = new HashMap<>();

            AttachmentDAO attDao = new AttachmentDAO();
            for (CategoryDTO catDto : catList) {
                ArrayList<BlogDTO> tempBlogList = blogDao.getAllAvailableBlogFromCategoryID(catDto.getCategoryID());
                if (tempBlogList.size() > 0) {
                    for (BlogDTO blog : tempBlogList) {
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
                                blogToImageMap.put(blog, base64Image);
                            }
                        }
                    }
                    catToBlogMap.put(catDto, tempBlogList);
                    System.out.println(tempBlogList.size());
                } else {
//                    list.put(catDto, null);
                }
            }

//            request.setAttribute("CATEGORY_LIST", catList);
//            request.setAttribute("BLOG_LIST", blogList);
            request.setAttribute("CAT_TO_BLOG_MAP", catToBlogMap);
            request.setAttribute("BLOG_TO_IMAGE_MAP", blogToImageMap);

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
