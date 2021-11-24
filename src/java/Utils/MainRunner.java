/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import DAO.AwardDAO;
import DAO.AwardListDAO;
import DAO.BlogDAO;
import DAO.BlogDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;

/**
 *
 * @author JohnnyMC
 */
public class MainRunner {

    public static void main(String[] args) {
        try {
//            BlogDAO blogDAO = new BlogDAO();
////            BlogDTO blogDTO = blogDAO.getBlogFromBlogID(87);
//            ArrayList<BlogDTO> blogList = blogDAO.getAllBlogs();
//            ArrayList<String> IDList = new ArrayList<String>();
//
//            Pattern r = Pattern.compile("(?:https:\\/\\/res.cloudinary.com\\/swpgogogo\\/image\\/upload\\/[\\s\\w\\d]+\\/attachments\\/)([\\d\\w]+)");
//
//            blogList.forEach(blog -> {
//                String thumb = blog.getThumbnail();
//                String content = blog.getContent();
//
//                Matcher m1 = r.matcher(content);
//                while (m1.find()) {
//                    IDList.add(m1.group(1));
//                }
//                
//                Matcher m2 = r.matcher(thumb);
//                while (m2.find()) {
//                    IDList.add(m2.group(1));
//                }
//
//            });
            ImageUtils.getAllImage();
//            System.out.println(IDList);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
