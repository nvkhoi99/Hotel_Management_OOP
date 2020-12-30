/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hotel.GUI.ServiceGUI;

import Hotel.BLL.ServiceBLL;
import Hotel.DTO.Booking;
import Hotel.DTO.services.RoomService;
import Hotel.DTO.services.Service;

import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Yue
 */
public class RoomServiceForm extends javax.swing.JFrame {

    private final int bookingId;
    private final int roomId;
    private final ServiceBLL serviceBLL = new ServiceBLL();
    private List<Service> services;

    public RoomServiceForm(int bookingId, int roomId) {
        this.bookingId = bookingId;
        this.roomId = roomId;
        initComponents();
        AddServices();
        adddichvuphong();
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(serviceList.getModel());
        serviceList.setRowSorter(rowSorter);
        txtSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String OwO = txtSearch.getText();
                if (OwO.trim().length() == 0) {
                    rowSorter.setRowFilter(null);

                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + OwO));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String OwO = txtSearch.getText();
                if (OwO.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + OwO));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                String OwO = txtSearch.getText();
                if (OwO.trim().length() == 0) {
                    rowSorter.setRowFilter(null);

                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + OwO));
                }
            }
        });
    }

    private void AddServices() {
        services = serviceBLL.getAllService();
        DefaultTableModel serviceModel = (DefaultTableModel) serviceList.getModel();
        for (Service service : services) {
            serviceModel.addRow(new Object[]{
                service.getId(),
                service.getName(),
                service.getUnit(),
                service.getPrice()
            });
        }
    }

    private void adddichvuphong() {
        DefaultTableModel roomServiceModel = (DefaultTableModel) tablelichsusudung.getModel();
        serviceBLL.initRoomServiceList(bookingId, roomId, roomServiceModel);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jDialog2 = new javax.swing.JDialog();
        txtTendv = new javax.swing.JTextField();
        txtDongia = new javax.swing.JTextField();
        txtNgaysudung = new javax.swing.JTextField();
        txtSoluong = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        delbtn = new javax.swing.JButton();
        updatebtn = new javax.swing.JButton();
        savebtn = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        cancel = new javax.swing.JButton();
        jSpinner1 = new javax.swing.JSpinner();
        main1 = new javax.swing.JPanel();
        jpPhai = new javax.swing.JPanel();
        jpTren1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jpDuoi1 = new javax.swing.JPanel();
        addDvbtn = new javax.swing.JButton();
        jpGiua1 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        choosenServices = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        saveService = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablelichsusudung = new javax.swing.JTable();
        jpTrai = new javax.swing.JPanel();
        jpTren = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        txtSearch = new javax.swing.JTextField();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        serviceList = new javax.swing.JTable();

        jDialog2.setBackground(new java.awt.Color(255, 153, 153));
        jDialog2.setMinimumSize(new java.awt.Dimension(500, 400));
        jDialog2.getContentPane().setLayout(new java.awt.GridBagLayout());

        txtTendv.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 30);
        jDialog2.getContentPane().add(txtTendv, gridBagConstraints);

        txtDongia.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 30);
        jDialog2.getContentPane().add(txtDongia, gridBagConstraints);

        txtNgaysudung.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 30);
        jDialog2.getContentPane().add(txtNgaysudung, gridBagConstraints);

        txtSoluong.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 30);
        jDialog2.getContentPane().add(txtSoluong, gridBagConstraints);

        jLabel3.setText("Tên dịch vụ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        jDialog2.getContentPane().add(jLabel3, gridBagConstraints);

        jLabel4.setText("Đơn giá");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        jDialog2.getContentPane().add(jLabel4, gridBagConstraints);

        jLabel5.setText("Ngày sử Dụng");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        jDialog2.getContentPane().add(jLabel5, gridBagConstraints);

        jLabel6.setText("Số Lượng");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        jDialog2.getContentPane().add(jLabel6, gridBagConstraints);

        delbtn.setText("Xóa");
        delbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delbtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.weighty = 0.1;
        jDialog2.getContentPane().add(delbtn, gridBagConstraints);

        updatebtn.setText("Sửa");
        updatebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatebtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.weighty = 0.1;
        jDialog2.getContentPane().add(updatebtn, gridBagConstraints);

        savebtn.setText("Lưu");
        savebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savebtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.weighty = 0.1;
        jDialog2.getContentPane().add(savebtn, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Cập nhật dịch vụ khách hàng");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        jDialog2.getContentPane().add(jLabel8, gridBagConstraints);

        cancel.setText("Hủy");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        jDialog2.getContentPane().add(cancel, gridBagConstraints);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
                formWindowLostFocus(evt);
            }
        });

        main1.setBackground(new java.awt.Color(204, 204, 255));
        main1.setLayout(new java.awt.BorderLayout(10, 0));

        jpPhai.setPreferredSize(new java.awt.Dimension(500, 610));
        jpPhai.setRequestFocusEnabled(false);
        jpPhai.setLayout(new java.awt.BorderLayout(0, 5));

        jpTren1.setLayout(new java.awt.GridLayout(1, 0));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hotel/GUI/Z_Icons/rsz_resort-la-gi.jpg"))); // NOI18N
        jpTren1.add(jLabel7);

        jpPhai.add(jpTren1, java.awt.BorderLayout.PAGE_START);

        jpDuoi1.setLayout(new java.awt.GridLayout(1, 0));

        addDvbtn.setBackground(new java.awt.Color(255, 153, 153));
        addDvbtn.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        addDvbtn.setForeground(new java.awt.Color(255, 0, 51));
        addDvbtn.setText("Thêm dịch vụ ");
        addDvbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addDvbtnActionPerformed(evt);
            }
        });
        jpDuoi1.add(addDvbtn);

        jpPhai.add(jpDuoi1, java.awt.BorderLayout.PAGE_END);

        jpGiua1.setLayout(new java.awt.BorderLayout(0, 10));

        jPanel10.setPreferredSize(new java.awt.Dimension(500, 200));
        jPanel10.setLayout(new java.awt.GridLayout(1, 0));

        choosenServices.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã dịch vụ", "Tên dịch vụ", "Đơn vị", "Đơn giá", "Số lượng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        choosenServices.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                choosenServicesMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(choosenServices);

        jPanel10.add(jScrollPane4);

        jpGiua1.add(jPanel10, java.awt.BorderLayout.PAGE_END);

        jPanel11.setLayout(new java.awt.BorderLayout());

        jPanel4.setOpaque(false);
        jPanel4.setPreferredSize(new java.awt.Dimension(50, 268));
        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel6.setLayout(new java.awt.GridLayout(1, 0));

        saveService.setBackground(new java.awt.Color(255, 153, 153));
        saveService.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        saveService.setForeground(new java.awt.Color(255, 0, 51));
        saveService.setText("<html>Cập<br>nhật<br>lịch<br>sử<br>Dịch<br>vụ</html>");
        saveService.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveServiceActionPerformed(evt);
            }
        });
        jPanel6.add(saveService);

        jPanel4.add(jPanel6, java.awt.BorderLayout.CENTER);

        jPanel11.add(jPanel4, java.awt.BorderLayout.LINE_END);

        tablelichsusudung.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã dùng", "Tên dịch vụ", "Ngày dùng", "Đơn giá", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablelichsusudung);

        jPanel11.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jpGiua1.add(jPanel11, java.awt.BorderLayout.CENTER);

        jpPhai.add(jpGiua1, java.awt.BorderLayout.CENTER);

        main1.add(jpPhai, java.awt.BorderLayout.LINE_END);

        jpTrai.setLayout(new java.awt.BorderLayout());

        jpTren.setPreferredSize(new java.awt.Dimension(480, 150));
        jpTren.setLayout(new java.awt.BorderLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Nhập tên dịch vụ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 12))); // NOI18N
        jPanel1.setMinimumSize(new java.awt.Dimension(100, 60));
        jPanel1.setName(""); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(480, 60));
        jPanel1.setRequestFocusEnabled(false);
        jPanel1.setLayout(new java.awt.GridBagLayout());

        txtSearch.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(txtSearch, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        jPanel1.add(filler1, gridBagConstraints);

        jpTren.add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hotel/GUI/Z_Icons/rsz_5phoi-canh-khach-san-dep_30-1024x683.jpg"))); // NOI18N
        jPanel3.add(jLabel1);

        jpTren.add(jPanel3, java.awt.BorderLayout.CENTER);

        jpTrai.add(jpTren, java.awt.BorderLayout.PAGE_START);

        jPanel9.setLayout(new java.awt.GridLayout(1, 0));

        serviceList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã dịch vụ", "Tên dịch vụ", "Đơn vị", "Đơn giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        serviceList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                serviceListMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(serviceList);

        jPanel9.add(jScrollPane3);

        jpTrai.add(jPanel9, java.awt.BorderLayout.CENTER);

        main1.add(jpTrai, java.awt.BorderLayout.CENTER);

        getContentPane().add(main1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void addDvbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addDvbtnActionPerformed
        if (choosenServices.getRowCount() > 0) {
            if (serviceBLL.addRoomServices(bookingId, roomId, (DefaultTableModel) choosenServices.getModel())) {
                JOptionPane.showMessageDialog(null, "Thêm dịch vụ thành công");
                adddichvuphong();
            } else {
                JOptionPane.showMessageDialog(null, "Thêm dịch vụ thất bại, đã có lỗi xảy ra");
            }
        }
    }//GEN-LAST:event_addDvbtnActionPerformed

    private void choosenServicesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_choosenServicesMouseClicked
        int select = choosenServices.getSelectedRow();
        DefaultTableModel choosenServicesModel = (DefaultTableModel) choosenServices.getModel();
        if ((int) choosenServices.getValueAt(select, 4) == 1) {
            choosenServicesModel.removeRow(select);
            //            choosenServices.updateUI();
        } else if ((int) choosenServices.getValueAt(select, 4) > 1) {
            int soluong = (int) choosenServices.getValueAt(select, 4) - 1;
            choosenServices.setValueAt(soluong, select, 4);
        }
    }//GEN-LAST:event_choosenServicesMouseClicked

    private void saveServiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveServiceActionPerformed
        if (tablelichsusudung.getSelectedRow() >= 0) {
            txtTendv.setText(tablelichsusudung.getValueAt(tablelichsusudung.getSelectedRow(), 1).toString());
            txtDongia.setText(tablelichsusudung.getValueAt(tablelichsusudung.getSelectedRow(), 3).toString());
            txtNgaysudung.setText(tablelichsusudung.getValueAt(tablelichsusudung.getSelectedRow(), 2).toString());
            txtSoluong.setText(tablelichsusudung.getValueAt(tablelichsusudung.getSelectedRow(), 4).toString());
            jDialog2.setLocationRelativeTo(null);
            jDialog2.setVisible(true);
            jDialog2.setFocusable(true);
            jDialog2.setAutoRequestFocus(true);
        }
    }//GEN-LAST:event_saveServiceActionPerformed

    private void serviceListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_serviceListMouseClicked
        if (choosenServices.getRowCount() > 0) {
            int dem = 0;
            for (int i = 0; i < choosenServices.getRowCount(); i++) {
                if (choosenServices.getValueAt(i, 0) == serviceList.getValueAt(serviceList.getSelectedRow(), 0)) {
                    int soluong = (int) choosenServices.getValueAt(i, 4) + 1;
                    choosenServices.setValueAt(soluong, i, 4);
                    dem += 1;
                }
            }
            if (dem == 0) {
                // serviceList.setDefaultEditor(Object.class,null);
                DefaultTableModel choosenServicesModel = (DefaultTableModel) choosenServices.getModel();
                Object[] row = new Object[]{
                    (int) serviceList.getValueAt(serviceList.getSelectedRow(), 0),
                    serviceList.getValueAt(serviceList.getSelectedRow(), 1).toString(),
                    serviceList.getValueAt(serviceList.getSelectedRow(), 2).toString(),
                    (int) serviceList.getValueAt(serviceList.getSelectedRow(), 3),
                    1
                };
                choosenServicesModel.addRow(row);
                choosenServices.updateUI();
            }
        } else {
            DefaultTableModel choosenServicesModel = (DefaultTableModel) choosenServices.getModel();
            Object[] row = new Object[]{
                (int) serviceList.getValueAt(serviceList.getSelectedRow(), 0),
                serviceList.getValueAt(serviceList.getSelectedRow(), 1).toString(),
                serviceList.getValueAt(serviceList.getSelectedRow(), 2).toString(),
                (int) serviceList.getValueAt(serviceList.getSelectedRow(), 3),
                1
            };
            choosenServicesModel.addRow(row);
            choosenServices.updateUI();

        }
    }//GEN-LAST:event_serviceListMouseClicked

    private void delbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delbtnActionPerformed
        if (serviceBLL.deleteRoomService((Integer) tablelichsusudung.getValueAt(tablelichsusudung.getSelectedRow(), 0))) {
            JOptionPane.showMessageDialog(null, "Đã xóa dịch vụ");
            adddichvuphong();
        } else {
            JOptionPane.showMessageDialog(null, "Xóa thất bại, đã có lỗi xảy ra");
        }
        jDialog2.setVisible(false);
    }//GEN-LAST:event_delbtnActionPerformed

    private void updatebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatebtnActionPerformed
        txtSoluong.setEditable(true);
        txtSoluong.requestFocus();
        txtSoluong.selectAll();
    }//GEN-LAST:event_updatebtnActionPerformed

    private void savebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savebtnActionPerformed
        if (serviceBLL.updateRoomService(bookingId, txtTendv.getText(), txtSoluong.getText(), txtNgaysudung.getText())) {
            jDialog2.setAlwaysOnTop(false);
            jDialog2.setVisible(false);
            JOptionPane.showMessageDialog(null, "Đã sửa dịch vụ");
            adddichvuphong();
            tablelichsusudung.setEnabled(false);
            tablelichsusudung.requestFocus();
        }

    }//GEN-LAST:event_savebtnActionPerformed

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        jDialog2.setVisible(false);

    }//GEN-LAST:event_cancelActionPerformed

    private void formWindowLostFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowLostFocus
        if (!jDialog2.isVisible()) {
            this.requestFocus();
        }
    }//GEN-LAST:event_formWindowLostFocus

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addDvbtn;
    private javax.swing.JButton cancel;
    private javax.swing.JTable choosenServices;
    private javax.swing.JButton delbtn;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JPanel jpDuoi1;
    private javax.swing.JPanel jpGiua1;
    private javax.swing.JPanel jpPhai;
    private javax.swing.JPanel jpTrai;
    private javax.swing.JPanel jpTren;
    private javax.swing.JPanel jpTren1;
    private javax.swing.JPanel main1;
    private javax.swing.JButton saveService;
    private javax.swing.JButton savebtn;
    private javax.swing.JTable serviceList;
    private javax.swing.JTable tablelichsusudung;
    private javax.swing.JTextField txtDongia;
    private javax.swing.JTextField txtNgaysudung;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSoluong;
    private javax.swing.JTextField txtTendv;
    private javax.swing.JButton updatebtn;
    // End of variables declaration//GEN-END:variables
}
