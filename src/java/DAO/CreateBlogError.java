/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class CreateBlogError implements Serializable{
    private String titleLengthErr;
    private String contentLengthErr;

    public CreateBlogError() {
    }

    public String getTitleLengthErr() {
        return titleLengthErr;
    }

    public void setTitleLengthErr(String titleLengthErr) {
        this.titleLengthErr = titleLengthErr;
    }

    public String getContentLengthErr() {
        return contentLengthErr;
    }

    public void setContentLengthErr(String contentLengthErr) {
        this.contentLengthErr = contentLengthErr;
    }


}

