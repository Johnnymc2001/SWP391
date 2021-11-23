/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import DAO.AwardDAO;
import DAO.AwardListDAO;
import java.sql.SQLException;

/**
 *
 * @author JohnnyMC
 */
public class MainRunner {

    public static void main(String[] args) {
        AwardListDAO dao = new AwardListDAO();
        try {
            dao.deleteExpiredAwardFromAllBlog();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
