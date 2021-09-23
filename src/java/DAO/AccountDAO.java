package DAO;

import DAO.AccountDTO;
import Utils.DBHelpers;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AccountDAO implements Serializable {

    /**
     * Register New Account
     *
     * @param dto AccountDTO
     * @return true if success, false if failed
     * @throws SQLException
     */
    public boolean createAccount(AccountDTO dto) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        int line = 0;
        try {
            ArrayList<AccountDTO> accountList = new ArrayList<AccountDTO>();

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "INSERT INTO Account (username, password, fullname, address, birthdate, email, phone, role ,categoryID, status) "
                        + "VALUES (?,?,?,?,?,?,?,?,?,?)";

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
                stm.setString(10, "AVAILABLE");

                line = stm.executeUpdate();
                return line != 0;

            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    /**
     * Get account ID using username
     *
     * @param username Username of the account
     * @return Return an integer if found, 0 if not found
     * @throws SQLException
     */
    public int getAccountIDByUsername(String username) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int accountID = 0;

        try {
            ArrayList<AccountDTO> accountList = new ArrayList<AccountDTO>();

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "SELECT accountID "
                        + "FROM Account "
                        + "WHERE username = ?";

                stm = con.prepareStatement(sql);
                stm.setString(1, username);

                rs = stm.executeQuery();

                while (rs.next()) {
                    accountID = rs.getInt("accountID");
                    return accountID;
                }

                return accountID;
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

        return accountID;
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
                        + "WHERE username = ? AND password = ? ";

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
                        + "Where accountID = ? ";

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

    /**
     * Get all account in the database
     *
     * @return ArrayList<AccountDTO> if found, NULL if not found
     * @throws SQLException
     */
    public ArrayList<AccountDTO> getAllAccount() throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            ArrayList<AccountDTO> accountList = new ArrayList<AccountDTO>();

            con = DBHelpers.makeConnection();

            if (con != null) {
                String sql = "SELECT accountID, username, password, fullname, address, birthdate, email, phone, role, categoryID, status "
                        + "FROM Account";

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

    public boolean updateAccount(int accountID, AccountDTO dto) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            // 1. Connect DB
            con = DBHelpers.makeConnection();
            // 2. Create SQL String
            if (con != null) {
                String sql = "UPDATE Account "
                        + "SET username = ?, password = ?, fullname = ?, address = ?, birthday = ?, email = ?, phone = ?, role = ?, categoryID = ?, status = ?  "
                        + "WHERE accountID = ?";
                // 3. Create statement object
                stm = con.prepareStatement(sql);

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
