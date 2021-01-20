/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hotel.BLL;

import Hotel.DAL.ReportDAL.ReportDAO;
import Hotel.DAL.ReportDAL.ReportType;
import Hotel.DAL.ServiceDAO;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

/**
 *
 * @author Yue
 */
public class ReportBLL {

    private final ReportDAO reportDAO = ReportDAO.getInstance();

    public void setTimeline(int reportType, int timeType, JComboBox comboBox) {
        try {
            comboBox.removeAllItems();
            ResultSet rs = reportDAO.getTimeline(ReportType.SerialOf(reportType), timeType);
            while (rs.next()) {
                comboBox.addItem(rs.getString(1));
            }
            rs.getStatement().close();
        } catch (SQLException ex) {
            Logger.getLogger(ReportBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getServiceNameList(JComboBox comboBox) {
        try {
            comboBox.removeAllItems();
            comboBox.addItem("Tất cả");
            ResultSet rs = ServiceDAO.getInstance().getService();
            while (rs.next()) {
                comboBox.addItem(rs.getString("sname"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DefaultTableModel getReport_ORDER_STATISTIC(ReportCondition reportCondition) {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(ReportType.DOANH_SO_DAT_PHONG.getColumnHeader());
        try {
            ResultSet rs = reportDAO.getReport(ReportType.DOANH_SO_DAT_PHONG, reportCondition);
            java.sql.Date dupe = null;
            int count = 0;
            int roomCount = 0;
            long money = 0;
            while (rs.next()) {
                Object[] obj = new Object[model.getColumnCount()];
                if (dupe == null) {
                    obj[0] = rs.getDate(1);
                    dupe = rs.getDate(1);
                    count = 0;
                    roomCount = 0;
                    money = 0;
                } else if (rs.getDate(1).equals(dupe)) {
                    obj[0] = "";
                } else {
                    obj[0] = "Tổng cộng";
                    obj[1] = count;
                    obj[2] = roomCount;
                    obj[3] = money;
                    model.addRow(obj);
                    dupe = null;
                    rs.previous();
                    continue;
                }
                obj[1] = rs.getInt(2);
                count++;
                obj[2] = rs.getInt(3);
                roomCount++;
                obj[3] = rs.getInt(4);
                money += rs.getInt(4);
                model.addRow(obj);
            }
            model.addRow(new Object[]{
                "Tổng cộng",
                count,
                roomCount,
                money
            });
            rs.getStatement().close();
        } catch (SQLException ex) {
            Logger.getLogger(ReportBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return model;
    }

    public DefaultTableModel getReport_STAY_STATISTIC(ReportCondition reportCondition) {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(ReportType.DOANH_THU_PHONG.getColumnHeader());
        try {
            ResultSet rs = reportDAO.getReport(ReportType.DOANH_THU_PHONG, reportCondition);
            java.sql.Date dupe = null;
            int count = 0;
            int roomCount = 0;
            long money = 0;
            while (rs.next()) {
                Object[] obj = new Object[model.getColumnCount()];
                if (dupe == null) {
                    obj[0] = rs.getDate(1);
                    dupe = rs.getDate(1);
                    count = 0;
                    roomCount = 0;
                    money = 0;
                } else if (rs.getDate(1).equals(dupe)) {
                    obj[0] = "";
                } else {
                    obj[0] = "Tổng cộng";
                    obj[1] = count;
                    obj[2] = roomCount;
                    obj[3] = money;
                    model.addRow(obj);
                    dupe = null;
                    rs.previous();
                    continue;
                }
                obj[1] = rs.getInt(2);
                count++;
                obj[2] = rs.getInt(3);
                roomCount++;
                obj[3] = rs.getInt(4);
                money += rs.getInt(4);
                model.addRow(obj);
            }
            model.addRow(new Object[]{
                "Tổng cộng",
                count,
                roomCount,
                money
            });
            rs.getStatement().close();
        } catch (SQLException ex) {
            Logger.getLogger(ReportBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return model;
    }

    public DefaultTableModel getReport_SERVICE_STATISTIC(ReportCondition reportCondition) {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(ReportType.DOANH_THU_DICH_VU.getColumnHeader());
        ResultSet rs;
        try {
            rs = reportDAO.getReport(ReportType.DOANH_THU_DICH_VU, reportCondition);
            String dupe = null;
            int count = 0;
            long money = 0;
            while (rs.next()) {
                Object[] obj = new Object[model.getColumnCount()];
                if (dupe == null) {
                    obj[0] = rs.getString(1);
                    dupe = rs.getString(1);
                    count = 0;
                    money = 0;
                } else if (rs.getString(1).equals(dupe)) {
                    obj[0] = "";
                } else {
                    obj[0] = "Tổng cộng";
                    obj[2] = count;
                    obj[4] = money;
                    model.addRow(obj);
                    dupe = null;
                    rs.previous();
                    continue;
                }
                obj[1] = rs.getDate(2);
                obj[2] = rs.getInt(3);
                count += rs.getInt(3);
                obj[3] = rs.getInt(4);
                obj[4] = rs.getInt(5);
                money += rs.getInt(5);
                model.addRow(obj);
            }
            model.addRow(new Object[]{
                "Tổng cộng",
                "",
                count,
                "",
                money
            });
            rs.getStatement().close();
        } catch (SQLException ex) {
            Logger.getLogger(ReportBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return model;
    }

    public void exportToExcel(JTable table, File file) {
        String path = file.toString().concat(".xlsx");
        new Hotel.DAL.ReportDAL.ExcelExporter().exportToExcel(table, path);
    }
}
