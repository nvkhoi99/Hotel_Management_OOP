package Hotel.DTO.Room;

/**
 *
 * @author Yue
 */
public class RoomForOrdering extends Room {

    private int dongiadat;

    public RoomForOrdering() {
    }

    public RoomForOrdering(int maphong, String tenphong, int maloaiphong, int dongiadat, boolean available) {
        super(maphong, tenphong, maloaiphong, available);
        this.dongiadat = dongiadat;
    }

    public int getDongiadat() {
        return dongiadat;
    }

    public void setDongiadat(int dongiadat) {
        this.dongiadat = dongiadat;
    }

}
