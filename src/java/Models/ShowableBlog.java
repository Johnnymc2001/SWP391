/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import DAO.AttachmentDTO;
import DAO.BlogDTO;
import java.util.ArrayList;

/**
 *
 * @author JohnnyMC
 */
public class ShowableBlog {
    private BlogDTO blog;
    ArrayList<AttachmentDTO> attachments;

    public ShowableBlog(BlogDTO blog, ArrayList<AttachmentDTO> attachments) {
        this.blog = blog;
        this.attachments = attachments;
    }

    public BlogDTO getBlog() {
        return blog;
    }

    public void setBlog(BlogDTO blog) {
        this.blog = blog;
    }

    public ArrayList<AttachmentDTO> getAttachments() {
        return attachments;
    }

    public void setAttachments(ArrayList<AttachmentDTO> attachments) {
        this.attachments = attachments;
    }


    
    
}
