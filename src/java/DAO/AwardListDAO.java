package DAO;

import Utils.DBHelpers;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AwardListDAO implements Serializable {

    public boolean createAwardList(AwardListDTO dto) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {

            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO AwardList (blogID, awardID, date, awardBy) "
                        + "VALUES (?, ?, ?, ?)";
                // 3. Create statement object
                stm = con.prepareStatement(sql);
                stm.setInt(1, dto.getBlogID());
                stm.setInt(2, dto.getAwardID());
                stm.setDate(3, dto.getDate());
                stm.setInt(4, dto.getAwardBy());

                int line = stm.executeUpdate();

                return line > 0;
            } // End connection
        } finally {

        }
        return false;
    }

    public AwardListDTO getAwardListFromAwardListID(int awardListId) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            AwardListDTO dto = null;

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "SELECT awardListID, blogID, awardID, date, awardBy "
                        + "FROM AwardList "
                        + "WHERE awardListID = ? ";

                stm = con.prepareStatement(sql);
                stm.setInt(1, awardListId);

                rs = stm.executeQuery();

                while (rs.next()) {
                    int awardListID = rs.getInt("awardListID");
                    int blogID = rs.getInt("blogID");
                    int awardID = rs.getInt("awardID");
                    Date date = rs.getDate("date");
                    int awardBy = rs.getInt("awardBy");
                    dto = new AwardListDTO(awardListID, blogID, awardID, date, awardBy);
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

    public ArrayList<AwardListDTO> getAwardListFromBlogId(int blogId) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            ArrayList<AwardListDTO> awardList = new ArrayList<AwardListDTO>();

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "SELECT awardListID, blogID, awardID, date, awardBy "
                        + "FROM AwardList "
                        + "WHERE blogID = ? "
                        + "ORDER BY date DESC";

                stm = con.prepareStatement(sql);
                stm.setInt(1, blogId);
                rs = stm.executeQuery();

                while (rs.next()) {
                    int awardListID = rs.getInt("awardListID");
                    int blogID = rs.getInt("blogID");
                    int awardID = rs.getInt("awardID");
                    Date date = rs.getDate("date");
                    int awardBy = rs.getInt("awardBy");
                    AwardListDTO dto = new AwardListDTO(awardListID, blogID, awardID, date, awardBy);
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

    public ArrayList<AwardListDTO> getAllAwardList() throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            ArrayList<AwardListDTO> awardList = new ArrayList<AwardListDTO>();

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "SELECT awardListID, blogID, awardID, date, awardBy "
                        + "FROM AwardList "
                        + "ORDER BY date DESC";

                stm = con.prepareStatement(sql);

                rs = stm.executeQuery();

                while (rs.next()) {
                    int awardListID = rs.getInt("awardListID");
                    int blogID = rs.getInt("blogID");
                    int awardID = rs.getInt("awardID");
                    Date date = rs.getDate("date");
                    int awardBy = rs.getInt("awardBy");
                    AwardListDTO dto = new AwardListDTO(awardListID, blogID, awardID, date, awardBy);
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

    public boolean updateAwardList(int awardListId, AwardListDTO dto) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            // 1. Connect DB
            con = DBHelpers.makeConnection();
            // 2. Create SQL String
            if (con != null) {
                String sql = "UPDATE AwardList "
                        + "SET blogID = ?, awardID = ?, date = ?, awardBy = ? "
                        + "WHERE awardListID = ?";
                // 3. Create statement object
                stm = con.prepareStatement(sql);

                stm.setInt(1, dto.getBlogID());
                stm.setInt(2, dto.getAwardID());
                stm.setDate(3, dto.getDate());
                stm.setInt(4, dto.getAwardBy());

                stm.setInt(5, awardListId);
                int line = stm.executeUpdate();

                return line > 0;
            } // End connection
        } finally {

        }
        return false;
    }

    public boolean deleteAwardList(int awardListId) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            // 1. Connect DB
            con = DBHelpers.makeConnection();
            // 2. Create SQL String
            if (con != null) {
                String sql = "DELETE FROM AwardList "
                        + "WHERE awardListID = ?";
                // 3. Create statement object
                stm = con.prepareStatement(sql);
                stm.setInt(1, awardListId);
                int line = stm.executeUpdate();

                return line > 0;
            } // End connection
        } finally {

        }
        return false;
    }
}
