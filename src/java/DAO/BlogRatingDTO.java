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
public class BlogRatingDTO implements Serializable{
       private int ratingID;
       private int blogID;
       private Date date;
       private double rate;
       private int ownerID;

    public BlogRatingDTO(int ratingID, int blogID, Date date, double rate, int ownerID) {
        this.ratingID = ratingID;
        this.blogID = blogID;
        this.date = date;
        this.rate = rate;
        this.ownerID = ownerID;
    }

    public BlogRatingDTO(int blogID, Date date, double rate, int ownerID) {
        this.blogID = blogID;
        this.date = date;
        this.rate = rate;
        this.ownerID = ownerID;
    }

    public int getRatingID() {
        return ratingID;
    }

    public void setRatingID(int ratingID) {
        this.ratingID = ratingID;
    }

    public int getBlogID() {
        return blogID;
    }

    public void setBlogID(int blogID) {
        this.blogID = blogID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    public BlogRatingDTO() {
    }
       
       
       
}
