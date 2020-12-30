/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hotel.DTO;

import Hotel.DTO.rooms.BookingRoom;
import Hotel.DTO.services.RoomService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yue
 */
public class Booking {

    private int id;
    private int customerId;
    private Timestamp bookingTime;
    private Timestamp checkInTime;
    private Timestamp checkOutTime;
    private int deposit;
    private int status;
    private Timestamp paytime;
    private long total;

    private List<BookingRoom> bookingRooms = new ArrayList<>();
    private List<RoomService> bookingServices = new ArrayList<>();

    public Booking() {
    }

    public Booking(int id, int customerId, Timestamp bookingTime, Timestamp checkInTime, Timestamp checkOutTime, Timestamp paytime, int deposit, int status) {
        this.id = id;
        this.customerId = customerId;
        this.bookingTime = bookingTime;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.paytime = paytime;
        this.deposit = deposit;
        this.status = status;
    }

    public Booking(int customerId, Timestamp bookingTime, Timestamp checkInTime, Timestamp checkOutTime, int deposit, int status) {
        this.customerId = customerId;
        this.bookingTime = bookingTime;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.deposit = deposit;
        this.status = status;
    }

    public Booking(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public Timestamp getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(Timestamp bookingTime) {
        this.bookingTime = bookingTime;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Timestamp getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(Timestamp checkInTime) {
        this.checkInTime = checkInTime;
    }

    public Timestamp getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(Timestamp checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
        this.status = deposit == 0 ? 0 : 1;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Timestamp getPaytime() {
        return paytime;
    }

    public void setPaytime(Timestamp paytime) {
        this.paytime = paytime;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<BookingRoom> getBookingRooms() {
        return bookingRooms;
    }

    public List<RoomService> getBookingServices() {
        return bookingServices;
    }
}
