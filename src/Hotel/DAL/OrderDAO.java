package Hotel.DAL;

import Hotel.DTO.Order;
import Hotel.DTO.Room.RoomForOrdering;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author Yue
 */
public class OrderDAO {

    private final Connection conn = DbConn.getConnection();
    private static OrderDAO instance = null;

    private OrderDAO() {

    }

    public static OrderDAO getInstance() {
        if (instance == null) {
            instance = new OrderDAO();
        }
        return instance;
    }

    public int addOrder(Order order) throws SQLException {
        conn.setAutoCommit(false);
        int orderId = 0;
        PreparedStatement ps = conn.prepareStatement("INSERT INTO dondatphong "
                + "(makh, ngaydat, ngaynhan, ngaytra, tongcoc, trangthai) "
                + "VALUES (?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setInt(1, order.getMakh());
        ps.setTimestamp(2, order.getNgaydat());
        ps.setTimestamp(3, order.getNgaynhan());
        ps.setTimestamp(4, order.getNgaytra());
        ps.setInt(5, order.getTongcoc());
        ps.setInt(6, order.getTrangthai());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        orderId = rs.getInt(1);
        rs.close();
        ps.close();

        return orderId;
    }

    public void addRoomToOrder(int orderId, ArrayList<RoomForOrdering> rooms) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO chitietdatphong "
                + "(madatphong, maphong, dongiadat) "
                + "VALUES (?, ?, ?)");
        for (RoomForOrdering room : rooms) {
            ps.setInt(1, orderId);
            ps.setInt(2, room.getMaphong());
            ps.setInt(3, room.getDongiadat());
            ps.addBatch();
        }
        ps.executeBatch();
        ps.close();
        conn.commit();
        conn.setAutoCommit(true);
    }

    public ResultSet getOrderedRoomsByTime(Timestamp checkIn, Timestamp checkOut) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT DISTINCT chitietdatphong.maphong "
                + "FROM dondatphong INNER JOIN chitietdatphong "
                + "ON dondatphong.madatphong = chitietdatphong.madatphong "
                + "WHERE  ngaytra >= ? AND ngaynhan <= ?  AND dondatphong.trangthai != 3");
        ps.setTimestamp(1, checkIn);
        ps.setTimestamp(2, checkOut);
        ResultSet rs = ps.executeQuery();

        return rs;
    }

    public ResultSet getOrder() throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT  * FROM dondatphong "
                + "ORDER BY trangthai ASC, madatphong DESC");
        ResultSet rs = ps.executeQuery();
        return rs;
    }

    public void changeStateOrder(int orderId, int state) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("UPDATE dondatphong "
                + "SET trangthai = ? WHERE madatphong = ?");
        ps.setInt(1, state);
        ps.setInt(2, orderId);
        ps.executeUpdate();
        ps.close();
    }

    public Timestamp getCheckInTimestampById(int orderId) throws SQLException {
        Timestamp timestamp = null;
        PreparedStatement ps = conn.prepareStatement("SELECT ngaynhan "
                + "FROM dondatphong WHERE madatphong = ?");
        ps.setInt(1, orderId);
        ResultSet rs = ps.executeQuery();
        rs.next();
        timestamp = rs.getTimestamp("ngaynhan");
        ps.close();

        return timestamp;
    }

    public ResultSet getRoomInOrder(int orderId) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT tenphong, tenloaiphong, dongiadat "
                + "FROM chitietdatphong INNER JOIN phong on chitietdatphong.maphong = phong.maphong "
                + "inner join loaiphong on phong.maloaiphong = loaiphong.maloaiphong "
                + "WHERE madatphong = ?");
        ps.setInt(1, orderId);
        ResultSet rs = ps.executeQuery();

        return rs;
    }

    public ResultSet getOrderById(int orderId) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM dondatphong "
                + "WHERE madatphong = ?");
        ps.setInt(1, orderId);
        ResultSet rs = ps.executeQuery();

        return rs;
    }

    public ResultSet checkEmptyRoomInOrder(int orderId) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT chitietdatphong.maphong "
                + "FROM chitietdatphong INNER JOIN phong ON chitietdatphong.maphong = phong.maphong "
                + "WHERE madatphong = ? AND mahientai != 0");
        ps.setInt(1, orderId);
        ResultSet rs = ps.executeQuery();

        return rs;
    }

    public ResultSet getOrderTop(int topCount) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM dondatphong "
                + "ORDER BY trangthai ASC, madatphong DESC "
                + "LIMIT 50 OFFSET ? ");
        ps.setInt(1, topCount);
        ResultSet rs = ps.executeQuery();

        return rs;
    }

    public ResultSet getCountOrder() throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT COUNT(madatphong) AS soluong FROM dondatphong");
        ResultSet rs = ps.executeQuery();

        return rs;
    }

}
