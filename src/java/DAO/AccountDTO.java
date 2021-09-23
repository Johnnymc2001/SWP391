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
public class AccountDTO implements Serializable {

    private int accountID;
    private String username;
    private String password;
    private String fullname;
    private String address;
    private Date birthday;
    private String email;
    private String phone;
    private String role;
    private String categoryID;
    private String status;

    public AccountDTO() {
    }

    public AccountDTO(int accountID, String username, String password, String fullname, String address, Date birthday, String email, String phone, String role, String categoryID, String status) {
        this.accountID = accountID;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.address = address;
        this.birthday = birthday;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.categoryID = categoryID;
    }

    public AccountDTO(String username, String password, String fullname, String address, Date birthday, String email, String phone, String role, String categoryID, String status) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.address = address;
        this.birthday = birthday;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.categoryID = categoryID;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

}
