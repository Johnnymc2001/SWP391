/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Utils.DBHelpers;
import java.io.Serializable;
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
public class BlogRatingDAO implements Serializable {

    public boolean createBlogRating(BlogRatingDTO dto) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        int line = 0;

        try {

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "INSERT INTO BlogRating (blogID, date, rate, ownerID) "
                        + "VALUES (?, ? ,?, ?)";

                stm = con.prepareStatement(sql);
                stm.setInt(1, dto.getBlogID());
                stm.setDate(2, dto.getDate());
                stm.setDouble(3, dto.getRate());
                stm.setInt(4, dto.getOwnerID());

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

    public ArrayList<BlogRatingDTO> getAllBlogRating() throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            BlogRatingDTO dto = null;
            ArrayList<BlogRatingDTO> blogRatingList = new ArrayList<BlogRatingDTO>();

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "SELECT ratingID, blogID, date, rate, ownerID "
                        + "FROM BlogRating "
                        + "ORDER BY date DESC";

                stm = con.prepareStatement(sql);

                rs = stm.executeQuery();

                while (rs.next()) {
                    int ratingID = rs.getInt("ratingId");
                    int blogID = rs.getInt("blogID");
                    Date date = rs.getDate("date");
                    double rate = rs.getDouble("rate");
                    int ownerID = rs.getInt("ownerID");

                    dto = new BlogRatingDTO(ratingID, blogID, date, rate, ownerID);
                    blogRatingList.add(dto);
                }

                return blogRatingList;
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

    public BlogRatingDTO getBlogRatingFromRatingID(int ratingId) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            BlogRatingDTO dto = null;

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "SELECT ratingID, blogID, date, rate, ownerID "
                        + "FROM BlogRating "
                        + "WHERE ratingId = ? "
                        + "ORDER BY date DESC";

                stm = con.prepareStatement(sql);
                stm.setInt(1, ratingId);

                rs = stm.executeQuery();

                while (rs.next()) {
                    int ratingID = rs.getInt("ratingId");
                    int blogID = rs.getInt("blogID");
                    Date date = rs.getDate("date");
                    double rate = rs.getDouble("rate");
                    int ownerID = rs.getInt("ownerID");

                    dto = new BlogRatingDTO(ratingID, blogID, date, rate, ownerID);
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

    public ArrayList<BlogRatingDTO> getAllBlogRatingFromBlogID(int blogId) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            BlogRatingDTO dto = null;
            ArrayList<BlogRatingDTO> blogRatingList = new ArrayList<BlogRatingDTO>();

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "SELECT ratingID, blogID, date, rate, ownerID "
                        + "FROM BlogRating "
                        + "WHERE blogID = ? "
                        + "ORDER BY date DESC";

                stm = con.prepareStatement(sql);
                stm.setInt(1, blogId);

                rs = stm.executeQuery();

                while (rs.next()) {
                    int ratingID = rs.getInt("ratingId");
                    int blogID = rs.getInt("blogID");
                    Date date = rs.getDate("date");
                    double rate = rs.getDouble("rate");
                    int ownerID = rs.getInt("ownerID");

                    dto = new BlogRatingDTO(ratingID, blogID, date, rate, ownerID);
                    blogRatingList.add(dto);
                }

                return blogRatingList;
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
    
    public BlogRatingDTO getBlogRatingFromBlogIDAndOwnerID(int blogId, int ownerId) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            BlogRatingDTO dto = null;

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "SELECT ratingID, blogID, date, rate, ownerID "
                        + "FROM BlogRating "
                        + "WHERE blogID = ? and ownerID = ? "
                        + "ORDER BY date DESC";

                stm = con.prepareStatement(sql);
                stm.setInt(1, blogId);
                stm.setInt(2, ownerId);

                rs = stm.executeQuery();

                while (rs.next()) {
                    int ratingID = rs.getInt("ratingId");
                    int blogID = rs.getInt("blogID");
                    Date date = rs.getDate("date");
                    double rate = rs.getDouble("rate");
                    int ownerID = rs.getInt("ownerID");

                    dto = new BlogRatingDTO(ratingID, blogID, date, rate, ownerID);
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

    public boolean updateBlogRating(int ratingId, BlogRatingDTO dto) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            // 1. Connect DB
            con = DBHelpers.makeConnection();
            // 2. Create SQL String
            if (con != null) {
                String sql = "UPDATE BlogRating "
                        + "SET blogID = ?, date = ?, rate = ?, ownerID = ?  "
                        + "WHERE ratingID = ?";
                // 3. Create statement object
                stm = con.prepareStatement(sql);

                stm.setInt(1, dto.getBlogID());
                stm.setDate(2, dto.getDate());
                stm.setDouble(3, dto.getRate());
                stm.setInt(4, dto.getOwnerID());

                stm.setInt(5, ratingId);
                int line = stm.executeUpdate();

                return line > 0;
            } // End connection
        } finally {

        }
        return false;
    }

    public boolean deleteBlogRating(int ratingId) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            // 1. Connect DB
            con = DBHelpers.makeConnection();
            // 2. Create SQL String
            if (con != null) {
                String sql = "DELETE FROM BlogRating "
                        + "WHERE ratingID = ?";
                // 3. Create statement object
                stm = con.prepareStatement(sql);
                stm.setInt(1, ratingId);
                int line = stm.executeUpdate();

                return line > 0;
            } // End connection
        } finally {

        }
        return false;
    }
}
