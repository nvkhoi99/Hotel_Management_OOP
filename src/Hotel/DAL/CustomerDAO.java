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

    public Customer getCustomerById(int id) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM customer WHERE cid = ? AND deleted = 0");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new Customer(
                    id,
                    rs.getString("cname"),
                    rs.getBoolean("cgender"),
                    rs.getString("cidentity"),
                    rs.getString("caddress"),
                    rs.getString("cphone")
            );
        }
        return null;
    }

    public ResultSet getCustomerByIdCard(String IdCard) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * "
                + "FROM customer WHERE cidentity = ? AND deleted = 0");
        ps.setString(1, IdCard);
        ResultSet rs = ps.executeQuery();

        return rs;
    }

    public ResultSet getCustomerByOrderId(int orderId) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT customer.cid, cname, cidentity, cphone "
                + "FROM customer INNER JOIN booking "
                + "ON customer.cid = booking.cid WHERE bid = ?");
        ps.setInt(1, orderId);
        ResultSet rs = ps.executeQuery();

        return rs;
    }

    public int addCustomer(Customer customer) throws SQLException {
        int id = 0;
        PreparedStatement ps = conn.prepareStatement("INSERT INTO customer "
                + "(cname, cgender, cidentity, caddress, cphone) "
                + "VALUES (?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1, customer.getFullname());
        ps.setBoolean(2, customer.isGender());
        ps.setString(3, customer.getIdentity());
        ps.setString(4, customer.getAddress());
        ps.setString(5, customer.getPhone());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        id = rs.getInt(1);
        rs.close();
        ps.close();

        return id;
    }

    public boolean updateCustomer(Customer customer) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("UPDATE customer SET "
                + "cname = ?, cgender = ?, cidentity = ?, caddress = ?, cphone = ? "
                + "WHERE cid = ?");
        ps.setString(1, customer.getFullname());
        ps.setBoolean(2, customer.isGender());
        ps.setString(3, customer.getIdentity());
        ps.setString(4, customer.getAddress());
        ps.setString(5, customer.getPhone());
        ps.setInt(6, customer.getId());
        int count = ps.executeUpdate();
        return count == 1;
    }

    public boolean removeCustomer(int cid) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("UPDATE customer SET "
                + "deleted = 1 WHERE cid = ?");
        ps.setInt(1, cid);
        int success = ps.executeUpdate();
        return success == 1;
    }
}
