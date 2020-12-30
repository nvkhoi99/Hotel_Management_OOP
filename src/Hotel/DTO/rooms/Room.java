/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hotel.DTO.rooms;

/**
 *
 * @author Yue
 */
public abstract class Room {

    protected int id;
    protected String name;
    protected int typeId;
    protected boolean available;

    public Room() {

    }

    public Room(int id, String name, int typeId, boolean available) {
        this.id = id;
        this.name = name;
        this.typeId = typeId;
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

}
