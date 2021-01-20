package Hotel.GUI.ReportGUI;

import Hotel.DAL.ReportDAL.ReportType;
import Hotel.BLL.ReportBLL;
import Hotel.BLL.ReportCondition;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JTable;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;

/**
 *
 * @author Yue
 */
public class ReportForm_1 extends JPanel {

    ReportBLL reportBLL = new ReportBLL();
    int currentType = 0;

    public ReportForm_1() {
        initComponents();
        for (ReportType i : ReportType.values()) {
            loaibaocao.addItem(i.toString());
        }
        Calendar calendar = Calendar.getInstance();
        String today = new SimpleDateFormat("dd/MM/yyyy").format(calendar.getTime());
        tungay.setText(today);
        denngay.setText(today);
        reportBLL.getServiceNameList(serviceName);
    }

    private void updateTimeline() {
        reportBLL.setTimeline(currentType, nam_thang.getSelectedIndex(), timeline);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        thoi_gian = new javax.swing.ButtonGroup();
        destination = new javax.swing.JFileChooser();
        timeCondition = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        thongkeBtn = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        loaibaocao = new javax.swing.JComboBox<>();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(50, 0), new java.awt.Dimension(50, 0), new java.awt.Dimension(50, 32767));
        serviceLbl = new javax.swing.JLabel();
        serviceName = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        moc = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        nam_thang = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        timeline = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        khoang = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        tungay = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        denngay = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        bangthongke = new javax.swing.JTable();
        sub = new javax.swing.JPanel();
        toExcel = new javax.swing.JButton();

        destination.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);
        destination.setApproveButtonToolTipText("");
        destination.setDialogTitle("Lưu vào...");

        setLayout(new java.awt.BorderLayout());

        timeCondition.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tiêu chí thống kê", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        timeCondition.setLayout(new java.awt.GridLayout(0, 1));

        jPanel1.setLayout(new java.awt.BorderLayout());

        thongkeBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        thongkeBtn.setText("Thống kê");
        thongkeBtn.setPreferredSize(new java.awt.Dimension(150, 30));
        thongkeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thongkeBtnActionPerformed(evt);
            }
        });
        jPanel1.add(thongkeBtn, java.awt.BorderLayout.LINE_END);

        java.awt.FlowLayout flowLayout4 = new java.awt.FlowLayout(java.awt.FlowLayout.LEADING);
        flowLayout4.setAlignOnBaseline(true);
        jPanel4.setLayout(flowLayout4);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Loại: ");
        jPanel4.add(jLabel5);

        loaibaocao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {  }));
        loaibaocao.setPreferredSize(new java.awt.Dimension(300, 30));
        loaibaocao.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                loaibaocaoItemStateChanged(evt);
            }
        });
        loaibaocao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loaibaocaoActionPerformed(evt);
            }
        });
        jPanel4.add(loaibaocao);
        jPanel4.add(filler1);

        serviceLbl.setLabelFor(serviceName);
        serviceLbl.setText("Dịch vụ:");
        jPanel4.add(serviceLbl);

        serviceName.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        serviceName.setPreferredSize(new java.awt.Dimension(200, 30));
        jPanel4.add(serviceName);

        jPanel1.add(jPanel4, java.awt.BorderLayout.CENTER);

        timeCondition.add(jPanel1);

        java.awt.FlowLayout flowLayout2 = new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 10, 5);
        flowLayout2.setAlignOnBaseline(true);
        jPanel2.setLayout(flowLayout2);

        thoi_gian.add(moc);
        moc.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        moc.setSelected(true);
        moc.setText("Theo mốc");
        moc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                mocItemStateChanged(evt);
            }
        });
        jPanel2.add(moc);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setLabelFor(nam_thang);
        jLabel1.setText("Theo:");
        jPanel2.add(jLabel1);

        nam_thang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tháng", "Năm" }));
        nam_thang.setPreferredSize(new java.awt.Dimension(90, 30));
        nam_thang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nam_thangActionPerformed(evt);
            }
        });
        jPanel2.add(nam_thang);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setLabelFor(timeline);
        jLabel4.setText("Chọn:");
        jPanel2.add(jLabel4);

        timeline.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        timeline.setPreferredSize(new java.awt.Dimension(120, 30));
        jPanel2.add(timeline);

        timeCondition.add(jPanel2);

        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 10, 5);
        flowLayout1.setAlignOnBaseline(true);
        jPanel3.setLayout(flowLayout1);

        thoi_gian.add(khoang);
        khoang.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        khoang.setText("Theo khoảng");
        khoang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                khoangItemStateChanged(evt);
            }
        });
        jPanel3.add(khoang);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setLabelFor(tungay);
        jLabel2.setText("Từ");
        jPanel3.add(jLabel2);

        tungay.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT))));
        tungay.setEnabled(false);
        tungay.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel3.add(tungay);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setLabelFor(denngay);
        jLabel3.setText("Đến");
        jPanel3.add(jLabel3);

        denngay.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));
        denngay.setEnabled(false);
        denngay.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel3.add(denngay);

        timeCondition.add(jPanel3);

        add(timeCondition, java.awt.BorderLayout.PAGE_START);

        bangthongke.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        bangthongke.setRowHeight(20);
        bangthongke.setRowMargin(2);
        jScrollPane1.setViewportView(bangthongke);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);

        sub.setPreferredSize(new java.awt.Dimension(100, 30));
        sub.setLayout(new java.awt.BorderLayout());

        toExcel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        toExcel.setText("Xuất file Excel");
        toExcel.setPreferredSize(new java.awt.Dimension(120, 30));
        toExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toExcelActionPerformed(evt);
            }
        });
        sub.add(toExcel, java.awt.BorderLayout.LINE_END);

        add(sub, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void nam_thangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nam_thangActionPerformed
        updateTimeline();
    }//GEN-LAST:event_nam_thangActionPerformed

    private void loaibaocaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loaibaocaoActionPerformed
        currentType = loaibaocao.getSelectedIndex();
        updateTimeline();
    }//GEN-LAST:event_loaibaocaoActionPerformed

    private void mocItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_mocItemStateChanged
        nam_thang.setEnabled(moc.isSelected());
        timeline.setEnabled(moc.isSelected());
    }//GEN-LAST:event_mocItemStateChanged

    private void khoangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_khoangItemStateChanged
        tungay.setEnabled(khoang.isSelected());
        denngay.setEnabled(khoang.isSelected());
    }//GEN-LAST:event_khoangItemStateChanged

    private void thongkeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thongkeBtnActionPerformed
        ReportType reportType = ReportType.SerialOf(loaibaocao.getSelectedIndex());
        ReportCondition reportCondition;
        if (thoi_gian.isSelected(moc.getModel())) {
            reportCondition = new ReportCondition(timeline.getSelectedItem().toString());
        } else /*if (thoi_gian.isSelected(khoang.getModel()))*/ {
            reportCondition = new ReportCondition(tungay.getText(), denngay.getText());
        }
        switch (reportType) {
            case DOANH_SO_DAT_PHONG:
                bangthongke.setModel(reportBLL.getReport_ORDER_STATISTIC(reportCondition));
                break;
            case DOANH_THU_PHONG:
                bangthongke.setModel(reportBLL.getReport_STAY_STATISTIC(reportCondition));
                break;
            case DOANH_THU_DICH_VU:
                if (serviceName.getSelectedIndex() != 0) reportCondition.setAddIn(serviceName.getSelectedItem().toString());
                bangthongke.setModel(reportBLL.getReport_SERVICE_STATISTIC(reportCondition));
                break;
        }
        CellRenderer cellRenderer = new CellRenderer();
        for (int i = 0; i < bangthongke.getColumnCount(); i++) {
            bangthongke.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }
    }//GEN-LAST:event_thongkeBtnActionPerformed

    private class CellRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row,
                int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (table.getValueAt(row, 0).equals("Tổng cộng")) {
                this.setValue(table.getValueAt(row, column));
                this.setFont(this.getFont().deriveFont(Font.BOLD));
                this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
            }
            return this;
        }
    }

    private void toExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toExcelActionPerformed
        java.util.Date date = new java.util.Date(System.currentTimeMillis());
        String fileName = "HotelReport-" + new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss").format(date);
        destination.setSelectedFile(new java.io.File(fileName));
        int result = destination.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            reportBLL.exportToExcel(bangthongke, destination.getSelectedFile());
        }
    }//GEN-LAST:event_toExcelActionPerformed

    private void loaibaocaoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_loaibaocaoItemStateChanged
        if (ReportType.SerialOf(loaibaocao.getSelectedIndex()) == ReportType.DOANH_THU_DICH_VU) {
            serviceName.setVisible(true);
            serviceLbl.setVisible(true);
        } else {
            serviceName.setVisible(false);
            serviceLbl.setVisible(false);
        }
    }//GEN-LAST:event_loaibaocaoItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable bangthongke;
    private javax.swing.JFormattedTextField denngay;
    private javax.swing.JFileChooser destination;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton khoang;
    private javax.swing.JComboBox<String> loaibaocao;
    private javax.swing.JRadioButton moc;
    private javax.swing.JComboBox<String> nam_thang;
    private javax.swing.JLabel serviceLbl;
    private javax.swing.JComboBox<String> serviceName;
    private javax.swing.JPanel sub;
    private javax.swing.ButtonGroup thoi_gian;
    private javax.swing.JButton thongkeBtn;
    private javax.swing.JPanel timeCondition;
    private javax.swing.JComboBox<String> timeline;
    private javax.swing.JButton toExcel;
    private javax.swing.JFormattedTextField tungay;
    // End of variables declaration//GEN-END:variables
}
