/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelpers {

    public static Connection makeConnection() throws SQLException {
        try {
            //        // 1. Get current Context
//        Context context = new InitialContext();
//        // 2. Get container Context
//        Context tomcatContext = (Context) context.lookup("java:comp/env");
//        // 3. Get data source
//        DataSource ds = (DataSource) tomcatContext.lookup("Database");
//        // 4. Get connection
//        Connection con = ds.getConnection();
// 1. Load Driver
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
// 2. Create connection string
//        String url = "jdbc:sqlserver://localhost:1433;databaseName=Registeration";
// 3. Open Connection
//        Connection con = DriverManager.getConnection(url, "sa", "123456789");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        String connectionUrl
                = "jdbc:sqlserver://swp391-group4.database.windows.net:1433;database=swp391_group4;user=usernameg4swp@swp391-group4;password=4!m3wp$MA835HU@6r&Fx;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
        Connection connection = DriverManager.getConnection(connectionUrl);
        return connection;

    }
}
