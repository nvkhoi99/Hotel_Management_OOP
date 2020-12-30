package Hotel.BLL;

import Hotel.BLL.HotelError.ErrorCode;
import Hotel.DAL.*;
import Hotel.DTO.Customer;
import Hotel.DTO.Booking;
import Hotel.DTO.RoomType;
import Hotel.DTO.rooms.BookingRoom;
import Hotel.DTO.rooms.Room;
import Hotel.DTO.rooms.RoomForOrdering;
import Hotel.GUI.BookingGUI.BookingForm;
import Hotel.GUI.BookingGUI.RoomToggle;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Yue
 */
public class BookingBLL {

    private final BookingForm bookingForm;
    private final OrderDAO orderDAO = OrderDAO.getInstance();
    public final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private final BookingDao bookingDao = new BookingDao();

    public BookingBLL() {
        this.bookingForm = null;
    }

    public BookingBLL(BookingForm bookingForm) {
        this.bookingForm = bookingForm;
    }

    public List<RoomType> getAllRoomType() {
        ArrayList<RoomType> roomTypeList = new ArrayList<>();
        try {
            ResultSet rs = RoomTypeDAO.getInstance().getRoomTypeList();
            while (rs.next()) {
                roomTypeList.add(new RoomType(
                        rs.getInt("tid"),
                        rs.getString("tname"),
                        rs.getInt("tprice")
                ));
            }
            rs.getStatement().close();
        } catch (SQLException ex) {
            Logger.getLogger(BookingBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return roomTypeList;
    }

    public List<RoomForOrdering> getAllRoom() {
        ArrayList<RoomForOrdering> roomList = new ArrayList<>();
        try {
            ResultSet rs = RoomDAO.getInstance().getAllRoom();
            while (rs.next()) {
                RoomForOrdering room = new RoomForOrdering();
                room.setId(rs.getInt("rid"));
                room.setName(rs.getString("rname"));
                room.setTypeId(rs.getInt("tid"));
                room.setAvailable(rs.getBoolean("rstatus"));
                roomList.add(room);
            }
            rs.getStatement().close();
        } catch (SQLException ex) {
            Logger.getLogger(BookingBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return roomList;
    }

    public ArrayList<RoomToggle> getRoomByRoomType(RoomToggle[] roomToggles, int tid) {
        ArrayList<RoomToggle> temp_RoomToggles = new ArrayList<>();
        for (RoomToggle i : roomToggles) {
            if (i.getRoom().getTypeId() == tid) {
                temp_RoomToggles.add(i);
            }
        }
        return temp_RoomToggles;
    }

    public Customer checkIdCard(String idCard) {
        try {
            if (!idCard.equals("")) {
                long temp_number = Long.valueOf(idCard);
            }
        } catch (NumberFormatException e) {
            try {
                throw new HotelError(ErrorCode.INVALID_IDCARD);
            } catch (HotelError ex) {
                ex.notifyError();
                bookingForm.eraseIdCard();
                return null;
            }
        }
        Customer customer = null;
        try {
            ResultSet rs = CustomerDAO.getInstance().getCustomerByIdCard(idCard);
            if (rs.next()) {
                customer = new Customer(rs.getInt("cid"),
                        rs.getString("cname"),
                        rs.getBoolean("cgender"),
                        idCard,
                        rs.getString("caddress"),
                        rs.getString("cphone")
                );
            }
            rs.getStatement().close();
        } catch (SQLException ex) {
            Logger.getLogger(BookingBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customer;
    }

    public boolean checkPhone(String sdt) {
        try {
            if (!sdt.equals("")) {
                long temp_number = Long.valueOf(sdt);
            }
        } catch (NumberFormatException e) {
            try {
                throw new HotelError(ErrorCode.INVALID_PHONE);
            } catch (HotelError ex) {
                ex.notifyError();
                return false;
            }
        }
        return true;
    }

    public Timestamp convert(String datetime, int hour) {
        Timestamp timestamps = null;
        try {
            long ms = dateFormat.parse(datetime).getTime();
            timestamps = new Timestamp(ms);
            timestamps.setHours(hour);
        } catch (ParseException ex) {
            Logger.getLogger(BookingBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return timestamps;
    }

    public ArrayList<Integer> checkOrder(String ngaynhan, String ngaytra) {
        ArrayList<Integer> orderedRooms = new ArrayList<>();
        try {
            Timestamp checkIn = convert(ngaynhan, Rules.STANDARD_CHECK_IN_HOUR);
            Timestamp checkOut = convert(ngaytra, Rules.STANDARD_CHECK_OUT_HOUR);
            ResultSet rs = orderDAO.getOrderedRoomsByTime(checkIn, checkOut);
            while (rs.next()) {
                orderedRooms.add(rs.getInt("rid"));
            }
            rs.getStatement().close();
        } catch (SQLException ex) {
            Logger.getLogger(BookingBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orderedRooms;
    }

    private Timestamp addTime(java.sql.Date date, int hour) {
        long ms = date.getTime() + 1000 * 60 * 60 * hour;
        Timestamp result = new Timestamp(ms);
        return result;
    }

    public ArrayList<Integer> checkOrder_new(Date ngaynhan, Date ngaytra) {
        ArrayList<Integer> availableRooms = new ArrayList<>();
        try {
            Timestamp checkIn = addTime(ngaynhan, Rules.STANDARD_CHECK_IN_HOUR);
            Timestamp checkOut = addTime(ngaytra, Rules.STANDARD_CHECK_OUT_HOUR);
//            ResultSet rs = orderDAO.getOrderedRoomsByTime(checkIn, checkOut);
//            while (rs.next()) {
//                orderedRooms.add(rs.getInt("rid"));
//            }
//            rs.getStatement().close();
            for (Room room : RoomDAO.getInstance().getAvailableRoom(checkIn, checkOut)) {
                availableRooms.add(room.getId());
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookingBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return availableRooms;
    }

    public void updateDeposit() {
        bookingForm.updateDeposit();
    }

    public int addOrder(Customer customer, String ngaynhan, String ngaytra,
                        ArrayList<RoomForOrdering> rooms, int state) {
        int orderId = 0;
        int check = JOptionPane.showConfirmDialog(null, "Xác nhận đặt phòng?", "Xác nhận", JOptionPane.OK_CANCEL_OPTION);
        if (check == JOptionPane.YES_OPTION) {
            try {
                if (rooms.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Chưa chọn phòng nào");
                    return 0;
                }
                if (customer.getId() == 0) {
                    customer.setId(CustomerDAO.getInstance().addCustomer(customer));
                }
                Timestamp checkIn = convert(ngaynhan, Rules.STANDARD_CHECK_IN_HOUR);
                Timestamp checkOut = convert(ngaytra, Rules.STANDARD_CHECK_OUT_HOUR);
                int sum = 0;
                for (RoomForOrdering room : rooms) {
                    sum += room.getPrice();
                }
                orderId = orderDAO.addOrder(new Booking(customer.getId(),
                        new Timestamp(System.currentTimeMillis()), checkIn, checkOut, sum, state));
                orderDAO.addRoomToOrder(orderId, rooms);
            } catch (SQLException ex) {
                try {
                    Logger.getLogger(BookingBLL.class.getName()).log(Level.SEVERE, null, ex);
                    DbConn.getConnection().rollback();
                    return 0;
                } catch (SQLException ex1) {
                    Logger.getLogger(BookingBLL.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }
        return orderId;
    }

    public int addBooking(Customer customer, String ngaynhan, String ngaytra,
                          ArrayList<RoomForOrdering> rooms, int state) {
        int bookingId = 0;
        int check = JOptionPane.showConfirmDialog(null, "Xác nhận đặt phòng?", "Xác nhận", JOptionPane.OK_CANCEL_OPTION);
        if (check == JOptionPane.YES_OPTION) {
            try {
                if (rooms.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Chưa chọn phòng nào");
                    return 0;
                }
                if (customer.getId() == 0) {
                    customer.setId(CustomerDAO.getInstance().addCustomer(customer));
                }
                Booking booking = new Booking();
                booking.setCustomerId(customer.getId());
                booking.setCheckInTime(convert(ngaynhan, Rules.STANDARD_CHECK_IN_HOUR));
                booking.setCheckOutTime(convert(ngaytra, Rules.STANDARD_CHECK_OUT_HOUR));
                int sum = 0;
                for (RoomForOrdering room : rooms) {
                    booking.getBookingRooms().add(new BookingRoom(
                            room.getId(),
                            room.getPrice()
                    ));
                    sum += room.getPrice();
                }
                booking.setDeposit(state == 0 ? 0 : sum);
                booking.setStatus(state);
                bookingId = bookingDao.addBooking(booking);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
        return bookingId;
    }

    public boolean checkOutRooms(List<Integer> bookingRoomIds) {
        try {
            int surchargePercent = 0;
            int hour = Calendar.getInstance().getTime().getHours();
            if (hour >= Rules.STANDARD_CHECK_OUT_HOUR
                    && hour < Rules.MAX_LATELY_CHECK_OUT_HOUR) {
                int surchargeCheck = JOptionPane.showConfirmDialog(null, "Tính phí trả muộn?",
                        "Trả muộn", JOptionPane.YES_NO_CANCEL_OPTION);
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
            bookingDao.checkOutRooms(bookingRoomIds, surchargePercent);
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }

    public boolean payTotalBooking(int bookingId) {
        try {
            bookingDao.payBooking(bookingId);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}