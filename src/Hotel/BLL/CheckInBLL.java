/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hotel.BLL;

import Hotel.BLL.HotelError.ErrorCode;
import Hotel.DAL.StayProfileDAO;
import Hotel.DTO.Customer;
import Hotel.DTO.Order;
import Hotel.DTO.StayProfile;
import Hotel.DAL.CustomerDAO;
import Hotel.DAL.DbConn;
import Hotel.DAL.OrderDAO;
import Hotel.GUI.CheckInGUI.CheckInForm;
import java.sql.Timestamp;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Yue
 */
public class CheckInBLL {

    private final CheckInForm checkInForm;
    private final OrderDAO orderDAO = OrderDAO.getInstance();
    private int pos = 0;

    public CheckInBLL(CheckInForm checkInForm) {
        this.checkInForm = checkInForm;
    }

    public void updateTable(DefaultTableModel orderModel) {
        try {
            orderModel.setRowCount(0);
            ResultSet rs = orderDAO.getOrder();
            while (rs.next()) {
                Object[] col = new Object[6];
                col[0] = rs.getInt("madatphong");
                col[1] = rs.getTimestamp("ngaydat");
                col[2] = rs.getTimestamp("ngaynhan");
                col[3] = rs.getTimestamp("ngaytra");
                col[4] = rs.getInt("tongcoc");
                switch (rs.getInt("trangthai")) {
                    case 0:
                        col[5] = "Chưa cọc";
                        break;
                    case 1:
                        col[5] = "Đã cọc";
                        break;
                    case 2:
                        col[5] = "Đã nhận";
                        break;
                    case 3:
                        col[5] = "Đã hủy";
                        break;
                    default:
                        break;
                }
                orderModel.addRow(col);
            }
            rs.getStatement().close();
        } catch (SQLException ex) {
            Logger.getLogger(CheckInBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cancelOrder(int orderId) {
        int check = JOptionPane.showConfirmDialog(null, "Xác nhận hủy phòng?", "Xác nhận", JOptionPane.OK_CANCEL_OPTION);
        if (check == JOptionPane.YES_OPTION) {
            try {
                orderDAO.changeStateOrder(orderId, Rules.CANCELED);
                JOptionPane.showMessageDialog(null, "Hủy phòng thành công");
            } catch (SQLException ex) {
                Logger.getLogger(CheckInBLL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void depositOrder(int orderId) {
        int check = JOptionPane.showConfirmDialog(null, "Xác nhận đặt cọc?", "Xác nhận", JOptionPane.OK_CANCEL_OPTION);
        if (check == JOptionPane.YES_OPTION) {
            try {
                orderDAO.changeStateOrder(orderId, Rules.DEPOSITED);
                JOptionPane.showMessageDialog(null, "Nhận cọc thành công");
            } catch (SQLException ex) {
                Logger.getLogger(CheckInBLL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean checkInOrder(int orderId) {
        int check = JOptionPane.showConfirmDialog(null, "Xác nhận nhận phòng?", "Xác nhận", JOptionPane.OK_CANCEL_OPTION);
        if (check == JOptionPane.YES_OPTION) {
            boolean success = true;
            try {
                ResultSet rs = orderDAO.checkEmptyRoomInOrder(orderId);
                if (rs.next()) {
                    rs.getStatement().close();
                    throw new HotelError(ErrorCode.STILL_CUSTOMER);
                }
                rs.getStatement().close();
                try {
                    DbConn.getConnection().setAutoCommit(false);

                    orderDAO.changeStateOrder(orderId, Rules.CHECKED_IN);
                    StayProfile stayProfile = new StayProfile();
                    stayProfile.setDondatphong(new Order(orderId));
                    stayProfile.setThucnhan(new Timestamp(System.currentTimeMillis()));
                    if (StayProfileDAO.getInstance().addStayProfile(stayProfile) == 0) {
                        throw new HotelError(ErrorCode.STAY_FAILED);
                    }

                    DbConn.getConnection().commit();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    Logger.getLogger(CheckInBLL.class.getName()).log(Level.SEVERE, null, ex);
                    DbConn.getConnection().rollback();
                    success = false;
                } catch (HotelError ex) {
                    ex.notifyError();
                    return false;
                } finally {
                    DbConn.getConnection().setAutoCommit(true);
                }
            } catch (SQLException ex) {
                Logger.getLogger(CheckInBLL.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } catch (HotelError ex) {
                ex.notifyError();
                return false;
            }
            return success;
        } else {
            return false;
        }
    }

    public Customer getCustomerByOrderId(int orderId) {
        Customer customer = new Customer();
        try {
            ResultSet rs = CustomerDAO.getInstance().getCustomerByOrderId(orderId);
            if (rs.next()) {
                customer.setMakh(rs.getInt("makh"));
                customer.setHotenkh(rs.getString("hotenkh"));
                customer.setCmnd(rs.getString("cmnd"));
                customer.setSdt(rs.getString("sdt"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customer;
    }

    public void updateOrderedRoomTable(int orderId, DefaultTableModel orderRoomModel) {
        try {
            int rowCount = orderRoomModel.getRowCount();
            for (int i = 0; i < rowCount; i++) {
                orderRoomModel.removeRow(0);
            }
            ResultSet rs = orderDAO.getRoomInOrder(orderId);
            while (rs.next()) {
                orderRoomModel.addRow(new Object[]{
                    rs.getString("tenphong"),
                    rs.getString("tenloaiphong"),
                    rs.getInt("dongiadat")
                });
            }
            rs.getStatement().close();
        } catch (SQLException ex) {
            Logger.getLogger(CheckInBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateTableTop(DefaultTableModel orderModel, int topCount) {
        try {
            orderModel.setRowCount(0);
            ResultSet rs = orderDAO.getOrderTop(topCount);
            while (rs.next()) {
                Object[] col = new Object[6];
                col[0] = rs.getInt("madatphong");
                col[1] = rs.getTimestamp("ngaydat");
                col[2] = rs.getTimestamp("ngaynhan");
                col[3] = rs.getTimestamp("ngaytra");
                col[4] = rs.getInt("tongcoc");
                switch (rs.getInt("trangthai")) {
                    case 0:
                        col[5] = "Chưa cọc";
                        break;
                    case 1:
                        col[5] = "Đã cọc";
                        break;
                    case 2:
                        col[5] = "Đã nhận";
                        break;
                    case 3:
                        col[5] = "Đã hủy";
                        break;
                    default:
                        break;
                }
                orderModel.addRow(col);
            }
            rs.getStatement().close();
        } catch (SQLException ex) {
            Logger.getLogger(CheckInBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getCountInOrder() {
        int count = 0;
        try {
            ResultSet rs = orderDAO.getCountOrder();
            if (rs.next()) {
                count = rs.getInt(1);
            }
            rs.getStatement().close();
        } catch (SQLException ex) {
            Logger.getLogger(CheckInBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public int getPosition(int madatphong) {
        try {
            pos = 0;
            ResultSet rs = orderDAO.getOrder();
            while (rs.next()) {
                if (rs.getInt("madatphong") == madatphong) {
                    return pos;
                } else {
                    pos++;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CheckInBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

}
