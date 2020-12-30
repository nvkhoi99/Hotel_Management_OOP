package Hotel.GUI.BookingGUI;

import Hotel.DTO.Customer;
import Hotel.DTO.rooms.RoomForOrdering;
import Hotel.DTO.RoomType;
import Hotel.BLL.ManagementBLL;
import Hotel.BLL.BookingBLL;
import Hotel.BLL.Rules;
import Hotel.GUI.ManagementGUI.RoomTypePanel;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.sql.Timestamp;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.SqlDateModel;

/**
 *
 * @author Yue
 */
public class BookingForm extends JPanel {

    private Customer customer = null;
    private ArrayList<RoomType> roomTypes;
    private List<RoomForOrdering> roomForOrderings;
    private final BookingBLL bookingBLL = new BookingBLL(this);
    private RoomTypePanel[] roomTypePanels;
    private RoomToggle[] roomButtons;
    private static final DecimalFormat currencyFormat = new DecimalFormat("#,##0 VNĐ");

    private SqlDateModel from_model, to_model;
    private JDatePanelImpl from_panel, to_panel;
    private JDatePickerImpl ngaynhan1, ngaytra1;

    private class DateLabelFormatter extends AbstractFormatter {

        private String datePattern = "dd/MM/yyyy";
        private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

        @Override
        public Object stringToValue(String text) throws ParseException {
            return dateFormatter.parseObject(text);
        }

        @Override
        public String valueToString(Object value) throws ParseException {
            if (value != null) {
                Calendar cal = (Calendar) value;
                return dateFormatter.format(cal.getTime());
            }

            return "";
        }
    }

    public BookingForm() {
        this.customer = null;
        initComponents();
        RoomTypePanel.setBookingBLL(bookingBLL);
        initRoomTypeList();
        initRoomList();
        //initInAndOut();
        reminder.setVisible(false);
        initFromTo();
    }

    private void initFromTo() {
        DateLabelFormatter dateFormatter = new DateLabelFormatter();

        from_model = new SqlDateModel(new Date(System.currentTimeMillis()));
        from_panel = new JDatePanelImpl(from_model);
        ngaynhan1 = new JDatePickerImpl(from_panel, dateFormatter);

        to_model = new SqlDateModel(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24));
        to_panel = new JDatePanelImpl(to_model);
        ngaytra1 = new JDatePickerImpl(to_panel, dateFormatter);

        ngaynhan.setVisible(false);
        ngaytra.setVisible(false);
        fromPanel.add(ngaynhan1);
        toPanel.add(ngaytra1);

        ngaynhan1.getJFormattedTextField().addPropertyChangeListener((PropertyChangeEvent evt) -> {
            validateCheckInTime();
        });
        ngaytra1.getJFormattedTextField().addPropertyChangeListener((PropertyChangeEvent evt) -> {
            validateCheckOutTime();
        });
    }

    private void validateCheckInTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        if (from_model.getValue().getTime() < calendar.getTimeInMillis()) {
            from_model.setValue(new Date(calendar.getTimeInMillis()));
        } else if (from_model.getValue().getTime() >= to_model.getValue().getTime()) {
            to_model.setValue(new Date(from_model.getValue().getTime() + 1000 * 60 * 60 * 24));
        }
        checkOrder_new();
    }

    private void validateCheckOutTime() {
        if (to_model.getValue().getTime() <= from_model.getValue().getTime()) {
            to_model.setValue(new Date(from_model.getValue().getTime() + 1000 * 60 * 60 * 24));
        }
        checkOrder_new();
    }

    private void initRoomTypeList() {
        roomList.removeAll();
        roomTypes = new ManagementBLL(null).getRoomTypeList();
        roomTypePanels = new RoomTypePanel[roomTypes.size()];
        for (int i = 0; i < roomTypes.size(); i++) {
            roomTypePanels[i] = new RoomTypePanel(roomTypes.get(i), RoomTypePanel.ORDER);
            roomList.add(roomTypePanels[i]);
        }
    }

    private void initRoomList() {
        roomForOrderings = bookingBLL.getAllRoom();
        roomButtons = new RoomToggle[roomForOrderings.size()];
        for (int i = 0; i < roomForOrderings.size(); i++) {
            roomButtons[i] = new RoomToggle(roomForOrderings.get(i));
            if (!roomButtons[i].getRoom().isAvailable()) {
                roomButtons[i].setEnabled(false);
            }
        }
        sortRoom();
    }

    private void sortRoom() {
        for (RoomTypePanel typePanel : roomTypePanels) {
            typePanel.removeAllRoom();
            int maloaiphong = typePanel.getRoomType().getId();
            ArrayList<RoomToggle> roomByType = bookingBLL.getRoomByRoomType(roomButtons, maloaiphong);
            for (RoomToggle temp : roomByType) {
                typePanel.addRoomToChoose(temp);
                temp.setTypePanel(typePanel);
                temp.getRoom().setPrice(typePanel.getRoomType().getPrice());
            }
        }
    }

    private void initInAndOut() {
        Calendar calendar = Calendar.getInstance();
        String homnay = bookingBLL.dateFormat.format(calendar.getTime());
        ngaynhan.setText(homnay);
        calendar.add(Calendar.DATE, 1);
        String ngaymai = bookingBLL.dateFormat.format(calendar.getTime());
        ngaytra.setText(ngaymai);
        checkOrder();
    }

    public void eraseIdCard() {
        cmnd.setText("");
    }

    private void checkOrder() {
        ArrayList<Integer> orderedRooms = bookingBLL.checkOrder(ngaynhan.getText(), ngaytra.getText());
        for (RoomToggle i : roomButtons) {
            if (i.isSelected()) {
                i.setSelected(false);
            }
            if (i.getRoom().isAvailable()) {
                if (orderedRooms.contains(i.getRoom().getId())) {
                    i.setEnabled(false);
                } else {
                    i.setEnabled(true);
                }
            }
        }
    }

    private void checkOrder_new() {
        ArrayList<Integer> availableRooms = bookingBLL.checkOrder_new(from_model.getValue(), to_model.getValue());
        for (RoomToggle i : roomButtons) {
            if (i.isSelected()) {
                i.setSelected(false);
            }
            if (i.getRoom().isAvailable()) {
                i.setEnabled(availableRooms.contains(i.getRoom().getId()));
            }
        }
    }

    public void updateDeposit() {
        int sum = 0;
        for (RoomTypePanel typePanel : roomTypePanels) {
            sum += typePanel.getChoose() * typePanel.getPrice();
        }
        tongcoc.setText(currencyFormat.format(sum));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        orderRoomList = new javax.swing.JPanel();
        from_to = new javax.swing.JPanel();
        fromPanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        ngaynhan = new javax.swing.JFormattedTextField();
        toPanel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        ngaytra = new javax.swing.JFormattedTextField();
        scrollList = new javax.swing.JScrollPane();
        roomList = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        orderButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();
        deposit = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        tongcoc = new javax.swing.JTextField();
        nhancoc = new javax.swing.JCheckBox();
        customerInfo = new javax.swing.JPanel();
        infoFill = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        hotenkh = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cmnd = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        diachi = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        sdt = new javax.swing.JTextField();
        gioitinh = new javax.swing.JComboBox<>();
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        jPanel3 = new javax.swing.JPanel();
        reminder = new javax.swing.JLabel();

        addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                formHierarchyChanged(evt);
            }
        });
        setLayout(new java.awt.BorderLayout(10, 0));

        orderRoomList.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách phòng", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Lucida Calligraphy", 0, 18))); // NOI18N
        orderRoomList.setLayout(new java.awt.BorderLayout());

        from_to.setPreferredSize(new java.awt.Dimension(100, 50));
        from_to.setLayout(new java.awt.GridLayout(1, 0));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Từ ngày:");
        fromPanel.add(jLabel5);

        ngaynhan.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));
        ngaynhan.setPreferredSize(new java.awt.Dimension(200, 30));
        ngaynhan.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ngaynhanFocusLost(evt);
            }
        });
        ngaynhan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ngaynhanKeyPressed(evt);
            }
        });
        fromPanel.add(ngaynhan);

        from_to.add(fromPanel);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Đến ngày");
        toPanel.add(jLabel8);

        ngaytra.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));
        ngaytra.setPreferredSize(new java.awt.Dimension(200, 30));
        ngaytra.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ngaytraFocusLost(evt);
            }
        });
        ngaytra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ngaytraKeyPressed(evt);
            }
        });
        toPanel.add(ngaytra);

        from_to.add(toPanel);

        orderRoomList.add(from_to, java.awt.BorderLayout.PAGE_START);

        roomList.setLayout(new javax.swing.BoxLayout(roomList, javax.swing.BoxLayout.PAGE_AXIS));
        scrollList.setViewportView(roomList);

        orderRoomList.add(scrollList, java.awt.BorderLayout.CENTER);

        jPanel1.setPreferredSize(new java.awt.Dimension(10, 50));
        jPanel1.setLayout(new java.awt.BorderLayout());

        orderButton.setBackground(new java.awt.Color(255, 153, 153));
        orderButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        orderButton.setForeground(new java.awt.Color(255, 0, 51));
        orderButton.setText("Đặt phòng");
        orderButton.setPreferredSize(new java.awt.Dimension(150, 31));
        orderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderButtonActionPerformed(evt);
            }
        });
        jPanel1.add(orderButton, java.awt.BorderLayout.LINE_END);

        refreshButton.setBackground(new java.awt.Color(255, 153, 153));
        refreshButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        refreshButton.setForeground(new java.awt.Color(255, 0, 51));
        refreshButton.setText("Làm mới");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });
        jPanel1.add(refreshButton, java.awt.BorderLayout.LINE_START);

        java.awt.FlowLayout flowLayout3 = new java.awt.FlowLayout(java.awt.FlowLayout.TRAILING);
        flowLayout3.setAlignOnBaseline(true);
        deposit.setLayout(flowLayout3);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 51));
        jLabel7.setText("Tổng cọc:");
        deposit.add(jLabel7);

        tongcoc.setEditable(false);
        tongcoc.setBackground(deposit.getBackground());
        tongcoc.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tongcoc.setForeground(new java.awt.Color(255, 0, 51));
        tongcoc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tongcoc.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 0, 51)));
        tongcoc.setCaretColor(new java.awt.Color(255, 0, 51));
        tongcoc.setPreferredSize(new java.awt.Dimension(150, 23));
        deposit.add(tongcoc);

        nhancoc.setBackground(deposit.getBackground());
        nhancoc.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        nhancoc.setText("Nhận cọc");
        nhancoc.setPreferredSize(new java.awt.Dimension(80, 40));
        deposit.add(nhancoc);

        jPanel1.add(deposit, java.awt.BorderLayout.CENTER);

        orderRoomList.add(jPanel1, java.awt.BorderLayout.PAGE_END);

        add(orderRoomList, java.awt.BorderLayout.CENTER);

        customerInfo.setBackground(new java.awt.Color(153, 204, 255));
        customerInfo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin khách hàng", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Lucida Calligraphy", 0, 18))); // NOI18N
        customerInfo.setForeground(new java.awt.Color(255, 255, 255));
        customerInfo.setPreferredSize(new java.awt.Dimension(300, 600));
        customerInfo.setLayout(new java.awt.BorderLayout());

        infoFill.setBackground(customerInfo.getBackground());
        infoFill.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Họ tên KH:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        infoFill.add(jLabel1, gridBagConstraints);

        hotenkh.setBackground(customerInfo.getBackground());
        hotenkh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        hotenkh.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        infoFill.add(hotenkh, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Giới tính:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        infoFill.add(jLabel2, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("CMND:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        infoFill.add(jLabel3, gridBagConstraints);

        cmnd.setBackground(customerInfo.getBackground());
        cmnd.setColumns(12);
        cmnd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmnd.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cmnd.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cmndFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        infoFill.add(cmnd, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Địa chỉ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        infoFill.add(jLabel4, gridBagConstraints);

        diachi.setBackground(customerInfo.getBackground());
        diachi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        diachi.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        infoFill.add(diachi, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Số điện thoại:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        infoFill.add(jLabel6, gridBagConstraints);

        sdt.setBackground(customerInfo.getBackground());
        sdt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sdt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        sdt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                sdtFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        infoFill.add(sdt, gridBagConstraints);

        gioitinh.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        gioitinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ"}));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        infoFill.add(gioitinh, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.weighty = 10.0;
        infoFill.add(filler5, gridBagConstraints);

        customerInfo.add(infoFill, java.awt.BorderLayout.CENTER);

        jPanel3.setBackground(customerInfo.getBackground());
        jPanel3.setMinimumSize(new java.awt.Dimension(10, 15));
        jPanel3.setPreferredSize(new java.awt.Dimension(10, 30));
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0);
        flowLayout1.setAlignOnBaseline(true);
        jPanel3.setLayout(flowLayout1);

        reminder.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        reminder.setForeground(java.awt.Color.red);
        reminder.setText("Chưa điền đầy đủ thông tin");
        reminder.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        reminder.setPreferredSize(new java.awt.Dimension(151, 30));
        jPanel3.add(reminder);

        customerInfo.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        add(customerInfo, java.awt.BorderLayout.LINE_START);
    }// </editor-fold>//GEN-END:initComponents

    private void cmndFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmndFocusLost
        customer = bookingBLL.checkIdCard(this.cmnd.getText());
        if (customer != null) {
            this.hotenkh.setText(customer.getFullname());
            this.gioitinh.setSelectedIndex(customer.isGender() ? 1 : 0);
            this.diachi.setText(customer.getAddress());
            this.sdt.setText(customer.getPhone());
        } else {
            customer = null;
        }
    }//GEN-LAST:event_cmndFocusLost

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        hotenkh.setText("");
        gioitinh.setSelectedIndex(0);
        cmnd.setText("");
        diachi.setText("");
        sdt.setText("");
        customer = null;
        reminder.setVisible(false);
        checkOrder_new();
    }//GEN-LAST:event_refreshButtonActionPerformed

    private JComponent checkInfoFilled() {
        if (cmnd.getText().equals("")) {
            return cmnd;
        }
        if (hotenkh.getText().equals("")) {
            return hotenkh;
        }
        if (diachi.getText().equals("")) {
            return diachi;
        }
        if (sdt.getText().equals("")) {
            return sdt;
        }
        return null;
    }

    private void getNewCustomer() {
        customer = new Customer();
        customer.setFullname(hotenkh.getText());
        customer.setGender((gioitinh.getSelectedIndex() == 1));
        customer.setIdentity(cmnd.getText());
        customer.setAddress(diachi.getText());
        customer.setPhone(sdt.getText());
    }

    private void orderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderButtonActionPerformed
        //Kiểm tra thông tin đã điền đủ chưa
        if (checkInfoFilled() != null) {
            reminder.setVisible(true);
            checkInfoFilled().requestFocus();
        } else {
            reminder.setVisible(false);
            if (customer == null) {
                getNewCustomer();
            }
            ArrayList<RoomForOrdering> orderRooms = new ArrayList<>();
            for (RoomToggle roomToggle : roomButtons) {
                if (roomToggle.isSelected()) {
                    orderRooms.add(roomToggle.getRoom());
                }
            }
            int bookingId = bookingBLL.addBooking(customer, ngaynhan1.getJFormattedTextField().getText(),
                    ngaytra1.getJFormattedTextField().getText(), orderRooms, nhancoc.isSelected() ? 1 : 0);
            if (bookingId > 0) {
                JOptionPane.showMessageDialog(null, "Đơn #" + bookingId + " đặt phòng thành công");
                Rules.StateIsChanged = true;
                checkOrder_new();
            } else {
                JOptionPane.showMessageDialog(null, "Đặt phòng thất bại, đã xảy ra lỗi");
            }
        }
    }//GEN-LAST:event_orderButtonActionPerformed

    private void ngaynhanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ngaynhanKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            ngaytra.requestFocus();
        }
    }//GEN-LAST:event_ngaynhanKeyPressed

    private void ngaytraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ngaytraKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            cmnd.requestFocus();
        }
    }//GEN-LAST:event_ngaytraKeyPressed

    private void formHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_formHierarchyChanged
        if (this.isShowing() && Rules.StateIsChanged) {
            initRoomTypeList();
            initRoomList();
            checkOrder_new();
            Rules.StateIsChanged = false;
        }
    }//GEN-LAST:event_formHierarchyChanged

    private void sdtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_sdtFocusLost
        if (!bookingBLL.checkPhone(sdt.getText())) {
            sdt.setText("");
        }
    }//GEN-LAST:event_sdtFocusLost

    private void ngaynhanFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ngaynhanFocusLost
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Timestamp checkIn = bookingBLL.convert(ngaynhan.getText(), 0);
        if (checkIn.getTime() < calendar.getTime().getTime()) {
            ngaynhan.setText(bookingBLL.dateFormat.format(calendar.getTime()));
        } else if (checkIn.getTime() > bookingBLL.convert(ngaytra.getText(), 0).getTime()) {
            long tomorrow = checkIn.getTime() + (1000 * 60 * 60 * 24);
            ngaytra.setText(bookingBLL.dateFormat.format(new Timestamp(tomorrow)));
        }
        checkOrder();
    }//GEN-LAST:event_ngaynhanFocusLost

    private void ngaytraFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ngaytraFocusLost
        Timestamp checkOut = bookingBLL.convert(ngaytra.getText(), 0);
        long tomorrow = bookingBLL.convert(ngaynhan.getText(), 0).getTime() + (1000 * 60 * 60 * 24);
        if (checkOut.getTime() < tomorrow) {
            ngaytra.setText(bookingBLL.dateFormat.format(new Timestamp(tomorrow)));
        }
        checkOrder();
    }//GEN-LAST:event_ngaytraFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cmnd;
    private javax.swing.JPanel customerInfo;
    private javax.swing.JPanel deposit;
    private javax.swing.JTextField diachi;
    private javax.swing.Box.Filler filler5;
    private javax.swing.JPanel fromPanel;
    private javax.swing.JPanel from_to;
    private javax.swing.JComboBox<String> gioitinh;
    private javax.swing.JTextField hotenkh;
    private javax.swing.JPanel infoFill;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JFormattedTextField ngaynhan;
    private javax.swing.JFormattedTextField ngaytra;
    private javax.swing.JCheckBox nhancoc;
    private javax.swing.JButton orderButton;
    private javax.swing.JPanel orderRoomList;
    private javax.swing.JButton refreshButton;
    private javax.swing.JLabel reminder;
    private javax.swing.JPanel roomList;
    private javax.swing.JScrollPane scrollList;
    private javax.swing.JTextField sdt;
    private javax.swing.JPanel toPanel;
    private javax.swing.JTextField tongcoc;
    // End of variables declaration//GEN-END:variables

}
