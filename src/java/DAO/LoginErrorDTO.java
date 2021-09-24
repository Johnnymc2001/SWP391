/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class LoginErrorDTO {
 private String usernameNullError;
 private String usernameNotExist;
 private String passwordNullError;
 private String passwordNotExist;

    public String getUsernameNullError() {
        return usernameNullError;
    }

    public void setUsernameNullError(String usernameNullError) {
        this.usernameNullError = usernameNullError;
    }

    public String getUsernameNotExist() {
        return usernameNotExist;
    }

    public void setUsernameNotExist(String usernameNotExist) {
        this.usernameNotExist = usernameNotExist;
    }

    public String getPasswordNullError() {
        return passwordNullError;
    }

    public void setPasswordNullError(String passwordNullError) {
        this.passwordNullError = passwordNullError;
    }

    public String getPasswordNotExist() {
        return passwordNotExist;
    }

    public void setPasswordNotExist(String passwordNotExist) {
        this.passwordNotExist = passwordNotExist;
    }
 

 

    public LoginErrorDTO(String usernameNullError, String usernameNotExist, String passwordNullError, String passwordNotExist) {
        this.usernameNullError = usernameNullError;
        this.usernameNotExist = usernameNotExist;
        this.passwordNullError = passwordNullError;
        this.passwordNotExist = passwordNotExist;
    }

    public LoginErrorDTO() {
    }

   
 
 
 
 
}
