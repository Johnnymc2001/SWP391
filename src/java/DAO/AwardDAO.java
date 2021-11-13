/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Utils.DBHelpers;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author JohnnyMC
 */
public class AwardDAO {

    public boolean createAward(AwardDTO dto) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {

            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO Award (awardName, effectiveDay) "
                        + "VALUES (?, ?)";
                // 3. Create statement object
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getAwardName());
                stm.setInt(2, dto.getEffectiveDay());

                int line = stm.executeUpdate();

                return line > 0;
            } // End connection
        } finally {

        }
        return false;
    }

    public AwardDTO getAwardFromAwardID(int awardId) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            AwardDTO dto = null;

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "SELECT awardID, awardName, effectiveDay "
                        + "FROM Award "
                        + "WHERE awardID = ? ";

                stm = con.prepareStatement(sql);
                stm.setInt(1, awardId);

                rs = stm.executeQuery();

                while (rs.next()) {
                    int awardID = rs.getInt("awardID");
                    String awardname = rs.getString("awardName");
                    int effectiveDay = rs.getInt("effectiveDay");
                    dto = new AwardDTO(awardID, awardname, effectiveDay);
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

    public ArrayList<AwardDTO> getAllAward() throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            ArrayList<AwardDTO> awardList = new ArrayList<AwardDTO>();

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "SELECT awardID, awardName, effectiveDay "
                        + "FROM Award ";

                stm = con.prepareStatement(sql);

                rs = stm.executeQuery();

                while (rs.next()) {
                    int awardID = rs.getInt("awardID");
                    String awardname = rs.getString("awardName");
                    int effectiveDay = rs.getInt("effectiveDay");
                    AwardDTO dto = new AwardDTO(awardID, awardname, effectiveDay);
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
    
    public ArrayList<AwardDTO> getAllAwardUnexpiredFromBlogID(int blogId) throws SQLException {
         Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            ArrayList<AwardDTO> awardList = new ArrayList<AwardDTO>();

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "SELECT awardID, awardName, effectiveDay "
                        + "FROM getAllUnexpiredRewardFromBlogId ";

                stm = con.prepareStatement(sql);

                rs = stm.executeQuery();

                while (rs.next()) {
                    int awardID = rs.getInt("awardID");
                    String awardname = rs.getString("awardName");
                    int effectiveDay = rs.getInt("effectiveDay");
                    AwardDTO dto = new AwardDTO(awardID, awardname, effectiveDay);
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

    public boolean updateAward(int awardId, AwardDTO dto) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            // 1. Connect DB
            con = DBHelpers.makeConnection();
            // 2. Create SQL String
            if (con != null) {
                String sql = "UPDATE Award "
                        + "SET awardName = ?, effectiveDay = ? "
                        + "WHERE awardID = ?";
                // 3. Create statement object
                stm = con.prepareStatement(sql);

                stm.setString(1, dto.getAwardName());
                stm.setInt(2, dto.getEffectiveDay());

                stm.setInt(3, awardId);
                int line = stm.executeUpdate();

                return line > 0;
            } // End connection
        } finally {

        }
        return false;
    }

    public boolean deleteAwardList(int awardId) throws SQLException {
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
