package Hotel.GUI.ManagementGUI;

import Hotel.DTO.RoomType;
import Hotel.BLL.ManagementBLL;
import Hotel.BLL.BookingBLL;
import Hotel.BLL.Rules;
import Hotel.GUI.BookingGUI.RoomToggle;
import java.awt.CardLayout;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Yue
 */
public class RoomTypePanel extends javax.swing.JPanel {

    public static final int MANAGE = 1;
    public static final int ORDER = 2;

    private final RoomType roomType;
    private static ManagementBLL managementBLL;
    private static BookingBLL bookingBLL;
    private static final DecimalFormat df = new DecimalFormat("#,##0 VNĐ");
    private static ArrayList<String> typeNameList;
    private static RoomTypePanel currentType;
    private int choose = 0;

    public static void setManagementBLL(ManagementBLL managementBLL) {
        RoomTypePanel.managementBLL = managementBLL;
    }

    public static void setBookingBLL(BookingBLL bookingBLL) {
        RoomTypePanel.bookingBLL = bookingBLL;
    }

    public int getChoose() {
        return choose;
    }

    public int getPrice() {
        return this.roomType.getPrice();
    }

    public static void setTypeNameList(ArrayList<String> nameList) {
        typeNameList = nameList;
    }

    public static ArrayList<String> getTypeNameList() {
        return typeNameList;
    }

    public RoomTypePanel(RoomType roomType, int type) {
        this.roomType = roomType;
        initComponents();
        initRoomType();
        CardLayout card = (CardLayout) panels.getLayout();
        card.show(panels, type == 1 ? "editPanel" : "choosePanel");
    }

    private void initRoomType() {
        roomTypeName.setText(roomType.getName());
        price.setText("Đơn giá: " + df.format(roomType.getPrice()) + " ");
    }

    public void addRoom(RoomButton room) {
        roomList.add(room);
    }

    public void addRoomToChoose(RoomToggle room) {
        roomList.add(room);
    }

    public void removeRoom(RoomButton room) {
        roomList.remove(room);
    }

    public void removeAllRoom() {
        roomList.removeAll();
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void increase() {
        amount.setText(String.valueOf(++choose));
        bookingBLL.updateDeposit();
    }

    public void decrease() {
        amount.setText(String.valueOf(--choose));
        bookingBLL.updateDeposit();
    }

    public void setUpdate(String newName, String newPrice) {
        this.roomTypeName.setText(newName);
        this.price.setText("Đơn giá: " + df.format(Integer.valueOf(newPrice)) + " ");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        updateTypeDialog = new javax.swing.JDialog();
        updateType = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        newTypeName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        newTypePrice = new javax.swing.JTextField();
        close = new javax.swing.JLabel();
        panels = new javax.swing.JPanel();
        editPanel = new javax.swing.JPanel();
        addnew = new javax.swing.JButton();
        update = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        choosePanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        amount = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        roomList = new javax.swing.JPanel();
        info = new javax.swing.JPanel();
        roomTypeName = new javax.swing.JLabel();
        price = new javax.swing.JLabel();

        updateTypeDialog.setLocation(getLocation());
        updateTypeDialog.setMinimumSize(new java.awt.Dimension(200, 200));
        updateTypeDialog.setModal(true);
        updateTypeDialog.setUndecorated(true);

        updateType.setBackground(new java.awt.Color(51, 102, 255));
        updateType.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        updateType.setForeground(new java.awt.Color(255, 255, 255));
        updateType.setText("Cập nhật");
        updateType.setBorder(null);
        updateType.setPreferredSize(new java.awt.Dimension(73, 50));
        updateType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateTypeActionPerformed(evt);
            }
        });
        updateTypeDialog.getContentPane().add(updateType, java.awt.BorderLayout.PAGE_END);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel2.setText("Đơn giá:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.4;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel3.add(jLabel2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.weightx = 6.0;
        gridBagConstraints.weighty = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 30);
        jPanel3.add(newTypeName, gridBagConstraints);

        jLabel3.setText("Tên loại:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.4;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel3.add(jLabel3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.weightx = 6.0;
        gridBagConstraints.weighty = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 30);
        jPanel3.add(newTypePrice, gridBagConstraints);

        close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hotel/GUI/Z_Icons/icons8_delete_24px.png"))); // NOI18N
        close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel3.add(close, gridBagConstraints);

        jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

        updateTypeDialog.getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        setMinimumSize(new java.awt.Dimension(138, 125));
        setPreferredSize(new java.awt.Dimension(0, 145));
        setLayout(new java.awt.BorderLayout(5, 0));

        panels.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 5));
        panels.setPreferredSize(new java.awt.Dimension(50, 167));
        panels.setLayout(new java.awt.CardLayout());

        editPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 1));

        addnew.setBackground(new java.awt.Color(0, 204, 0));
        addnew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hotel/GUI/Z_Icons/plus_math_30px.png"))); // NOI18N
        addnew.setToolTipText("Thêm phòng mới");
        addnew.setMaximumSize(new java.awt.Dimension(50, 50));
        addnew.setMinimumSize(new java.awt.Dimension(50, 50));
        addnew.setPreferredSize(new java.awt.Dimension(40, 40));
        addnew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addnewActionPerformed(evt);
            }
        });
        editPanel.add(addnew);

        update.setBackground(new java.awt.Color(51, 102, 255));
        update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hotel/GUI/Z_Icons/wrench_30px.png"))); // NOI18N
        update.setToolTipText("Sửa loại phòng");
        update.setMaximumSize(new java.awt.Dimension(50, 50));
        update.setMinimumSize(new java.awt.Dimension(50, 50));
        update.setPreferredSize(new java.awt.Dimension(40, 40));
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });
        editPanel.add(update);

        delete.setBackground(new java.awt.Color(218, 0, 0));
        delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hotel/GUI/Z_Icons/delete_30px.png"))); // NOI18N
        delete.setToolTipText("Xóa loại phòng");
        delete.setMaximumSize(new java.awt.Dimension(50, 50));
        delete.setMinimumSize(new java.awt.Dimension(50, 50));
        delete.setPreferredSize(new java.awt.Dimension(40, 40));
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });
        editPanel.add(delete);

        panels.add(editPanel, "editPanel");

        choosePanel.setLayout(new java.awt.BorderLayout());

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("<html><p style = \"text-align: center\">Đã<br>chọn:</html>");
        jLabel4.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel4.setPreferredSize(new java.awt.Dimension(27, 50));
        choosePanel.add(jLabel4, java.awt.BorderLayout.PAGE_START);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Phòng");
        jLabel5.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel5.setPreferredSize(new java.awt.Dimension(30, 50));
        choosePanel.add(jLabel5, java.awt.BorderLayout.PAGE_END);

        amount.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        amount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        amount.setText("0");
        choosePanel.add(amount, java.awt.BorderLayout.CENTER);

        panels.add(choosePanel, "choosePanel");

        add(panels, java.awt.BorderLayout.LINE_END);

        roomList.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jScrollPane1.setViewportView(roomList);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);

        info.setLayout(new java.awt.GridLayout(1, 0));

        roomTypeName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        roomTypeName.setText("jLabel1");
        info.add(roomTypeName);

        price.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        price.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        price.setText("jLabel1");
        info.add(price);

        add(info, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        updateTypeDialog.setLocationRelativeTo(this.update);
        currentType = this;
        newTypeName.setText(this.roomType.getName());
        newTypePrice.setText(String.valueOf(this.roomType.getPrice()));
        updateTypeDialog.setVisible(true);
    }//GEN-LAST:event_updateActionPerformed

    private void addnewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addnewActionPerformed
        String newName = JOptionPane.showInputDialog(this, "Nhập tên phòng mới: ",
                this.roomTypeName.getText(), JOptionPane.PLAIN_MESSAGE);
        if (newName != null) {
            if (managementBLL.addNewRoom(roomType.getId(), newName)) {
                JOptionPane.showMessageDialog(null, "Thêm phòng " + newName + " thành công");
                Rules.StateIsChanged = true;
            }
        }
    }//GEN-LAST:event_addnewActionPerformed

    private void updateTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateTypeActionPerformed
        RoomType rt = new RoomType();
        rt.setId(currentType.getRoomType().getId());
        rt.setName(newTypeName.getText());
        try {
            rt.setPrice(Integer.valueOf(newTypePrice.getText()));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Đơn giá không hợp lệ");
            return;
        }
        if (managementBLL.updateRoomType(rt)) {
            JOptionPane.showMessageDialog(null, "Cập nhật thành công");
            currentType.getRoomType().setName(roomTypeName.getText());
            currentType.getRoomType().setPrice(Integer.valueOf(newTypePrice.getText()));
            currentType.setUpdate(newTypeName.getText(), newTypePrice.getText());
            Rules.StateIsChanged = true;
            updateTypeDialog.setVisible(false);
        }
    }//GEN-LAST:event_updateTypeActionPerformed

    private void closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseClicked
        updateTypeDialog.setVisible(false);
    }//GEN-LAST:event_closeMouseClicked

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        if (managementBLL.deleteRoomType(this, roomType)) {
            JOptionPane.showMessageDialog(null, "Xóa loại phòng thành công");
            Rules.StateIsChanged = true;
        }
    }//GEN-LAST:event_deleteActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addnew;
    private javax.swing.JLabel amount;
    private javax.swing.JPanel choosePanel;
    private javax.swing.JLabel close;
    private javax.swing.JButton delete;
    private javax.swing.JPanel editPanel;
    private javax.swing.JPanel info;
    private static javax.swing.JLabel jLabel2;
    private static javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private static javax.swing.JPanel jPanel1;
    private static javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTextField newTypeName;
    private static javax.swing.JTextField newTypePrice;
    private javax.swing.JPanel panels;
    private javax.swing.JLabel price;
    private javax.swing.JPanel roomList;
    private javax.swing.JLabel roomTypeName;
    private javax.swing.JButton update;
    private javax.swing.JButton updateType;
    private static javax.swing.JDialog updateTypeDialog;
    // End of variables declaration//GEN-END:variables
}
