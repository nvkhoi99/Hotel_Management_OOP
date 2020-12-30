package Hotel.GUI.CheckOutGUI;

import Hotel.DTO.Customer;
import Hotel.DTO.StayProfile;
import Hotel.BLL.CheckOutBLL;
import Hotel.BLL.Rules;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Yue
 */
public class PayDialog extends javax.swing.JDialog {

    CheckOutBLL checkOutBLL;
    Customer customer;
    StayProfile profile;
    int sum_price = 0;
    long sum_room = 0;
    long sum_service = 0;
    int early_checkIn;
    int lately_checkOut;
    long sumBill;
    DecimalFormat formatter = new DecimalFormat("#,##0");
    DefaultTableModel model;

    public PayDialog(java.awt.Frame parent, boolean modal, Customer customer,
            StayProfile profile, CheckOutBLL checkOutBLL, DefaultTableModel model) {
        super(parent, modal);
        this.checkOutBLL = checkOutBLL;
        this.model = model;
        this.customer = customer;
        this.profile = profile;
        initComponents();
        initBill();
        showPayServiceList();
        calculateSum();
        calculateSurcharge();
        calculateBill();
    }

    private void initBill() {
        mathuephong.setText(String.valueOf(profile.getMathuephong()));
        makh.setText(String.valueOf(profile.getDondatphong().getId()));
        hotenkh.setText(customer.getFullname());
        ngaynhan.setText(profile.getDondatphong().getCheckInTime().toString());
        ngaytra.setText(profile.getDondatphong().getCheckOutTime().toString());
        thucnhan.setText(profile.getThucnhan().toString());
        Timestamp checkOutTime = new Timestamp(System.currentTimeMillis());
        checkOutTime.setNanos(0);
        profile.setThuctra(checkOutTime);
        thuctra.setText(checkOutTime.toString());
    }

    private void calculateSum() {
        checkOutBLL.initStayTable(profile, (DefaultTableModel) dsphong.getModel());
        for (int i = 0; i < dsphong.getRowCount(); i++) {
            sum_price += (int) dsphong.getValueAt(i, 2);
            sum_room += (long) dsphong.getValueAt(i, 4);
        }
        for (int i = 0; i < payService.getRowCount(); i++) {
            sum_service += (long) payService.getValueAt(i, 5);
        }
    }

    private void calculateSurcharge() {
        int[] surchage = checkOutBLL.calculateSurcharge(profile.getThucnhan(), profile.getThuctra(), sum_price);
        early_checkIn = surchage[0];
        nhan_som.setSelected(early_checkIn != 0);

        lately_checkOut = surchage[1];
        tra_muon.setSelected(lately_checkOut != 0);
    }

    private void calculateBill() {
        tongcong.setText(formatter.format(sum_room + sum_service));

        int surchage = (nhan_som.isSelected() ? early_checkIn : 0)
                + (tra_muon.isSelected() ? lately_checkOut : 0);
        phuthu.setText(formatter.format(surchage));

        tongcoc.setText(formatter.format(profile.getDondatphong().getDeposit()));

        sumBill = sum_room + sum_service - profile.getDondatphong().getDeposit() + surchage;
        tongthanhtoan.setText(formatter.format(sumBill));

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        hoadon = new javax.swing.JPanel();
        billInfo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        hotenkh = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        thuctra = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        thucnhan = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        makh = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        ngaytra = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        ngaynhan = new javax.swing.JTextField();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(50, 0), new java.awt.Dimension(50, 0), new java.awt.Dimension(32767, 0));
        jLabel8 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        mathuephong = new javax.swing.JTextField();
        tinhtien = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        tongcong = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        nhan_som = new javax.swing.JCheckBox();
        tra_muon = new javax.swing.JCheckBox();
        jLabel11 = new javax.swing.JLabel();
        tongthanhtoan = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        tongcoc = new javax.swing.JTextField();
        phuthu = new javax.swing.JTextField();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(50, 0), new java.awt.Dimension(50, 0), new java.awt.Dimension(50, 32767));
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        dsphong = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        payService = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(607, 550));
        setModal(true);
        setPreferredSize(new java.awt.Dimension(900, 700));

        hoadon.setBackground(new java.awt.Color(239, 239, 239));
        hoadon.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5), javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED)));
        hoadon.setLayout(new java.awt.BorderLayout());

        billInfo.setBackground(hoadon.getBackground());
        billInfo.setPreferredSize(new java.awt.Dimension(837, 250));
        billInfo.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jLabel1.setText("HÓA ĐƠN THANH TOÁN");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.5;
        billInfo.add(jLabel1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("<html><p style='text-align:center'>Khách hàng/<br/>Đại diện:</p></html>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        billInfo.add(jLabel2, gridBagConstraints);

        hotenkh.setEditable(false);
        hotenkh.setBackground(hoadon.getBackground());
        hotenkh.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        hotenkh.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        hotenkh.setPreferredSize(new java.awt.Dimension(1, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.1;
        billInfo.add(hotenkh, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Thực trả:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        billInfo.add(jLabel3, gridBagConstraints);

        thuctra.setEditable(false);
        thuctra.setBackground(hoadon.getBackground());
        thuctra.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        thuctra.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        thuctra.setPreferredSize(new java.awt.Dimension(1, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.1;
        billInfo.add(thuctra, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Thực nhận:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        billInfo.add(jLabel4, gridBagConstraints);

        thucnhan.setEditable(false);
        thucnhan.setBackground(hoadon.getBackground());
        thucnhan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        thucnhan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        thucnhan.setPreferredSize(new java.awt.Dimension(1, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.1;
        billInfo.add(thucnhan, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Mã khách hàng:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        billInfo.add(jLabel5, gridBagConstraints);

        makh.setEditable(false);
        makh.setBackground(hoadon.getBackground());
        makh.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        makh.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        makh.setOpaque(false);
        makh.setPreferredSize(new java.awt.Dimension(1, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.1;
        billInfo.add(makh, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Ngày trả:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        billInfo.add(jLabel6, gridBagConstraints);

        ngaytra.setEditable(false);
        ngaytra.setBackground(hoadon.getBackground());
        ngaytra.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ngaytra.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        ngaytra.setPreferredSize(new java.awt.Dimension(1, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.1;
        billInfo.add(ngaytra, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Ngày nhận:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        billInfo.add(jLabel7, gridBagConstraints);

        ngaynhan.setEditable(false);
        ngaynhan.setBackground(hoadon.getBackground());
        ngaynhan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ngaynhan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        ngaynhan.setPreferredSize(new java.awt.Dimension(1, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.1;
        billInfo.add(ngaynhan, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 4;
        billInfo.add(filler1, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Các phòng đã ở:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.weighty = 0.3;
        billInfo.add(jLabel8, gridBagConstraints);

        jLabel13.setText("<html><p style='text-align:center'>Hóa đơn số:<br/>(Mã thuê phòng)</p></html>\n");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weighty = 0.1;
        billInfo.add(jLabel13, gridBagConstraints);

        mathuephong.setEditable(false);
        mathuephong.setBackground(hoadon.getBackground());
        mathuephong.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        mathuephong.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        mathuephong.setPreferredSize(new java.awt.Dimension(100, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weighty = 0.1;
        billInfo.add(mathuephong, gridBagConstraints);

        hoadon.add(billInfo, java.awt.BorderLayout.PAGE_START);

        tinhtien.setBackground(hoadon.getBackground());
        tinhtien.setPreferredSize(new java.awt.Dimension(837, 100));
        tinhtien.setLayout(new java.awt.GridBagLayout());

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 0, 51));
        jLabel9.setText("Tổng cộng:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        tinhtien.add(jLabel9, gridBagConstraints);

        tongcong.setBackground(hoadon.getBackground());
        tongcong.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tongcong.setForeground(new java.awt.Color(255, 0, 51));
        tongcong.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        tongcong.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 0, 51)));
        tongcong.setPreferredSize(new java.awt.Dimension(1, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 100, 0, 0);
        tinhtien.add(tongcong, gridBagConstraints);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Phụ thu:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        tinhtien.add(jLabel10, gridBagConstraints);

        nhan_som.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        nhan_som.setText("Nhận phòng sớm");
        nhan_som.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nhan_somActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        tinhtien.add(nhan_som, gridBagConstraints);

        tra_muon.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tra_muon.setText("Trả phòng muộn");
        tra_muon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tra_muonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        tinhtien.add(tra_muon, gridBagConstraints);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 0, 51));
        jLabel11.setText("Tổng thanh toán:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        tinhtien.add(jLabel11, gridBagConstraints);

        tongthanhtoan.setBackground(hoadon.getBackground());
        tongthanhtoan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tongthanhtoan.setForeground(new java.awt.Color(255, 0, 51));
        tongthanhtoan.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        tongthanhtoan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 0, 51)));
        tongthanhtoan.setPreferredSize(new java.awt.Dimension(1, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 100, 0, 0);
        tinhtien.add(tongthanhtoan, gridBagConstraints);

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("Đã cọc:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        tinhtien.add(jLabel12, gridBagConstraints);

        tongcoc.setBackground(hoadon.getBackground());
        tongcoc.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tongcoc.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        tongcoc.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        tongcoc.setPreferredSize(new java.awt.Dimension(1, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 100, 0, 0);
        tinhtien.add(tongcoc, gridBagConstraints);

        phuthu.setBackground(hoadon.getBackground());
        phuthu.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        phuthu.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        phuthu.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        phuthu.setPreferredSize(new java.awt.Dimension(1, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 100, 0, 0);
        tinhtien.add(phuthu, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 4;
        tinhtien.add(filler2, gridBagConstraints);

        hoadon.add(tinhtien, java.awt.BorderLayout.PAGE_END);

        jPanel1.setPreferredSize(new java.awt.Dimension(472, 400));
        jPanel1.setLayout(new java.awt.GridLayout(0, 1));

        jPanel3.setPreferredSize(new java.awt.Dimension(472, 200));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setBackground(hoadon.getBackground());
        jScrollPane1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 10), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102))));
        jScrollPane1.setRowHeaderView(null);

        dsphong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Phòng", "Loại phòng", "Đơn giá", "Số đêm ở", "Thành tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Long.class, java.lang.Long.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        dsphong.setGridColor(new java.awt.Color(153, 153, 153));
        dsphong.setName(""); // NOI18N
        dsphong.setRowHeight(20);
        dsphong.setRowMargin(2);
        dsphong.setShowGrid(true);
        jScrollPane1.setViewportView(dsphong);

        jPanel3.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel3);

        jPanel2.setPreferredSize(new java.awt.Dimension(10, 100));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jScrollPane2.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 10), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))));

        payService.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Dịch vụ ", "Ngày dùng", "Phòng ", "Đơn giá ", "Số lượng", "Thành tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Long.class
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
        payService.setPreferredSize(new java.awt.Dimension(375, 64));
        jScrollPane2.setViewportView(payService);

        jPanel2.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel2);

        hoadon.add(jPanel1, java.awt.BorderLayout.CENTER);

        getContentPane().add(hoadon, java.awt.BorderLayout.CENTER);

        jButton1.setBackground(new java.awt.Color(0, 204, 0));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("THANH TOÁN");
        jButton1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 5));
        jButton1.setPreferredSize(new java.awt.Dimension(97, 50));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, java.awt.BorderLayout.PAGE_END);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void nhan_somActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nhan_somActionPerformed
        calculateBill();
    }//GEN-LAST:event_nhan_somActionPerformed

    private void tra_muonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tra_muonActionPerformed
        calculateBill();
    }//GEN-LAST:event_tra_muonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        profile.setTongthanhtoan(sumBill);
        if (checkOutBLL.completeProfile(profile)) {
            JOptionPane.showMessageDialog(null, "Thanh toán thành công");
            Rules.StateIsChanged = true;
            checkOutBLL.updateProflieList(model);
            dispose();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    public void showPayServiceList() {
        checkOutBLL.showPayService((DefaultTableModel) payService.getModel(), Integer.parseInt(mathuephong.getText()));

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel billInfo;
    private javax.swing.JTable dsphong;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.JPanel hoadon;
    private javax.swing.JTextField hotenkh;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField makh;
    private javax.swing.JTextField mathuephong;
    private javax.swing.JTextField ngaynhan;
    private javax.swing.JTextField ngaytra;
    private javax.swing.JCheckBox nhan_som;
    private javax.swing.JTable payService;
    private javax.swing.JTextField phuthu;
    private javax.swing.JTextField thucnhan;
    private javax.swing.JTextField thuctra;
    private javax.swing.JPanel tinhtien;
    private javax.swing.JTextField tongcoc;
    private javax.swing.JTextField tongcong;
    private javax.swing.JTextField tongthanhtoan;
    private javax.swing.JCheckBox tra_muon;
    // End of variables declaration//GEN-END:variables

}
