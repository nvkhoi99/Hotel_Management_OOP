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
public class Room {

    protected int maphong;
    protected String tenphong;
    protected int maloaiphong;
    protected boolean available;

    public Room() {

    }

    public Room(int maphong, String tenphong, int maloaiphong, boolean available) {
        this.maphong = maphong;
        this.tenphong = tenphong;
        this.maloaiphong = maloaiphong;
        this.available = available;
    }

    public int getMaphong() {
        return maphong;
    }

    public void setMaphong(int maphong) {
        this.maphong = maphong;
    }

    public String getTenphong() {
        return tenphong;
    }

    public void setTenphong(String tenphong) {
        this.tenphong = tenphong;
    }

    public int getMaloaiphong() {
        return maloaiphong;
    }

    public void setMaloaiphong(int maloaiphong) {
        this.maloaiphong = maloaiphong;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

}
