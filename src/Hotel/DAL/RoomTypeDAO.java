package Hotel.DAL;

import Hotel.DTO.RoomType;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Yue
 */
public class RoomTypeDAO {

    private static RoomTypeDAO instance = null;
    private final Connection conn = DbConn.getConnection();

    private RoomTypeDAO() {

    }

    public static RoomTypeDAO getInstance() {
        if (instance == null) {
            instance = new RoomTypeDAO();
        }
        return instance;
    }

    public ResultSet getRoomTypeList() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM loaiphong ORDER BY dongia");

        return rs;
    }

    public boolean checkRoomTypeName(RoomType rt) throws SQLException {
        boolean check = false;
        PreparedStatement ps = conn.prepareStatement("SELECT tenloaiphong "
                + "FROM loaiphong WHERE tenloaiphong = ? AND maloaiphong != ?");
        ps.setString(1, rt.getTenloaiphong());
        ps.setInt(2, rt.getMaloaiphong());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            check = true;
        }
        rs.close();

        return check;
    }

    public void addRoomType(RoomType roomType) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO loaiphong "
                + "(tenloaiphong, dongia) VALUES (?, ?)");
        ps.setString(1, roomType.getTenloaiphong());
        ps.setInt(2, roomType.getDongia());
        ps.executeUpdate();
        ps.close();

    }

    public void updateRoomType(RoomType roomType) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("UPDATE loaiphong "
                + "SET tenloaiphong = ?, dongia = ? WHERE maloaiphong = ?");
        ps.setString(1, roomType.getTenloaiphong());
        ps.setInt(2, roomType.getDongia());
        ps.setInt(3, roomType.getMaloaiphong());
        ps.executeUpdate();
        ps.close();
    }

    public void deleteRoomType(int typeId) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("DELETE "
                + "FROM loaiphong WHERE maloaiphong = ?");
        ps.setInt(1, typeId);
        ps.executeUpdate();
        ps.close();
    }

    public int getTypeIdByName(String name) throws SQLException {
        int id = 0;
        PreparedStatement ps = conn.prepareStatement("SELECT maloaiphong "
                + "FROM loaiphong where tenloaiphong = ?");
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            id = rs.getInt(1);
        }
        rs.close();
        ps.close();

        return id;
    }
}
