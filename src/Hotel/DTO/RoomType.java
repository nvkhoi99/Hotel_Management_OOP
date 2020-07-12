/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hotel.DTO;

/**
 *
 * @author Yue
 */
public class RoomType {

    private int maloaiphong;
    private String tenloaiphong;
    private int dongia;

    public RoomType() {

    }

    public RoomType(int maloaiphong, String tenloaiphong, int dongia) {
        this.maloaiphong = maloaiphong;
        this.tenloaiphong = tenloaiphong;
        this.dongia = dongia;
    }

    public int getMaloaiphong() {
        return maloaiphong;
    }

    public void setMaloaiphong(int maloaiphong) {
        this.maloaiphong = maloaiphong;
    }

    public String getTenloaiphong() {
        return tenloaiphong;
    }

    public void setTenloaiphong(String tenloaiphong) {
        this.tenloaiphong = tenloaiphong;
    }

    public int getDongia() {
        return dongia;
    }

    public void setDongia(int dongia) {
        this.dongia = dongia;
    }

}
