/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author JohnnyMC
 */
public class BlogCommentDTO implements Serializable {
    private int commentID;
    private int blogID;
    private Timestamp time;
    private String content;
    private int ownerID;

    public BlogCommentDTO(int commentID, int blogID, Timestamp time, String content, int ownerID) {
        this.commentID = commentID;
        this.blogID = blogID;
        this.time = time;
        this.content = content;
        this.ownerID = ownerID;
    }

    public BlogCommentDTO(int blogID, Timestamp time, String content, int ownerID) {
        this.blogID = blogID;
        this.time = time;
        this.content = content;
        this.ownerID = ownerID;
    }

    
    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public int getBlogID() {
        return blogID;
    }

    public void setBlogID(int blogID) {
        this.blogID = blogID;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }


    
}
