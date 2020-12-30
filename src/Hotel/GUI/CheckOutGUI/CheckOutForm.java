package Hotel.GUI.CheckOutGUI;

import Hotel.DTO.Customer;
import Hotel.DTO.StayProfile;
import Hotel.BLL.CheckInBLL;
import Hotel.BLL.CheckOutBLL;
import java.text.DecimalFormat;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Yue
 */
public class CheckOutForm extends JPanel {

    private final CheckOutBLL checkOutBLL = new CheckOutBLL(this);
    private Customer currentCustomer;
    private StayProfile stayProfile;
    private int nhoso = 0;
    private int soMax = checkOutBLL.getCountProfileMax();
    private int vitri = 0;

    public CheckOutForm() {
        initComponents();
        initTables();
        searchCondition.setSelected(stayId_radio.getModel(), true);
        if (nhoso == 0) {
            previous.setVisible(false);
        }
    }

    private void initTables() {

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < profileList.getColumnCount(); i++) {
            profileList.getColumnModel().getColumn(i).setHeaderRenderer(renderer);
            profileList.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
        for (int i = 0; i < stayRoomList.getColumnCount(); i++) {
            stayRoomList.getColumnModel().getColumn(i).setHeaderRenderer(renderer);
            stayRoomList.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
    }

    public void updateProfilelist() {
        checkOutBLL.updateProflieList((DefaultTableModel) profileList.getModel());
        checkOutBLL.updateProflieListTop((DefaultTableModel) profileList.getModel(), 0);
        updateUI();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        searchCondition = new javax.swing.ButtonGroup();
        profileInfo = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        hotenkh = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        sdt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        ngaynhan = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        thucnhan = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        ngaytra = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        thuctra = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tongcoc = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        tongthanhtoan = new javax.swing.JTextField();
        scroll_list2 = new javax.swing.JScrollPane();
        stayRoomList = new javax.swing.JTable();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        checkOutAndPay = new javax.swing.JButton();
        list = new javax.swing.JPanel();
        searchPanel = new javax.swing.JPanel();
        searchBy = new javax.swing.JPanel();
        stayId_radio = new javax.swing.JRadioButton();
        orderId_radio = new javax.swing.JRadioButton();
        txtSearch = new javax.swing.JTextField();
        searchBtn = new javax.swing.JButton();
        scroll_list = new javax.swing.JScrollPane();
        profileList = new javax.swing.JTable();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
        jPanel2 = new javax.swing.JPanel();
        next = new javax.swing.JLabel();
        previous = new javax.swing.JLabel();

        addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                formHierarchyChanged(evt);
            }
        });
        setLayout(new java.awt.BorderLayout(10, 0));

        profileInfo.setBackground(new java.awt.Color(153, 204, 255));
        profileInfo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin hồ sơ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 2, 20))); // NOI18N
        profileInfo.setPreferredSize(new java.awt.Dimension(300, 549));
        profileInfo.setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(profileInfo.getBackground());
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("<html><p style='text-align:center'>Khách hàng/<br/>Đại diện:</p></html>");
        jLabel2.setMinimumSize(new java.awt.Dimension(70, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel1.add(jLabel2, gridBagConstraints);

        hotenkh.setBackground(profileInfo.getBackground());
        hotenkh.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        hotenkh.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 2.7;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(hotenkh, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("SĐT:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel1.add(jLabel3, gridBagConstraints);

        sdt.setBackground(profileInfo.getBackground());
        sdt.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        sdt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 2.7;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(sdt, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Ngày nhận:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel1.add(jLabel5, gridBagConstraints);

        ngaynhan.setBackground(profileInfo.getBackground());
        ngaynhan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ngaynhan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 2.7;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(ngaynhan, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Thực nhận:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel1.add(jLabel6, gridBagConstraints);

        thucnhan.setBackground(profileInfo.getBackground());
        thucnhan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        thucnhan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 2.7;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(thucnhan, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Ngày trả:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel1.add(jLabel4, gridBagConstraints);

        ngaytra.setBackground(profileInfo.getBackground());
        ngaytra.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ngaytra.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 2.7;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(ngaytra, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Thực trả:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel1.add(jLabel7, gridBagConstraints);

        thuctra.setBackground(profileInfo.getBackground());
        thuctra.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        thuctra.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 2.7;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(thuctra, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Đã cọc:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel1.add(jLabel8, gridBagConstraints);

        tongcoc.setBackground(profileInfo.getBackground());
        tongcoc.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tongcoc.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 2.7;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(tongcoc, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 0, 51));
        jLabel9.setText("<html><p style='text-align:center'>Tổng thanh toán:</p></html>");
        jLabel9.setMinimumSize(new java.awt.Dimension(70, 30));
        jLabel9.setPreferredSize(new java.awt.Dimension(70, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel1.add(jLabel9, gridBagConstraints);

        tongthanhtoan.setBackground(profileInfo.getBackground());
        tongthanhtoan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tongthanhtoan.setForeground(new java.awt.Color(255, 0, 51));
        tongthanhtoan.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tongthanhtoan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 0, 51)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 2.7;
        gridBagConstraints.weighty = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(tongthanhtoan, gridBagConstraints);

        scroll_list2.setMinimumSize(new java.awt.Dimension(300, 150));

        stayRoomList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên phòng", "Loại phòng", "Đơn giá thuê"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        stayRoomList.setRowHeight(20);
        stayRoomList.setRowMargin(2);
        stayRoomList.setShowGrid(true);
        scroll_list2.setViewportView(stayRoomList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        jPanel1.add(scroll_list2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        jPanel1.add(filler3, gridBagConstraints);

        profileInfo.add(jPanel1, java.awt.BorderLayout.CENTER);

        checkOutAndPay.setBackground(new java.awt.Color(255, 153, 153));
        checkOutAndPay.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        checkOutAndPay.setForeground(new java.awt.Color(255, 0, 51));
        checkOutAndPay.setText("Trả phòng và thanh toán");
        checkOutAndPay.setPreferredSize(new java.awt.Dimension(31, 50));
        checkOutAndPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkOutAndPayActionPerformed(evt);
            }
        });
        profileInfo.add(checkOutAndPay, java.awt.BorderLayout.PAGE_END);

        add(profileInfo, java.awt.BorderLayout.LINE_END);

        list.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách hồ sơ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 2, 20))); // NOI18N
        list.setLayout(new java.awt.BorderLayout(0, 10));

        searchBy.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm theo", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));
        searchBy.setMinimumSize(new java.awt.Dimension(120, 68));
        searchBy.setPreferredSize(new java.awt.Dimension(135, 70));
        searchBy.setLayout(new javax.swing.BoxLayout(searchBy, javax.swing.BoxLayout.PAGE_AXIS));

        searchCondition.add(stayId_radio);
        stayId_radio.setText("Mã thuê phòng");
        stayId_radio.setIconTextGap(3);
        searchBy.add(stayId_radio);

        searchCondition.add(orderId_radio);
        orderId_radio.setText("Mã đặt phòng");
        orderId_radio.setIconTextGap(3);
        searchBy.add(orderId_radio);

        searchPanel.add(searchBy);

        txtSearch.setPreferredSize(new java.awt.Dimension(300, 30));
        searchPanel.add(txtSearch);

        searchBtn.setText("Tìm kiếm");
        searchBtn.setPreferredSize(new java.awt.Dimension(100, 30));
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });
        searchPanel.add(searchBtn);

        list.add(searchPanel, java.awt.BorderLayout.PAGE_START);

        profileList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã thuê phòng", "Mã đặt phòng", "Ngày thực nhận", "Ngày thực trả", "Tổng thanh toán"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Long.class
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
        profileList.setGridColor(new java.awt.Color(153, 153, 153));
        profileList.setRowHeight(20);
        profileList.setRowMargin(2);
        profileList.setShowGrid(true);
        profileList.getTableHeader().setReorderingAllowed(false);
        profileList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                profileListMouseClicked(evt);
            }
        });
        scroll_list.setViewportView(profileList);

        list.add(scroll_list, java.awt.BorderLayout.CENTER);
        list.add(filler1, java.awt.BorderLayout.LINE_START);
        list.add(filler2, java.awt.BorderLayout.LINE_END);

        jPanel2.setPreferredSize(new java.awt.Dimension(30, 30));
        jPanel2.setLayout(new java.awt.BorderLayout());

        next.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hotel/GUI/Z_Icons/arrow2.png"))); // NOI18N
        next.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nextMouseClicked(evt);
            }
        });
        jPanel2.add(next, java.awt.BorderLayout.LINE_END);

        previous.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hotel/GUI/Z_Icons/arrow.png"))); // NOI18N
        previous.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                previousMouseClicked(evt);
            }
        });
        jPanel2.add(previous, java.awt.BorderLayout.LINE_START);

        list.add(jPanel2, java.awt.BorderLayout.PAGE_END);

        add(list, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    public void goToProfile(int stayId) {
        stayId_radio.setSelected(true);
        txtSearch.setText(String.valueOf(stayId));
        searchBtnActionPerformed(null);
    }

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        int col = searchCondition.getSelection().equals(stayId_radio.getModel()) ? 0 : 1;
        vitri = 0;
        vitri = checkOutBLL.getPosition(Integer.parseInt(txtSearch.getText()), col);
        int a = vitri / 50;
        nhoso = a * 50;
        if (nhoso == 0) {
            previous.setVisible(false);
        } else {
            previous.setVisible(true);
        }
        if (nhoso > soMax - 2) {
            next.setVisible(false);
        } else {
            next.setVisible(true);
        }
        checkOutBLL.updateProflieListTop((DefaultTableModel) profileList.getModel(), nhoso);
        for (int i = 0; i < profileList.getRowCount(); i++) {
            if (profileList.getValueAt(i, col).toString().equalsIgnoreCase(txtSearch.getText())) {
                profileList.setRowSelectionInterval(i, i);
                profileListMouseClicked(null);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Không tìm thấy");
    }//GEN-LAST:event_searchBtnActionPerformed

    private void formHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_formHierarchyChanged
        if (this.isShowing()) {
            updateProfilelist();
        }
    }//GEN-LAST:event_formHierarchyChanged

    private void profileListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profileListMouseClicked
        if (profileList.getSelectedRow() >= 0) {
            int stayId = (int) profileList.getValueAt(profileList.getSelectedRow(), 0);
            CheckInBLL checkInBLL = new CheckInBLL(null);

            stayProfile = checkOutBLL.getProfileById(stayId);
            currentCustomer = checkInBLL.getCustomerByOrderId(stayProfile.getDondatphong().getId());
            hotenkh.setText(currentCustomer.getFullname());
            sdt.setText(currentCustomer.getPhone());
            ngaynhan.setText(stayProfile.getDondatphong().getCheckInTime().toString());
            ngaytra.setText(stayProfile.getDondatphong().getCheckOutTime().toString());
            thucnhan.setText(stayProfile.getThucnhan().toString());
            thuctra.setText(String.valueOf(stayProfile.getThuctra()));
            tongcoc.setText(String.valueOf(new DecimalFormat("#,##0").format(stayProfile.getDondatphong().getDeposit())));
            tongthanhtoan.setText(stayProfile.getThuctra() == null ? "Chưa thanh toán"
                    : new DecimalFormat("#,##0 vnd").format(stayProfile.getTongthanhtoan()));
            checkInBLL.updateOrderedRoomTable(stayProfile.getDondatphong().getId(), (DefaultTableModel) stayRoomList.getModel());

            checkOutAndPay.setVisible((stayProfile.getThuctra() == null));

        }
    }//GEN-LAST:event_profileListMouseClicked

    private void checkOutAndPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkOutAndPayActionPerformed
        if (profileList.getSelectedRow() >= 0) {
            new PayDialog(null, true, currentCustomer, stayProfile,
                    checkOutBLL, (DefaultTableModel) profileList.getModel()).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Cần chọn hồ sơ cần thanh toán!");
        }
    }//GEN-LAST:event_checkOutAndPayActionPerformed

    private void nextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextMouseClicked
        if (nhoso >= 0 && nhoso < soMax - 1) {
            nhoso += 50;
            checkOutBLL.updateProflieListTop((DefaultTableModel) profileList.getModel(), nhoso);
            previous.setVisible(true);
        }
        if (nhoso > soMax - 2) {
            next.setVisible(false);
        }
    }//GEN-LAST:event_nextMouseClicked

    private void previousMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_previousMouseClicked
        if (nhoso > 0) {
            nhoso -= 50;
            checkOutBLL.updateProflieListTop((DefaultTableModel) profileList.getModel(), nhoso);
            next.setVisible(true);
        }
        if (nhoso == 0) {
            previous.setVisible(false);
        }
    }//GEN-LAST:event_previousMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton checkOutAndPay;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.JTextField hotenkh;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel list;
    private javax.swing.JLabel next;
    private javax.swing.JTextField ngaynhan;
    private javax.swing.JTextField ngaytra;
    private javax.swing.JRadioButton orderId_radio;
    private javax.swing.JLabel previous;
    private javax.swing.JPanel profileInfo;
    private javax.swing.JTable profileList;
    private javax.swing.JScrollPane scroll_list;
    private javax.swing.JScrollPane scroll_list2;
    private javax.swing.JTextField sdt;
    private javax.swing.JButton searchBtn;
    private javax.swing.JPanel searchBy;
    private javax.swing.ButtonGroup searchCondition;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JRadioButton stayId_radio;
    private javax.swing.JTable stayRoomList;
    private javax.swing.JTextField thucnhan;
    private javax.swing.JTextField thuctra;
    private javax.swing.JTextField tongcoc;
    private javax.swing.JTextField tongthanhtoan;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
