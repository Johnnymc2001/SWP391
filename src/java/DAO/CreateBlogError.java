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
    private String categoryErr;

    public CreateBlogError() {
    }
    
}

