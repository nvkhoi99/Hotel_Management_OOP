package Hotel.GUI;

import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JLabel;
import Hotel.GUI.CheckInGUI.CheckInForm;
import Hotel.GUI.CheckOutGUI.CheckOutForm;
import Hotel.GUI.ManagementGUI.ManagementForm;
import Hotel.GUI.OrderGUI.OrderForm;
import Hotel.GUI.ServiceGUI.ServiceForm;
import Hotel.GUI.ReportGUI.ReportForm_1;

/**
 *
 * @author Yue
 */
public class MainForm extends javax.swing.JFrame {

    private final Color DEFAULT_COLOR;
    private final Color CHOOSEN_COLOR = new Color(153, 255, 255);
    private JLabel currentChoosen;

    private CardLayout card;
    private CheckOutForm checkOutForm = new CheckOutForm();

    public MainForm() {
        initComponents();
        card = (CardLayout) main.getLayout();
        initForms();

        DEFAULT_COLOR = nav.getBackground();
        setChoosenColor(quanlyphong);
    }

    private void initForms() {
        main.add(new ManagementForm(), quanlyphong.getText());

        main.add(new OrderForm(), datphong.getText());

        main.add(new CheckInForm(), nhanphong.getText());

        main.add(checkOutForm, traphong.getText());

        main.add(new ServiceForm(), quanlydichvu.getText());

        main.add(new ReportForm_1(), lapbaocao.getText());
    }

    public void goToProfile(int stayId) {
        setChoosenColor(traphong);
        card.show(main, traphong.getText());
        checkOutForm.goToProfile(stayId);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nav = new javax.swing.JPanel();
        quanlyphong = new javax.swing.JLabel();
        datphong = new javax.swing.JLabel();
        nhanphong = new javax.swing.JLabel();
        traphong = new javax.swing.JLabel();
        quanlydichvu = new javax.swing.JLabel();
        lapbaocao = new javax.swing.JLabel();
        content = new javax.swing.JPanel();
        main = new javax.swing.JPanel();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 25), new java.awt.Dimension(0, 25), new java.awt.Dimension(32767, 25));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quản lý khách sạn");
        setPreferredSize(new java.awt.Dimension(1250, 700));

        nav.setBackground(new java.awt.Color(153, 204, 255));
        nav.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 255, 255)));
        nav.setPreferredSize(new java.awt.Dimension(1250, 40));
        nav.setLayout(new java.awt.GridLayout(1, 0));

        quanlyphong.setBackground(nav.getBackground());
        quanlyphong.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        quanlyphong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        quanlyphong.setText("Quản lý phòng");
        quanlyphong.setOpaque(true);
        quanlyphong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                setActive(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                HoverButton(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                OutHoverButton(evt);
            }
        });
        nav.add(quanlyphong);

        datphong.setBackground(nav.getBackground());
        datphong.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        datphong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        datphong.setText("Đặt phòng");
        datphong.setOpaque(true);
        datphong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                setActive(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                HoverButton(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                OutHoverButton(evt);
            }
        });
        nav.add(datphong);

        nhanphong.setBackground(nav.getBackground());
        nhanphong.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        nhanphong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nhanphong.setText("Nhận phòng");
        nhanphong.setOpaque(true);
        nhanphong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                setActive(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                HoverButton(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                OutHoverButton(evt);
            }
        });
        nav.add(nhanphong);

        traphong.setBackground(nav.getBackground());
        traphong.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        traphong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        traphong.setText("Trả phòng");
        traphong.setOpaque(true);
        traphong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                setActive(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                HoverButton(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                OutHoverButton(evt);
            }
        });
        nav.add(traphong);

        quanlydichvu.setBackground(nav.getBackground());
        quanlydichvu.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        quanlydichvu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        quanlydichvu.setText("Quản lý dịch vụ");
        quanlydichvu.setOpaque(true);
        quanlydichvu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                setActive(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                HoverButton(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                OutHoverButton(evt);
            }
        });
        nav.add(quanlydichvu);

        lapbaocao.setBackground(nav.getBackground());
        lapbaocao.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lapbaocao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lapbaocao.setText("Thống kê báo cáo");
        lapbaocao.setOpaque(true);
        lapbaocao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                setActive(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                HoverButton(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                OutHoverButton(evt);
            }
        });
        nav.add(lapbaocao);

        getContentPane().add(nav, java.awt.BorderLayout.PAGE_START);

        content.setLayout(new java.awt.BorderLayout());

        main.setLayout(new java.awt.CardLayout());
        content.add(main, java.awt.BorderLayout.CENTER);
        content.add(filler3, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(content, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void setChoosenColor(JLabel label) {
        if (currentChoosen != null) {
            currentChoosen.setBackground(DEFAULT_COLOR);
        }
        label.setBackground(CHOOSEN_COLOR);
        currentChoosen = label;
    }

    private void HoverButton(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HoverButton
        if (evt.getComponent().getBackground() != CHOOSEN_COLOR) {
            evt.getComponent().setBackground(new Color(185, 233, 255));
        }
    }//GEN-LAST:event_HoverButton

    private void OutHoverButton(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OutHoverButton
        if (evt.getComponent().getBackground() != CHOOSEN_COLOR) {
            evt.getComponent().setBackground(DEFAULT_COLOR);
        }
    }//GEN-LAST:event_OutHoverButton

    private void setActive(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_setActive
        JLabel activeLabel = (JLabel) evt.getComponent();
        setChoosenColor(activeLabel);
        card.show(main, activeLabel.getText());
    }//GEN-LAST:event_setActive

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel content;
    private javax.swing.JLabel datphong;
    private javax.swing.Box.Filler filler3;
    private javax.swing.JLabel lapbaocao;
    private javax.swing.JPanel main;
    private javax.swing.JPanel nav;
    private javax.swing.JLabel nhanphong;
    private javax.swing.JLabel quanlydichvu;
    private javax.swing.JLabel quanlyphong;
    private javax.swing.JLabel traphong;
    // End of variables declaration//GEN-END:variables
}
