/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hotel.DAL;

import Hotel.DTO.services.RoomService;
import Hotel.DTO.services.Service;

import java.sql.*;

/**
 *
 * @author Yue
 */
public class ServiceDAO {

    private static ServiceDAO instance = null;
    private final Connection conn = DbConn.getConnection();

    private ServiceDAO() {

    }

    public static ServiceDAO getInstance() {
        if (instance == null) {
            instance = new ServiceDAO();
        }
        return instance;
    }

    public ResultSet getService() throws SQLException {
        String sql = "Select * from service WHERE deleted = 0";
        PreparedStatement ps = DbConn.getConnection().prepareStatement(sql);

        return ps.executeQuery();
    }

    public ResultSet getRoomService(int bid$, int rid$) throws SQLException {
        String sql = " Select * from booking_service inner join service on booking_service.sid = service.sid "
                + "where bid  = ? and rid = ? order by bstime";
        PreparedStatement ps = DbConn.getConnection().prepareStatement(sql);
        ps.setInt(1, bid$);
        ps.setInt(2, rid$);

        return ps.executeQuery();
    }

    public void addRoomService(RoomService[] roomService) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO booking_service" +
                "(sid, bid, rid, bsprice, quantity) "
                + "VALUES (?, ?, ?, ? ,?)");
        for (RoomService i : roomService) {
            ps.setInt(1, i.getServiceId());
            ps.setInt(2, i.getBookingId());
            ps.setInt(3, i.getRoomId());
            ps.setInt(4, i.getPrice());
            ps.setInt(5, i.getQuantity());
            ps.addBatch();
        }
        ps.executeBatch();
        ps.close();
    }

    public void updateRoomService(RoomService roomService) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("UPDATE booking_service "
                + "SET quantity = ? WHERE bid = ? AND sid = ? AND bstime = ?");
        ps.setInt(1, roomService.getQuantity());
        ps.setInt(2, roomService.getBookingId());
        ps.setInt(3, roomService.getServiceId());
        ps.setTimestamp(4, roomService.getUseTime());
        ps.executeUpdate();
        ps.close();
    }

    public int getServiceIdByName(String serviceName) throws SQLException {
        int serviceId = 0;
        PreparedStatement ps = conn.prepareStatement("SELECT sid FROM service "
                + "WHERE sname = ?");
        ps.setString(1, serviceName);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            serviceId = rs.getInt(1);
        }
        ps.close();

        return serviceId;
    }

    public void deleteRoomService(int id) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("DELETE FROM booking_service "
                + "WHERE bsid = ?");
        ps.setInt(1, id);
        ps.executeUpdate();
        ps.close();
    }

    public void addServiceeeeee(Service service) throws SQLException {
        CallableStatement ps = conn.prepareCall("{call spAddService(?, ?, ?)}");
        ps.setString(1, service.getName());
        ps.setString(2, service.getUnit());
        ps.setInt(3, service.getPrice());
        ps.execute();
        ps.close();
    }

    public void deleteService(int sid) throws SQLException {
        String sql = "DELETE FROM service WHERE sid = ? ";
        PreparedStatement ps = DbConn.getConnection().prepareStatement(sql);
        ps.setInt(1, sid);
        ps.executeUpdate();
        ps.close();
    }

    public void updateService(Service service) throws SQLException {
        String sql = "UPDATE service set sname = ?, sunit = ?, sprice = ? "
                + "WHERE sid = ?";
        PreparedStatement ps = DbConn.getConnection().prepareStatement(sql);
        ps.setString(1, service.getName());
        ps.setString(2, service.getUnit());
        ps.setInt(3, service.getPrice());
        ps.setInt(4, service.getId());
        ps.executeUpdate();
        ps.close();
    }

    public boolean deleteService(String sid) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("UPDATE service " +
                "SET deleted = 1 WHERE sid = ?");
        int success = ps.executeUpdate();

        return success == 1;
    }
}
