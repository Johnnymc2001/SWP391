/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dead;

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
    private String content;
    private byte[] data;

    public AttachmentDTO(int attachmentID, int blogID, String type, String content, byte[] data) {
        this.attachmentID = attachmentID;
        this.blogID = blogID;
        this.type = type;
        this.content = content;
        this.data = data;
    }

    public AttachmentDTO(int blogID, String type, String content, byte[] data) {
        this.blogID = blogID;
        this.type = type;
        this.content = content;
        this.data = data;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
    
    

}
