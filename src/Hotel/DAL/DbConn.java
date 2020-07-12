package Hotel.DAL;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yue
 */
public class DbConn {

    public static final String MYSQL_HOTELMANAGEMENT = "jdbc:mysql://localhost:3306/hotel_oop"
            + "?useUnicode=yes&characterEncoding=UTF-8";
    public static final String USER = "root";
    public static final String PASS = "1234567890";

    private static Connection conn = null;

    private DbConn() {

    }

    public static Connection getConnection() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(MYSQL_HOTELMANAGEMENT, USER, PASS);
            } catch (SQLException ex) {
                Logger.getLogger(DbConn.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return conn;
    }

    public void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(DbConn.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
