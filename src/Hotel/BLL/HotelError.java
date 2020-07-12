/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hotel.BLL;

import javax.swing.JOptionPane;

/**
 *
 * @author Yue
 */
public class HotelError extends Throwable {

    public enum ErrorCode {
        EMPTY_NAME,
        INVALID_PRICE,
        NAME_DUPLICATE,
        LOW_PRICE,
        STILL_HAVE_ROOM,
        STILL_CUSTOMER,
        INVALID_IDCARD,
        INVALID_PHONE,
        STILL_HAVE_ORDER,
        STAY_FAILED,
        INVALID_NUM,
        INVALID_TEXT
    }

    private final ErrorCode errorCode;

    public HotelError(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    private String getErrorMessage() {
        switch (errorCode) {
            case EMPTY_NAME:
                return "Tên trống";
            case INVALID_PRICE:
                return "Đơn giá không hợp lệ";
            case NAME_DUPLICATE:
                return "Tên trùng lặp";
            case LOW_PRICE:
                return "Đơn giá quá thấp";
            case STILL_HAVE_ROOM:
                return "Vẫn còn phòng trong loại phòng này, vui lòng sắp xếp lại";
            case STILL_CUSTOMER:
                return "Khách cũ vẫn chưa trả phòng";
            case INVALID_IDCARD:
                return "CMND không hợp lệ";
            case INVALID_PHONE:
                return "Số điện thoại không hợp lệ";
            case STILL_HAVE_ORDER:
                return "Phòng này vẫn còn đơn đặt";
            case STAY_FAILED:
                return "Không có phòng nào được nhận";
            case INVALID_NUM:
                return "Số lơợng không hợp lệ";
            case INVALID_TEXT:
                return "Chưa điền đủ thông tin ";
            default:
                return "Lỗi chưa xác định";
        }
    }

    public void notifyError() {
        JOptionPane.showMessageDialog(null, this.getErrorMessage());
    }
}
