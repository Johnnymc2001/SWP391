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
public class AwardListDTO implements Serializable {

    private int awardListID;
    private int blogID;
    private int awardID;
    private Date date;
    private int awardBy;

    public AwardListDTO() {
    }

    public AwardListDTO(int awardListID, int blogID, int awardID, Date date, int awardBy) {
        this.awardListID = awardListID;
        this.blogID = blogID;
        this.awardID = awardID;
        this.date = date;
        this.awardBy = awardBy;
    }

    public AwardListDTO(int blogID, int awardID, Date date, int awardBy) {
        this.blogID = blogID;
        this.awardID = awardID;
        this.date = date;
        this.awardBy = awardBy;
    }

    public int getAwardListID() {
        return awardListID;
    }

    public void setAwardListID(int awardListID) {
        this.awardListID = awardListID;
    }

    public int getBlogID() {
        return blogID;
    }

    public void setBlogID(int blogID) {
        this.blogID = blogID;
    }

    public int getAwardID() {
        return awardID;
    }

    public void setAwardID(int awardID) {
        this.awardID = awardID;
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
