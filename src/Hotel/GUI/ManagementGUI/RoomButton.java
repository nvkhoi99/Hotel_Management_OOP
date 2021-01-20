package Hotel.GUI.ManagementGUI;

import Hotel.DTO.rooms.RoomForManaging;
import Hotel.BLL.ManagementBLL;
import Hotel.BLL.Rules;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

public class RoomButton extends JButton {

    private static final Color DEFAULT_COLOR = new Color(240, 240, 240);
    private static final Color STAYED_COLOR = new Color(102, 204, 255);
    private static final Color UNAVALABLE_COLOR = new Color(255, 204, 0);

    private RoomForManaging room;
    private RoomTypePanel typePanel;
    private static final JPopupMenu popupMenu = new JPopupMenu();
    private static final JMenuItem updateRoom = new JMenuItem("Sửa phòng");
    private static final JMenuItem deleteRoom = new JMenuItem("Xóa phòng");
    private static final JMenuItem goToProfile = new JMenuItem("Tới hồ sơ");
    private static final JMenuItem goToService = new JMenuItem("Dịch vụ phòng");
    private static final JMenuItem openCloseOrder = new JMenuItem("Mở đặt phòng");
    private static RoomButton currentRoom;
    private static ManagementBLL managementBLL;

    private static RoomClickListener listener;

    public static void setListener(RoomClickListener listener) {
        RoomButton.listener = listener;
    }

    public void setTypePanel(RoomTypePanel typePanel) {
        this.typePanel = typePanel;
    }

    public RoomTypePanel getTypePanel() {
        return typePanel;
    }

    static {
        popupMenu.setPopupSize(120, 120);

        popupMenu.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                if (currentRoom.getRoom().getCurrentBooking() == 0) {
                    popupMenu.add(updateRoom);
                    popupMenu.add(deleteRoom);
                } else {
                    popupMenu.add(goToService);
                    popupMenu.add(goToProfile);
                    popupMenu.add(updateRoom);
                }
                popupMenu.add(openCloseOrder);
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                popupMenu.removeAll();
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
                popupMenu.removeAll();
            }

        });

        updateRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateRoomDialog(null, true, currentRoom.getRoom(), managementBLL).setVisible(true);
                Rules.StateIsChanged = true;
            }
        });

        deleteRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (managementBLL.deleteRoom(currentRoom)) {
                    JOptionPane.showMessageDialog(null, "Xóa phòng thành công");
                    Rules.StateIsChanged = true;
                }
            }
        });

        goToProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                managementBLL.goToProfile(currentRoom.getRoom().getCurrentBooking());
            }
        });

        openCloseOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentRoom.getRoom().setAvailable(!currentRoom.getRoom().isAvailable());
                managementBLL.changeStateRoom(currentRoom.getRoom());
                currentRoom.setAvailable();
                openCloseOrder.setText(currentRoom.getRoom().isAvailable()
                        ? "Đóng đặt phòng" : "Mở đặt phòng");
                Rules.StateIsChanged = true;
            }
        });

        goToService.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Hotel.GUI.ServiceGUI.RoomServiceForm(currentRoom.getRoom().getCurrentBooking(),
                        currentRoom.getRoom().getId()).setVisible(true);
            }
        });
    }

    public static void setManagementBLL(ManagementBLL management) {
        managementBLL = management;
    }

    public RoomForManaging getRoom() {
        return room;
    }

    public static RoomForManaging getCurrentRoom() {
        return currentRoom.getRoom();
    }

    public RoomButton(RoomForManaging room) {
        super();
        this.room = room;
        this.setPreferredSize(new Dimension(100, 100));
        setAvailable();
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                currentRoom = RoomButton.this;
                if (e.getButton() == MouseEvent.BUTTON1) {
                    RoomButton.listener.onRoomClick(currentRoom);
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    Point p = RoomButton.this.getMousePosition();
                    popupMenu.show(RoomButton.this, p.x, p.y);
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

    public void setAvailable() {
        if (room.getCurrentBooking() == 0) {
            this.setText(room.getName());
            this.setBackground(DEFAULT_COLOR);
        } else {
            this.setText("<html><p style='text-align:center'>"
                    + room.getName() + "<br/>#"
                    + String.format("%07d", room.getCurrentBooking()));
            this.setBackground(STAYED_COLOR);
        }
        if (!room.isAvailable()) {
            this.setBackground(UNAVALABLE_COLOR);
        }
    }

    public interface RoomClickListener {

        void onRoomClick(RoomButton room);
    }

    public static void goToBooking() {
        goToProfile.doClick();
    }
}
