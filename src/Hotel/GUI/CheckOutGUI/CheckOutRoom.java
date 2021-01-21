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
        java.awt.GridBagConstraints gridBagConstraints;

        jScrollPane1 = new javax.swing.JScrollPane();
        dstraphong = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        payCheck = new javax.swing.JCheckBox();
        traphong = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(300, 300));

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

        jPanel1.setPreferredSize(new java.awt.Dimension(10, 50));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        payCheck.setSelected(true);
        payCheck.setText("Tính tiền");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        jPanel1.add(payCheck, gridBagConstraints);

        traphong.setText("Trả phòng");
        traphong.setPreferredSize(new java.awt.Dimension(200, 50));
        traphong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                traphongActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        jPanel1.add(traphong, gridBagConstraints);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

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
        if (new BookingBLL().checkOutRooms(choosedRooms, payCheck.isSelected())) {
            JOptionPane.showMessageDialog(null, "Trả phòng thành công");
            listener.onDataChanged(0);
            dispose();
        }
    }//GEN-LAST:event_traphongActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable dstraphong;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JCheckBox payCheck;
    private javax.swing.JButton traphong;
    // End of variables declaration//GEN-END:variables
}
