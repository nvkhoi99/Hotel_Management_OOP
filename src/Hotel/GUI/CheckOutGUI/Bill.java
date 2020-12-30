package Hotel.GUI.CheckOutGUI;

import Hotel.BLL.BookingBLL;
import Hotel.BLL.ManagementBLL;
import Hotel.BLL.ServiceBLL;
import Hotel.DTO.Booking;
import Hotel.DTO.RoomType;
import Hotel.DTO.rooms.BookingRoom;
import Hotel.DTO.rooms.Room;
import Hotel.DTO.services.RoomService;
import Hotel.DTO.services.Service;
import Hotel.GUI.common.DataChangeListener;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.List;

/**
 *
 * @author Admin
 */
public class Bill extends javax.swing.JDialog {

    private List<RoomType> roomTypePool;
    private List<? extends Room> roomPool;
    private List<Service> servicePool;
    private final DataChangeListener listener;

    private Booking booking;
    private DefaultTableModel roomListModel = new DefaultTableModel();
    private DefaultTableModel serviceListModel;
    private int tongTiendv = 0;
    private int tongTienphong = 0;

    private ServiceBLL serviceBLL = new ServiceBLL();

    public Bill(Frame parent, boolean modal, Booking booking,
            List<? extends Room> roomPool, List<RoomType> roomTypePool, List<Service> servicePool, DataChangeListener listener) {
        super(parent, modal);
        this.booking = booking;
        this.roomPool = roomPool;
        this.roomTypePool = roomTypePool;
        this.servicePool = servicePool;
        this.listener = listener;
        initComponents();
        initializeRoomList();
        initializeServiceList();
        DecimalFormat formatter = new DecimalFormat("#,##0.0");
        tenkh.setText(new ManagementBLL().getCustomerName(booking.getCustomerId()));
        mathuephong.setText(String.format("%07d", booking.getId()));
        tiencoc.setText(formatter.format(booking.getDeposit()));
        jtFTongtienphong.setText(formatter.format(tongTienphong));
        jtFTongtiendv.setText(formatter.format(tongTiendv));
        jTFTongcong.setText(formatter.format(tongTienphong + tongTiendv));
        jTFTongthanhtoan.setText(formatter.format(tongTienphong + tongTiendv - booking.getDeposit()));
        Chot.setVisible(booking.getPaytime() == null);
    }

    private void initializeRoomList() {
        roomListModel = (DefaultTableModel) dsthuephong.getModel();
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < dsthuephong.getColumnCount(); i++) {
            dsthuephong.getColumnModel().getColumn(i).setHeaderRenderer(renderer);
            dsthuephong.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
        for (BookingRoom room : booking.getBookingRooms()) {
            Object[] col = new Object[7];
            Room rm = roomPool.stream().filter(r -> r.getId() == room.getRoomId()).findFirst().get();
            col[0] = rm.getName();
            col[1] = roomTypePool.stream().filter(rt -> rt.getId() == rm.getTypeId()).findFirst().get().getName();
            col[2] = room.getRealCheckIn();
            col[3] = room.getRealCheckOut();
            col[4] = room.getRoomPrice();
            col[5] = room.getSurcharge();
            col[6] = Thanhtien((int) col[4], (Integer) col[5], (Timestamp) col[2], (Timestamp) col[3]);
            tongTienphong += (int) col[6];
            roomListModel.addRow(col);
        }
    }

    private long calculateStayTime(Timestamp thucnhan, Timestamp thuctra) {
        long HOUR = 1000 * 60 * 60;
        long DAY = HOUR * 24;

        Timestamp checkIn;
        Timestamp checkOut;

        if (thucnhan.getHours() >= 5
                && thucnhan.getHours() < 14) {
            checkIn = new Timestamp(thucnhan.getTime());
        } else {
            checkIn = new Timestamp(thucnhan.getTime() - 14 * HOUR);
        }
        checkIn.setHours(14);
        checkIn.setMinutes(0);
        checkIn.setSeconds(0);

        if (thucnhan.getHours() >= 12
                && thucnhan.getHours() < 18) {
            checkOut = new Timestamp(thuctra.getTime());
        } else {
            checkOut = new Timestamp(thuctra.getTime() + 6 * HOUR);
        }
        checkOut.setHours(12);
        checkOut.setMinutes(0);
        checkOut.setSeconds(0);

        return (checkOut.getTime() - checkIn.getTime()) / DAY + 1;
    }

    private int Thanhtien(int dongia, int phuthu, Timestamp thucnhan, Timestamp thuctra) {
        long songay = calculateStayTime(thucnhan, thuctra);
        int tien = (int) (dongia * songay + phuthu);
        return tien;
    }

    private int PhuThu(int dongia, Timestamp thucnhan, Timestamp thuctra) {
        int phuthu = 0;
        if (thucnhan.getHours() >= 5 && thucnhan.getHours() < 14) {
            phuthu += (int) (dongia * 0.15);
        }
        if (thuctra.getHours() >= 12 && thuctra.getHours() < 18) {
            phuthu += (int) (dongia * 0.15);
        }
        return phuthu;
    }

    Object[] columnsname1 = {"maph", "A", "B", "C", "D", "E"};

    public Class getaClass1(int column) {
        switch (column) {
            case 0:
                return Timestamp.class;
            case 1:
                return String.class;
            case 2:
                return Integer.class;
            case 3:
                return Integer.class;
            case 4:
                return Integer.class;
        }
        return null;
    }

    ;
    private void initializeServiceList() {
//        DefaultTableModel defaultTableModel = new DefaultTableModel() {
//            public Class getColumnClass1(int column) {
//                return getaClass1(column);
//            }
//        };
//        dsDichvu.setModel(defaultTableModel);
//        defaultTableModel.addColumn("Ngày dùng");
//        defaultTableModel.addColumn("Dịch vụ");
//        defaultTableModel.addColumn("Phòng");
//        defaultTableModel.addColumn("Đơn giá");
//        defaultTableModel.addColumn("Số lượng");
//        defaultTableModel.addColumn("Thành tiền");
        DefaultTableModel serviceModel = (DefaultTableModel) dsDichvu.getModel();
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < dsDichvu.getColumnCount(); i++) {
            dsDichvu.getColumnModel().getColumn(i).setHeaderRenderer(renderer);
            dsDichvu.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
        for (RoomService roomService : booking.getBookingServices()) {
            Object[] col = new Object[6];
            col[0] = roomService.getUseTime();
            col[1] = servicePool.stream().filter(rs -> rs.getId() == roomService.getServiceId()).findFirst().get().getName();
            col[2] = roomPool.stream().filter(r -> r.getId() == roomService.getRoomId()).findFirst().get().getName();
            col[3] = roomService.getPrice();
            col[4] = roomService.getQuantity();
            col[5] = calculateMoneyService((int) col[3], (int) col[4]);
            tongTiendv += (int) col[5];
            serviceModel.addRow(col);
        }
    }

    private int calculateMoneyService(int dongia, int Soluong) {
        return dongia * Soluong;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel16 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        tieude = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        main = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        mathuephong = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tenkh = new javax.swing.JTextField();
        jpanel123 = new javax.swing.JPanel();
        jlableTongcong = new javax.swing.JLabel();
        jTFTongcong = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        tiencoc = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTFTongthanhtoan = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        dichvu = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        dsthuephong = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jtFTongtienphong = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        dsDichvu = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jtFTongtiendv = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        Chot = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(30, 0), new java.awt.Dimension(30, 0), new java.awt.Dimension(30, 32767));
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(30, 0), new java.awt.Dimension(30, 0), new java.awt.Dimension(30, 32767));

        jLabel16.setText("jLabel16");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 204, 255));
        setModal(true);
        setPreferredSize(new java.awt.Dimension(900, 700));

        jPanel3.setLayout(new java.awt.BorderLayout(0, 8));

        tieude.setPreferredSize(new java.awt.Dimension(886, 120));
        tieude.setLayout(new java.awt.BorderLayout());

        jPanel2.setEnabled(false);
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Tên khách sạn/ Hotel name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel2.add(jLabel2, gridBagConstraints);

        jTextField1.setText("Khách sạn KHODU");
        jTextField1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(102, 102, 102)));
        jTextField1.setEnabled(false);
        jTextField1.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 0, 50);
        jPanel2.add(jTextField1, gridBagConstraints);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Địa chỉ/Address:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.5;
        jPanel2.add(jLabel3, gridBagConstraints);

        jTextField2.setText("Hà Nội");
        jTextField2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jTextField2.setEnabled(false);
        jTextField2.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 50);
        jPanel2.add(jTextField2, gridBagConstraints);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Số điện thoại/Tel:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.5;
        jPanel2.add(jLabel4, gridBagConstraints);

        jTextField3.setText("09696969696");
        jTextField3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jTextField3.setEnabled(false);
        jTextField3.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 50);
        jPanel2.add(jTextField3, gridBagConstraints);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Email:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.5;
        jPanel2.add(jLabel5, gridBagConstraints);

        jTextField4.setText("Hotel@gmail.com");
        jTextField4.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jTextField4.setEnabled(false);
        jTextField4.setOpaque(false);
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 50);
        jPanel2.add(jTextField4, gridBagConstraints);

        tieude.add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel3.add(tieude, java.awt.BorderLayout.PAGE_START);

        main.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        main.setLayout(new java.awt.BorderLayout(0, 10));

        jPanel4.setRequestFocusEnabled(false);
        jPanel4.setVerifyInputWhenFocusTarget(false);
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel6.setText("Hồ sơ thuê phòng:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        jPanel4.add(jLabel6, gridBagConstraints);

        mathuephong.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 50);
        jPanel4.add(mathuephong, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
        jLabel7.setText("Chốt hóa đơn");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 1.4;
        jPanel4.add(jLabel7, gridBagConstraints);

        jLabel8.setText("Họ tên khách hàng:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        jPanel4.add(jLabel8, gridBagConstraints);

        tenkh.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 50);
        jPanel4.add(tenkh, gridBagConstraints);

        main.add(jPanel4, java.awt.BorderLayout.PAGE_START);

        jpanel123.setLayout(new java.awt.GridBagLayout());

        jlableTongcong.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlableTongcong.setForeground(new java.awt.Color(255, 0, 51));
        jlableTongcong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlableTongcong.setText("Tổng cộng:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.weighty = 0.1;
        jpanel123.add(jlableTongcong, gridBagConstraints);

        jTFTongcong.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTFTongcong.setForeground(new java.awt.Color(255, 0, 51));
        jTFTongcong.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        jpanel123.add(jTFTongcong, gridBagConstraints);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Đã cọc:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.weighty = 0.1;
        jpanel123.add(jLabel12, gridBagConstraints);

        tiencoc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tiencoc.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        jpanel123.add(tiencoc, gridBagConstraints);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 0, 51));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Tổng thanh toán:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.weighty = 0.1;
        jpanel123.add(jLabel13, gridBagConstraints);

        jTFTongthanhtoan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTFTongthanhtoan.setForeground(new java.awt.Color(255, 0, 51));
        jTFTongthanhtoan.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        jpanel123.add(jTFTongthanhtoan, gridBagConstraints);

        main.add(jpanel123, java.awt.BorderLayout.PAGE_END);

        jPanel6.setLayout(new java.awt.BorderLayout());

        jPanel7.setPreferredSize(new java.awt.Dimension(50, 291));
        jPanel7.setLayout(new java.awt.GridLayout(0, 1, 0, 10));

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Phòng");
        jPanel7.add(jLabel9);

        dichvu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dichvu.setText("Dịch vụ");
        jPanel7.add(dichvu);

        jPanel6.add(jPanel7, java.awt.BorderLayout.LINE_START);

        jPanel8.setLayout(new java.awt.GridLayout(0, 1, 10, 10));

        jPanel5.setLayout(new java.awt.BorderLayout());

        dsthuephong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Phòng", "Loại phòng", "Thực nhận", "Thực trả", "Đơn giá", "Phụ thu", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        dsthuephong.setShowGrid(true);
        jScrollPane1.setViewportView(dsthuephong);

        jPanel5.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel11.setPreferredSize(new java.awt.Dimension(10, 30));
        jPanel11.setLayout(new java.awt.BorderLayout());

        jtFTongtienphong.setBackground(new java.awt.Color(255, 255, 153));
        jtFTongtienphong.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jtFTongtienphong.setForeground(new java.awt.Color(102, 102, 255));
        jtFTongtienphong.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtFTongtienphong.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jtFTongtienphong.setOpaque(false);
        jtFTongtienphong.setPreferredSize(new java.awt.Dimension(167, 19));
        jPanel11.add(jtFTongtienphong, java.awt.BorderLayout.LINE_END);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Tổng tiền phòng:");
        jLabel10.setPreferredSize(new java.awt.Dimension(500, 13));
        jPanel11.add(jLabel10, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel11, java.awt.BorderLayout.PAGE_END);

        jPanel8.add(jPanel5);

        jPanel10.setLayout(new java.awt.BorderLayout());

        dsDichvu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ngày dùng", "Dịch vụ", "Phòng", "Đơn giá", "Số lượng", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        dsDichvu.setShowGrid(true);
        jScrollPane2.setViewportView(dsDichvu);

        jPanel10.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel12.setPreferredSize(new java.awt.Dimension(10, 30));
        jPanel12.setLayout(new java.awt.BorderLayout());

        jtFTongtiendv.setBackground(new java.awt.Color(255, 255, 153));
        jtFTongtiendv.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jtFTongtiendv.setForeground(new java.awt.Color(102, 102, 255));
        jtFTongtiendv.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtFTongtiendv.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jtFTongtiendv.setOpaque(false);
        jtFTongtiendv.setPreferredSize(new java.awt.Dimension(167, 19));
        jPanel12.add(jtFTongtiendv, java.awt.BorderLayout.LINE_END);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Tổng tiền dịch vụ:");
        jLabel11.setPreferredSize(new java.awt.Dimension(500, 13));
        jPanel12.add(jLabel11, java.awt.BorderLayout.CENTER);

        jPanel10.add(jPanel12, java.awt.BorderLayout.PAGE_END);

        jPanel8.add(jPanel10);

        jPanel6.add(jPanel8, java.awt.BorderLayout.CENTER);

        main.add(jPanel6, java.awt.BorderLayout.CENTER);

        jPanel3.add(main, java.awt.BorderLayout.CENTER);

        Chot.setBackground(new java.awt.Color(255, 153, 153));
        Chot.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        Chot.setForeground(new java.awt.Color(255, 0, 51));
        Chot.setText("Thanh Toán");
        Chot.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        Chot.setPreferredSize(new java.awt.Dimension(63, 50));
        Chot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChotActionPerformed(evt);
            }
        });
        jPanel3.add(Chot, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        filler1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 0, new java.awt.Color(0, 0, 0)));
        getContentPane().add(filler1, java.awt.BorderLayout.LINE_START);

        filler2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(0, 0, 0)));
        getContentPane().add(filler2, java.awt.BorderLayout.LINE_END);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void ChotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChotActionPerformed
        if (new BookingBLL().payTotalBooking(booking.getId())) {
            JOptionPane.showMessageDialog(null, "Đã cập nhật thanh toán!");
            listener.onDataChanged(0);
        }
        dispose();
    }//GEN-LAST:event_ChotActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Chot;
    private javax.swing.JLabel dichvu;
    private javax.swing.JTable dsDichvu;
    private javax.swing.JTable dsthuephong;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTFTongcong;
    private javax.swing.JTextField jTFTongthanhtoan;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JLabel jlableTongcong;
    private javax.swing.JPanel jpanel123;
    private javax.swing.JTextField jtFTongtiendv;
    private javax.swing.JTextField jtFTongtienphong;
    private javax.swing.JPanel main;
    private javax.swing.JTextField mathuephong;
    private javax.swing.JTextField tenkh;
    private javax.swing.JTextField tiencoc;
    private javax.swing.JPanel tieude;
    // End of variables declaration//GEN-END:variables
}
