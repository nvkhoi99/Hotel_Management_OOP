package Hotel.GUI.CheckInGUI;

import Hotel.DTO.Customer;
import Hotel.BLL.CheckInBLL;
import Hotel.BLL.Rules;
import Hotel.DTO.Booking;
import Hotel.GUI.common.DataChangeListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class CheckInForm extends javax.swing.JPanel implements DataChangeListener<Booking> {

    private final CheckInBLL checkInBLL = new CheckInBLL(this);
    private int nhoso = 0;
    private int soMax = checkInBLL.getCountInOrder();
    private JTable orderJTable = new JTable();
    private int vitri = 0;

    public CheckInForm() {
        initComponents();
        initTable();
        if (nhoso == 0) {
            previous.setVisible(false);
        }
    }

    private void initTable() {

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < orderList.getColumnCount(); i++) {
            orderList.getColumnModel().getColumn(i).setHeaderRenderer(renderer);
            orderList.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
    }

    private void updateBookingTable() {
        //checkInBLL.updateTable((DefaultTableModel) orderList.getModel());
        checkInBLL.updateTableTop((DefaultTableModel) orderList.getModel(), 0);
//        checkInBLL.updateTable((DefaultTableModel) orderJTable.getModel());
        updateUI();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        searchPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        searchBtn = new javax.swing.JButton();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        jScrollPane1 = new javax.swing.JScrollPane();
        orderList = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        next = new javax.swing.JLabel();
        previous = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        hotenkh = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        sdt = new javax.swing.JTextField();
        ngaynhan = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        ngaytra = new javax.swing.JTextField();
        tongcoc = new javax.swing.JTextField();
        trangthai = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        dsphongdat = new javax.swing.JTable();
        cmnd = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        buttons = new javax.swing.JPanel();
        btnEditBookingRoom = new javax.swing.JButton();
        huyphong = new javax.swing.JButton();
        nhancoc = new javax.swing.JButton();
        nhanphong = new javax.swing.JButton();

        setBackground(new java.awt.Color(204, 204, 255));
        addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                formHierarchyChanged(evt);
            }
        });
        setLayout(new java.awt.BorderLayout(10, 0));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Danh sách đơn đặt", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 2, 22))); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(696, 604));
        jPanel2.setLayout(new java.awt.BorderLayout(0, 10));

        jPanel3.setBackground(jPanel2.getBackground());
        jPanel3.setLayout(new java.awt.BorderLayout(10, 0));

        searchPanel.setBackground(jPanel2.getBackground());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Tìm theo mã:");
        searchPanel.add(jLabel1);

        txtSearch.setPreferredSize(new java.awt.Dimension(300, 30));
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });
        searchPanel.add(txtSearch);

        searchBtn.setText("Tìm kiếm");
        searchBtn.setPreferredSize(new java.awt.Dimension(100, 30));
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });
        searchPanel.add(searchBtn);

        jPanel3.add(searchPanel, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel3, java.awt.BorderLayout.PAGE_START);
        jPanel2.add(filler2, java.awt.BorderLayout.LINE_START);

        orderList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Phiếu đặt", "Ngày đặt", "Ngày nhận", "Ngày trả", "Tổng cọc", "Trạng thái"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        orderList.setGridColor(new java.awt.Color(153, 153, 153));
        orderList.setRowHeight(20);
        orderList.setRowMargin(2);
        orderList.setShowGrid(true);
        orderList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                orderListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(orderList);

        jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel4.setPreferredSize(new java.awt.Dimension(30, 30));
        jPanel4.setLayout(new java.awt.BorderLayout());

        next.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hotel/GUI/Z_Icons/next.png"))); // NOI18N
        next.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nextMouseClicked(evt);
            }
        });
        jPanel4.add(next, java.awt.BorderLayout.LINE_END);

        previous.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hotel/GUI/Z_Icons/return.png"))); // NOI18N
        previous.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                previousMouseClicked(evt);
            }
        });
        jPanel4.add(previous, java.awt.BorderLayout.LINE_START);

        jPanel2.add(jPanel4, java.awt.BorderLayout.PAGE_END);

        add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel8.setBackground(new java.awt.Color(153, 204, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Thông tin đặt phòng", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 2, 22))); // NOI18N
        jPanel8.setPreferredSize(new java.awt.Dimension(300, 100));
        jPanel8.setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(jPanel8.getBackground());
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 1, 0), "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setToolTipText("");
        jPanel1.setPreferredSize(new java.awt.Dimension(350, 604));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("<html><p style='text-align:center'>Khách hàng/<br/>Đại diện:</p></html>");
        jLabel2.setMinimumSize(new java.awt.Dimension(70, 30));
        jLabel2.setPreferredSize(new java.awt.Dimension(70, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel1.add(jLabel2, gridBagConstraints);

        hotenkh.setBackground(jPanel1.getBackground());
        hotenkh.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        hotenkh.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(hotenkh, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Số điện thoại:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel1.add(jLabel4, gridBagConstraints);

        sdt.setBackground(jPanel1.getBackground());
        sdt.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        sdt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(sdt, gridBagConstraints);

        ngaynhan.setBackground(jPanel1.getBackground());
        ngaynhan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ngaynhan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(ngaynhan, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Thời gian nhận:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel1.add(jLabel6, gridBagConstraints);

        ngaytra.setBackground(jPanel1.getBackground());
        ngaytra.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ngaytra.setToolTipText("");
        ngaytra.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(ngaytra, gridBagConstraints);

        tongcoc.setBackground(jPanel1.getBackground());
        tongcoc.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tongcoc.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(tongcoc, gridBagConstraints);

        trangthai.setBackground(jPanel1.getBackground());
        trangthai.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        trangthai.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(trangthai, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Thời gian trả:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel1.add(jLabel7, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Tiền cọc:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel1.add(jLabel8, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Trạng thái:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel1.add(jLabel9, gridBagConstraints);

        jScrollPane2.setMinimumSize(new java.awt.Dimension(300, 150));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(300, 500));

        dsphongdat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên phòng", "Loại phòng", "Đơn giá"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        dsphongdat.setGridColor(new java.awt.Color(153, 153, 153));
        dsphongdat.setRowHeight(20);
        dsphongdat.setRowMargin(2);
        dsphongdat.setShowGrid(true);
        jScrollPane2.setViewportView(dsphongdat);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 4.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel1.add(jScrollPane2, gridBagConstraints);

        cmnd.setBackground(jPanel1.getBackground());
        cmnd.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cmnd.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(cmnd, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("CMND:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel1.add(jLabel5, gridBagConstraints);

        jPanel8.add(jPanel1, java.awt.BorderLayout.CENTER);

        buttons.setPreferredSize(new java.awt.Dimension(300, 120));
        buttons.setLayout(new java.awt.GridLayout(0, 1));

        btnEditBookingRoom.setText("Sửa thông tin");
        buttons.add(btnEditBookingRoom);

        huyphong.setBackground(new java.awt.Color(255, 153, 153));
        huyphong.setForeground(new java.awt.Color(255, 0, 51));
        huyphong.setText("Hủy phòng");
        huyphong.setToolTipText("");
        huyphong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                huyphongActionPerformed(evt);
            }
        });
        buttons.add(huyphong);

        nhancoc.setBackground(new java.awt.Color(255, 153, 153));
        nhancoc.setForeground(new java.awt.Color(255, 0, 51));
        nhancoc.setText("Nhận cọc");
        nhancoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nhancocActionPerformed(evt);
            }
        });
        buttons.add(nhancoc);

        nhanphong.setBackground(new java.awt.Color(255, 153, 153));
        nhanphong.setForeground(new java.awt.Color(255, 0, 51));
        nhanphong.setText("Nhận phòng");
        nhanphong.setToolTipText("");
        nhanphong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nhanphongActionPerformed(evt);
            }
        });
        buttons.add(nhanphong);

        jPanel8.add(buttons, java.awt.BorderLayout.PAGE_END);

        add(jPanel8, java.awt.BorderLayout.LINE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void huyphongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_huyphongActionPerformed
        if (orderList.getSelectedRow() >= 0) {
            checkInBLL.cancelOrder((int) orderList.getValueAt(orderList.getSelectedRow(), 0));
            updateBookingTable();
        }
    }//GEN-LAST:event_huyphongActionPerformed

    private void orderListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_orderListMouseClicked
        int select = orderList.getSelectedRow();
        int orderId = (int) orderList.getValueAt(select, 0);
        if (orderId >= 0) {
            Customer customer = checkInBLL.getCustomerByOrderId(orderId);
            hotenkh.setText(customer.getFullname());
            cmnd.setText(customer.getIdentity());
            sdt.setText(customer.getPhone());
            ngaynhan.setText(orderList.getValueAt(select, 2).toString());
            ngaytra.setText(orderList.getValueAt(select, 3).toString());
            tongcoc.setText(String.valueOf(orderList.getValueAt(select, 4)));
            trangthai.setText(String.valueOf(orderList.getValueAt(select, 5)));
            checkInBLL.updateOrderedRoomTable(orderId, (DefaultTableModel) dsphongdat.getModel());
            if (orderList.getValueAt(select, 5).equals("Đã hủy")
                    || orderList.getValueAt(select, 5).equals("Đã nhận")) {
                buttons.setVisible(false);
            } else {
                buttons.setVisible(true);
            }
        }

    }//GEN-LAST:event_orderListMouseClicked

    private void nhancocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nhancocActionPerformed
        if (orderList.getSelectedRow() >= 0) {
            checkInBLL.depositOrder((int) orderList.getValueAt(orderList.getSelectedRow(), 0));
            updateBookingTable();
        }
    }//GEN-LAST:event_nhancocActionPerformed

    private void nhanphongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nhanphongActionPerformed
                if (orderList.getSelectedRow() >= 0) {
            if (checkInBLL.checkInOrder((int) orderList.getValueAt(orderList.getSelectedRow(), 0))) {
                JOptionPane.showMessageDialog(null, "Nhận phòng thành công");
                updateBookingTable();
                Rules.StateIsChanged = true;
            }
        }
    }//GEN-LAST:event_nhanphongActionPerformed

    private void formHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_formHierarchyChanged
        if (this.isShowing()) {
            updateBookingTable();
        }
    }//GEN-LAST:event_formHierarchyChanged

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        vitri = 0;
        vitri = checkInBLL.getPosition(Integer.parseInt(txtSearch.getText()));
        int a = vitri / 50;
        nhoso = a * 50;
        if (nhoso == 0) {
            previous.setVisible(false);

        } else {
            previous.setVisible(true);
        }
        checkInBLL.updateTableTop((DefaultTableModel) orderList.getModel(), nhoso);
        for (int i = 0; i < orderList.getRowCount(); i++) {
            if (orderList.getValueAt(i, 0).toString().equalsIgnoreCase(txtSearch.getText())) {
                orderList.setRowSelectionInterval(i, i);
                orderListMouseClicked(null);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Không tìm thấy");
    }//GEN-LAST:event_searchBtnActionPerformed

    private void nextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextMouseClicked

        if (nhoso >= 0 && nhoso < soMax - 1) {
            nhoso += 5;
            checkInBLL.updateTableTop((DefaultTableModel) orderList.getModel(), nhoso);
            previous.setVisible(true);
        }
        if (nhoso > soMax - 2) {
            next.setVisible(false);
        }

    }//GEN-LAST:event_nextMouseClicked

    private void previousMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_previousMouseClicked
        if (nhoso > 0) {
            nhoso -= 5;
            checkInBLL.updateTableTop((DefaultTableModel) orderList.getModel(), nhoso);
            next.setVisible(true);
        }
        if (nhoso == 0) {
            previous.setVisible(false);
        }
    }//GEN-LAST:event_previousMouseClicked

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditBookingRoom;
    private javax.swing.JPanel buttons;
    private javax.swing.JTextField cmnd;
    private javax.swing.JTable dsphongdat;
    private javax.swing.Box.Filler filler2;
    private javax.swing.JTextField hotenkh;
    private javax.swing.JButton huyphong;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel next;
    private javax.swing.JTextField ngaynhan;
    private javax.swing.JTextField ngaytra;
    private javax.swing.JButton nhancoc;
    private javax.swing.JButton nhanphong;
    private javax.swing.JTable orderList;
    private javax.swing.JLabel previous;
    private javax.swing.JTextField sdt;
    private javax.swing.JButton searchBtn;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JTextField tongcoc;
    private javax.swing.JTextField trangthai;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onDataChanged(Booking booking) {

    }
}
