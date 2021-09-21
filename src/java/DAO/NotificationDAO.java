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
public class NotificationDAO implements Serializable {

    public  boolean createNotification(NotificationDTO dto) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "INSERT INTO Notification (ownerID, isRead, type, content, date) "
                        + "VALUES (?, ? ,?, ?, ?)";

                stm = con.prepareStatement(sql);
                stm.setInt(1, dto.getOwnerID());
                stm.setBoolean(2, dto.isIsRead());
                stm.setString(3, dto.getType());
                stm.setString(4, dto.getContent());
                stm.setDate(5, dto.getDate());

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

    public  ArrayList<NotificationDTO> getAllNotification() throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            NotificationDTO dto = null;
            ArrayList<NotificationDTO> notificationList = new ArrayList<NotificationDTO>();

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "SELECT notificationID, ownerID, isRead, type, content, date "
                        + "FROM Notification ";

                stm = con.prepareStatement(sql);

                rs = stm.executeQuery();

                while (rs.next()) {
                    int notificationID = rs.getInt("notificationID");
                    int ownerID = rs.getInt("ownerID");
                    boolean isRead = rs.getBoolean("isRead");
                    String type = rs.getString("type");
                    String content = rs.getString("content");
                    Date date = rs.getDate("date");

                    dto = new NotificationDTO(notificationID, ownerID, isRead, type, content, date);
                    notificationList.add(dto);
                }

                return notificationList;
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

    public  ArrayList<NotificationDTO> getAllNotificationFromAccountID(int accountId) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            NotificationDTO dto = null;
            ArrayList<NotificationDTO> notificationList = new ArrayList<NotificationDTO>();

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "SELECT notificationID, ownerID, isRead, type, content, date "
                        + "FROM Notification "
                        + "WHERE ownerID = ?";

                stm = con.prepareStatement(sql);
                stm.setInt(1, accountId);

                rs = stm.executeQuery();

                while (rs.next()) {
                    int notificationID = rs.getInt("notificationID");
                    int ownerID = rs.getInt("ownerID");
                    boolean isRead = rs.getBoolean("isRead");
                    String type = rs.getString("type");
                    String content = rs.getString("content");
                    Date date = rs.getDate("date");

                    dto = new NotificationDTO(notificationID, ownerID, isRead, type, content, date);
                    notificationList.add(dto);
                }

                return notificationList;
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

    public  NotificationDTO getNotificationFromNotificationID(int notificationId) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            NotificationDTO dto = null;

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "SELECT notificationID, ownerID, isRead, type, content, date "
                        + "FROM Notification "
                        + "WHERE notificationID = ?";

                stm = con.prepareStatement(sql);
                stm.setInt(1, notificationId);

                rs = stm.executeQuery();

                while (rs.next()) {
                    int notificationID = rs.getInt("notificationID");
                    int ownerID = rs.getInt("ownerID");
                    boolean isRead = rs.getBoolean("isRead");
                    String type = rs.getString("type");
                    String content = rs.getString("content");
                    Date date = rs.getDate("date");

                    dto = new NotificationDTO(notificationID, ownerID, isRead, type, content, date);
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

    public  boolean updateNotification(int notificationId, NotificationDTO dto) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "UPDATE Notification "
                        + "SET ownerID = ?, isRead = ?, type = ?, content = ?, date = ? "
                        + "WHERE notificationID = ?";

                stm = con.prepareStatement(sql);

                stm.setInt(1, dto.getOwnerID());
                stm.setBoolean(2, dto.isIsRead());
                stm.setString(3, dto.getType());
                stm.setString(4, dto.getContent());
                stm.setDate(5, dto.getDate());

                stm.setInt(6, notificationId);

                int line = stm.executeUpdate();

                return line > 0;
            }
        } finally {

        }
        return false;
    }

    public  boolean deleteNotification(int notificationId) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "DELETE FROM Notification "
                        + "WHERE notificationID = ?";

                stm = con.prepareStatement(sql);
                stm.setInt(1, notificationId);

                int line = stm.executeUpdate();

                return line > 0;
            }
        } finally {

        }
        return false;
    }
}
