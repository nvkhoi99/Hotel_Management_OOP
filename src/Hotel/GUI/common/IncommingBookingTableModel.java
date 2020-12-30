/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hotel.GUI.common;

import Hotel.DTO.Booking;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Yue
 */
public class IncommingBookingTableModel extends AbstractTableModel {

    private ArrayList<Booking> bookingList;

    public IncommingBookingTableModel(ArrayList<Booking> bookingList) {
        this.bookingList = bookingList;
    }

    @Override
    public int getRowCount() {
        return bookingList.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Mã hồ sơ";
            case 1:
                return "Ngày nhận";
            case 2:
                return "Ngày trả";
            default:
                return "???";
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Booking booking = bookingList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return booking.getId();
            case 1:
                return booking.getCheckInTime();
            case 2:
                return booking.getCheckOutTime();
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public ArrayList<Booking> getBookingList() {
        return bookingList;
    }
}
