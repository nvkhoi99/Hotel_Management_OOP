/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hotel.DAL;

import Hotel.DTO.rooms.Room;
import Hotel.DTO.rooms.RoomForManaging;

import java.sql.*;
import java.util.ArrayList;

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
        return stmt.executeQuery("SELECT * FROM room WHERE deleted = 0 ORDER BY rid");
    }

    public boolean checkRoomName(String roomName, int roomId) throws SQLException {
        boolean check = false;
        PreparedStatement ps = conn.prepareStatement("SELECT rname "
                + "FROM room WHERE rname = ? AND rid != ? WHERE deleted = 0");
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
        CallableStatement ps = conn.prepareCall("{call spAddRoom(?, ?)}");
        ps.setString(1, room.getName());
        ps.setInt(2, room.getTypeId());
        ps.execute();
        ps.close();
    }

    public void updateRoomInfo(Room room) throws SQLException {
        CallableStatement ps = conn.prepareCall("{call spEditRoom(?, ?, ?)}");
        ps.setInt(1, room.getId());
        ps.setString(2, room.getName());
        ps.setInt(3, room.getTypeId());
        ps.execute();
        ps.close();
    }

    public void deleteRoom(int roomId) throws SQLException {
        CallableStatement ps = conn.prepareCall("{call spDeleteRoom(?)}");
        ps.setInt(1, roomId);
        ps.execute();
        ps.close();
    }

    public boolean checkRoomByType(int typeId) throws SQLException {
        boolean check = false;
        PreparedStatement ps = conn.prepareStatement("SELECT tid "
                + "FROM room WHERE rid = ? AND deleted = 0");
        ps.setInt(1, typeId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            check = true;
        }
        ps.close();

        return check;
    }

    public void changeStateRoom(int roomId, boolean state) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("UPDATE room "
                + "SET rstatus = ? WHERE rid = ?");
        ps.setBoolean(1, state);
        ps.setInt(2, roomId);
        ps.executeUpdate();
    }

    public ResultSet getOrdersByRoomId(int roomId) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT dondatphong.madatphong, ngaynhan, ngaytra "
                + "FROM dondatphong INNER JOIN chitietdatphong ON dondatphong.madatphong = chitietdatphong.madatphong "
                + "WHERE rid = ? AND trangthai < 2");
        ps.setInt(1, roomId);
        ResultSet rs = ps.executeQuery();

        return rs;
    }

    public ArrayList<Room> getAvailableRoom(Timestamp from, Timestamp to) throws SQLException {
        CallableStatement ps = conn.prepareCall("{call spGetAvailableRooms(?, ?)}");
        ps.setTimestamp(1, from);
        ps.setTimestamp(2, to);
        ResultSet rs = ps.executeQuery();
        ArrayList<Room> roomList = new ArrayList<>();
        while (rs.next()) {
            roomList.add(new RoomForManaging(
                    rs.getInt("rid"),
                    rs.getString("rname"),
                    rs.getInt("tid"),
                    0, true
            ));
        }
        return roomList;
    }
}
