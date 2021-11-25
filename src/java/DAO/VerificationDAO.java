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
                String sql = "INSERT INTO AccountVerification VALUES(?, ?, ?, ?, ?)";

                stm = con.prepareStatement(sql);
                stm.setInt(1, dto.getAccountID());
                stm.setString(2, dto.getEmail());
                stm.setString(3, dto.getCode());
                stm.setTimestamp(4, dto.getTime());
                stm.setString(5, dto.getType());

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

    public VerificationDTO GetVerificationDTOUsingCode(String code, String type) throws SQLException {
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement stm = null;

        VerificationDTO dto = null;

        try {

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "SELECT accountID, email, verifyCode, time, type FROM AccountVerification WHERE verifyCode = ? and type = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, code);
                stm.setString(2, type);

                rs = stm.executeQuery();

                while (rs.next()) {
                    int accountID = rs.getInt("accountID");
                    String email = rs.getString("email");
                    Timestamp time = rs.getTimestamp("time");
                    dto = new VerificationDTO(accountID, email, code, time, type);
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

    public VerificationDTO GetVerificationDTOUsingAccountID(int accountId, String type) throws SQLException {
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement stm = null;

        VerificationDTO dto = null;

        try {

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "SELECT accountID, email, verifyCode, time, type FROM AccountVerification WHERE accountID = ? and type = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, accountId);
                stm.setString(2, type);
                rs = stm.executeQuery();

                while (rs.next()) {
                    String code = rs.getString("verifyCode");
                    String email = rs.getString("email");
                    Timestamp time = rs.getTimestamp("time");
                    dto = new VerificationDTO(accountId, email, code, time, type);
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

    public VerificationDTO GetVerificationDTOUsingEmail(String email, String type) throws SQLException {
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement stm = null;

        VerificationDTO dto = null;

        try {

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "SELECT accountID, email, verifyCode, time, type FROM AccountVerification WHERE email = ? and type = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, type);
                rs = stm.executeQuery();

                while (rs.next()) {
                    int accountId = rs.getInt("accountID");
                    String code = rs.getString("verifyCode");
                    Timestamp time = rs.getTimestamp("time");
                    dto = new VerificationDTO(accountId, email, code, time, type);
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

    public boolean UpdateVerification(VerificationDTO dto) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "UPDATE AccountVerification SET email = ?, verifyCode = ?, time = ? WHERE accountID = ? and type = ? ";

                stm = con.prepareStatement(sql);

                stm.setString(1, dto.getEmail());
                stm.setString(2, dto.getCode());
                stm.setTimestamp(3, dto.getTime());
                stm.setInt(4, dto.getAccountID());
                stm.setString(5, dto.getType());

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

    public boolean RemoveVerification(int accountId, String type) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "DELETE FROM AccountVerification WHERE accountID = ? and type = ?";

                stm = con.prepareStatement(sql);
                stm.setInt(1, accountId);
                stm.setString(2, type);
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
