/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hotel.DTO.Room;

/**
 *
 * @author Yue
 */
public class RoomForManaging extends Room {

    private int mahientai;

    public RoomForManaging(int mahientai) {

    }

    public RoomForManaging(int maphong, String tenphong, int maloaiphong, int mahientai, boolean available) {
        super(maphong, tenphong, maloaiphong, available);
        this.mahientai = mahientai;
    }

    public int getMahientai() {
        return mahientai;
    }

    public void setMahientai(int mahientai) {
        this.mahientai = mahientai;
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
