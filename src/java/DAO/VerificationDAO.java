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
import java.sql.Timestamp;

/**
 *
 * @author JohnnyMC
 */
public class VerificationDAO {

    public boolean AddVerification(VerificationDTO dto) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "INSERT INTO AccountVerfication VALUES(?, ?, ?)";

                stm = con.prepareStatement(sql);
                stm.setInt(1, dto.getAccountID());
                stm.setString(2, dto.getCode());
                stm.setTimestamp(3, dto.getTime());

                int line = stm.executeUpdate();

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

    public VerificationDTO GetAccountIdUsingCode(String code) throws SQLException {
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement stm = null;

        VerificationDTO dto = null;

        try {

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "SELECT accountID, verifyCode, time FROM AccountVerification WHERE verifyCode = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, code);
                rs = stm.executeQuery();

                while (rs.next()) {
                    int accountID = rs.getInt("accountID");
                    Timestamp time = rs.getTimestamp("time");
                    dto = new VerificationDTO(accountID, code, time);
                }
            }
        } finally {

            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return dto;
    }
    
     public VerificationDTO GetCodeUsingAccountId(int accountId) throws SQLException {
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement stm = null;

        VerificationDTO dto = null;

        try {

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "SELECT accountID, verifyCode, time FROM AccountVerification WHERE accountID = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, accountId);
                rs = stm.executeQuery();

                while (rs.next()) {
                    String code = rs.getString("verifyCode");
                    Timestamp time = rs.getTimestamp("time");
                    dto = new VerificationDTO(accountId, code, time);
                }
            }
        } finally {

            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return dto;
    }

    public boolean RemoveVerification(int accountId) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "DELETE FROM AccountVerification WHERE accountID = ?";

                stm = con.prepareStatement(sql);
                stm.setInt(1, accountId);
                int line = stm.executeUpdate();

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
}
