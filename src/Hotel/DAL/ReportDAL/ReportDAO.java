/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hotel.DAL.ReportDAL;

import Hotel.BLL.ReportCondition;
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
                    sql = "SELECT date_format(convert(btime, DATE), ?) as thang "
                            + "FROM booking group by date_format(convert(btime, DATE),?) "
                            + "ORDER BY btime DESC";
                    break;
                case DOANH_THU_PHONG:
                    sql = "SELECT date_format(convert(paytime, DATE), ?) as thang "
                            + "FROM booking "
                            + "WHERE NOT isnull(paytime) "
                            + "GROUP BY date_format(convert(paytime, DATE),?) "
                            + "ORDER BY paytime DESC";
                    break;
                case DOANH_THU_DICH_VU:
                    sql = "SELECT date_format(convert(bstime, DATE), ?) as thang "
                            + "FROM booking_service group by date_format(convert(bstime, DATE),?) "
                            + "ORDER BY bstime DESC";
            }
        }
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, timeType == 0 ? "%m/%Y" : "%Y");
        ps.setString(2, timeType == 0 ? "%m/%Y" : "%Y");
        ResultSet rs = ps.executeQuery();

        return rs;
    }

    public ResultSet getReport(ReportType reportType, ReportCondition reportCondition) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(reportType.getSQL());
        ps.setDate(1, reportCondition.getFrom());
        ps.setDate(2, reportCondition.getTo());
        if (reportType == ReportType.DOANH_THU_DICH_VU) {
            ps.setString(3, reportCondition.getAddIn());
        }
        ResultSet rs = ps.executeQuery();

        return rs;
    }

    public ResultSet getReportAddingCondition(ReportType reportType, ReportCondition reportCondition, String condition)
            throws SQLException {
        String sql = reportType.getSQL().concat(condition);
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setDate(1, reportCondition.getFrom());
        ps.setDate(2, reportCondition.getTo());
        ResultSet rs = ps.executeQuery();

        return rs;
    }
}
