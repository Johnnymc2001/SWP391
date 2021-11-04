package DAO;

import DAO.AccountDTO;
import Utils.DBHelpers;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AccountDAO implements Serializable {

    /**
     * Register New Account
     *
     * @param dto AccountDTO
     * @return true if success, false if failed
     * @throws SQLException
     */
    public int createAccount(AccountDTO dto) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        int line = 0;
        try {
            ArrayList<AccountDTO> accountList = new ArrayList<AccountDTO>();

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "INSERT INTO Account (username, password, fullname, address, birthdate, email, phone, role ,categoryID, status) "
                        + "VALUES (?,?,?,?,?,?,?,?,?,?)";

                stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                stm.setString(1, dto.getUsername());
                stm.setString(2, dto.getPassword());
                stm.setString(3, dto.getFullname());
                stm.setString(4, dto.getAddress());
                stm.setDate(5, dto.getBirthday());
                stm.setString(6, dto.getEmail());
                stm.setString(7, dto.getPhone());
                stm.setString(8, dto.getRole());
                stm.setString(9, "Mentor".equals(dto.getRole()) ? dto.getCategoryID() : null);
                if (null != dto.getStatus()) {
                    stm.setString(10, dto.getStatus());
                } else {
                    stm.setString(10, "PENDING");
                }
                

                line = stm.executeUpdate();
                
                if (line > 0) {
                    ResultSet keys = stm.getGeneratedKeys();
                    if (keys.next()) {
                        int key = (int) stm.getGeneratedKeys().getLong(1);
                        System.out.println(key);
                        return key;
                    }
                    return 0;
                }

            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return 0;
    }

    /**
     * Check for login using combination of username and password
     *
     * @param usr Username
     * @param pwd Password
     * @return AccountDTO if found, NULL if not found
     * @throws SQLException
     */
    public AccountDTO checkLogin(String usr, String pwd) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            ArrayList<AccountDTO> accountList = new ArrayList<AccountDTO>();

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "SELECT accountID, username, password, fullname, address, birthdate, email, phone, role ,categoryID, status "
                        + "FROM Account "
                        + "WHERE username = ? COLLATE SQL_Latin1_General_CP1_CS_AS "
                        + "AND password = ? COLLATE SQL_Latin1_General_CP1_CS_AS ";

                stm = con.prepareStatement(sql);
                stm.setString(1, usr);
                stm.setString(2, pwd);

                rs = stm.executeQuery();
                AccountDTO dto = null;

                while (rs.next()) {
                    int accountID = rs.getInt("accountID");
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullname = rs.getString("fullname");
                    String address = rs.getString("address");
                    Date birthdate = rs.getDate("birthdate");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    String role = rs.getString("role");
                    String categoryID = "Mentor".equals(role) ? rs.getString("categoryID") : "None";
                    String status = rs.getString("status");

                    dto = new AccountDTO(accountID, username, password, fullname, address, birthdate, email, phone, role, categoryID, status);
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

    /**
     * Get account using username
     *
     * @param usr Username of the account
     * @return Return an account if found, null if not found
     * @throws SQLException
     */
    public AccountDTO getAccountFromUsername(String usr) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            AccountDTO dto = null;

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "SELECT accountID, username, password, fullname, address, birthdate, email, phone, role , categoryID, status "
                        + "FROM Account "
                        + "Where username = ? "
                        + "ORDER BY role";

                stm = con.prepareStatement(sql);
                stm.setString(1, usr);

                rs = stm.executeQuery();

                while (rs.next()) {
                    int accountID = rs.getInt("accountID");
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullname = rs.getString("fullname");
                    String address = rs.getString("address");
                    Date birthdate = rs.getDate("birthdate");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    String role = rs.getString("role");
                    String categoryID = "Mentor".equals(role) ? rs.getString("categoryID") : "None";
                    String status = rs.getString("status");

                    dto = new AccountDTO(accountID, username, password, fullname, address, birthdate, email, phone, role, categoryID, status);
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

    public AccountDTO getAccountFromAcoountID(int accountId) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            AccountDTO dto = null;

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "SELECT accountID, username, password, fullname, address, birthdate, email, phone, role , categoryID, status "
                        + "FROM Account "
                        + "Where accountID = ? "
                        + "ORDER BY role";

                stm = con.prepareStatement(sql);
                stm.setInt(1, accountId);

                rs = stm.executeQuery();

                while (rs.next()) {
                    int accountID = rs.getInt("accountID");
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullname = rs.getString("fullname");
                    String address = rs.getString("address");
                    Date birthdate = rs.getDate("birthdate");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    String role = rs.getString("role");
                    String categoryID = "Mentor".equals(role) ? rs.getString("categoryID") : "None";
                    String status = rs.getString("status");

                    dto = new AccountDTO(accountID, username, password, fullname, address, birthdate, email, phone, role, categoryID, status);
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
    
     public AccountDTO getAccountFromEmail(String Email) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            AccountDTO dto = null;

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "SELECT accountID, username, password, fullname, address, birthdate, email, phone, role , categoryID, status "
                        + "FROM Account "
                        + "Where email = ? "
                        + "ORDER BY role";

                stm = con.prepareStatement(sql);
                stm.setString(1, Email);

                rs = stm.executeQuery();

                while (rs.next()) {
                    int accountID = rs.getInt("accountID");
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullname = rs.getString("fullname");
                    String address = rs.getString("address");
                    Date birthdate = rs.getDate("birthdate");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    String role = rs.getString("role");
                    String categoryID = "Mentor".equals(role) ? rs.getString("categoryID") : "None";
                    String status = rs.getString("status");

                    dto = new AccountDTO(accountID, username, password, fullname, address, birthdate, email, phone, role, categoryID, status);
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

    public ArrayList<AccountDTO> getAllAccount() throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            ArrayList<AccountDTO> accountList = new ArrayList<AccountDTO>();

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "SELECT accountID, username, password, fullname, address, birthdate, email, phone, role, categoryID, status "
                        + "FROM Account "
                        + "ORDER BY role";

                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();

                while (rs.next()) {
                    int accountID = rs.getInt("accountID");
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullname = rs.getString("fullname");
                    String address = rs.getString("address");
                    Date birthdate = rs.getDate("birthdate");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    String role = rs.getString("role");
                    String categoryID = "Mentor".equals(role) ? rs.getString("categoryID") : "None";
                    String status = rs.getString("status");

                    AccountDTO dto = new AccountDTO(accountID, username, password, fullname, address, birthdate, email, phone, role, categoryID, status);
                    accountList.add(dto);
                }

                return accountList;
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

    /**
     * Get all account in the database
     *
     * @return ArrayList if found, NULL if not found
     * @throws SQLException
     */
    public ArrayList<AccountDTO> getAllAccountFromRole(String searchRole) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            ArrayList<AccountDTO> accountList = new ArrayList<AccountDTO>();

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "SELECT accountID, username, password, fullname, address, birthdate, email, phone, role, categoryID, status "
                        + "FROM Account "
                        + "WHERE role = ? "
                        + "ORDER BY role";

                stm = con.prepareStatement(sql);
                stm.setString(1, searchRole);

                rs = stm.executeQuery();

                while (rs.next()) {
                    int accountID = rs.getInt("accountID");
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullname = rs.getString("fullname");
                    String address = rs.getString("address");
                    Date birthdate = rs.getDate("birthdate");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    String role = rs.getString("role");
                    String categoryID = "Mentor".equals(role) ? rs.getString("categoryID") : "None";
                    String status = rs.getString("status");

                    AccountDTO dto = new AccountDTO(accountID, username, password, fullname, address, birthdate, email, phone, role, categoryID, status);
                    accountList.add(dto);
                }

                return accountList;
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

    public boolean updateAccount(int accountID, AccountDTO dto) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            // 1. Connect DB
            con = DBHelpers.makeConnection();
            // 2. Create SQL String
            if (con != null) {
                String sql = "UPDATE Account "
                        + "SET username = ?, password = ?, fullname = ?, address = ?, birthdate = ?, email = ?, phone = ?, role = ?, categoryID = ?, status = ?  "
                        + "WHERE accountID = ?";
                // 3. Create statement object
                stm = con.prepareStatement(sql);

                stm.setString(1, dto.getUsername());
                stm.setString(2, dto.getPassword());
                stm.setString(3, dto.getFullname());
                stm.setString(4, dto.getAddress());
                stm.setDate(5, dto.getBirthday());
                stm.setString(6, dto.getEmail());
                stm.setString(7, dto.getPhone());
                stm.setString(8, dto.getRole());
                stm.setString(9, "Mentor".equals(dto.getRole()) ? dto.getCategoryID() : null);
                stm.setString(10, dto.getStatus());
                stm.setInt(11, accountID);

                int line = stm.executeUpdate();

                return line > 0;
            } // End connection
        } finally {

        }
        return false;
    }

    public boolean activateAccount(int accountID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            // 1. Connect DB
            con = DBHelpers.makeConnection();
            // 2. Create SQL String
            if (con != null) {
                String sql = "UPDATE Account "
                        + "SET status = ?  "
                        + "WHERE accountID = ?";
                // 3. Create statement object
                stm = con.prepareStatement(sql);

                stm.setString(1, "AVAILABLE");
                stm.setInt(2, accountID);

                int line = stm.executeUpdate();

                return line > 0;
            } // End connection
        } finally {

        }
        return false;
    }

    public boolean deactivateAccount(int accountID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            // 1. Connect DB
            con = DBHelpers.makeConnection();
            // 2. Create SQL String
            if (con != null) {
                String sql = "UPDATE Account "
                        + "SET status = ?  "
                        + "WHERE accountID = ?";
                // 3. Create statement object
                stm = con.prepareStatement(sql);

                stm.setString(1, "UNAVAILABLE");
                stm.setInt(2, accountID);

                int line = stm.executeUpdate();

                return line > 0;
            } // End connection
        } finally {

        }
        return false;
    }
}
