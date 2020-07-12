package Hotel.GUI.ManagementGUI;

import Hotel.DTO.Room.RoomForManaging;
import Hotel.DTO.RoomType;
import Hotel.BLL.ManagementBLL;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import Hotel.BLL.Rules;

public class ManagementForm extends JPanel {

    private ArrayList<RoomType> roomTypes;
    private ArrayList<RoomForManaging> roomForManagings;
    private final ManagementBLL managementBLL = new ManagementBLL(this);
    private RoomTypePanel[] roomTypePanels;
    private RoomButton[] roomButtons;

    public ArrayList<RoomForManaging> getRoomForManagings() {
        return roomForManagings;
    }

    public ManagementForm() {
        initComponents();
        RoomTypePanel.setManagementBLL(managementBLL);
        RoomButton.setManagementBLL(managementBLL);
        initRoomTypeList();
        initRoomList();
        sortRoom();
    }

    public void initRoomTypeList() {
        roomList.removeAll();
        roomTypes = managementBLL.getRoomTypeList();
        roomTypePanels = new RoomTypePanel[roomTypes.size()];
        RoomTypePanel.setTypeNameList(new ArrayList<>());
        for (int i = 0; i < roomTypes.size(); i++) {
            roomTypePanels[i] = new RoomTypePanel(roomTypes.get(i), RoomTypePanel.MANAGE);
            roomList.add(roomTypePanels[i]);
            RoomTypePanel.getTypeNameList().add(roomTypePanels[i].getRoomType().getTenloaiphong());
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
            int maloaiphong = typePanel.getRoomType().getMaloaiphong();
            ArrayList<RoomButton> roomByType = managementBLL.getRoomByRoomType(roomButtons, maloaiphong);
            for (RoomButton temp : roomByType) {
                typePanel.addRoom(temp);
                temp.setTypePanel(typePanel);
            }
        }
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
        list = new javax.swing.JScrollPane();
        roomList = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        addNewRoomType = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));

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

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Quản lý phòng", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 2, 24))); // NOI18N
        addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                formHierarchyChanged(evt);
            }
        });
        setLayout(new java.awt.BorderLayout(10, 10));

        list.setBorder(null);

        roomList.setLayout(new javax.swing.BoxLayout(roomList, javax.swing.BoxLayout.PAGE_AXIS));
        list.setViewportView(roomList);

        add(list, java.awt.BorderLayout.CENTER);

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

        add(jPanel1, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents

    private void addnewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addnewActionPerformed
        RoomType roomType = new RoomType();
        roomType.setTenloaiphong(newTypeName.getText());
        try {
            roomType.setDongia(Integer.valueOf(newTypePrice.getText()));
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addNewRoomType;
    private javax.swing.JDialog addNewRoomTypeDialog;
    private javax.swing.JButton addnew;
    private javax.swing.JButton cancel_addnew;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane list;
    private javax.swing.JTextField newTypeName;
    private javax.swing.JTextField newTypePrice;
    private javax.swing.JPanel roomList;
    // End of variables declaration//GEN-END:variables
}
