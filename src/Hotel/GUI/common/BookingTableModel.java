package Hotel.GUI.common;

import Hotel.DAL.CustomerDAO;
import Hotel.DTO.Booking;

import javax.swing.table.AbstractTableModel;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookingTableModel extends AbstractTableModel {
    private ArrayList<Booking> bookingList;
    private final CustomerDAO dao = CustomerDAO.getInstance();

    public BookingTableModel(ArrayList<Booking> bookingList) {
        this.bookingList = bookingList;
    }

    @Override
    public int getRowCount() {
        return bookingList.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Mã hồ sơ";
            case 1:
                return "Khách hàng";
            case 2:
                return "Ngày thanh toán";
            case 3:
                return "Tổng thanh toán";
            default:
                return "???";
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Booking value = bookingList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return value.getId();
            case 1:
                try {
                    return dao.getCustomerById(value.getCustomerId()).getFullname();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return "";
                }
            case 2:
                return value.getPaytime();
            case 3:
                return value.getTotal();
            default:
                return "???";
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Booking getBooking(int rowIndex) {
        return bookingList.get(rowIndex);
    }
}
