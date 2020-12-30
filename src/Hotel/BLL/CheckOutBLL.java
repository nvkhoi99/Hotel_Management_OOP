/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hotel.BLL;

import Hotel.DAL.BookingDao;
import Hotel.DTO.Booking;
import Hotel.DTO.StayProfile;
import Hotel.DAL.OrderDAO;
import Hotel.DAL.StayProfileDAO;
import Hotel.DTO.rooms.Room;
import Hotel.GUI.CheckOutGUI.CheckOutForm;
import Hotel.GUI.common.BookingTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Timestamp;
import java.util.stream.Collectors;
import javax.swing.table.TableModel;

/**
 *
 * @author Yue
 */
public class CheckOutBLL {

    private final JPanel checkOutForm;
    private final StayProfileDAO stayProfileDAO = StayProfileDAO.getInstance();
    private int pos = 0;

    private BookingDao bookingDao = new BookingDao();

    public CheckOutBLL(JPanel checkOutForm) {
        this.checkOutForm = checkOutForm;
    }

    public void updateProflieList(DefaultTableModel profileModel) {
        try {
            profileModel.setRowCount(0);
            ResultSet rs = stayProfileDAO.getAllProfile();
            while (rs.next()) {
                profileModel.addRow(new Object[]{
                    rs.getInt("mathuephong"),
                    rs.getInt("madatphong"),
                    rs.getTimestamp("thucnhan"),
                    rs.getTimestamp("thuctra"),
                    rs.getLong("tongthanhtoan")
                });
            }
            rs.getStatement().close();
        } catch (SQLException ex) {
            Logger.getLogger(CheckOutBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Booking getOrderInProfile(int orderId) {
        Booking booking = new Booking();
        try {
            ResultSet rs = OrderDAO.getInstance().getOrderById(orderId);
            if (rs.next()) {
                booking.setId(orderId);
                booking.setCustomerId(rs.getInt("makh"));
                booking.setCheckInTime(rs.getTimestamp("ngaynhan"));
                booking.setCheckOutTime(rs.getTimestamp("ngaytra"));
                booking.setDeposit(rs.getInt("tongcoc"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CheckOutBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return booking;
    }

    public StayProfile getProfileById(int stayId) {
        StayProfile stayProfile = new StayProfile();
        try {
            ResultSet rs = stayProfileDAO.getProfileById(stayId);
            if (rs.next()) {
                stayProfile.setMathuephong(stayId);
                stayProfile.setDondatphong(getOrderInProfile(rs.getInt("madatphong")));
                stayProfile.setThucnhan(rs.getTimestamp("thucnhan"));
                stayProfile.setThuctra(rs.getTimestamp("thuctra"));
                stayProfile.setTongthanhtoan(rs.getLong("tongthanhtoan"));
            }
            rs.getStatement().close();
        } catch (SQLException ex) {
            Logger.getLogger(CheckOutBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stayProfile;
    }

    public void initStayTable(StayProfile profile, DefaultTableModel model) {
        try {
            ResultSet rs = OrderDAO.getInstance().getRoomInOrder(profile.getDondatphong().getId());
            long so_dem_o = calculateStayTime(profile.getThucnhan(), profile.getThuctra());
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("tenphong"),
                    rs.getString("tenloaiphong"),
                    rs.getInt("dongiadat"),
                    so_dem_o,
                    so_dem_o * rs.getInt("dongiadat")
                });
            }
        } catch (SQLException ex) {
            Logger.getLogger(CheckOutBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private long calculateStayTime(Timestamp thucnhan, Timestamp thuctra) {
        long HOUR = 1000 * 60 * 60;
        long DAY = HOUR * 24;

        Timestamp checkIn;
        Timestamp checkOut;

        if (thucnhan.getHours() >= Rules.MAX_EARLY_CHECK_IN_HOUR
                && thucnhan.getHours() < Rules.STANDARD_CHECK_IN_HOUR) {
            checkIn = new Timestamp(thucnhan.getTime());
        } else {
            checkIn = new Timestamp(thucnhan.getTime() - Rules.STANDARD_CHECK_IN_HOUR * HOUR);
        }
        checkIn.setHours(14);
        checkIn.setMinutes(0);
        checkIn.setSeconds(0);

        if (thucnhan.getHours() >= Rules.STANDARD_CHECK_OUT_HOUR
                && thucnhan.getHours() < Rules.MAX_LATELY_CHECK_OUT_HOUR) {
            checkOut = new Timestamp(thuctra.getTime());
        } else {
            checkOut = new Timestamp(thuctra.getTime() + (24 - Rules.MAX_LATELY_CHECK_OUT_HOUR) * HOUR);
        }
        checkOut.setHours(12);
        checkOut.setMinutes(0);
        checkOut.setSeconds(0);

        return ((checkOut.getTime() - checkIn.getTime()) / DAY + 1);
    }

    public int[] calculateSurcharge(Timestamp realIn, Timestamp realOut, int price) {
        int[] surcharge = new int[]{calculateEarlyCheckIn(realIn, price),
            calculateLatelyCheckOut(realOut, price)};
        return surcharge;
    }

    public int calculateEarlyCheckIn(Timestamp realIn, int price) {
        if (realIn.getHours() >= Rules.MAX_EARLY_CHECK_IN_HOUR
                && realIn.getHours() < Rules.STANDARD_CHECK_IN_HOUR) {
            return price * Rules.SURCHARGE_EARLY_PERCENT / 100;
        }
        return 0;
    }

    public int calculateLatelyCheckOut(Timestamp realOut, int price) {
        if (realOut.getHours() >= Rules.STANDARD_CHECK_OUT_HOUR
                && realOut.getHours() < Rules.MAX_LATELY_CHECK_OUT_HOUR) {
            return price * Rules.SURCHARGE_LATELY_PERCENT / 100;
        }
        return 0;
    }

    public boolean completeProfile(StayProfile stayProfile) {
        if (JOptionPane.showConfirmDialog(null, "Xác nhận thanh toán?", "Xác nhận",
                JOptionPane.OK_CANCEL_OPTION) == JOptionPane.YES_OPTION) {
            try {
                stayProfileDAO.updateProfile(stayProfile);
            } catch (SQLException ex) {
                Logger.getLogger(CheckOutBLL.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    public void updateProflieListTop(DefaultTableModel profileModel, int count) {
        try {
            profileModel.setRowCount(0);
            ResultSet rs = stayProfileDAO.getAllProfileTop(count);
            while (rs.next()) {
                Object[] col = new Object[5];
                col[0] = rs.getInt("mathuephong");
                col[1] = rs.getInt("madatphong");
                col[2] = rs.getTimestamp("thucnhan");
                col[3] = rs.getTimestamp("thuctra");
                col[4] = rs.getLong("tongthanhtoan");
                profileModel.addRow(col);
            }
            rs.getStatement().close();
        } catch (SQLException ex) {
            Logger.getLogger(CheckOutBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public TableModel getBookingsByPage(int page) {
        try {
            ArrayList<Booking> bookings = bookingDao.getCheckedInBookingByPage(page);
            return new BookingTableModel(bookings);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getCountProfileMax() {
        int count = 0;
        try {

            ResultSet rs = stayProfileDAO.getCountProfile();
            while (rs.next()) {
                count = rs.getInt(1);
            }
            rs.getStatement().close();
        } catch (SQLException ex) {
            Logger.getLogger(CheckOutBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public void showPayService(DefaultTableModel tableModel, int mathuephong) {
        try {
            tableModel.setRowCount(0);
            ResultSet rs = stayProfileDAO.getService(mathuephong);
            while (rs.next()) {
                Object[] row = new Object[6];
                row[0] = rs.getString("tendv");
                row[1] = rs.getTimestamp("ngaydung");
                row[2] = rs.getString("tenphong");
                row[3] = rs.getInt("dongia");
                row[4] = rs.getInt("soluong");
                row[5] = rs.getLong("dongia") * rs.getInt("soluong");

                tableModel.addRow(row);
            }
            rs.getStatement().close();
        } catch (SQLException ex) {
            Logger.getLogger(CheckOutBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getPosition(int ma, int choose) {
        try {
            pos = 0;
            ResultSet rs = stayProfileDAO.getAllProfile();
            if (choose == 1) {
                while (rs.next()) {
                    if (rs.getInt("madatphong") == ma) {
                        return pos;
                    } else {
                        pos++;
                    }
                }
            } else {
                while (rs.next()) {
                    if (rs.getInt("mathuephong") == ma) {
                        return pos;
                    } else {
                        pos++;
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CheckInBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
