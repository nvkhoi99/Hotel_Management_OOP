/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hotel.DAL.ReportDAL;

/**
 *
 * @author Yue
 */
public enum ReportType {
    DOANH_SO_DAT_PHONG,
    DOANH_THU_PHONG,
    DOANH_THU_DICH_VU;

    @Override
    public String toString() {
        switch (this) {
            case DOANH_SO_DAT_PHONG:
                return "Doanh số đặt phòng";
            case DOANH_THU_PHONG:
                return "Doanh thu hồ sơ thuê phòng";
            case DOANH_THU_DICH_VU:
                return "Doanh thu dịch vụ";
        }
        return null;
    }

    public static ReportType SerialOf(int type) {
        switch (type) {
            case 0:
                return DOANH_SO_DAT_PHONG;
            case 1:
                return DOANH_THU_PHONG;
            case 2:
                return DOANH_THU_DICH_VU;
        }
        return null;
    }

    public String getSQL() {
        switch (this) {
            case DOANH_SO_DAT_PHONG:
                return "select convert(ngaydat, date) as ngay_dat, dondatphong.madatphong, "
                        + "count(maphong) as so_phong_dat,tongcoc "
                        + "from dondatphong inner join chitietdatphong on dondatphong.madatphong = chitietdatphong.madatphong "
                        + "where ngaydat between ? and ? "
                        + "group by convert(ngaydat, date), dondatphong.madatphong ";
            case DOANH_THU_PHONG:
                return "SELECT convert(thuctra, date) as ngay, mathuephong, count(maphong) as so_phong_o, tongthanhtoan "
                        + "from hosothuephong inner join dondatphong on hosothuephong.madatphong = dondatphong.madatphong "
                        + "inner join chitietdatphong on dondatphong.madatphong = chitietdatphong.madatphong "
                        + "where thuctra between ? and ? "
                        + "group by convert(thuctra, date), mathuephong, tongthanhtoan ";
            case DOANH_THU_DICH_VU:
                return "select tendv, convert(ngaydung,date), sum(soluong), dichvuphong.dongia, sum(soluong * dichvuphong.dongia) as thanhtien "
                        + "from dichvuphong inner join dichvu on dichvuphong.madv = dichvu.madv "
                        + "where ngaydung between ? and ? "
                        + "group by tendv, convert(ngaydung,date)";
        }
        return null;
    }

    public Object[] getColumnHeader() {
        switch (this) {
            case DOANH_SO_DAT_PHONG:
                return new Object[]{"Ngày đặt", "Mã Đặt Phòng", "Số lượng phòng", "Tổng cọc"};
            case DOANH_THU_PHONG:
                return new Object[]{"Ngày", "Mã thuê phòng", " Số lượng phòng", " Tổng thanh toán"};
            case DOANH_THU_DICH_VU:
                return new Object[]{"Tên dịch vụ", "Ngày dùng", "Số lượng", "Đơn giá", "Thành tiền"};
        }
        return null;
    }

}
