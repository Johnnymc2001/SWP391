/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Utils.ImageUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author JohnnyMC
 */
@MultipartConfig
@WebServlet(name = "ImageUploadServlet", urlPatterns = {"/ImageUploadServlet"})
public class ImageUploadServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        String header = request.getContentType();
        AttachmentDAO dao = new AttachmentDAO();

        try {
            if (header.contains("multipart/form-data")) { // When Image Exists
                Part part = request.getPart("file");
                if (part != null) {
                    InputStream data = part.getInputStream();
                    System.out.println("UPLOAD IMAGE");

                    int blogID = 2;
                    String type = "IMAGE";
                    String content = "";
                    byte[] bytesImage = ImageUtils.InputStreamToBytes(data);

                    AttachmentDTO dto = new AttachmentDTO(blogID, type, content, bytesImage);

                    dao.createAttachment(dto);
                }
            }

            System.out.println("GET IMAGE");
            ArrayList<AttachmentDTO> list = dao.getAllAttachments();
            ArrayList<String> base64 = new ArrayList<>();

            for (AttachmentDTO dto : list) {
                System.out.println(dto.getData());
                String b64 = ImageUtils.BytesToBase64(dto.getData());
                base64.add(b64);
            };

//            byte[] b = list.get(1).getData();
//            String b64 = Base64.getEncoder().encodeToString(b);
//            base64.add(b64);
            System.out.println(base64.size());

            request.setAttribute("IMAGE", base64);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            request.getRequestDispatcher("image.jsp").forward(request, response);
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
