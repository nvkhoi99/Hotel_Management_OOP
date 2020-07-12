/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hotel.BLL;

import Hotel.BLL.HotelError.ErrorCode;
import Hotel.DAL.ServiceDAO;
import Hotel.DTO.Dichvu;
import Hotel.DTO.Dichvuphong;
import Hotel.GUI.ServiceGUI.RoomServiceForm;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.poi.ooxml.util.IdentifierManager;

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

    public void initServiceList(DefaultTableModel model) {
        try {
            model.setRowCount(0);
            ResultSet rs = serviceDAO.getDichvu();
            while (rs.next()) {
                Object[] col = new Object[4];
                col[0] = rs.getInt("madv");
                col[1] = rs.getString("tendv");
                col[2] = rs.getString("dvtinh");
                col[3] = rs.getInt("dongia");
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
            ResultSet rs = serviceDAO.getDichvuphong(stayId, roomId);
            while (rs.next()) {
                Object[] col = new Object[4];
                col[0] = rs.getString("tendv");
                col[1] = rs.getTimestamp("ngaydung");
                col[2] = rs.getInt("dongia");
                col[3] = rs.getInt("soluong");
                model.addRow(col);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean addRoomServices(int stayId, int roomId, DefaultTableModel model) {
        Dichvuphong[] dichvuphongs = new Dichvuphong[model.getRowCount()];
        Timestamp currentDateTime = new Timestamp(System.currentTimeMillis());
        for (int i = 0; i < model.getRowCount(); i++) {
            dichvuphongs[i] = new Dichvuphong(stayId, roomId, currentDateTime,
                    (int) model.getValueAt(i, 0),
                    (int) model.getValueAt(i, 3),
                    (int) model.getValueAt(i, 4)
            );
        }
        try {
            serviceDAO.addRoomService(dichvuphongs);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceBLL.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public boolean deleteRoomService(int mathue, String tendv, String ngaydung) {
        try {
            int check = JOptionPane.showConfirmDialog(null, "Xác nhân xóa dịch vụ phòng?", "Xác nhận",
                    JOptionPane.YES_NO_OPTION);
            if (check == JOptionPane.YES_OPTION) {
                Dichvuphong dichvuphong = new Dichvuphong();
                dichvuphong.setMathuephong(mathue);
                dichvuphong.setMadv(serviceDAO.getServiceIdByName(tendv));
                dichvuphong.setNgaydung(Timestamp.valueOf(ngaydung));
                serviceDAO.deleteRoomService(dichvuphong);
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

    public void AddService(String tendichvu, String donvitinh, String dongia) throws HotelError {
        try {
            int nipp = Integer.valueOf(dongia);
            if (nipp < 0) {
                throw new HotelError(ErrorCode.INVALID_PRICE);

            }
            Dichvu dichvu = new Dichvu();
            dichvu.setTendv(tendichvu);
            dichvu.setDvtinh(donvitinh);
            dichvu.setDongia(nipp);
            serviceDAO.addServiceeeeee(dichvu);
            JOptionPane.showConfirmDialog(null, "Bạn sẽ không sai nếu không ai bik bạn đang nàm gì =)) ", "A GREAT MAN ONCE SAID : ", JOptionPane.OK_OPTION);
        } catch (HotelError ex) {
            ex.notifyError();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void UpdateService(String madichvu, String tendichvu, String donvitinh, String dongia) {
        try {
            int nipp = Integer.valueOf(dongia);
            if (nipp < 0) {
                throw new HotelError(ErrorCode.INVALID_PRICE);
            }
            Dichvu dichvu = new Dichvu();
            dichvu.setMadv(Integer.valueOf(madichvu));
            dichvu.setTendv(tendichvu);
            dichvu.setDvtinh(donvitinh);
            dichvu.setDongia(nipp);

            serviceDAO.updateService(dichvu);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceBLL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HotelError ex) {
            ex.notifyError();
        }
        JOptionPane.showConfirmDialog(null, "Xin chúc mừng bạn đã sửa dịch vụ thành công !!! ", "★ Happy New Year ★ ", JOptionPane.YES_OPTION);
    }

    public boolean updateRoomService(int mathue, String tendv, String soluong, String ngaydung) {
        try {
            int num = Integer.valueOf(soluong);
            if (num <= 0) {
                JOptionPane.showConfirmDialog(null, "Đơn giá thì phải lơn hơn 0 ms bán đc chứ có phải người âm đ đâu mà để nhỏ hơn  =))",
                        "No Caption !!!", JOptionPane.OK_OPTION);
                throw new HotelError(ErrorCode.INVALID_NUM);
            }
            Dichvuphong dichvuphong = new Dichvuphong();
            dichvuphong.setMathuephong(mathue);
            dichvuphong.setMadv(serviceDAO.getServiceIdByName(tendv));
            dichvuphong.setSoluong(num);
            dichvuphong.setNgaydung(Timestamp.valueOf(ngaydung));
            serviceDAO.updateRoomService(dichvuphong);
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
