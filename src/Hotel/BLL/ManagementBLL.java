package Hotel.BLL;

import Hotel.BLL.HotelError.ErrorCode;
import Hotel.DAL.BookingDao;
import Hotel.DAL.CustomerDAO;
import Hotel.DTO.rooms.Room;
import Hotel.DTO.rooms.RoomForManaging;
import Hotel.DTO.RoomType;
import Hotel.DAL.RoomTypeDAO;
import Hotel.DAL.RoomDAO;
import Hotel.DTO.Booking;
import Hotel.DTO.Customer;
import Hotel.GUI.MainForm;
import Hotel.GUI.ManagementGUI.ManagementForm;
import Hotel.GUI.ManagementGUI.RoomButton;
import Hotel.GUI.ManagementGUI.RoomTypePanel;
import Hotel.GUI.common.IncommingBookingTableModel;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import java.sql.Timestamp;

/**
 *
 * @author Yue
 */
public class ManagementBLL {

    private final ManagementForm rootForm;
    private final RoomDAO roomDAO = RoomDAO.getInstance();
    private final RoomTypeDAO roomTypeDAO = RoomTypeDAO.getInstance();
    private ArrayList<RoomType> roomTypes;
    private ArrayList<RoomForManaging> roomForManagings;

    private final BookingDao bookingDao = new BookingDao();

    public ManagementBLL() {
        rootForm = null;
    };

    public ManagementBLL(ManagementForm rootForm) {
        this.rootForm = rootForm;
    }

    public ArrayList<RoomType> getRoomTypeList() {
        roomTypes = new ArrayList<>();
        try {
            ResultSet rs = roomTypeDAO.getRoomTypeList();
            while (rs.next()) {
                roomTypes.add(new RoomType(rs.getInt("tid"),
                        rs.getString("tname"),
                        rs.getInt("tprice")));
            }
            rs.getStatement().close();
        } catch (SQLException ex) {
            Logger.getLogger(ManagementBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return roomTypes;
    }

    public ArrayList<RoomForManaging> getAllRoom() {
        roomForManagings = new ArrayList<>();
        try {
            ResultSet rs = roomDAO.getAllRoom();
            while (rs.next()) {
                roomForManagings.add(new RoomForManaging(rs.getInt("rid"),
                        rs.getString("rname"),
                        rs.getInt("tid"),
                        rs.getInt("current_booking"),
                        rs.getBoolean("rstatus")));
            }
            rs.getStatement().close();
        } catch (SQLException ex) {
            Logger.getLogger(ManagementBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return roomForManagings;
    }

    public ArrayList<RoomButton> getRoomByRoomType(RoomButton[] roomButtons, int maloaiphong) {
        ArrayList<RoomButton> temp_RoomButton = new ArrayList<>();
        for (RoomButton i : roomButtons) {
            if (i.getRoom().getTypeId() == maloaiphong) {
                temp_RoomButton.add(i);
            }
        }
        return temp_RoomButton;
    }

    private void checkValidate(RoomType rt) throws HotelError {
        if (rt.getName().length() <= 0) {
            throw new HotelError(ErrorCode.EMPTY_NAME);
        }
        if (rt.getPrice() <= 0) {
            throw new HotelError(ErrorCode.INVALID_PRICE);
        } else if (rt.getPrice() <= 100000) {
            throw new HotelError(ErrorCode.LOW_PRICE);
        }
    }

    public boolean addNewRoomType(RoomType rt) {
        try {
            checkValidate(rt);
            roomTypeDAO.addRoomType(rt);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return false;
        } catch (HotelError ex) {
            ex.notifyError();
            return false;
        }
        return true;
    }

    public boolean updateRoomType(RoomType rt) {
        try {
            if (roomTypeDAO.checkRoomTypeName(rt)) {
                throw new HotelError(ErrorCode.NAME_DUPLICATE);
            }
            checkValidate(rt);
            roomTypeDAO.updateRoomType(rt);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return false;
        } catch (HotelError ex) {
            ex.notifyError();
            return false;
        }
        rootForm.updateUI();
        return true;
    }

    public boolean deleteRoomType(RoomTypePanel roomTypePanel, RoomType roomType) {
        try {
            int check = JOptionPane.showConfirmDialog(null, "Xác nhận xóa loại phòng " + roomType.getName() + "?",
                    "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (check == JOptionPane.YES_OPTION) {
                roomTypeDAO.deleteRoomType(roomType.getId());
                rootForm.deleteRoomType(roomTypePanel);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return false;
        }
        return true;
    }

    public boolean addNewRoom(int typeId, String roomName) {
        try {
            Room room = new Room() {};
            room.setName(roomName);
            room.setTypeId(typeId);
            roomDAO.addNewRoom(room);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
        rootForm.initRoomList();
        return true;
    }

    public ArrayList<String> getNameTypeList() {
        ArrayList<String> temp_list = new ArrayList<>();
        try {
            ResultSet rs = roomTypeDAO.getRoomTypeList();
            while (rs.next()) {
                temp_list.add(rs.getString("tname"));
            }
            rs.getStatement().close();
        } catch (SQLException ex) {
            Logger.getLogger(ManagementBLL.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return temp_list;
    }

    public boolean updateRoom(Room room, String newName, String newType) {
        try {
            Room newRoom = new Room() {};
            newRoom.setId(room.getId());
            newRoom.setName(newName);
            int typeId = roomTypeDAO.getTypeIdByName(newType);
            newRoom.setTypeId(typeId);
            roomDAO.updateRoomInfo(newRoom);
            room.setName(newName);
            room.setTypeId(typeId);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return false;
        }
        rootForm.sortRoom();
        rootForm.updateUI();
        return true;
    }

    public boolean deleteRoom(RoomButton roomButton) {
        if (JOptionPane.showConfirmDialog(null, "Xác nhận xóa phòng " + roomButton.getRoom().getName() + "?",
                "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            try {
                roomDAO.deleteRoom(roomButton.getRoom().getId());
                roomButton.getTypePanel().removeRoom(roomButton);
                rootForm.updateUI();
                return true;
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
                return false;
            }
        } else {
            return false;
        }
    }

    public void goToProfile(int stayId) {
        MainForm mainForm = (MainForm) rootForm.getTopLevelAncestor();
        mainForm.goToProfile(stayId);
    }

    public void changeStateRoom(Room room) {
        try {
            roomDAO.changeStateRoom(room.getId(), room.isAvailable());
        } catch (SQLException ex) {
            Logger.getLogger(ManagementBLL.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public TableModel updateIncommingBookings(Room room) {
        IncommingBookingTableModel model = null;
        try {
            model = new IncommingBookingTableModel(bookingDao.getIncommingBooking(room));
        } catch (Exception ex) {
            Logger.getLogger(BookingBLL.class.getName()).log(Level.SEVERE, null, ex);
        }

        return model;
    }

    public String getCustomerName(int id) {
        String fullname = "";
        try {
            Customer customer = CustomerDAO.getInstance().getCustomerById(id);
            fullname = customer.getFullname();
        } catch (SQLException ex) {
            Logger.getLogger(ManagementBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fullname;
    }

    public Booking getBookingByRoom(RoomForManaging room) {
        Booking booking = null;
        try {
            booking = bookingDao.getBookingInfo(room.getCurrentBooking(), room.getId());
        } catch (Exception ex) {
            Logger.getLogger(ManagementBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return booking;
    }

    public ArrayList<Room> getAvailableRooms(Timestamp from, Timestamp to) {
        try {
            return roomDAO.getAvailableRoom(from, to);
        } catch (SQLException ex) {
            Logger.getLogger(ManagementBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean changeRoom(int bookingRoomId, int newRoom, boolean pay) {
        try {
            bookingDao.changeRoom(bookingRoomId, newRoom, pay);
        } catch (SQLException ex) {
            Logger.getLogger(ManagementBLL.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
}
