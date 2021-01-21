/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hotel.BLL;

import Hotel.BLL.HotelError.ErrorCode;
import Hotel.DAL.ServiceDAO;
import Hotel.DTO.services.RoomService;
import Hotel.DTO.services.Service;
import Hotel.GUI.ServiceGUI.RoomServiceForm;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Yue
 */
public class ServiceBLL {

    private ServiceDAO serviceDAO = ServiceDAO.getInstance();
    private RoomServiceForm roomServiceForm;

    public ServiceBLL() {

    }

    public ServiceBLL(RoomServiceForm roomServiceForm) {
        this.roomServiceForm = roomServiceForm;
    }

    public List<Service> getAllService() {
        ArrayList<Service> services = new ArrayList<>();
        try {
            ResultSet rs = serviceDAO.getService();
            while (rs.next()) {
                services.add(new Service(
                        rs.getInt("sid"),
                        rs.getString("sname"),
                        rs.getString("sunit"),
                        rs.getInt("sprice")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return services;
    }

    public void initServiceList(DefaultTableModel model) {
        try {
            model.setRowCount(0);
            ResultSet rs = serviceDAO.getService();
            while (rs.next()) {
                Object[] col = new Object[4];
                col[0] = rs.getInt("sid");
                col[1] = rs.getString("sname");
                col[2] = rs.getString("sunit");
                col[3] = rs.getInt("sprice");
                model.addRow(col);
            }
            rs.getStatement().close();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void initRoomServiceList(int stayId, int roomId, DefaultTableModel model) {
        try {
            model.setRowCount(0);
            ResultSet rs = serviceDAO.getRoomService(stayId, roomId);
            while (rs.next()) {
                Object[] col = new Object[5];
                col[0] = rs.getInt("bsid");
                col[1] = rs.getString("sname");
                col[2] = rs.getTimestamp("bstime");
                col[3] = rs.getInt("bsprice");
                col[4] = rs.getInt("quantity");
                model.addRow(col);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean addRoomServices(int stayId, int roomId, DefaultTableModel model) {
        RoomService[] roomServices = new RoomService[model.getRowCount()];
        Timestamp currentDateTime = new Timestamp(System.currentTimeMillis());
        for (int i = 0; i < model.getRowCount(); i++) {
            roomServices[i] = new RoomService(
                    stayId,
                    roomId,
                    (int) model.getValueAt(i, 0),
                    currentDateTime,
                    (int) model.getValueAt(i, 3),
                    (int) model.getValueAt(i, 4)
            );
        }
        try {
            serviceDAO.addRoomService(roomServices);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceBLL.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public boolean deleteRoomService(int id) {
        try {
            int check = JOptionPane.showConfirmDialog(null, "Xác nhân xóa dịch vụ phòng?", "Xác nhận",
                    JOptionPane.YES_NO_OPTION);
            if (check == JOptionPane.YES_OPTION) {
                serviceDAO.deleteRoomService(id);
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceBLL.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public void DeleteService(String madichvu) {
        try {
            int nipp = Integer.valueOf(madichvu);
            serviceDAO.deleteService(nipp);
            JOptionPane.showConfirmDialog(null, "Xin chúc mừng ! Bạn đã giết dịch vụ thành công ! <3 ", "Yo , Ya  Murderer ", JOptionPane.OK_OPTION);
        } catch (Exception e) {

        }
    }

    public void AddService(String tendichvu, String donvitinh, String sprice) throws HotelError {
        try {
            int nipp = Integer.valueOf(sprice);
            if (nipp < 0) {
                throw new HotelError(ErrorCode.INVALID_PRICE);

            }
            Service service = new Service();
            service.setName(tendichvu);
            service.setUnit(donvitinh);
            service.setPrice(nipp);
            serviceDAO.addServiceeeeee(service);
            JOptionPane.showConfirmDialog(null, "Thêm thành công ", "A GREAT MAN ONCE SAID : ", JOptionPane.OK_OPTION);
        } catch (HotelError ex) {
            ex.notifyError();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public void UpdateService(String madichvu, String tendichvu, String donvitinh, String sprice) {
        try {
            int nipp = Integer.valueOf(sprice);
            if (nipp < 0) {
                throw new HotelError(ErrorCode.INVALID_PRICE);
            }
            Service service = new Service();
            service.setId(Integer.valueOf(madichvu));
            service.setName(tendichvu);
            service.setUnit(donvitinh);
            service.setPrice(nipp);

            serviceDAO.updateService(service);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceBLL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HotelError ex) {
            ex.notifyError();
        }
        JOptionPane.showConfirmDialog(null, "Xin chúc mừng bạn đã sửa dịch vụ thành công !!! ", "★ Happy New Year ★ ", JOptionPane.YES_OPTION);
    }

    public boolean updateRoomService(int mathue, String sname, String quantity, String bstime) {
        try {
            int num = Integer.valueOf(quantity);
            if (num <= 0) {
                JOptionPane.showConfirmDialog(null, "Đơn giá thì phải lơn hơn 0 ms bán đc chứ có phải người âm đ đâu mà để nhỏ hơn  =))",
                        "No Caption !!!", JOptionPane.OK_OPTION);
                throw new HotelError(ErrorCode.INVALID_NUM);
            }
            RoomService roomService = new RoomService();
            roomService.setBookingId(mathue);
            roomService.setServiceId(serviceDAO.getServiceIdByName(sname));
            roomService.setQuantity(num);
            roomService.setUseTime(Timestamp.valueOf(bstime));
            serviceDAO.updateRoomService(roomService);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceBLL.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (HotelError ex) {
            ex.notifyError();
            return false;
        } catch (NumberFormatException ex) {
            new HotelError(ErrorCode.INVALID_NUM).notifyError();
            return false;
        }
        return true;
    }
}
