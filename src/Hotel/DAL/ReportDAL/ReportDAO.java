/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hotel.DAL.ReportDAL;

import Hotel.BLL.ReportDates;
import Hotel.DAL.DbConn;
import java.sql.*;

/**
 *
 * @author Yue
 */
public class ReportDAO {

    private final Connection conn = DbConn.getConnection();
    private static ReportDAO instance = null;

    private ReportDAO() {

    }

    public static ReportDAO getInstance() {
        if (instance == null) {
            instance = new ReportDAO();
        }
        return instance;
    }

    public ResultSet getTimeline(ReportType reportType, int timeType) throws SQLException {
        String sql = null;
        if (null == reportType) {

        } else {
            switch (reportType) {
                case DOANH_SO_DAT_PHONG:
                    sql = "SELECT date_format(convert(ngaydat, DATE),?) as thang "
                            + "FROM dondatphong group by date_format(convert(ngaydat, DATE),?) "
                            + "ORDER BY ngaydat DESC";
                    break;
                case DOANH_THU_PHONG:
                    sql = "SELECT date_format(convert(thuctra, DATE),?) as thang "
                            + "FROM hosothuephong group by date_format(convert(thuctra, DATE),?) "
                            + "ORDER BY thuctra DESC";
                    break;
                case DOANH_THU_DICH_VU:
                    sql = "SELECT date_format(convert(ngaydung, DATE),?) as thang "
                            + "FROM dichvuphong group by date_format(convert(ngaydung, DATE),?) "
                            + "ORDER BY ngaydung DESC";
            }
        }
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, timeType == 0 ? "%m/%Y" : "%Y");
        ps.setString(2, timeType == 0 ? "%m/%Y" : "%Y");
        ResultSet rs = ps.executeQuery();

        return rs;
    }

    public ResultSet getReport(ReportType reportType, ReportDates reportDates) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(reportType.getSQL());
        ps.setDate(1, reportDates.getFrom());
        ps.setDate(2, reportDates.getTo());
        ResultSet rs = ps.executeQuery();

        return rs;
    }

    public ResultSet getReportAddingCondition(ReportType reportType, ReportDates reportDates, String condition)
            throws SQLException {
        String sql = reportType.getSQL().concat(condition);
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setDate(1, reportDates.getFrom());
        ps.setDate(2, reportDates.getTo());
        ResultSet rs = ps.executeQuery();

        return rs;
    }
}
