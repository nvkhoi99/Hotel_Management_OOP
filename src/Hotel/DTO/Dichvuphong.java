/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hotel.DTO;

/**
 *
 * @author Admin
 */
import java.sql.Timestamp;

public class Dichvuphong {

    private int mathuephong;
    private int maphong;
    private Timestamp ngaydung;
    private int madv;
    private int dongia;
    private int soluong;

    public Dichvuphong() {

    }

    public Dichvuphong(int mathuephong, int maphong, Timestamp ngaydung, int madv, int dongia, int soluong) {
        this.mathuephong = mathuephong;
        this.maphong = maphong;
        this.ngaydung = ngaydung;
        this.madv = madv;
        this.dongia = dongia;
        this.soluong = soluong;
    }

    public Dichvuphong(Timestamp ngaydung, int madv, int dongia, int soluong) {
        this.ngaydung = ngaydung;
        this.madv = madv;
        this.dongia = dongia;
        this.soluong = soluong;
    }

    public int getMathuephong() {
        return mathuephong;
    }

    public void setMathuephong(int mathuephong) {
        this.mathuephong = mathuephong;
    }

    public int getMaphong() {
        return maphong;
    }

    public void setMaphong(int maphong) {
        this.maphong = maphong;
    }

    public Timestamp getNgaydung() {
        return ngaydung;
    }

    public void setNgaydung(Timestamp ngaydung) {
        this.ngaydung = ngaydung;
    }

    public int getMadv() {
        return madv;
    }

    public void setMadv(int madv) {
        this.madv = madv;
    }

    public int getDongia() {
        return dongia;
    }

    public void setDongia(int dongia) {
        this.dongia = dongia;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

}
