/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hotel.BLL;

import Hotel.BLL.HotelError.ErrorCode;
import Hotel.DAL.*;
import Hotel.DTO.Customer;
import Hotel.DTO.Booking;
import Hotel.DTO.StayProfile;
import Hotel.GUI.CheckInGUI.CheckInForm;
import java.sql.Timestamp;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
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

    private final BookingDao bookingDao = new BookingDao();

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

    public void depositOrder(int bookingId) {
        int check = JOptionPane.showConfirmDialog(null, "Xác nhận đặt cọc?",
                "Xác nhận", JOptionPane.OK_CANCEL_OPTION);
        if (check == JOptionPane.YES_OPTION) {
            try {
                bookingDao.depositBooking(bookingId);
                JOptionPane.showMessageDialog(null, "Nhận cọc thành công");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }

    public boolean checkInOrder(int bookingId) {
        int check = JOptionPane.showConfirmDialog(null, "Xác nhận nhận phòng?", "Xác nhận", JOptionPane.OK_CANCEL_OPTION);
        if (check == JOptionPane.YES_OPTION) {
            boolean success = true;
            try {
                int surchargePercent = 0;
                int hour = Calendar.getInstance().getTime().getHours();
                if (hour >= Rules.MAX_EARLY_CHECK_IN_HOUR
                        && hour < Rules.STANDARD_CHECK_IN_HOUR) {
                    int surchargeCheck = JOptionPane.showConfirmDialog(null, "Tính phí nhận sớm?",
                            "Nhận sớm", JOptionPane.YES_NO_CANCEL_OPTION);
                    switch (surchargeCheck) {
                        case JOptionPane.YES_OPTION:
                            surchargePercent = Rules.SURCHARGE_EARLY_PERCENT;
                            break;
                        case JOptionPane.NO_OPTION:
                            break;
                        default:
                            return false;
                    }
                }
                bookingDao.checkInBooking(bookingId, surchargePercent);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
                success = false;
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
                customer.setId(rs.getInt("cid"));
                customer.setFullname(rs.getString("cname"));
                customer.setIdentity(rs.getString("cidentity"));
                customer.setPhone(rs.getString("cphone"));
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
                    rs.getString("rname"),
                    rs.getString("tname"),
                    rs.getInt("rprice")
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
                col[0] = rs.getInt("bid");
                col[1] = rs.getTimestamp("btime");
                col[2] = rs.getTimestamp("check_in");
                col[3] = rs.getTimestamp("check_out");
                col[4] = rs.getInt("deposit");
                switch (rs.getInt("bstatus")) {
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
                if (rs.getInt("bid") == madatphong) {
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
