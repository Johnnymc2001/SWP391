/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import DAO.CategoryDAO;
import DAO.CategoryDTO;
import java.sql.SQLException;

/**
 *
 * @author JohnnyMC
 */
public class TestMain {
    public static void main(String[] args) {
        try {
            System.out.println(CategoryDAO.deleteCategory("XM"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
