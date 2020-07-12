/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hotel.DTO;

import java.sql.Timestamp;

/**
 *
 * @author Yue
 */
public class Order {

    private int madatphong;
    private int makh;
    private Timestamp ngaydat;
    private Timestamp ngaynhan;
    private Timestamp ngaytra;
    private int tongcoc;
    private int trangthai;

    public Order() {
    }

    public Order(int madatphong, int makh, Timestamp ngaydat, Timestamp ngaynhan, Timestamp ngaytra, int tongcoc, int trangthai) {
        this.madatphong = madatphong;
        this.makh = makh;
        this.ngaydat = ngaydat;
        this.ngaynhan = ngaynhan;
        this.ngaytra = ngaytra;
        this.tongcoc = tongcoc;
        this.trangthai = trangthai;
    }

    public Order(int makh, Timestamp ngaydat, Timestamp ngaynhan, Timestamp ngaytra, int tongcoc, int trangthai) {
        this.makh = makh;
        this.ngaydat = ngaydat;
        this.ngaynhan = ngaynhan;
        this.ngaytra = ngaytra;
        this.tongcoc = tongcoc;
        this.trangthai = trangthai;
    }

    public Order(int madatphong) {
        this.madatphong = madatphong;
    }

    public int getMadatphong() {
        return madatphong;
    }

    public void setMadatphong(int madatphong) {
        this.madatphong = madatphong;
    }

    public int getMakh() {
        return makh;
    }

    public Timestamp getNgaydat() {
        return ngaydat;
    }

    public void setNgaydat(Timestamp ngaydat) {
        this.ngaydat = ngaydat;
    }

    public void setMakh(int makh) {
        this.makh = makh;
    }

    public Timestamp getNgaynhan() {
        return ngaynhan;
    }

    public void setNgaynhan(Timestamp ngaynhan) {
        this.ngaynhan = ngaynhan;
    }

    public Timestamp getNgaytra() {
        return ngaytra;
    }

    public void setNgaytra(Timestamp ngaytra) {
        this.ngaytra = ngaytra;
    }

    public int getTongcoc() {
        return tongcoc;
    }

    public void setTongcoc(int tongcoc) {
        this.tongcoc = tongcoc;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

}
