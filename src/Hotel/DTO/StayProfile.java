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
public class StayProfile {

    private int mathuephong;
    private Booking dondatphong;
    private Timestamp thucnhan;
    private Timestamp thuctra;
    private long tongthanhtoan;

    public StayProfile() {
    }

    public StayProfile(int mathuephong, Booking dondatphong, Timestamp thucnhan, Timestamp thuctra, long tongthanhtoan) {
        this.mathuephong = mathuephong;
        this.dondatphong = dondatphong;
        this.thucnhan = thucnhan;
        this.thuctra = thuctra;
        this.tongthanhtoan = tongthanhtoan;
    }

    public int getMathuephong() {
        return mathuephong;
    }

    public void setMathuephong(int mathuephong) {
        this.mathuephong = mathuephong;
    }

    public Booking getDondatphong() {
        return dondatphong;
    }

    public void setDondatphong(Booking dondatphong) {
        this.dondatphong = dondatphong;
    }

    public Timestamp getThucnhan() {
        return thucnhan;
    }

    public void setThucnhan(Timestamp thucnhan) {
        this.thucnhan = thucnhan;
    }

    public Timestamp getThuctra() {
        return thuctra;
    }

    public void setThuctra(Timestamp thuctra) {
        this.thuctra = thuctra;
    }

    public long getTongthanhtoan() {
        return tongthanhtoan;
    }

    public void setTongthanhtoan(long tongthanhtoan) {
        this.tongthanhtoan = tongthanhtoan;
    }

}
