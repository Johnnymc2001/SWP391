/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MentorController;

import DAO.AccountDAO;
import DAO.AccountDTO;
import DAO.AwardDAO;
import DAO.AwardDTO;
import DAO.AwardListDAO;
import DAO.AwardListDTO;
import DAO.BlogDAO;
//import DAO.BlogDTO;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
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

/**
 *
 * @author Admin
 */
@WebServlet(name = "MentorAwardServlet", urlPatterns = {"/MentorAwardServlet"})
public class MentorAwardServlet extends HttpServlet {

    private final String AWARD_PAGE = "awardPage";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        ServletContext sc = request.getServletContext();
        HashMap<String, String> roadmap = (HashMap<String, String>) sc.getAttribute("ROADMAP");
        String url = null;
        String txtBlogID = request.getParameter("txtBlogID");
        String txtAwardID = request.getParameter("txtAwardID");
        String txtAwardListID = request.getParameter("txtAwardListID");
        String Action = request.getParameter("btnAction");
        String txtEffectiveDays = request.getParameter("txtEffectiveDays");
        String txtAwardName = request.getParameter("txtAwardName");

        int EffectiveDays = 0;
        int blogID = 0;
        int awardID = 0;
        int awardListID = 0;

        try {

            HttpSession session = request.getSession();
            AccountDTO account = (AccountDTO) session.getAttribute("USER");
            if (null == account || !account.getRole().equals("Mentor")) {
                response.sendError(401);
                return;
            }
            url = roadmap.get(AWARD_PAGE);
            AwardListDAO ALdao = new AwardListDAO();
            AwardListDTO ALdto = new AwardListDTO();
            blogID = Integer.parseInt(txtBlogID);
            BlogDAO Bdao = new BlogDAO();
//            BlogDTO Bdto = new BlogDTO();
            AwardDAO Adao = new AwardDAO();
            AwardDTO Adto = new AwardDTO();

            if (null != txtAwardID) {
                awardID = Integer.parseInt(txtAwardID);
            } else {
                System.out.println("Award ID NULL!");
            }
            if (null != txtAwardListID) {
                awardListID = Integer.parseInt(txtAwardListID);
            } else {
                System.out.println("Award ID Remove NULL!");
            }
            if (null != txtEffectiveDays) {
                EffectiveDays = Integer.parseInt(txtEffectiveDays);
            } else {
                System.out.println("txtEffectiveDays NULL");
            }

            if (null != Action && Action.equals("Create Award")) {
                Adto = new AwardDTO(txtAwardName, EffectiveDays);
                boolean error = true;
                for (AwardDTO adto : Adao.getAllAward()) {
                    if (adto.getAwardName().equals(txtAwardName)) {
                        error = false;
                        request.setAttribute("ERROR_AWARD_NAME", "Award name already exist!");
                    }
                    if (awardID < 1) {
                        error = false;
                        request.setAttribute("ERROR_EFFECTIVE", "Effective days should be longer than 0 days!");
                    }
                }
                if (error) {
                    Adao.createAward(Adto);
                    request.setAttribute("CREATE_SUCCESS", "Award name created successfully!");
                }
            } else if (null != Action && Action.equals("Award Blog")) {
                Date date = new Date(Calendar.getInstance().getTime().getTime());
                ALdto = new AwardListDTO(blogID, awardID, date, account.getAccountID());
                ALdao.createAwardList(ALdto);
                request.setAttribute("BLOGAWARD", ALdao.getAwardListFromBlogId(blogID));

                response.sendRedirect("award?txtBlogID=" + blogID);
                return;
            } else if (null != Action && Action.equals("Remove Award")) {
                System.out.println("MentorAward award removed " + awardListID);

                if (ALdao.deleteAwardList(awardListID)) {
                    request.setAttribute("BLOGAWARD", ALdao.getAwardListFromBlogId(blogID));
                    response.sendRedirect("award?txtBlogID=" + blogID);
                    return;
                } else {
                    System.out.println("Delete Fail");
                }

            }
            if (null != txtBlogID) {
                request.setAttribute("ALL_AWARD", Adao.getAllAward());
                request.setAttribute("BLOG", Bdao.getBlogFromBlogID(blogID));
                request.setAttribute("BLOGAWARD", ALdao.getAwardListFromBlogId(blogID));
                request.setAttribute("MENTORLIST", new AccountDAO().getAllAccountFromRole("Mentor"));
            } else {
                System.out.println("Blog ID NULL!");
            }
        } catch (SQLException ex) {
            String msg = ex.getMessage();
            log("AwardServlet _ SQL " + msg);
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
            Logger.getLogger(MentorAwardServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(MentorAwardServlet.class.getName()).log(Level.SEVERE, null, ex);
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
