/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hotel.DTO.services;

/**
 *
 * @author Admin
 */
import java.sql.Timestamp;

public class RoomService {

    private int id;
    private int serviceId;
    private int bookingId;
    private int roomId;
    private Timestamp useTime;
    private int price;
    private int quantity;

    public RoomService() {

    }

    public RoomService(int id, int serviceId, int bookingId, int roomId, Timestamp useTime, int price, int quantity) {
        this.id = id;
        this.bookingId = bookingId;
        this.roomId = roomId;
        this.useTime = useTime;
        this.serviceId = serviceId;
        this.price = price;
        this.quantity = quantity;
    }

    public RoomService(int bookingId, int roomId, int serviceId, Timestamp useTime, int price, int quantity) {
        this.id = 0;
        this.bookingId = bookingId;
        this.roomId = roomId;
        this.useTime = useTime;
        this.serviceId = serviceId;
        this.price = price;
        this.quantity = quantity;
    }

    public RoomService(Timestamp useTime, int serviceId, int price, int quantity) {
        this.id = 0;
        this.useTime = useTime;
        this.serviceId = serviceId;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public Timestamp getUseTime() {
        return useTime;
    }

    public void setUseTime(Timestamp useTime) {
        this.useTime = useTime;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
