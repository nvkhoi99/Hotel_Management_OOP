package Hotel.DTO.rooms;

import java.sql.Timestamp;

public class BookingRoom {

    private int id;
    private int roomId;
    private int roomPrice;
    private Timestamp realCheckIn;
    private Timestamp realCheckOut;
    private int surcharge;

    public BookingRoom() {
    }

    public BookingRoom(int roomId, int roomPrice) {
        this.roomId = roomId;
        this.roomPrice = roomPrice;
    }

    public BookingRoom(int id, int roomId, int roomPrice, Timestamp realCheckIn, Timestamp realCheckOut, int surcharge) {
        this.id = id;
        this.roomId = roomId;
        this.roomPrice = roomPrice;
        this.realCheckIn = realCheckIn;
        this.realCheckOut = realCheckOut;
        this.surcharge = surcharge;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(int roomPrice) {
        this.roomPrice = roomPrice;
    }

    public Timestamp getRealCheckIn() {
        return realCheckIn;
    }

    public void setRealCheckIn(Timestamp realCheckIn) {
        this.realCheckIn = realCheckIn;
    }

    public Timestamp getRealCheckOut() {
        return realCheckOut;
    }

    public void setRealCheckOut(Timestamp realCheckOut) {
        this.realCheckOut = realCheckOut;
    }

    public int getSurcharge() {
        return surcharge;
    }

    public void setSurcharge(int surcharge) {
        this.surcharge = surcharge;
    }
}
