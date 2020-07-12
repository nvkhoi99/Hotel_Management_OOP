/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hotel.DAL;

import Hotel.DTO.Room.Room;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Yue
 */
public class RoomDAO {

    private final Connection conn = DbConn.getConnection();
    private static RoomDAO instance = null;

    private RoomDAO() {

    }

    public static RoomDAO getInstance() {
        if (instance == null) {
            instance = new RoomDAO();
        }
        return instance;
    }

    public ResultSet getAllRoom() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM phong ORDER BY maphong");

        return rs;
    }

    public boolean checkRoomName(String roomName, int roomId) throws SQLException {
        boolean check = false;
        PreparedStatement ps = conn.prepareStatement("SELECT tenphong "
                + "FROM phong WHERE tenphong = ? AND maphong != ?");
        ps.setString(1, roomName);
        ps.setInt(2, roomId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            check = true;
        }
        rs.close();
        ps.close();

        return check;
    }

    public void addNewRoom(Room room) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO phong "
                + "(tenphong, maloaiphong) VALUES (?, ?)");
        ps.setString(1, room.getTenphong());
        ps.setInt(2, room.getMaloaiphong());
        ps.execute();
        ps.close();
    }

    public void updateRoomInfo(Room room) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("UPDATE phong "
                + "SET tenphong = ?, maloaiphong = ? WHERE maphong = ?");
        ps.setString(1, room.getTenphong());
        ps.setInt(2, room.getMaloaiphong());
        ps.setInt(3, room.getMaphong());
        ps.execute();
        ps.close();
    }

    public void deleteRoom(int roomId) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("DELETE "
                + "FROM phong WHERE maphong = ?");
        ps.setInt(1, roomId);
        ps.execute();
        ps.close();
    }

    public boolean checkRoomByType(int typeId) throws SQLException {
        boolean check = false;
        PreparedStatement ps = conn.prepareStatement("SELECT maphong "
                + "FROM phong where maloaiphong = ?");
        ps.setInt(1, typeId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            check = true;
        }
        ps.close();

        return check;
    }

    public void changeStateRoom(int roomId, boolean state) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("UPDATE phong "
                + "SET tinhtrang = ? WHERE maphong = ?");
        ps.setBoolean(1, state);
        ps.setInt(2, roomId);
        ps.executeUpdate();
    }

    public ResultSet getOrdersByRoomId(int roomId) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT dondatphong.madatphong, ngaynhan, ngaytra "
                + "FROM dondatphong INNER JOIN chitietdatphong ON dondatphong.madatphong = chitietdatphong.madatphong "
                + "WHERE maphong = ? AND trangthai < 2");
        ps.setInt(1, roomId);
        ResultSet rs = ps.executeQuery();

        return rs;
    }

}
