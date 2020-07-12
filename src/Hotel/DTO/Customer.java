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
public class Customer {

    private int makh;
    private String hotenkh;
    private boolean gioitinh;
    private String cmnd;
    private String diachi;
    private String sdt;

    public Customer() {

    }

    public Customer(int makh, String hotenkh, boolean gioitinh, String cmnd, String diachi, String sdt) {
        this.makh = makh;
        this.hotenkh = hotenkh;
        this.gioitinh = gioitinh;
        this.cmnd = cmnd;
        this.diachi = diachi;
        this.sdt = sdt;
    }

    public int getMakh() {
        return makh;
    }

    public void setMakh(int makh) {
        this.makh = makh;
    }

    public String getHotenkh() {
        return hotenkh;
    }

    public void setHotenkh(String hotenkh) {
        this.hotenkh = hotenkh;
    }

    public boolean isGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(boolean gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

}
