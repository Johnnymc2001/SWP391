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
public class NotificationDTO implements Serializable {

    private int notificationID;
    private int ownerID;
    private boolean isRead;
    private String type;
    private String content;
    private Date date;
    private String redirectUrl;

    public NotificationDTO(int notificationID, int ownerID, boolean isRead, String type, String content, Date date, String redirectUrl) {
        this.notificationID = notificationID;
        this.ownerID = ownerID;
        this.isRead = isRead;
        this.type = type;
        this.content = content;
        this.date = date;
        this.redirectUrl = redirectUrl;
    }

    public NotificationDTO(int notificationID, int ownerID, boolean isRead, String type, String content, Date date) {
        this.notificationID = notificationID;
        this.ownerID = ownerID;
        this.isRead = isRead;
        this.type = type;
        this.content = content;
        this.date = date;
        this.redirectUrl = "";
    }

    public NotificationDTO(int ownerID, boolean isRead, String type, String content, Date date) {
        this.ownerID = ownerID;
        this.isRead = isRead;
        this.type = type;
        this.content = content;
        this.date = date;
        this.redirectUrl = "";
    }

    public NotificationDTO() {
    }

    public int getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(int notificationID) {
        this.notificationID = notificationID;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    public boolean isIsRead() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    @Override
    public String toString() {
        return "NotificationDTO{" + "notificationID=" + notificationID + ", ownerID=" + ownerID + ", isRead=" + isRead + ", type=" + type + ", content=" + content + ", date=" + date + ", redirectUrl=" + redirectUrl + '}';
    }

}
