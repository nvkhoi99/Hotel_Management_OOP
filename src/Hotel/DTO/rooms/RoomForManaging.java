/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hotel.DTO.rooms;

/**
 *
 * @author Yue
 */
public class RoomForManaging extends Room {

    private int currentBooking;

    public RoomForManaging() {

    }

    public RoomForManaging(int maphong, String tenphong, int maloaiphong, int currentBooking, boolean available) {
        super(maphong, tenphong, maloaiphong, available);
        this.currentBooking = currentBooking;
    }

    public int getCurrentBooking() {
        return currentBooking;
    }

    public void setCurrentBooking(int currentBooking) {
        this.currentBooking = currentBooking;
    }

//    public static RoomButton getPosition(RoomButton[] roomButtons, int roomId, int start, int end) {
//        if (start <= end) {
//            int pos = (start + end) / 2;
//            if (roomButtons[pos].getRoom().getMaphong() < roomId) {
//                return getPosition(roomButtons, roomId, pos + 1, end);
//            }
//            if (roomButtons[pos].getRoom().getMaphong() > roomId) {
//                return getPosition(roomButtons, roomId, start, pos - 1);
//            }
//            return roomButtons[pos];
//        }
//        return null;
//    }
}
