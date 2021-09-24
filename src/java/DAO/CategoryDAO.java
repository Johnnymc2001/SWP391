/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Utils.DBHelpers;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author JohnnyMC
 */
public class CategoryDAO {

    public  boolean createCategory(CategoryDTO dto) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        int line = 0;

        try {

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "INSERT INTO Category (categoryID, categoryName) "
                        + "VALUES (?, ?)";

                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getCategoryID());
                stm.setString(2, dto.getCategoryName());

                line = stm.executeUpdate();

                return line != 0;

            }
        } finally {

            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public  ArrayList<CategoryDTO> getAllCategory() throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            CategoryDTO dto = null;
            ArrayList<CategoryDTO> categoryList = new ArrayList<CategoryDTO>();

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "SELECT categoryID, categoryName "
                        + "FROM Category ";

                stm = con.prepareStatement(sql);

                rs = stm.executeQuery();

                while (rs.next()) {
                    String categoryID = rs.getString("categoryID");
                    String categoryName = rs.getString("categoryName");

                    dto = new CategoryDTO(categoryID, categoryName);
                    categoryList.add(dto);
                }

                return categoryList;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public  CategoryDTO getCategoryFromCategoryID(String categoryId) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            CategoryDTO dto = null;

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "SELECT categoryID, categoryName "
                        + "FROM Category "
                        + "WHERE categoryID = ?";

                stm = con.prepareStatement(sql);
                stm.setString(1, categoryId);

                rs = stm.executeQuery();

                while (rs.next()) {
                    String categoryID = rs.getString("categoryID");
                    String categoryName = rs.getString("categoryName");

                     dto = new CategoryDTO(categoryID, categoryName);
                }

                return dto;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public  boolean updateCategory(String categoryId, CategoryDTO dto) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            // 1. Connect DB
            con = DBHelpers.makeConnection();
            // 2. Create SQL String
            if (con != null) {
                String sql = "UPDATE Category "
                        + "SET categoryName = ?  "
                        + "WHERE categoryID = ?";
                // 3. Create statement object
                stm = con.prepareStatement(sql);

                stm.setString(1, dto.getCategoryName());

                stm.setString(2, dto.getCategoryID());
                int line = stm.executeUpdate();

                return line > 0;
            } // End connection
        } finally {

        }
        return false;
    }

    public  boolean deleteCategory(String categoryId) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            // 1. Connect DB
            con = DBHelpers.makeConnection();
            // 2. Create SQL String
            if (con != null) {
                String sql = "DELETE FROM Category "
                        + "WHERE categoryID = ?";
                // 3. Create statement object
                stm = con.prepareStatement(sql);
                stm.setString(1, categoryId);
                int line = stm.executeUpdate();

                return line > 0;
            } // End connection
        } finally {

        }
        return false;
    }
}
