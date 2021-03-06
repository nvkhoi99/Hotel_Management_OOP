/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hotel.GUI.CheckInGUI;

import Hotel.DTO.Booking;
import Hotel.GUI.common.DataChangeListener;

/**
 *
 * @author Yue
 */
public class CheckInRoomEditorPanel extends javax.swing.JFrame {

    private Booking booking;
    private DataChangeListener listener;

    /**
     * Creates new form CheckInRoomEditorPanel
     *
     * @param listener
     * @param booking
     */
    public CheckInRoomEditorPanel(DataChangeListener<Booking> listener, Booking booking) {
        this.booking = booking;
        this.listener = listener;

        initComponents();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        bookingRoomList = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(75, 150));

        btnAdd.setText("Thêm");
        btnAdd.setPreferredSize(new java.awt.Dimension(60, 23));
        jPanel1.add(btnAdd);

        btnRemove.setText("Xoá");
        btnRemove.setPreferredSize(new java.awt.Dimension(60, 23));
        jPanel1.add(btnRemove);

        getContentPane().add(jPanel1, java.awt.BorderLayout.LINE_END);

        bookingRoomList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Phòng", "Loại", "Đơn giá"
            }
        ));
        jScrollPane1.setViewportView(bookingRoomList);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable bookingRoomList;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnRemove;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
