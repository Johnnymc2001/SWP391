/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Date;
import java.util.regex.Pattern;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class AccountError {

    private String userNameLengthError;
    private String userNameExistError;
    private String passwordLengthError;
    private String confirmNotMatched;
    private String fullNameLengthError;
    private String userNameExisted;
    private String accountIDExisted;
    private String phoneErrorFormat;
    private String emailErrorFormat;

    public String getUserNameLengthError() {
        return userNameLengthError;
    }

    public void setUserNameLengthError(String userNameLengthError) {
        this.userNameLengthError = userNameLengthError;
    }

    public String getPasswordLengthError() {
        return passwordLengthError;
    }

    public void setPasswordLengthError(String passwordLengthError) {
        this.passwordLengthError = passwordLengthError;
    }

    public String getConfirmNotMatched() {
        return confirmNotMatched;
    }

    public void setConfirmNotMatched(String confirmNotMatched) {
        this.confirmNotMatched = confirmNotMatched;
    }

    public String getFullNameLengthError() {
        return fullNameLengthError;
    }

    public void setFullNameLengthError(String fullNameLengthError) {
        this.fullNameLengthError = fullNameLengthError;
    }

    public String getUserNameExisted() {
        return userNameExisted;
    }

    public void setUserNameExisted(String userNameExisted) {
        this.userNameExisted = userNameExisted;
    }

    public String getAccountIDExisted() {
        return accountIDExisted;
    }

    public void setAccountIDExisted(String accountIDExisted) {
        this.accountIDExisted = accountIDExisted;
    }

    public String getPhoneErrorFormat() {
        return phoneErrorFormat;
    }

    public void setPhoneErrorFormat(String phoneErrorFormat) {
        this.phoneErrorFormat = phoneErrorFormat;
    }

    public String getEmailErrorFormat() {
        return emailErrorFormat;
    }

    public String getUserNameExistError() {
        return userNameExistError;
    }

    public void setUserNameExistError(String userNameExistError) {
        this.userNameExistError = userNameExistError;
    }

    public void setEmailErrorFormat(String emailErrorFormat) {
        this.emailErrorFormat = emailErrorFormat;
    }

    public AccountError(String userNameLengthError, String passwordLengthError, String confirmNotMatched, String fullNameLengthError, String userNameExisted, String accountIDExisted, String phone, String emailErrorFormat) {
        this.userNameLengthError = userNameLengthError;
        this.passwordLengthError = passwordLengthError;
        this.confirmNotMatched = confirmNotMatched;
        this.fullNameLengthError = fullNameLengthError;
        this.userNameExisted = userNameExisted;
        this.accountIDExisted = accountIDExisted;
        this.phoneErrorFormat = phone;
        this.emailErrorFormat = emailErrorFormat;
    }

    public AccountError() {
    }

    public boolean checkValidEmail(String email) {
        String regex = "([\\w\\d\\_\\-])+@[\\w]+\\.[\\w\\.]+";
        Pattern pat = Pattern.compile(regex);
        return pat.matcher(email).matches();
    }

    public boolean checkValidPhoneNumber(String phone) {
        String regex = "[\\d]{8,10}";
        Pattern pat = Pattern.compile(regex);
        return pat.matcher(phone).matches();
    }

}
