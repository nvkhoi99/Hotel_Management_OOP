/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hotel.DAL;

import Hotel.DTO.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Yue
 */
public class CustomerDAO {

    private static CustomerDAO instance = null;
    private final Connection conn = DbConn.getConnection();

    private CustomerDAO() {

    }

    public static CustomerDAO getInstance() {
        if (instance == null) {
            instance = new CustomerDAO();
        }
        return instance;
    }

    public ResultSet getCustomerByIdCard(String IdCard) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * "
                + "FROM khachhang WHERE cmnd = ?");
        ps.setString(1, IdCard);
        ResultSet rs = ps.executeQuery();

        return rs;
    }

    public ResultSet getCustomerByOrderId(int orderId) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT khachhang.makh, hotenkh, cmnd, sdt "
                + "FROM khachhang INNER JOIN dondatphong "
                + "ON khachhang.makh = dondatphong.makh WHERE madatphong = ?");
        ps.setInt(1, orderId);
        ResultSet rs = ps.executeQuery();

        return rs;
    }

    public int addCustomer(Customer customer) throws SQLException {
        int id = 0;
        PreparedStatement ps = conn.prepareStatement("INSERT INTO khachhang "
                + "(hotenkh, gioitinh, cmnd, diachi, sdt) "
                + "VALUES (?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1, customer.getHotenkh());
        ps.setBoolean(2, customer.isGioitinh());
        ps.setString(3, customer.getCmnd());
        ps.setString(4, customer.getDiachi());
        ps.setString(5, customer.getSdt());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        id = rs.getInt(1);
        rs.close();
        ps.close();

        return id;
    }

}
