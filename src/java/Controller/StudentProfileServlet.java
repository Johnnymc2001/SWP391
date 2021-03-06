/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.AccountDAO;
import DAO.AccountDTO;
import DAO.BlogDAO;
import DAO.BlogDTO;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;
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
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@WebServlet(name = "StudentProfileServlet", urlPatterns = {"/StudentProfileServlet"})
public class StudentProfileServlet extends HttpServlet {

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
        ServletContext sc = request.getServletContext();
        HashMap<String, String> roadmap = (HashMap<String, String>) sc.getAttribute("ROADMAP");

        String txtID = request.getParameter("userID");
        String username = request.getParameter("username");
        String fullname = request.getParameter("fullname");
        String address = request.getParameter("address");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String birthdate = request.getParameter("birthdate");
        String button = request.getParameter("btnAction");
        System.out.println("conm de" + birthdate);
        System.out.println(button + address);
        String url = "";
        int id = 0;
        java.sql.Date birthDateValue = null;
        boolean foundError = false;
        boolean updatePassword = false;

        BlogDAO blogdao = new BlogDAO();
        AccountDAO accDao = new AccountDAO();

        AccountDTO curUser = (AccountDTO) request.getSession().getAttribute("USER");

        AccountDTO profileAccount = null;

        try {
            if (null != txtID && !txtID.equals("")) {
                id = Integer.parseInt(txtID);
            }
            // Parse Profile Account From Parameter Or Current Account
            if (id != 0) {
                profileAccount = accDao.getAccountFromAcoountID(id);
            } else if (null != curUser) {
                profileAccount = curUser;
            }

            if (null != profileAccount) {
                if (button != null) {
                    if (button.equals("UpdateProfile") && (profileAccount.getAccountID() == curUser.getAccountID())) {
                        java.sql.Date presentDate = new Date(System.currentTimeMillis());

                        //Add constrain
                        if (fullname == null || fullname.matches("[\\sA-Za-z]{2,30}") == false) {
                            foundError = true;
                            request.setAttribute("FULL_NAME_ERROR", "Full Name must be from 2 - 30 characters (No Number Or Special Character!)");
                        }

                        if (null != password && !"".equals(password)) {
                            if (!password.trim().matches("^[\\d\\w\\@\\-\\_\\#\\$\\%\\^\\&\\*\\!\\(\\)]{8,20}$")) {
                                foundError = true;
                                request.setAttribute("PASSWORD_ERROR", "Password must be from 8-20 character");
                            } else if (confirmPassword == null || !confirmPassword.trim().equals(password.trim())) {
                                foundError = true;
                                request.setAttribute("PASSWORD_ERROR", "Confirm Password must be same!");
                            } else {
                                updatePassword = true;
                            }
                        }
                        if (null == address || !address.matches("[\\s\\d\\w\\\\.\\\\,]{4,100}")) {
                            foundError = true;
                            request.setAttribute("ADDRESS_ERROR", "Address must be from 4 - 100 characters");
                        }

                        if (phone == null || phone.matches("([0-9]){8,12}") == false) {
                            foundError = true;
                            request.setAttribute("PHONE_ERROR", "Phone Number  invalid ");
                        }
                        if (birthdate == null || "".equals(birthdate)) {
                            foundError = true;
                            request.setAttribute("BIRTHDATE_ERROR", "Birthdate is not valid");
                        } else {
                            birthDateValue = java.sql.Date.valueOf(birthdate);
                            if (birthDateValue.after(presentDate)) {
                                foundError = true;
                                request.setAttribute("BIRTHDATE_ERROR", "Birthdate is not valid");
                            }
                        }

                        if (foundError == false) {
                            profileAccount.setUsername(username);
                            if (updatePassword == true) {
                                profileAccount.setPassword(password);
                            }
                            profileAccount.setFullname(fullname);
                            profileAccount.setBirthday(birthDateValue);
                            profileAccount.setEmail(email);
                            profileAccount.setPhone(phone);
                            profileAccount.setAddress(address);
                            if (accDao.updateAccount(curUser.getAccountID(), profileAccount)) {
                                
                                HttpSession session = request.getSession();
                                session.setAttribute("USER", profileAccount);
                                if (updatePassword == true) {
                                    request.setAttribute("UPDATE_SUCCESS", "Profile & Password Updated Successfully!");
                                }else{
                                    request.setAttribute("UPDATE_SUCCESS", "Profile Updated Successfully!");
                                }
                            }
                            request.setAttribute("ACCOUNT", accDao.getAccountFromAcoountID(curUser.getAccountID()));

                            url = roadmap.get("profilePage");
                        } else {
                            request.setAttribute("ACCOUNT", accDao.getAccountFromAcoountID(curUser.getAccountID()));
                            url = roadmap.get("profilePage");
                        }

//                        System.out.println("update");
//                        java.sql.Date sqlDate;
//                        sqlDate = java.sql.Date.valueOf(birthdate);
//                        profileAccount.setUsername(username);
//                        profileAccount.setPassword(password);
//                        profileAccount.setFullname(fullname);
//                        profileAccount.setBirthday(sqlDate);
//                        profileAccount.setEmail(email);
//                        profileAccount.setPhone(phone);
//                        profileAccount.setAddress(address);
//                        accDao.updateAccount(curUser.getAccountID(), profileAccount);
//                        request.setAttribute("ACCOUNT", accDao.getAccountFromAcoountID(curUser.getAccountID()));
//                        url = roadmap.get("profilePage");
                    }
                } else {
                    request.setAttribute("ACCOUNT", profileAccount);
                    url = roadmap.get("profilePage");
                }

                ArrayList<BlogDTO> bloglist = blogdao.getAllBlogFromAccountId(profileAccount.getAccountID())
                        .stream().filter(Blog -> "APPROVED".equals(Blog.getStatus()))
                        .collect(Collectors.toCollection(ArrayList::new));

                request.setAttribute("BLOGLIST", bloglist);
                request.setAttribute("ACCOUNT", profileAccount);
//                    System.out.println("bloglist: " + bloglist.size());
//                    System.out.println("account :" + profileAccount.toString());

            } else {
                url = roadmap.get("home");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
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
