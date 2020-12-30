package Hotel.GUI.ManagementGUI;

import Hotel.BLL.CheckOutBLL;
import Hotel.DTO.rooms.RoomForManaging;
import Hotel.DTO.RoomType;
import Hotel.BLL.ManagementBLL;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import Hotel.BLL.Rules;
import Hotel.DTO.Booking;
import Hotel.DTO.rooms.Room;
import Hotel.GUI.ServiceGUI.RoomServiceForm;
import Hotel.GUI.ServiceGUI.ServiceForm;
import java.awt.CardLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Timestamp;

public class ManagementForm extends JPanel {

    private ArrayList<RoomType> roomTypes;
    private ArrayList<RoomForManaging> roomForManagings;
    private final ManagementBLL managementBLL = new ManagementBLL(this);
    private RoomTypePanel[] roomTypePanels;
    private RoomButton[] roomButtons;

    private Room[] availableRooms;
    private Booking currentBooking;

    public ManagementForm() {
        initComponents();
        RoomTypePanel.setManagementBLL(managementBLL);
        RoomButton.setManagementBLL(managementBLL);
        initRoomTypeList();
        initRoomList();

        RoomButton.setListener((RoomButton room) -> {
            CardLayout card = (CardLayout) infoPanel.getLayout();
            if (room.getRoom().getCurrentBooking() == 0) {
                updateIncomingBooking(room.getRoom());
                card.show(infoPanel, "empty");
            } else {
                currentBooking = managementBLL.getBookingByRoom(room.getRoom());
                txtBookingId.setText(String.valueOf(currentBooking.getId()));
                txtCustomerName.setText(managementBLL.getCustomerName(currentBooking.getCustomerId()));
                txtCheckIn.setText(currentBooking.getBookingRooms().get(0).getRealCheckIn().toString());
                txtCheckOut.setText(currentBooking.getCheckOutTime().toString());
                roomName.setText("Phòng " + room.getRoom().getName());
                card.show(infoPanel, "stayed");
            }
        });

        incomingBookings.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && incomingBookings.getSelectedRow() != -1) {

                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    private void initRoomTypeList() {
        roomList.removeAll();
        roomTypes = managementBLL.getRoomTypeList();
        roomTypePanels = new RoomTypePanel[roomTypes.size()];
        RoomTypePanel.setTypeNameList(new ArrayList<>());
        for (int i = 0; i < roomTypes.size(); i++) {
            roomTypePanels[i] = new RoomTypePanel(roomTypes.get(i), RoomTypePanel.MANAGE);
            roomList.add(roomTypePanels[i]);
            RoomTypePanel.getTypeNameList().add(roomTypePanels[i].getRoomType().getName());
        }
    }

    public void initRoomList() {
        roomForManagings = managementBLL.getAllRoom();
        roomButtons = new RoomButton[roomForManagings.size()];
        for (int i = 0; i < roomForManagings.size(); i++) {
            roomButtons[i] = new RoomButton(roomForManagings.get(i));
        }
        sortRoom();
        updateUI();
    }

    public void sortRoom() {
        for (RoomTypePanel typePanel : roomTypePanels) {
            typePanel.removeAllRoom();
            int maloaiphong = typePanel.getRoomType().getId();
            ArrayList<RoomButton> roomByType = managementBLL.getRoomByRoomType(roomButtons, maloaiphong);
            for (RoomButton temp : roomByType) {
                typePanel.addRoom(temp);
                temp.setTypePanel(typePanel);
            }
        }
    }

    private void updateIncomingBooking(RoomForManaging room) {
        incomingBookings.setModel(managementBLL.updateIncommingBookings(room));
    }

    public void deleteRoomType(RoomTypePanel temp) {
        roomList.remove(temp);
        updateUI();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        addNewRoomTypeDialog = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        cancel_addnew = new javax.swing.JButton();
        addnew = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        newTypeName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));
        newTypePrice = new javax.swing.JTextField();
        changeRoomDialog = new javax.swing.JDialog();
        jPanel6 = new javax.swing.JPanel();
        btnOk = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        sltRoomType = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        sltRoom = new javax.swing.JComboBox<>();
        chkOldRoomPay = new javax.swing.JCheckBox();
        roomPanel = new javax.swing.JPanel();
        list = new javax.swing.JScrollPane();
        roomList = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        addNewRoomType = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        infoPanel = new javax.swing.JPanel();
        emptyRoomPanel = new javax.swing.JPanel();
        emptyRoomButtons = new javax.swing.JPanel();
        btnEditRoom = new javax.swing.JButton();
        openCloseBooking = new javax.swing.JButton();
        btnRemoveRoom = new javax.swing.JButton();
        bookingScrollPane = new javax.swing.JScrollPane();
        incomingBookings = new javax.swing.JTable();
        stayedRoomPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        roomName = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtBookingId = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtCustomerName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtCheckIn = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtCheckOut = new javax.swing.JTextField();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 100), new java.awt.Dimension(0, 100), new java.awt.Dimension(32767, 100));
        jPanel5 = new javax.swing.JPanel();
        btnOrderService = new javax.swing.JButton();
        btnChangeRoom = new javax.swing.JButton();
        btnCheckOut = new javax.swing.JButton();

        addNewRoomTypeDialog.setTitle("Thêm loại phòng");
        addNewRoomTypeDialog.setLocation(new java.awt.Point(0, 0));
        addNewRoomTypeDialog.setMinimumSize(new java.awt.Dimension(300, 250));
        addNewRoomTypeDialog.setModal(true);
        addNewRoomTypeDialog.setPreferredSize(new java.awt.Dimension(300, 250));

        jPanel3.setPreferredSize(new java.awt.Dimension(100, 50));
        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

        cancel_addnew.setText("Hủy");
        cancel_addnew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancel_addnewActionPerformed(evt);
            }
        });
        jPanel3.add(cancel_addnew);

        addnew.setText("Thêm mới");
        addnew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addnewActionPerformed(evt);
            }
        });
        jPanel3.add(addnew);

        addNewRoomTypeDialog.getContentPane().add(jPanel3, java.awt.BorderLayout.PAGE_END);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thêm loại phòng mới", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Times New Roman", 2, 18))); // NOI18N
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Tên loại phòng:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.4;
        gridBagConstraints.weighty = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 0);
        jPanel4.add(jLabel1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.weightx = 5.6;
        gridBagConstraints.weighty = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 30);
        jPanel4.add(newTypeName, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Đơn giá:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.4;
        gridBagConstraints.weighty = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 0);
        jPanel4.add(jLabel2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.weighty = 0.1;
        jPanel4.add(filler2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.weightx = 5.6;
        gridBagConstraints.weighty = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 30);
        jPanel4.add(newTypePrice, gridBagConstraints);

        addNewRoomTypeDialog.getContentPane().add(jPanel4, java.awt.BorderLayout.CENTER);

        addNewRoomTypeDialog.getAccessibleContext().setAccessibleParent(this);

        changeRoomDialog.setTitle("Đổi phòng");

        jPanel6.setPreferredSize(new java.awt.Dimension(100, 50));

        btnOk.setText("OK");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });
        jPanel6.add(btnOk);

        btnCancel.setText("Huỷ");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        jPanel6.add(btnCancel);

        changeRoomDialog.getContentPane().add(jPanel6, java.awt.BorderLayout.PAGE_END);

        jPanel7.setLayout(new java.awt.GridBagLayout());

        jLabel11.setFont(new java.awt.Font("Times New Roman", 2, 24)); // NOI18N
        jLabel11.setText("Đổi phòng");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        jPanel7.add(jLabel11, gridBagConstraints);

        jLabel12.setText("Loại phòng:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jPanel7.add(jLabel12, gridBagConstraints);

        sltRoomType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));
        sltRoomType.setMinimumSize(new java.awt.Dimension(30, 30));
        sltRoomType.setPreferredSize(new java.awt.Dimension(100, 20));
        sltRoomType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                sltRoomTypeItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel7.add(sltRoomType, gridBagConstraints);

        jLabel13.setText("Phòng:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jPanel7.add(jLabel13, gridBagConstraints);

        sltRoom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));
        sltRoom.setMinimumSize(new java.awt.Dimension(30, 30));
        sltRoom.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel7.add(sltRoom, gridBagConstraints);

        chkOldRoomPay.setSelected(true);
        chkOldRoomPay.setText("Tính tiền phòng cũ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        jPanel7.add(chkOldRoomPay, gridBagConstraints);

        changeRoomDialog.getContentPane().add(jPanel7, java.awt.BorderLayout.CENTER);

        addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                formHierarchyChanged(evt);
            }
        });
        setLayout(new java.awt.BorderLayout(10, 10));

        roomPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Quản lý phòng", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 2, 24))); // NOI18N
        roomPanel.setLayout(new java.awt.BorderLayout());

        list.setBorder(null);
        list.setPreferredSize(new java.awt.Dimension(0, 50));

        roomList.setLayout(new javax.swing.BoxLayout(roomList, javax.swing.BoxLayout.PAGE_AXIS));
        list.setViewportView(roomList);

        roomPanel.add(list, java.awt.BorderLayout.CENTER);

        jPanel1.setPreferredSize(new java.awt.Dimension(10, 55));
        java.awt.FlowLayout flowLayout2 = new java.awt.FlowLayout(java.awt.FlowLayout.TRAILING);
        flowLayout2.setAlignOnBaseline(true);
        jPanel1.setLayout(flowLayout2);

        addNewRoomType.setBackground(new java.awt.Color(0, 204, 0));
        addNewRoomType.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        addNewRoomType.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hotel/GUI/Z_Icons/add_32px.png"))); // NOI18N
        addNewRoomType.setText("<html><p style=\"text-align:center\">Thêm loại phòng</p></html>");
        addNewRoomType.setPreferredSize(new java.awt.Dimension(125, 45));
        addNewRoomType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNewRoomTypeActionPerformed(evt);
            }
        });
        jPanel1.add(addNewRoomType);
        jPanel1.add(filler1);

        roomPanel.add(jPanel1, java.awt.BorderLayout.PAGE_START);

        add(roomPanel, java.awt.BorderLayout.CENTER);

        infoPanel.setBackground(new java.awt.Color(153, 204, 255));
        infoPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin phòng", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 2, 24))); // NOI18N
        infoPanel.setPreferredSize(new java.awt.Dimension(300, 10));
        infoPanel.setLayout(new java.awt.CardLayout());

        emptyRoomPanel.setBackground(infoPanel.getBackground());
        emptyRoomPanel.setLayout(new java.awt.BorderLayout());

        emptyRoomButtons.setBackground(infoPanel.getBackground());
        emptyRoomButtons.setLayout(new java.awt.GridLayout(0, 1));

        btnEditRoom.setText("Sửa thông tin phòng");
        emptyRoomButtons.add(btnEditRoom);

        openCloseBooking.setText("Đóng/mở đặt phòng");
        emptyRoomButtons.add(openCloseBooking);

        btnRemoveRoom.setText("Xoá phòng");
        emptyRoomButtons.add(btnRemoveRoom);

        emptyRoomPanel.add(emptyRoomButtons, java.awt.BorderLayout.PAGE_END);

        incomingBookings.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        bookingScrollPane.setViewportView(incomingBookings);

        emptyRoomPanel.add(bookingScrollPane, java.awt.BorderLayout.CENTER);

        infoPanel.add(emptyRoomPanel, "empty");

        stayedRoomPanel.setBackground(infoPanel.getBackground());
        stayedRoomPanel.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(infoPanel.getBackground());
        jPanel2.setLayout(new java.awt.GridBagLayout());

        roomName.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        roomName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        roomName.setText("Phòng");
        roomName.setPreferredSize(new java.awt.Dimension(250, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        jPanel2.add(roomName, gridBagConstraints);

        jLabel3.setText("Mã hồ sơ:");
        jLabel3.setPreferredSize(new java.awt.Dimension(55, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel2.add(jLabel3, gridBagConstraints);

        txtBookingId.setBackground(infoPanel.getBackground());
        txtBookingId.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtBookingId.setPreferredSize(new java.awt.Dimension(200, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jPanel2.add(txtBookingId, gridBagConstraints);

        jLabel4.setText("Khách hàng:");
        jLabel4.setPreferredSize(new java.awt.Dimension(55, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel2.add(jLabel4, gridBagConstraints);

        txtCustomerName.setBackground(infoPanel.getBackground());
        txtCustomerName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtCustomerName.setPreferredSize(new java.awt.Dimension(200, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jPanel2.add(txtCustomerName, gridBagConstraints);

        jLabel5.setText("Ngày nhận:");
        jLabel5.setPreferredSize(new java.awt.Dimension(55, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel2.add(jLabel5, gridBagConstraints);

        txtCheckIn.setBackground(infoPanel.getBackground());
        txtCheckIn.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtCheckIn.setPreferredSize(new java.awt.Dimension(200, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jPanel2.add(txtCheckIn, gridBagConstraints);

        jLabel6.setText("<html>Ngày trả<br>(dự kiến)</html>");
        jLabel6.setPreferredSize(new java.awt.Dimension(55, 28));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel2.add(jLabel6, gridBagConstraints);

        txtCheckOut.setBackground(infoPanel.getBackground());
        txtCheckOut.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtCheckOut.setPreferredSize(new java.awt.Dimension(200, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jPanel2.add(txtCheckOut, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.weighty = 0.8;
        jPanel2.add(filler3, gridBagConstraints);

        stayedRoomPanel.add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel5.setPreferredSize(new java.awt.Dimension(10, 100));
        jPanel5.setLayout(new java.awt.GridLayout(0, 1));

        btnOrderService.setText("Gọi dịch vụ");
        btnOrderService.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrderServiceActionPerformed(evt);
            }
        });
        jPanel5.add(btnOrderService);

        btnChangeRoom.setText("Đổi phòng");
        btnChangeRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeRoomActionPerformed(evt);
            }
        });
        jPanel5.add(btnChangeRoom);

        btnCheckOut.setText("Trả phòng");
        btnCheckOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckOutActionPerformed(evt);
            }
        });
        jPanel5.add(btnCheckOut);

        stayedRoomPanel.add(jPanel5, java.awt.BorderLayout.PAGE_END);

        infoPanel.add(stayedRoomPanel, "stayed");

        add(infoPanel, java.awt.BorderLayout.LINE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void addnewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addnewActionPerformed
        RoomType roomType = new RoomType();
        roomType.setName(newTypeName.getText());
        try {
            roomType.setPrice(Integer.valueOf(newTypePrice.getText()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Đơn giá không hợp lệ");
            return;
        }
        boolean check = managementBLL.addNewRoomType(roomType);
        if (check) {
            JOptionPane.showMessageDialog(null, "Thêm mới thành công");
            initRoomTypeList();
            sortRoom();
            cancel_addnew.doClick();
            updateUI();
            Rules.StateIsChanged = true;
        } else {
            JOptionPane.showMessageDialog(null, "Thêm mới thất bại");
        }
    }//GEN-LAST:event_addnewActionPerformed

    private void cancel_addnewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancel_addnewActionPerformed
        addNewRoomTypeDialog.setVisible(false);
    }//GEN-LAST:event_cancel_addnewActionPerformed

    private void addNewRoomTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNewRoomTypeActionPerformed
        addNewRoomTypeDialog.setLocationRelativeTo(null);
        addNewRoomTypeDialog.setVisible(true);
    }//GEN-LAST:event_addNewRoomTypeActionPerformed

    private void formHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_formHierarchyChanged
        if (this.isShowing() && Rules.StateIsChanged) {
            initRoomList();
            Rules.StateIsChanged = false;
        }
    }//GEN-LAST:event_formHierarchyChanged

    private void btnChangeRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeRoomActionPerformed
        availableRooms = managementBLL.getAvailableRooms(new Timestamp(System.currentTimeMillis()),
                Timestamp.valueOf(txtCheckOut.getText())).toArray(new Room[0]);
        for (RoomTypePanel roomTypePanel : roomTypePanels) {
            sltRoomType.addItem(roomTypePanel.getRoomType().getName());
        }
        changeRoomDialog.setSize(200, 200);
        changeRoomDialog.setLocationRelativeTo(null);
        changeRoomDialog.setVisible(true);
    }//GEN-LAST:event_btnChangeRoomActionPerformed

    private void sltRoomTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_sltRoomTypeItemStateChanged
        sltRoom.removeAllItems();
        int currentTypeId = 0;
        for (RoomTypePanel panel : roomTypePanels) {
            if (panel.getRoomType().getName().equals(sltRoomType.getSelectedItem())) {
                currentTypeId = panel.getRoomType().getId();
                break;
            }
        }
        for (Room room : availableRooms) {
            if (room.getTypeId() == currentTypeId
                    && room.getId() != RoomButton.getCurrentRoom().getId()) {
                sltRoom.addItem(room.getName());
            }
        }
    }//GEN-LAST:event_sltRoomTypeItemStateChanged

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        int newRoomId = 0;
        for (Room room : availableRooms) {
            if (room.getName().equals(sltRoom.getSelectedItem())) {
                newRoomId = room.getId();
                break;
            }
        }
        if (managementBLL.changeRoom(currentBooking.getBookingRooms().get(0).getId(),
                newRoomId, chkOldRoomPay.isSelected())) {
            initRoomList();
            Rules.StateIsChanged = true;
        }
        changeRoomDialog.setVisible(false);
    }//GEN-LAST:event_btnOkActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        changeRoomDialog.setVisible(false);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnCheckOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckOutActionPerformed
        RoomButton.goToBooking();
    }//GEN-LAST:event_btnCheckOutActionPerformed

    private void btnOrderServiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrderServiceActionPerformed
        new RoomServiceForm(RoomButton.getCurrentRoom().getCurrentBooking(),
                RoomButton.getCurrentRoom().getId()).setVisible(true);
    }//GEN-LAST:event_btnOrderServiceActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addNewRoomType;
    private javax.swing.JDialog addNewRoomTypeDialog;
    private javax.swing.JButton addnew;
    private javax.swing.JScrollPane bookingScrollPane;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnChangeRoom;
    private javax.swing.JButton btnCheckOut;
    private javax.swing.JButton btnEditRoom;
    private javax.swing.JButton btnOk;
    private javax.swing.JButton btnOrderService;
    private javax.swing.JButton btnRemoveRoom;
    private javax.swing.JButton cancel_addnew;
    private javax.swing.JDialog changeRoomDialog;
    private javax.swing.JCheckBox chkOldRoomPay;
    private javax.swing.JPanel emptyRoomButtons;
    private javax.swing.JPanel emptyRoomPanel;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.JTable incomingBookings;
    private javax.swing.JPanel infoPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane list;
    private javax.swing.JTextField newTypeName;
    private javax.swing.JTextField newTypePrice;
    private javax.swing.JButton openCloseBooking;
    private javax.swing.JPanel roomList;
    private javax.swing.JLabel roomName;
    private javax.swing.JPanel roomPanel;
    private javax.swing.JComboBox<String> sltRoom;
    private javax.swing.JComboBox<String> sltRoomType;
    private javax.swing.JPanel stayedRoomPanel;
    private javax.swing.JTextField txtBookingId;
    private javax.swing.JTextField txtCheckIn;
    private javax.swing.JTextField txtCheckOut;
    private javax.swing.JTextField txtCustomerName;
    // End of variables declaration//GEN-END:variables
}
