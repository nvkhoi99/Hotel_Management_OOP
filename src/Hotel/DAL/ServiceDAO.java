/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hotel.DAL;

import Hotel.DTO.Dichvu;
import Hotel.DTO.Dichvuphong;
import java.sql.*;
import java.util.ArrayList;

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

    public ResultSet getDichvu() throws SQLException {
        String sql = "Select * from dichvu";
        PreparedStatement ps = DbConn.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        return rs;
    }

    public ResultSet getDichvuphong(int mathuephong$, int maphong$) throws SQLException {
        String sql = " Select * from dichvuphong inner join dichvu on dichvuphong.madv = dichvu.madv "
                + "where mathuephong  = ? and maphong = ? order by ngaydung asc";
        PreparedStatement ps = DbConn.getConnection().prepareStatement(sql);
        ps.setInt(1, mathuephong$);
        ps.setInt(2, maphong$);
        ResultSet rs = ps.executeQuery();

        return rs;
    }

    public void addRoomService(Dichvuphong[] dichvuphong) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO dichvuphong "
                + "VALUES (?, ?, ?, ? ,? ,?)");
        for (Dichvuphong i : dichvuphong) {
            ps.setInt(1, i.getMathuephong());
            ps.setInt(2, i.getMadv());
            ps.setInt(3, i.getDongia());
            ps.setTimestamp(4, i.getNgaydung());
            ps.setInt(5, i.getMaphong());
            ps.setInt(6, i.getSoluong());
            ps.addBatch();
        }
        ps.executeBatch();
        ps.close();
    }

    public void updateRoomService(Dichvuphong dichvuphong) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("UPDATE dichvuphong "
                + "SET soluong = ? WHERE mathuephong = ? AND madv = ? AND ngaydung = ?");
        ps.setInt(1, dichvuphong.getSoluong());
        ps.setInt(2, dichvuphong.getMathuephong());
        ps.setInt(3, dichvuphong.getMadv());
        ps.setTimestamp(4, dichvuphong.getNgaydung());
        ps.executeUpdate();
        ps.close();
    }

    public int getServiceIdByName(String serviceName) throws SQLException {
        int serviceId = 0;
        PreparedStatement ps = conn.prepareStatement("SELECT madv FROM dichvu "
                + "WHERE tendv = ?");
        ps.setString(1, serviceName);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            serviceId = rs.getInt(1);
        }
        ps.close();

        return serviceId;
    }

    public void deleteRoomService(Dichvuphong dichvuphong) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("DELETE FROM dichvuphong "
                + "WHERE mathuephong = ? AND madv = ? AND ngaydung = ?");
        ps.setInt(1, dichvuphong.getMathuephong());
        ps.setInt(2, dichvuphong.getMadv());
        ps.setTimestamp(3, dichvuphong.getNgaydung());
        ps.executeUpdate();
        ps.close();
    }

    public void addServiceeeeee(Dichvu dichvu) throws SQLException {
        //String sql = "INSERT INTO dichvu VALUES (?,?,?);";
        String sql = "INSERT INTO hotel_oop.dichvu (tendv, dvtinh, dongia) VALUES (?, ?, ?)";
        PreparedStatement ps = DbConn.getConnection().prepareStatement(sql);
        ps.setString(1, dichvu.getTendv());
        ps.setString(2, dichvu.getDvtinh());
        ps.setInt(3, dichvu.getDongia());
        ps.executeUpdate();
        ps.close();
    }

    public void deleteService(int madv) throws SQLException {
        String sql = "DELETE FROM dichvu WHERE madv = ? ";
        PreparedStatement ps = DbConn.getConnection().prepareStatement(sql);
        ps.setInt(1, madv);
        ps.executeUpdate();
        ps.close();
    }

    public void updateService(Dichvu dichvu) throws SQLException {
        String sql = "UPDATE dichvu set tendv = ?, dvtinh = ?, dongia = ? "
                + "WHERE madv = ?";
        PreparedStatement ps = DbConn.getConnection().prepareStatement(sql);
        ps.setString(1, dichvu.getTendv());
        ps.setString(2, dichvu.getDvtinh());
        ps.setInt(3, dichvu.getDongia());
        ps.setInt(4, dichvu.getMadv());
        ps.executeUpdate();
        ps.close();
    }

    public void deleteService(String madichvu) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
