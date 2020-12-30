package Hotel.DAL;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yue
 */
public class DbConn {

    public static final String MYSQL_HOTELMANAGEMENT = "jdbc:mysql://localhost:3306/hotel_project"
            + "?useUnicode=yes&characterEncoding=UTF-8";
    public static String USER;
    public static String PASS;

    private static Connection conn = null;

    private DbConn() {

    }

    public static Connection getConnection() {
        if (conn == null) {
            try {
                Scanner scanner = new Scanner(new File("login.txt"));
                USER = scanner.nextLine();
                PASS = scanner.nextLine();
                conn = DriverManager.getConnection(MYSQL_HOTELMANAGEMENT, USER, PASS);
            } catch (SQLException ex) {
                Logger.getLogger(DbConn.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
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
