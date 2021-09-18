/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author JohnnyMC
 */
public class AwardDTO implements Serializable {

    private int awardID;
    private int blogID;
    private String awardType;
    private Date date;
    private int awardBy;

    public AwardDTO(int awardID, int blogID, String awardType, Date date, int awardBy) {
        this.awardID = awardID;
        this.blogID = blogID;
        this.awardType = awardType;
        this.date = date;
        this.awardBy = awardBy;
    }

    public AwardDTO(int blogID, String awardType, Date date, int awardBy) {
        this.blogID = blogID;
        this.awardType = awardType;
        this.date = date;
        this.awardBy = awardBy;
    }

    public AwardDTO() {
    }

    public int getAwardID() {
        return awardID;
    }

    public void setAwardID(int awardID) {
        this.awardID = awardID;
    }

    public int getBlogID() {
        return blogID;
    }

    public void setBlogID(int blogID) {
        this.blogID = blogID;
    }

    public String getAwardType() {
        return awardType;
    }

    public void setAwardType(String awardType) {
        this.awardType = awardType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getAwardBy() {
        return awardBy;
    }

    public void setAwardBy(int awardBy) {
        this.awardBy = awardBy;
    }

}
