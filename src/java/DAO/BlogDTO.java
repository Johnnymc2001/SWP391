/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.io.Serializable;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Maru
 */
public class BlogDTO implements Serializable {

    private int blogID;
    private String title;
    private String content;
    private Date postDate;
    private String categoryID;
    private String status;
    private int mentorID;
    private Date approvedDate;
    private String note;
    private String tags;
    private int studentID;
    private String thumbnail;
    // for SQL 
    private int awardCount;
    private double avgRate;
    private int commentCount;

    // CONSTRUCTOR USE FOR SQL ONLY!
    public BlogDTO(int blogID, String title, String content, Date postDate, String categoryID, String status, int mentorID, Date approvedDate, String note, String tags, int studentID, String thumbnail, int awardCount, double avgRate, int commentCount) {
        this.blogID = blogID;
        this.title = title;
        this.content = content;
        this.postDate = postDate;
        this.categoryID = categoryID;
        this.status = status;
        this.mentorID = mentorID;
        this.approvedDate = approvedDate;
        this.note = note;
        this.tags = tags;
        this.studentID = studentID;
        this.thumbnail = thumbnail;
        // SQL ONLY
        this.awardCount = awardCount;
        this.avgRate = avgRate;
        this.commentCount = commentCount;
    }
    
    public BlogDTO(int blogID, String title, String content, Date postDate, String categoryID, String status, int mentorID, Date approvedDate, String note, String tags, int studentID, String thumbnail) {
        this.blogID = blogID;
        this.title = title;
        this.content = content;
        this.postDate = postDate;
        this.categoryID = categoryID;
        this.status = status;
        this.mentorID = mentorID;
        this.approvedDate = approvedDate;
        this.note = note;
        this.tags = tags;
        this.studentID = studentID;
        this.thumbnail = thumbnail;
    }

    public BlogDTO(String title, String content, Date postDate, String categoryID, String status, int mentorID, Date approvedDate, String note, String tags, int studentID, String thumbnail) {
        this.title = title;
        this.content = content;
        this.postDate = postDate;
        this.categoryID = categoryID;
        this.status = status;
        this.mentorID = mentorID;
        this.approvedDate = approvedDate;
        this.note = note;
        this.tags = tags;
        this.studentID = studentID;
        this.thumbnail = thumbnail;
    }

    public BlogDTO(String title, String content, Date postDate, String categoryID, String status, String note, String tags, int studentID, String thumbnail) {
        this.title = title;
        this.content = content;
        this.postDate = postDate;
        this.categoryID = categoryID;
        this.status = status;
        this.note = note;
        this.tags = tags;
        this.studentID = studentID;
        this.thumbnail = thumbnail;
    }

    public BlogDTO(String title, String content, Date postDate, String categoryID, String note, String tags, int studentID, String thumbnail) {
        this.title = title;
        this.content = content;
        this.postDate = postDate;
        this.categoryID = categoryID;
        status = "PENDING";
        this.note = note;
        this.tags = tags;
        this.studentID = studentID;
        this.thumbnail = thumbnail;
    }

    public BlogDTO() {
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public int getBlogID() {
        return blogID;
    }

    public void setBlogID(int blogID) {
        this.blogID = blogID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getMentorID() {
        return mentorID;
    }

    public void setMentorID(int mentorID) {
        this.mentorID = mentorID;
    }

    public Date getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(Date date) {
        this.approvedDate = date;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
   
    public ArrayList<BlogCommentDTO> getAllComments() {
        BlogCommentDAO blogDao = new BlogCommentDAO();
        try {
            return blogDao.getAllBlogCommentFromBlogID(this.blogID);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return new ArrayList<BlogCommentDTO>();
    }
    
    public AccountDTO getAccount() {
        AccountDAO accDao = new AccountDAO();
        try {
            return accDao.getAccountFromAcoountID(this.studentID);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    
    public int getAverageRating() throws SQLException {
        BlogDAO blogDao = new BlogDAO();
        return blogDao.getAverageRatingFromBlogID(this.blogID);
    }

    public int getAwardCount() {
        return awardCount;
    }

    public void setAwardCount(int awardCount) {
        this.awardCount = awardCount;
    }

    public double getAvgRate() {
        return avgRate;
    }

    public void setAvgRate(double avgRate) {
        this.avgRate = avgRate;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    
    @Override
    public String toString() {
        return "BlogDTO{" + "blogID=" + blogID + ", title=" + title + ", content=" + content + ", postDate=" + postDate + ", categoryID=" + categoryID + ", status=" + status + ", mentorID=" + mentorID + ", approvedDate=" + approvedDate + ", tags=" + tags + ", studentID=" + studentID + '}';
    }

}
