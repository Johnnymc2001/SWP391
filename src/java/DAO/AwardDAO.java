package DAO;

import Utils.DBHelpers;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AwardDAO implements Serializable {

    public  boolean createAward(AwardDTO dto) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {

            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO Award (blogID, awardType, date, awardBy) "
                        + "VALUES (?, ?, ?, ?)";
                // 3. Create statement object
                stm = con.prepareStatement(sql);
                stm.setInt(1, dto.getBlogID());
                stm.setString(2, dto.getAwardType());
                stm.setDate(3, dto.getDate());
                stm.setInt(4, dto.getAwardBy());

                int line = stm.executeUpdate();

                return line > 0;
            } // End connection
        } finally {

        }
        return false;
    }

    public  AwardDTO getAwardFromAwardID(int awardId) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            AwardDTO dto = null;

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "SELECT awardID, blogID, awardType, date, awardBy "
                        + "FROM Award "
                        + "WHERE awardID = ? "
                        + "ORDER BY date DESC";

                stm = con.prepareStatement(sql);
                stm.setInt(1, awardId);

                rs = stm.executeQuery();

                while (rs.next()) {
                    int awardID = rs.getInt("awardID");
                    int blogID = rs.getInt("blogID");
                    String awardType = rs.getString("awardType");
                    Date date = rs.getDate("date");
                    int awardBy = rs.getInt("awardBy");
                    dto = new AwardDTO(awardID, blogID, awardType, date, awardBy);
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

    public  ArrayList<AwardDTO> getAwardFromBlogId(int blogId) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            ArrayList<AwardDTO> awardList = new ArrayList<AwardDTO>();

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "SELECT awardID, blogID, awardType, date, awardBy "
                        + "FROM Award "
                        + "WHERE blogID = ? "
                        + "ORDER BY date DESC";

                stm = con.prepareStatement(sql);
                stm.setInt(1, blogId);
                rs = stm.executeQuery();

                while (rs.next()) {
                    int awardID = rs.getInt("awardID");
                    int blogID = rs.getInt("blogID");
                    String awardType = rs.getString("awardType");
                    Date date = rs.getDate("date");
                    int awardBy = rs.getInt("awardBy");
                    AwardDTO dto = new AwardDTO(awardID, blogID, awardType, date, awardBy);
                    awardList.add(dto);
                }

                return awardList;
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

    /**
     * Get all blog in the database
     *
     * @return ArrayList<BlogDTO> if found, NULL if not found
     * @throws SQLException
     */
    public  ArrayList<AwardDTO> getAllAward() throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            ArrayList<AwardDTO> awardList = new ArrayList<AwardDTO>();

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "SELECT awardID, blogID, awardType, date, awardBy "
                        + "FROM Award "
                        + "ORDER BY date DESC";

                stm = con.prepareStatement(sql);

                rs = stm.executeQuery();

                while (rs.next()) {
                    int awardID = rs.getInt("awardID");
                    int blogID = rs.getInt("blogID");
                    String awardType = rs.getString("awardType");
                    Date date = rs.getDate("date");
                    int awardBy = rs.getInt("awardBy");
                    AwardDTO dto = new AwardDTO(awardID, blogID, awardType, date, awardBy);
                    awardList.add(dto);
                }

                return awardList;
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

    public  boolean updateAward(int awardId, AwardDTO dto) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            // 1. Connect DB
            con = DBHelpers.makeConnection();
            // 2. Create SQL String
            if (con != null) {
                String sql = "UPDATE Award "
                        + "SET blogID = ?, awardType = ?, date = ?, awardBy = ? "
                        + "WHERE awardID = ?";
                // 3. Create statement object
                stm = con.prepareStatement(sql);

                stm.setInt(1, dto.getBlogID());
                stm.setString(2, dto.getAwardType());
                stm.setDate(3, dto.getDate());
                stm.setInt(4, dto.getAwardBy());

                stm.setInt(5, awardId);
                int line = stm.executeUpdate();

                return line > 0;
            } // End connection
        } finally {

        }
        return false;
    }

    public  boolean deleteAward(int awardId) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            // 1. Connect DB
            con = DBHelpers.makeConnection();
            // 2. Create SQL String
            if (con != null) {
                String sql = "DELETE FROM Award "
                        + "WHERE awardID = ?";
                // 3. Create statement object
                stm = con.prepareStatement(sql);
                stm.setInt(1, awardId);
                int line = stm.executeUpdate();

                return line > 0;
            } // End connection
        } finally {

        }
        return false;
    }
}
