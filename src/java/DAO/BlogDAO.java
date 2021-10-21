package DAO;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Utils.DBHelpers;
import java.sql.Date;
import java.sql.Statement;

public class BlogDAO implements Serializable {

    // List Blog By Specification
    public ArrayList<BlogDTO> getAllBlogFromAccountId(int accountId) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            ArrayList<BlogDTO> blogList = new ArrayList<BlogDTO>();

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "SELECT blogID, title, content, postDate, categoryID, status, approvedByID, approvedDate, tags, ownerID, thumbnail, thumbnail "
                        + "FROM Blog "
                        + "WHERE ownerID = ? "
                        + "ORDER BY postDate DESC";

                stm = con.prepareStatement(sql);
                stm.setInt(1, accountId);
                rs = stm.executeQuery();

                while (rs.next()) {
                    int blogID = rs.getInt("blogID");
                    String title = rs.getString("title");
                    String content = rs.getString("content");
                    Date postDate = rs.getDate("postDate");
                    String categoryID = rs.getString("categoryID");
                    String status = rs.getString("status");
                    int approvedByID = rs.getInt("approvedByID");
                    Date approvedDate = rs.getDate("approvedDate");
                    String tags = rs.getString("tags");
                    int ownerID = rs.getInt("ownerID");
                    String thumbnail = rs.getString("thumbnail");

                    BlogDTO dto = new BlogDTO(blogID, title, content, postDate, categoryID, status, approvedByID, approvedDate, tags, ownerID, thumbnail);
                    blogList.add(dto);
                }

                return blogList;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public ArrayList<BlogDTO> getAllBlogFromCategoryId(String categoryId) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            ArrayList<BlogDTO> blogList = new ArrayList<BlogDTO>();

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "SELECT blogID, title, content, postDate, categoryID, status, approvedByID, approvedDate, tags, ownerID, thumbnail, thumbnail "
                        + "FROM Blog "
                        + "WHERE categoryID = ? "
                        + "ORDER BY postDate DESC";

                stm = con.prepareStatement(sql);
                stm.setString(1, categoryId);
                rs = stm.executeQuery();

                while (rs.next()) {
                    int blogID = rs.getInt("blogID");
                    String title = rs.getString("title");
                    String content = rs.getString("content");
                    Date postDate = rs.getDate("postDate");
                    String categoryID = rs.getString("categoryID");
                    String status = rs.getString("status");
                    int approvedByID = rs.getInt("approvedByID");
                    Date approvedDate = rs.getDate("approvedDate");
                    String tags = rs.getString("tags");
                    int ownerID = rs.getInt("ownerID");
                    String thumbnail = rs.getString("thumbnail");

                    BlogDTO dto = new BlogDTO(blogID, title, content, postDate, categoryID, status, approvedByID, approvedDate, tags, ownerID, thumbnail);
                    blogList.add(dto);
                }

                return blogList;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public ArrayList<BlogDTO> getAllBlogLikeTitle(String searchTitle) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            ArrayList<BlogDTO> blogList = new ArrayList<BlogDTO>();

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "SELECT blogID, title, content, postDate, categoryID, status, approvedByID, approvedDate, tags, ownerID, thumbnail, thumbnail "
                        + "FROM Blog "
                        + "WHERE title LIKE ? "
                        + "ORDER BY postDate DESC";

                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchTitle + "%");
                rs = stm.executeQuery();

                while (rs.next()) {
                    int blogID = rs.getInt("blogID");
                    String title = rs.getString("title");
                    String content = rs.getString("content");
                    Date postDate = rs.getDate("postDate");
                    String categoryID = rs.getString("categoryID");
                    String status = rs.getString("status");
                    int approvedByID = rs.getInt("approvedByID");
                    Date approvedDate = rs.getDate("approvedDate");
                    String tags = rs.getString("tags");
                    int ownerID = rs.getInt("ownerID");
                    String thumbnail = rs.getString("thumbnail");

                    BlogDTO dto = new BlogDTO(blogID, title, content, postDate, categoryID, status, approvedByID, approvedDate, tags, ownerID, thumbnail);
                    blogList.add(dto);
                }

                return blogList;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public ArrayList<BlogDTO> getAllApprovedBlogLikeTitle(String searchTitle) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            ArrayList<BlogDTO> blogList = new ArrayList<BlogDTO>();

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "SELECT blogID, title, content, postDate, categoryID, status, approvedByID, approvedDate, tags, ownerID, thumbnail "
                        + "FROM Blog "
                        + "WHERE title LIKE ? AND status = ? "
                        + "ORDER BY postDate DESC";

                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchTitle + "%");
                stm.setString(2, "APPROVED");
                rs = stm.executeQuery();

                while (rs.next()) {
                    int blogID = rs.getInt("blogID");
                    String title = rs.getString("title");
                    String content = rs.getString("content");
                    Date postDate = rs.getDate("postDate");
                    String categoryID = rs.getString("categoryID");
                    String status = rs.getString("status");
                    int approvedByID = rs.getInt("approvedByID");
                    Date approvedDate = rs.getDate("approvedDate");
                    String tags = rs.getString("tags");
                    int ownerID = rs.getInt("ownerID");
                    String thumbnail = rs.getString("thumbnail");

                    BlogDTO dto = new BlogDTO(blogID, title, content, postDate, categoryID, status, approvedByID, approvedDate, tags, ownerID, thumbnail);
                    blogList.add(dto);
                }

                return blogList;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public ArrayList<BlogDTO> getAllBlogLikeTag(String searchTag) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            ArrayList<BlogDTO> blogList = new ArrayList<BlogDTO>();

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "SELECT blogID, title, content, postDate, categoryID, status, approvedByID, approvedDate, tags, ownerID, thumbnail "
                        + "FROM Blog "
                        + "WHERE tags LIKE ? "
                        + "ORDER BY postDate DESC";

                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchTag + "%");
                rs = stm.executeQuery();

                while (rs.next()) {
                    int blogID = rs.getInt("blogID");
                    String title = rs.getString("title");
                    String content = rs.getString("content");
                    Date postDate = rs.getDate("postDate");
                    String categoryID = rs.getString("categoryID");
                    String status = rs.getString("status");
                    int approvedByID = rs.getInt("approvedByID");
                    Date approvedDate = rs.getDate("approvedDate");
                    String tags = rs.getString("tags");
                    int ownerID = rs.getInt("ownerID");
                    String thumbnail = rs.getString("thumbnail");

                    BlogDTO dto = new BlogDTO(blogID, title, content, postDate, categoryID, status, approvedByID, approvedDate, tags, ownerID, thumbnail);
                    blogList.add(dto);
                }

                return blogList;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public ArrayList<BlogDTO> getAllBlogLikeTitleAndFromCategoryID(String searchTitle, String categoryId) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            ArrayList<BlogDTO> blogList = new ArrayList<BlogDTO>();

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "SELECT blogID, title, content, postDate, categoryID, status, approvedByID, approvedDate, tags, ownerID, thumbnail "
                        + "FROM Blog "
                        + "WHERE title LIKE ? AND categoryID = ? "
                        + "ORDER BY postDate DESC";

                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchTitle + "%");
                stm.setString(2, categoryId);
                rs = stm.executeQuery();

                while (rs.next()) {
                    int blogID = rs.getInt("blogID");
                    String title = rs.getString("title");
                    String content = rs.getString("content");
                    Date postDate = rs.getDate("postDate");
                    String categoryID = rs.getString("categoryID");
                    String status = rs.getString("status");
                    int approvedByID = rs.getInt("approvedByID");
                    Date approvedDate = rs.getDate("approvedDate");
                    String tags = rs.getString("tags");
                    int ownerID = rs.getInt("ownerID");
                    String thumbnail = rs.getString("thumbnail");

                    BlogDTO dto = new BlogDTO(blogID, title, content, postDate, categoryID, status, approvedByID, approvedDate, tags, ownerID, thumbnail);
                    blogList.add(dto);
                }

                return blogList;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    // Function
    public ArrayList<BlogDTO> getAllApprovedBlogWithMostAward(int maxBlog) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            ArrayList<BlogDTO> blogList = new ArrayList<BlogDTO>();

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "SELECT blogID, title, content, postDate, categoryID, status, approvedByID, approvedDate, tags, ownerID, thumbnail "
                        + "FROM getAllApprovedBlogWithMostAward(?) "
                        + "WHERE status = ? ";

                stm = con.prepareStatement(sql);
                stm.setInt(1, maxBlog);
                stm.setString(2, "APPROVED");
                rs = stm.executeQuery();

                while (rs.next()) {
                    int blogID = rs.getInt("blogID");
                    String title = rs.getString("title");
                    String content = rs.getString("content");
                    Date postDate = rs.getDate("postDate");
                    String categoryID = rs.getString("categoryID");
                    String status = rs.getString("status");
                    int approvedByID = rs.getInt("approvedByID");
                    Date approvedDate = rs.getDate("approvedDate");
                    String tags = rs.getString("tags");
                    int ownerID = rs.getInt("ownerID");
                    String thumbnail = rs.getString("thumbnail");

                    BlogDTO dto = new BlogDTO(blogID, title, content, postDate, categoryID, status, approvedByID, approvedDate, tags, ownerID, thumbnail);
                    blogList.add(dto);
                }

                return blogList;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public ArrayList<BlogDTO> getAllApprovedBlogWithHighestRating(int maxBlog) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            ArrayList<BlogDTO> blogList = new ArrayList<BlogDTO>();

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "SELECT blogID, title, content, postDate, categoryID, status, approvedByID, approvedDate, tags, ownerID, thumbnail "
                        + "FROM getAllApprovedBlogWithHighestRating(?) "
                        + "WHERE status = ? ";

                stm = con.prepareStatement(sql);
                stm.setInt(1, maxBlog);
                stm.setString(2, "APPROVED");
                rs = stm.executeQuery();

                while (rs.next()) {
                    int blogID = rs.getInt("blogID");
                    String title = rs.getString("title");
                    String content = rs.getString("content");
                    Date postDate = rs.getDate("postDate");
                    String categoryID = rs.getString("categoryID");
                    String status = rs.getString("status");
                    int approvedByID = rs.getInt("approvedByID");
                    Date approvedDate = rs.getDate("approvedDate");
                    String tags = rs.getString("tags");
                    int ownerID = rs.getInt("ownerID");
                    String thumbnail = rs.getString("thumbnail");

                    BlogDTO dto = new BlogDTO(blogID, title, content, postDate, categoryID, status, approvedByID, approvedDate, tags, ownerID, thumbnail);
                    blogList.add(dto);
                }

                return blogList;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public ArrayList<BlogDTO> getAllApprovedBlogWithMostAwardAndHighestRating(int maxBlog) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            ArrayList<BlogDTO> blogList = new ArrayList<BlogDTO>();

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "SELECT blogID, title, content, postDate, categoryID, status, approvedByID, approvedDate, tags, ownerID, thumbnail "
                        + "FROM getAllApprovedBlogWithMostAwardAndHighestRating(?) "
                        + "WHERE status = ? ";

                stm = con.prepareStatement(sql);
                stm.setInt(1, maxBlog);
                stm.setString(2, "APPROVED");
                rs = stm.executeQuery();

                while (rs.next()) {
                    int blogID = rs.getInt("blogID");
                    String title = rs.getString("title");
                    String content = rs.getString("content");
                    Date postDate = rs.getDate("postDate");
                    String categoryID = rs.getString("categoryID");
                    String status = rs.getString("status");
                    int approvedByID = rs.getInt("approvedByID");
                    Date approvedDate = rs.getDate("approvedDate");
                    String tags = rs.getString("tags");
                    int ownerID = rs.getInt("ownerID");
                    String thumbnail = rs.getString("thumbnail");

                    BlogDTO dto = new BlogDTO(blogID, title, content, postDate, categoryID, status, approvedByID, approvedDate, tags, ownerID, thumbnail);
                    blogList.add(dto);
                }

                return blogList;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public ArrayList<BlogDTO> getAllApprovedBlogWithHighestComment(int maxBlog) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            ArrayList<BlogDTO> blogList = new ArrayList<BlogDTO>();

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "SELECT blogID, title, content, postDate, categoryID, status, approvedByID, approvedDate, tags, ownerID, thumbnail "
                        + "FROM getAllApprovedBlogWithHighestComment(?) "
                        + "WHERE status = ? ";

                stm = con.prepareStatement(sql);
                stm.setInt(1, maxBlog);
                stm.setString(2, "APPROVED");
                rs = stm.executeQuery();

                while (rs.next()) {
                    int blogID = rs.getInt("blogID");
                    String title = rs.getString("title");
                    String content = rs.getString("content");
                    Date postDate = rs.getDate("postDate");
                    String categoryID = rs.getString("categoryID");
                    String status = rs.getString("status");
                    int approvedByID = rs.getInt("approvedByID");
                    Date approvedDate = rs.getDate("approvedDate");
                    String tags = rs.getString("tags");
                    int ownerID = rs.getInt("ownerID");
                    String thumbnail = rs.getString("thumbnail");

                    BlogDTO dto = new BlogDTO(blogID, title, content, postDate, categoryID, status, approvedByID, approvedDate, tags, ownerID, thumbnail);
                    blogList.add(dto);
                }

                return blogList;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }
    
    public int getAverageRatingFromBlogID(int blogID) throws SQLException {
          Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            ArrayList<BlogDTO> blogList = new ArrayList<BlogDTO>();

            con = DBHelpers.makeConnection();
            int rating = 0;
            if (con != null) {
                String sql = "SELECT blogID, ratingCount "
                        + "FROM getAverageRatingFromBlogID(?)";

                stm = con.prepareStatement(sql);
                stm.setInt(1, blogID);
                rs = stm.executeQuery();

                while (rs.next()) {
                    rating = rs.getInt("ratingCount"); 
                }

                return rating;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return 0;
    }

    // Blog With Status
    public ArrayList<BlogDTO> getAllBlogWithStatus(String s) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            ArrayList<BlogDTO> blogList = new ArrayList<BlogDTO>();

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "SELECT blogID, title, content, postDate, categoryID, status, approvedByID, approvedDate, tags, ownerID, thumbnail "
                        + "FROM Blog "
                        + "WHERE status = ? "
                        + "ORDER BY postDate DESC";

                stm = con.prepareStatement(sql);
                stm.setString(1, s);
                rs = stm.executeQuery();

                while (rs.next()) {
                    int blogID = rs.getInt("blogID");
                    String title = rs.getString("title");
                    String content = rs.getString("content");
                    Date postDate = rs.getDate("postDate");
                    String categoryID = rs.getString("categoryID");
                    String status = rs.getString("status");
                    int approvedByID = rs.getInt("approvedByID");
                    Date approvedDate = rs.getDate("approvedDate");
                    String tags = rs.getString("tags");
                    int ownerID = rs.getInt("ownerID");
                    String thumbnail = rs.getString("thumbnail");

                    BlogDTO dto = new BlogDTO(blogID, title, content, postDate, categoryID, status, approvedByID, approvedDate, tags, ownerID, thumbnail);
                    blogList.add(dto);
                }

                return blogList;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public ArrayList<BlogDTO> getAllBlogWithStatus(String s, int max) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            ArrayList<BlogDTO> blogList = new ArrayList<BlogDTO>();

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "SELECT TOP (?) "
                        + "blogID, title, content, postDate, categoryID, status, approvedByID, approvedDate, tags, ownerID, thumbnail "
                        + "FROM Blog "
                        + "WHERE status = ? "
                        + "ORDER BY postDate DESC";

                stm = con.prepareStatement(sql);
                stm.setInt(1, max);
                stm.setString(2, s);
                rs = stm.executeQuery();

                while (rs.next()) {
                    int blogID = rs.getInt("blogID");
                    String title = rs.getString("title");
                    String content = rs.getString("content");
                    Date postDate = rs.getDate("postDate");
                    String categoryID = rs.getString("categoryID");
                    String status = rs.getString("status");
                    int approvedByID = rs.getInt("approvedByID");
                    Date approvedDate = rs.getDate("approvedDate");
                    String tags = rs.getString("tags");
                    int ownerID = rs.getInt("ownerID");
                    String thumbnail = rs.getString("thumbnail");

                    BlogDTO dto = new BlogDTO(blogID, title, content, postDate, categoryID, status, approvedByID, approvedDate, tags, ownerID, thumbnail);
                    blogList.add(dto);
                }

                return blogList;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public ArrayList<BlogDTO> getAllBlogWithStatusFromCategoryId(String s, String catID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            ArrayList<BlogDTO> blogList = new ArrayList<BlogDTO>();

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "SELECT "
                        + "blogID, title, content, postDate, categoryID, status, approvedByID, approvedDate, tags, ownerID, thumbnail "
                        + "FROM Blog "
                        + "WHERE status = ? AND categoryID = ? "
                        + "ORDER BY postDate DESC";

                stm = con.prepareStatement(sql);
                stm.setString(1, s);
                stm.setString(2, catID);
                rs = stm.executeQuery();

                while (rs.next()) {
                    int blogID = rs.getInt("blogID");
                    String title = rs.getString("title");
                    String content = rs.getString("content");
                    Date postDate = rs.getDate("postDate");
                    String categoryID = rs.getString("categoryID");
                    String status = rs.getString("status");
                    int approvedByID = rs.getInt("approvedByID");
                    Date approvedDate = rs.getDate("approvedDate");
                    String tags = rs.getString("tags");
                    int ownerID = rs.getInt("ownerID");
                    String thumbnail = rs.getString("thumbnail");

                    BlogDTO dto = new BlogDTO(blogID, title, content, postDate, categoryID, status, approvedByID, approvedDate, tags, ownerID, thumbnail);
                    blogList.add(dto);
                }

                return blogList;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public ArrayList<BlogDTO> getAllBlogWithStatusFromCategoryId(String s, String catID, int max) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            ArrayList<BlogDTO> blogList = new ArrayList<BlogDTO>();

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "SELECT TOP (?) "
                        + "blogID, title, content, postDate, categoryID, status, approvedByID, approvedDate, tags, ownerID "
                        + "FROM Blog "
                        + "WHERE status = ? AND categoryID = ? "
                        + "ORDER BY postDate DESC";

                stm = con.prepareStatement(sql);
                stm.setInt(1, max);
                stm.setString(2, s);
                stm.setString(3, catID);
                rs = stm.executeQuery();

                while (rs.next()) {
                    int blogID = rs.getInt("blogID");
                    String title = rs.getString("title");
                    String content = rs.getString("content");
                    Date postDate = rs.getDate("postDate");
                    String categoryID = rs.getString("categoryID");
                    String status = rs.getString("status");
                    int approvedByID = rs.getInt("approvedByID");
                    Date approvedDate = rs.getDate("approvedDate");
                    String tags = rs.getString("tags");
                    int ownerID = rs.getInt("ownerID");
                    String thumbnail = rs.getString("thumbnail");

                    BlogDTO dto = new BlogDTO(blogID, title, content, postDate, categoryID, status, approvedByID, approvedDate, tags, ownerID, thumbnail);
                    blogList.add(dto);
                }

                return blogList;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    // Normal CRUD
    public int createBlog(BlogDTO dto) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            // 1. Connect DB
            con = DBHelpers.makeConnection();
            // 2. Create SQL String
            if (con != null) {
                String sql = "INSERT INTO Blog (title, content, postDate, categoryID, status, tags, ownerID, thumbnail) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                // 3. Create statement object
                stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                stm.setString(1, dto.getTitle());
                stm.setString(2, dto.getContent());
                stm.setDate(3, dto.getPostDate());
                stm.setString(4, dto.getCategoryID());
                stm.setString(5, dto.getStatus());
                stm.setString(6, dto.getTags());
                stm.setInt(7, dto.getStudentID());
                stm.setString(8, dto.getThumbnail());

                int line = stm.executeUpdate();

                if (line > 0) {
                    ResultSet keys = stm.getGeneratedKeys();
                    if (keys.next()) {
                        int key = (int) stm.getGeneratedKeys().getLong(1);
                        System.out.println(key);
                        return key;
                    }
                    return 0;
                }
            } // End connection
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

        }
        return 0;
    }

    public BlogDTO getBlogFromBlogID(int blogId) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            BlogDTO dto = null;

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "SELECT blogID, title, content,postDate, categoryID, status, approvedByID, approvedDate, tags, ownerID, thumbnail "
                        + "FROM Blog "
                        + "WHERE blogID = ?";

                stm = con.prepareStatement(sql);
                stm.setInt(1, blogId);

                rs = stm.executeQuery();

                while (rs.next()) {
                    int blogID = rs.getInt("blogID");
                    String title = rs.getString("title");
                    String content = rs.getString("content");
                    Date postDate = rs.getDate("postDate");
                    String categoryID = rs.getString("categoryID");
                    String status = rs.getString("status");
                    int approvedByID = rs.getInt("approvedByID");
                    Date approvedDate = rs.getDate("approvedDate");
                    String tags = rs.getString("tags");
                    int ownerID = rs.getInt("ownerID");
                    String thumbnail = rs.getString("thumbnail");

                    dto = new BlogDTO(blogID, title, content, postDate, categoryID, status, approvedByID, approvedDate, tags, ownerID, thumbnail);
                }

                return dto;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public ArrayList<BlogDTO> getAllBlogs() throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            ArrayList<BlogDTO> blogList = new ArrayList<BlogDTO>();

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "SELECT blogID, title, content, postDate, categoryID, status, approvedByID, approvedDate, tags, ownerID, thumbnail "
                        + "FROM Blog "
                        + "ORDER BY postDate DESC";

                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();

                while (rs.next()) {
                    int blogID = rs.getInt("blogID");
                    String title = rs.getString("title");
                    String content = rs.getString("content");
                    Date postDate = rs.getDate("postDate");
                    String categoryID = rs.getString("categoryID");
                    String status = rs.getString("status");
                    int approvedByID = rs.getInt("approvedByID");
                    Date approvedDate = rs.getDate("approvedDate");
                    String tags = rs.getString("tags");
                    int ownerID = rs.getInt("ownerID");
                    String thumbnail = rs.getString("thumbnail");

                    BlogDTO dto = new BlogDTO(blogID, title, content, postDate, categoryID, status, approvedByID, approvedDate, tags, ownerID, thumbnail);
                    blogList.add(dto);
                }

                return blogList;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public boolean updateBlog(int blogId, BlogDTO dto) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            // 1. Connect DB
            con = DBHelpers.makeConnection();
            // 2. Create SQL String
            if (con != null) {
                String sql = "UPDATE Blog "
                        + "SET title = ?, content = ?, postDate = ?, categoryID = ?, status = ?, approvedByID = ?, approvedDate = ?, tags = ?, ownerID = ? "
                        + "WHERE blogID = ? ";
                // 3. Create statement object
                stm = con.prepareStatement(sql);

                stm.setString(1, dto.getTitle());
                stm.setString(2, dto.getContent());
                stm.setDate(3, dto.getPostDate());
                stm.setString(4, dto.getCategoryID());
                stm.setString(5, dto.getStatus());
                stm.setInt(6, dto.getMentorID());
                stm.setDate(7, dto.getApprovedDate());
                stm.setString(8, dto.getTags());
                stm.setInt(9, dto.getStudentID());

                stm.setInt(10, blogId);
                int line = stm.executeUpdate();

                return line > 0;
            } // End connection
        } finally {

        }
        return false;
    }

    public boolean setBlogStatusFromBlogID(int blogId, String status) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            // 1. Connect DB
            con = DBHelpers.makeConnection();
            // 2. Create SQL String
            if (con != null) {
                String sql = "UPDATE Blog "
                        + "SET status = ?  "
                        + "WHERE blogID = ? ";
                // 3. Create statement object
                stm = con.prepareStatement(sql);

                stm.setString(1, status);

                stm.setInt(2, blogId);
                int line = stm.executeUpdate();

                return line > 0;
            } // End connection
        } finally {

        }
        return false;
    }

    public boolean approveBlog(int blogId) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            // 1. Connect DB
            con = DBHelpers.makeConnection();
            // 2. Create SQL String
            if (con != null) {
                String sql = "UPDATE Blog "
                        + "SET status = ? "
                        + "WHERE blogID = ?";
                // 3. Create statement object
                stm = con.prepareStatement(sql);
                stm.setString(1, "APPROVED");
                stm.setInt(2, blogId);
                int line = stm.executeUpdate();

                return line > 0;
            } // End connection
        } finally {

        }
        return false;
    }

    public boolean disapproveBlog(int blogId) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            // 1. Connect DB
            con = DBHelpers.makeConnection();
            // 2. Create SQL String
            if (con != null) {
                String sql = "UPDATE Blog "
                        + "SET status = ? "
                        + "WHERE blogID = ?";
                // 3. Create statement object
                stm = con.prepareStatement(sql);
                stm.setString(1, "DISAPPROVED");
                stm.setInt(2, blogId);
                int line = stm.executeUpdate();

                return line > 0;
            } // End connection
        } finally {

        }
        return false;
    }

}
