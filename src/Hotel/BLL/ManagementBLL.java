package Hotel.BLL;

import Hotel.BLL.HotelError.ErrorCode;
import Hotel.DTO.Room.Room;
import Hotel.DTO.Room.RoomForManaging;
import Hotel.DTO.RoomType;
import Hotel.DAL.RoomTypeDAO;
import Hotel.DAL.RoomDAO;
import Hotel.GUI.MainForm;
import Hotel.GUI.ManagementGUI.ManagementForm;
import Hotel.GUI.ManagementGUI.RoomButton;
import Hotel.GUI.ManagementGUI.RoomTypePanel;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

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

    public ManagementBLL(ManagementForm rootForm) {
        this.rootForm = rootForm;
    }

    public ArrayList<RoomType> getRoomTypeList() {
        roomTypes = new ArrayList<>();
        try {
            ResultSet rs = roomTypeDAO.getRoomTypeList();
            while (rs.next()) {
                roomTypes.add(new RoomType(rs.getInt("maloaiphong"),
                        rs.getString("tenloaiphong"),
                        rs.getInt("dongia")));
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
                roomForManagings.add(new RoomForManaging(rs.getInt("maphong"),
                        rs.getString("tenphong"),
                        rs.getInt("maloaiphong"),
                        rs.getInt("mahientai"),
                        rs.getBoolean("tinhtrang")));
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
            if (i.getRoom().getMaloaiphong() == maloaiphong) {
                temp_RoomButton.add(i);
            }
        }
        return temp_RoomButton;
    }

    private void checkValidate(RoomType rt) throws HotelError {
        if (rt.getTenloaiphong().length() <= 0) {
            throw new HotelError(ErrorCode.EMPTY_NAME);
        }
        if (rt.getDongia() <= 0) {
            throw new HotelError(ErrorCode.INVALID_PRICE);
        } else if (rt.getDongia() <= 100000) {
            throw new HotelError(ErrorCode.LOW_PRICE);
        }
    }

    public boolean addNewRoomType(RoomType rt) {
        try {
            checkValidate(rt);
            roomTypeDAO.addRoomType(rt);
        } catch (SQLException ex) {
            Logger.getLogger(ManagementBLL.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ManagementBLL.class.getName()).log(Level.SEVERE, null, ex);
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
            if (roomDAO.checkRoomByType(roomType.getMaloaiphong())) {
                throw new HotelError(ErrorCode.STILL_HAVE_ROOM);
            }
            int check = JOptionPane.showConfirmDialog(null, "Xác nhận xóa loại phòng " + roomType.getTenloaiphong() + "?",
                    "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (check == JOptionPane.YES_OPTION) {
                roomTypeDAO.deleteRoomType(roomType.getMaloaiphong());
                rootForm.deleteRoomType(roomTypePanel);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManagementBLL.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (HotelError ex) {
            ex.notifyError();
            return false;
        }
        return true;
    }

    public boolean addNewRoom(int typeId, String roomName) {
        try {
            ArrayList<RoomForManaging> temp = rootForm.getRoomForManagings();
            for (int i = 0; i < temp.size(); i++) {
                if (temp.get(i).getTenphong().equalsIgnoreCase(roomName)) {
                    throw new HotelError(ErrorCode.NAME_DUPLICATE);
                }
            }
            if (roomName.equals("")) {
                throw new HotelError(ErrorCode.EMPTY_NAME);
            }
            Room room_temp = new Room();
            room_temp.setMaloaiphong(typeId);
            room_temp.setTenphong(roomName);

            roomDAO.addNewRoom(room_temp);
        } catch (SQLException ex) {
            Logger.getLogger(ManagementBLL.class
                    .getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (HotelError ex) {
            ex.notifyError();
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
                temp_list.add(rs.getString("tenloaiphong"));
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
            if (newName.equals("")) {
                throw new HotelError(ErrorCode.EMPTY_NAME);
            }
            if (roomDAO.checkRoomName(newName, room.getMaphong())) {
                throw new HotelError(ErrorCode.NAME_DUPLICATE);
            }
            Room new_room = new Room();
            new_room.setMaphong(room.getMaphong());
            new_room.setTenphong(newName);
            new_room.setMaloaiphong(roomTypeDAO.getTypeIdByName(newType));
            roomDAO.updateRoomInfo(new_room);

            room.setTenphong(new_room.getTenphong());
            room.setMaloaiphong(new_room.getMaloaiphong());
        } catch (SQLException ex) {
            Logger.getLogger(ManagementBLL.class
                    .getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (HotelError ex) {
            ex.notifyError();
            return false;
        }
        rootForm.sortRoom();
        rootForm.updateUI();
        return true;
    }

    public boolean deleteRoom(RoomButton roomButton) {
        try {
            ResultSet rs = roomDAO.getOrdersByRoomId(roomButton.getRoom().getMaphong());
            if (rs.next()) {
                throw new HotelError(ErrorCode.STILL_HAVE_ORDER);
            }
        } catch (HotelError ex) {
            ex.notifyError();
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(ManagementBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (JOptionPane.showConfirmDialog(null, "Xác nhận xóa phòng " + roomButton.getRoom().getTenphong() + "?",
                "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            try {
                roomDAO.deleteRoom(roomButton.getRoom().getMaphong());
                roomButton.getTypePanel().removeRoom(roomButton);
                rootForm.updateUI();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(ManagementBLL.class
                        .getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } else {
            return false;
        }
    }

    public void updateRoot() {
        rootForm.updateUI();
    }

    public void goToProfile(int stayId) {
        MainForm mainForm = (MainForm) rootForm.getTopLevelAncestor();
        mainForm.goToProfile(stayId);
    }

    public void changeStateRoom(Room room) {
        try {
            roomDAO.changeStateRoom(room.getMaphong(), room.isAvailable());
        } catch (SQLException ex) {
            Logger.getLogger(ManagementBLL.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
}
