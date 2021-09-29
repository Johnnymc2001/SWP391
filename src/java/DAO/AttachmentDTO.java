/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import javax.servlet.annotation.MultipartConfig;


/**
 *
 * @author JohnnyMC
 */
@MultipartConfig
public class AttachmentDTO {

    private int attachmentID;
    private int blogID;
    private String type;
    private String dataText;
    private byte[] dataBinary;

    public AttachmentDTO(int attachmentID, int blogID, String type, String dataText, byte[] dataBinary) {
        this.attachmentID = attachmentID;
        this.blogID = blogID;
        this.type = type;
        this.dataText = dataText;
        this.dataBinary = dataBinary;
    }

    public AttachmentDTO(int blogID, String type, String dataText, byte[] dataBinary) {
        this.blogID = blogID;
        this.type = type;
        this.dataText = dataText;
        this.dataBinary = dataBinary;
    }

    public int getAttachmentID() {
        return attachmentID;
    }

    public void setAttachmentID(int attachmentID) {
        this.attachmentID = attachmentID;
    }

    public int getBlogID() {
        return blogID;
    }

    public void setBlogID(int blogID) {
        this.blogID = blogID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDataText() {
        return dataText;
    }

    public void setDataText(String dataText) {
        this.dataText = dataText;
    }

    public byte[] getDataBinary() {
        return dataBinary;
    }

    public void setDataBinary(byte[] dataBinary) {
        this.dataBinary = dataBinary;
    }
    
    
    

}
