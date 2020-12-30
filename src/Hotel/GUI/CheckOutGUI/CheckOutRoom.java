/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hotel.GUI.CheckOutGUI;

import Hotel.BLL.BookingBLL;
import Hotel.DTO.Booking;
import Hotel.DTO.rooms.BookingRoom;
import Hotel.DTO.rooms.Room;
import Hotel.GUI.common.DataChangeListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class CheckOutRoom extends javax.swing.JDialog {

    private Booking booking;
    private List<? extends Room> roomPool;
    private List<Integer> bookingRoomIds = new ArrayList<>();
    private final DataChangeListener<Integer> listener;
    Object[] columnsname1 = {"chon", "tenphong"};

    public Class<?> getaClass1(int column) {
        switch (column) {
            case 0:
                return Boolean.class;
            case 1:
                return String.class;
        }
        return null;
    }

    public CheckOutRoom(Frame parent, boolean modal,
                        Booking booking, List<? extends Room> roomPool, DataChangeListener<Integer> listener) {
        super(parent, modal);
        this.booking = booking;
        this.roomPool = roomPool;
        this.listener = listener;
        initComponents();
        initializeRoomList();
    }

    private void initializeRoomList() {
        DefaultTableModel model = (DefaultTableModel) dstraphong.getModel();
        for (BookingRoom broom : booking.getBookingRooms()) {
            if (broom.getRealCheckOut() == null) {
                bookingRoomIds.add(broom.getId());
                model.addRow(new Object[]{roomPool.stream()
                        .filter(room -> room.getId() == broom.getRoomId())
                        .findFirst().get().getName(), false});
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        traphong = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        dstraphong = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(300, 300));

        traphong.setText("Trả phòng");
        traphong.setPreferredSize(new java.awt.Dimension(79, 50));
        traphong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                traphongActionPerformed(evt);
            }
        });
        getContentPane().add(traphong, java.awt.BorderLayout.PAGE_END);

        dstraphong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên phòng", "check"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(dstraphong);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void traphongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_traphongActionPerformed
        ArrayList<Integer> choosedRooms = new ArrayList<>();
        for (int i = 0; i < dstraphong.getRowCount(); i++) {
            if (dstraphong.getValueAt(i, 1).equals(true)) {
                choosedRooms.add(bookingRoomIds.get(i));
            }
        }
        if (new BookingBLL().checkOutRooms(choosedRooms)) {
            JOptionPane.showMessageDialog(null, "Trả phòng thành công");
            listener.onDataChanged(0);
            dispose();
        }
    }//GEN-LAST:event_traphongActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable dstraphong;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton traphong;
    // End of variables declaration//GEN-END:variables
}
