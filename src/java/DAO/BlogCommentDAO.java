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
public class BlogCommentDAO implements Serializable {

    public  boolean createBlogComment(BlogCommentDTO dto) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        int line = 0;

        try {

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "INSERT INTO BlogComment (blogID, date, content, ownerID) "
                        + "VALUES (?, ?, ? ,?)";

                stm = con.prepareStatement(sql);
                stm.setInt(1, dto.getBlogID());
                stm.setDate(2, dto.getDate());
                stm.setString(3, dto.getContent());
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

    public  ArrayList<BlogCommentDTO> getAllBlogComment() throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            BlogCommentDTO dto = null;
            ArrayList<BlogCommentDTO> commentList = new ArrayList<BlogCommentDTO>();

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "SELECT commentID, blogID, date, content, ownerID "
                        + "FROM BlogComment "
                        + "ORDER BY date DESC";
                stm = con.prepareStatement(sql);

                rs = stm.executeQuery();

                while (rs.next()) {
                    int commentID = rs.getInt("commentID");
                    int blogID = rs.getInt("blogID");
                    Date date = rs.getDate("date");
                    String content = rs.getString("content");
                    int ownerID = rs.getInt("ownerID");

                    dto = new BlogCommentDTO(commentID, blogID, date, content, ownerID);
                    commentList.add(dto);
                }

                return commentList;
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

    public  BlogCommentDTO getBlogCommentFromCommentID(int commentId) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            BlogCommentDTO dto = null;

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "SELECT commentID, blogID, date, content, ownerID "
                        + "FROM BlogComment "
                        + "WHERE commentID = ? "
                        + "ORDER BY date DESC";

                stm = con.prepareStatement(sql);
                stm.setInt(1, commentId);

                rs = stm.executeQuery();

                while (rs.next()) {
                    int commentID = rs.getInt("commentID");
                    int blogID = rs.getInt("blogID");
                    Date date = rs.getDate("date");
                    String content = rs.getString("content");
                    int ownerID = rs.getInt("ownerID");

                    dto = new BlogCommentDTO(commentID, blogID, date, content, ownerID);
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

    public  ArrayList<BlogCommentDTO> getAllBlogCommentFromBlogID(int blogId) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            BlogCommentDTO dto = null;
            ArrayList<BlogCommentDTO> commentList = new ArrayList<BlogCommentDTO>();

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "SELECT commentID, blogID, date, content, ownerID "
                        + "FROM BlogComment "
                        + "WHERE blogID = ? "
                        + "ORDER BY date DESC";

                stm = con.prepareStatement(sql);
                stm.setInt(1, blogId);

                rs = stm.executeQuery();

                while (rs.next()) {
                    int commentID = rs.getInt("commentID");
                    int blogID = rs.getInt("blogID");
                    Date date = rs.getDate("date");
                    String content = rs.getString("content");
                    int ownerID = rs.getInt("ownerID");

                    dto = new BlogCommentDTO(commentID, blogID, date, content, ownerID);
                    commentList.add(dto);
                }

                return commentList;
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

    public  boolean updateBlogComment(int commentId, BlogCommentDTO dto) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            // 1. Connect DB
            con = DBHelpers.makeConnection();
            // 2. Create SQL String
            if (con != null) {
                String sql = "UPDATE BlogComment "
                        + "SET blogID = ?, date = ?, content = ?, ownerID = ?  "
                        + "WHERE commentID = ?";
                // 3. Create statement object
                stm = con.prepareStatement(sql);

                stm.setInt(1, dto.getBlogID());
                stm.setDate(2, dto.getDate());
                stm.setString(3, dto.getContent());
                stm.setInt(4, dto.getOwnerID());

                stm.setInt(5, commentId);
                // 4. Execute Query
                int line = stm.executeUpdate();

                return line > 0;
            } // End connection
        } finally {

        }
        return false;
    }

    public  boolean deleteBlogComment(int commentId) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            // 1. Connect DB
            con = DBHelpers.makeConnection();
            // 2. Create SQL String
            if (con != null) {
                String sql = "DELETE FROM BlogComment "
                        + "WHERE commentID = ?";
                // 3. Create statement object
                stm = con.prepareStatement(sql);
                stm.setInt(1, commentId);
                // 4. Execute Query
                int line = stm.executeUpdate();

                return line > 0;
            } // End connection
        } finally {

        }
        return false;
    }

}
