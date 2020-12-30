/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hotel.GUI.BookingGUI;

import Hotel.DTO.rooms.RoomForOrdering;
import Hotel.GUI.ManagementGUI.RoomTypePanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JToggleButton;

/**
 *
 * @author Yue
 */
public class RoomToggle extends JToggleButton {

    private RoomForOrdering orderRoom;
    private RoomTypePanel typePanel;
    private static final Color DEFAULT_COLOR = new Color(153, 204, 255);
    private static final Color CHOOSEN_COLOR = new Color(153, 188, 255);

    public void setOrderRoom(RoomForOrdering orderRoom) {
        this.orderRoom = orderRoom;
    }

    public RoomForOrdering getRoom() {
        return this.orderRoom;
    }

    public void setTypePanel(RoomTypePanel typePanel) {
        this.typePanel = typePanel;
    }

    public RoomToggle(RoomForOrdering roomForOrdering) {
        super();
        this.orderRoom = roomForOrdering;
        this.setText(orderRoom.getName());
        this.setPreferredSize(new Dimension(100, 100));
        this.setBackground(DEFAULT_COLOR);
        this.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent evt) {
                changeColor(evt.getStateChange());
            }
        });
    }

    private void changeColor(int state) {
        if (state == ItemEvent.SELECTED) {
            this.setBackground(CHOOSEN_COLOR);
            typePanel.increase();
        } else {
            this.setBackground(DEFAULT_COLOR);
            typePanel.decrease();
        }
    }

}
