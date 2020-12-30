package Hotel.GUI.ManagementGUI;

import Hotel.DTO.rooms.Room;
import Hotel.BLL.ManagementBLL;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Yue
 */
public class UpdateRoomDialog extends javax.swing.JDialog {

    private final ManagementBLL managementBLL;
    private final Room currentRoom;

    public UpdateRoomDialog(java.awt.Frame parent, boolean modal, Room room, ManagementBLL managementBLL) {
        super(parent, modal);
        this.managementBLL = managementBLL;
        initComponents();
        this.currentRoom = room;
        newName.setText(room.getName());
        ArrayList<String> nameList = managementBLL.getNameTypeList();
        nameList.forEach((i) -> {
            newType.addItem(i);
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        newName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        newType = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sửa thông tin phòng");
        setIconImage(null);
        setIconImages(null);
        setMinimumSize(new java.awt.Dimension(200, 200));

        jButton1.setBackground(new java.awt.Color(51, 102, 255));
        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Cập nhật");
        jButton1.setPreferredSize(new java.awt.Dimension(73, 50));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, java.awt.BorderLayout.PAGE_END);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Tên phòng:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.4;
        gridBagConstraints.weighty = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel1.add(jLabel1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.weightx = 6.0;
        gridBagConstraints.weighty = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(newName, gridBagConstraints);

        jLabel2.setText("Loại phòng:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.4;
        gridBagConstraints.weighty = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel1.add(jLabel2, gridBagConstraints);

        newType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.weightx = 6.0;
        gridBagConstraints.weighty = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(newType, gridBagConstraints);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (managementBLL.updateRoom(currentRoom, newName.getText(),
                (String) newType.getSelectedItem())) {
            JOptionPane.showMessageDialog(null, "Cập nhật thành công");
            dispose();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField newName;
    private javax.swing.JComboBox<String> newType;
    // End of variables declaration//GEN-END:variables
}
