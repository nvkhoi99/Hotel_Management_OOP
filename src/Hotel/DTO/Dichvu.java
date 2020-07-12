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

public class Dichvu {

    private int madv;
    private String tendv;
    private String dvtinh;
    private int dongia;
    private int soluong;
    private Timestamp ngaydung;
    private int maphong;

    public Dichvu() {

    }

    public Dichvu(int madv, String tendv, String dvtinh, int dongia, int soluong, Timestamp ngaydung, int maphong) {
        this.madv = madv;
        this.tendv = tendv;
        this.dvtinh = dvtinh;
        this.dongia = dongia;
        this.soluong = soluong;
        this.ngaydung = ngaydung;
        this.maphong = maphong;
    }

    public Dichvu(int madv, String tendv, String dvtinh, int dongia) {
        this.madv = madv;
        this.tendv = tendv;
        this.dvtinh = dvtinh;
        this.dongia = dongia;
    }

    public int getMadv() {
        return madv;
    }

    public void setMadv(int madv) {
        this.madv = madv;
    }

    public String getTendv() {
        return tendv;
    }

    public void setTendv(String tendv) {
        this.tendv = tendv;
    }

    public String getDvtinh() {
        return dvtinh;
    }

    public void setDvtinh(String dvtinh) {
        this.dvtinh = dvtinh;
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

    public Timestamp getNgaydung() {
        return ngaydung;
    }

    public void setNgaydung(Timestamp ngaydung) {
        this.ngaydung = ngaydung;
    }

    public int getMaphong() {
        return maphong;
    }

    public void setMaphong(int maphong) {
        this.maphong = maphong;
    }

}
