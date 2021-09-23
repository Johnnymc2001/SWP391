/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dead;

import Utils.DBHelpers;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.annotation.MultipartConfig;

/**
 *
 * @author JohnnyMC
 */
@MultipartConfig
public class AttachmentDAO {

    public  boolean createAttachment(AttachmentDTO dto) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {

            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO Attachment (blogID, type, content, data) "
                        + "VALUES (?, ?, ?, ?)";
                // 3. Create statement object
                stm = con.prepareStatement(sql);
                stm.setInt(1, dto.getBlogID());
                stm.setString(2, dto.getType());
                stm.setString(3, dto.getContent());
                stm.setBytes(4, dto.getData());

                int line = stm.executeUpdate();

                return line > 0;
            } // End connection
        } finally {

        }
        return false;

    }

    public  ArrayList<AttachmentDTO> getAllAttachments() throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        ArrayList<AttachmentDTO> listAttachments = new ArrayList<>();
        try {

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "SELECT attachmentID, blogID, type, content, data "
                        + "FROM Attachment ";

                stm = con.prepareStatement(sql);

                rs = stm.executeQuery();

                while (rs.next()) {
                    int attachmentID = rs.getInt("attachmentID");
                    int blogID = rs.getInt("blogID");
                    String type = rs.getString("type");
                    String content = rs.getString("content");
                    byte[] data = rs.getBytes("data");
                    AttachmentDTO dto = new AttachmentDTO(attachmentID, blogID, type, content, data);
                    listAttachments.add(dto);
                }

                return listAttachments;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
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
    
   public  ArrayList<AttachmentDTO> getAllAttachmentsFromBlogID(int blogId) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        ArrayList<AttachmentDTO> listAttachments = new ArrayList<>();
        try {

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "SELECT attachmentID, blogID, type, content, data "
                        + "FROM Attachment "
                        + "WHERE blogID = ?";

                stm = con.prepareStatement(sql);
                stm.setInt(1, blogId);
                rs = stm.executeQuery();

                while (rs.next()) {
                    int attachmentID = rs.getInt("attachmentID");
                    int blogID = rs.getInt("blogID");
                    String type = rs.getString("type");
                    String content = rs.getString("content");
                    byte[] data = rs.getBytes("data");
                    AttachmentDTO dto = new AttachmentDTO(attachmentID, blogID, type, content, data);
                    listAttachments.add(dto);
                }

                return listAttachments;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
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
}
