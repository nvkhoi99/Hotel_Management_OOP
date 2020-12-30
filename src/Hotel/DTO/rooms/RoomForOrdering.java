package Hotel.DTO.rooms;

/**
 *
 * @author Yue
 */
public class RoomForOrdering extends Room {

    private int price;

    public RoomForOrdering() {
    }

    public RoomForOrdering(int maphong, String tenphong, int maloaiphong, int price, boolean available) {
        super(maphong, tenphong, maloaiphong, available);
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
