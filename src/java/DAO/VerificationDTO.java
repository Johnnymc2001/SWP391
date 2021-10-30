/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author JohnnyMC
 */
public class VerificationDTO implements Serializable {
    private int accountID;
    private String email;
    private String code;
    private Timestamp time;

    public VerificationDTO() {
    }

    public VerificationDTO(int accountID, String email, String code, Timestamp time) {
        this.accountID = accountID;
        this.email = email;
        this.code = code;
        this.time = time;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }   

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
