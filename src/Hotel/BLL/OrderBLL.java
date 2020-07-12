package Hotel.BLL;

import Hotel.BLL.HotelError.ErrorCode;
import Hotel.DTO.Customer;
import Hotel.DTO.Order;
import Hotel.DTO.Room.RoomForOrdering;
import Hotel.DAL.CustomerDAO;
import Hotel.DAL.DbConn;
import Hotel.DAL.OrderDAO;
import Hotel.DAL.RoomDAO;
import Hotel.GUI.OrderGUI.OrderForm;
import Hotel.GUI.OrderGUI.RoomToggle;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Yue
 */
public class OrderBLL {

    private final OrderForm orderForm;
    private final OrderDAO orderDAO = OrderDAO.getInstance();
    public final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public OrderBLL(OrderForm orderForm) {
        this.orderForm = orderForm;
    }

    public ArrayList<RoomForOrdering> getAllRoom() {
        ArrayList<RoomForOrdering> roomList = new ArrayList<>();
        try {
            ResultSet rs = RoomDAO.getInstance().getAllRoom();
            while (rs.next()) {
                RoomForOrdering room = new RoomForOrdering();
                room.setMaphong(rs.getInt("maphong"));
                room.setTenphong(rs.getString("tenphong"));
                room.setMaloaiphong(rs.getInt("maloaiphong"));
                room.setAvailable(rs.getBoolean("tinhtrang"));
                roomList.add(room);
            }
            rs.getStatement().close();
        } catch (SQLException ex) {
            Logger.getLogger(OrderBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return roomList;
    }

    public ArrayList<RoomToggle> getRoomByRoomType(RoomToggle[] roomToggles, int maloaiphong) {
        ArrayList<RoomToggle> temp_RoomToggles = new ArrayList<>();
        for (RoomToggle i : roomToggles) {
            if (i.getRoom().getMaloaiphong() == maloaiphong) {
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
                orderForm.eraseIdCard();
                return null;
            }
        }
        Customer customer = null;
        try {
            ResultSet rs = CustomerDAO.getInstance().getCustomerByIdCard(idCard);
            if (rs.next()) {
                customer = new Customer(rs.getInt("makh"),
                        rs.getString("hotenkh"),
                        rs.getBoolean("gioitinh"),
                        idCard,
                        rs.getString("diachi"),
                        rs.getString("sdt")
                );
            }
            rs.getStatement().close();
        } catch (SQLException ex) {
            Logger.getLogger(OrderBLL.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(OrderBLL.class.getName()).log(Level.SEVERE, null, ex);
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
                orderedRooms.add(rs.getInt("maphong"));
            }
            rs.getStatement().close();
        } catch (SQLException ex) {
            Logger.getLogger(OrderBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orderedRooms;
    }

    private Timestamp addTime(java.sql.Date date, int hour) {
        long ms = date.getTime() + 1000 * 60 * 60 * hour;
        Timestamp result = new Timestamp(ms);
        return result;
    }

    public ArrayList<Integer> checkOrder_new(java.sql.Date ngaynhan, java.sql.Date ngaytra) {
        ArrayList<Integer> orderedRooms = new ArrayList<>();
        try {
            Timestamp checkIn = addTime(ngaynhan, Rules.STANDARD_CHECK_IN_HOUR);
            Timestamp checkOut = addTime(ngaytra, Rules.STANDARD_CHECK_OUT_HOUR);
            ResultSet rs = orderDAO.getOrderedRoomsByTime(checkIn, checkOut);
            while (rs.next()) {
                orderedRooms.add(rs.getInt("maphong"));
            }
            rs.getStatement().close();
        } catch (SQLException ex) {
            Logger.getLogger(OrderBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orderedRooms;
    }

    public void updateDeposit() {
        orderForm.updateDeposit();
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
                if (customer.getMakh() == 0) {
                    customer.setMakh(CustomerDAO.getInstance().addCustomer(customer));
                }
                Timestamp checkIn = convert(ngaynhan, Rules.STANDARD_CHECK_IN_HOUR);
                Timestamp checkOut = convert(ngaytra, Rules.STANDARD_CHECK_OUT_HOUR);
                int sum = 0;
                for (RoomForOrdering room : rooms) {
                    sum += room.getDongiadat();
                }
                orderId = orderDAO.addOrder(new Order(customer.getMakh(),
                        new Timestamp(System.currentTimeMillis()), checkIn, checkOut, sum, state));
                orderDAO.addRoomToOrder(orderId, rooms);
            } catch (SQLException ex) {
                try {
                    Logger.getLogger(OrderBLL.class.getName()).log(Level.SEVERE, null, ex);
                    DbConn.getConnection().rollback();
                    return 0;
                } catch (SQLException ex1) {
                    Logger.getLogger(OrderBLL.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }
        return orderId;
    }
}
