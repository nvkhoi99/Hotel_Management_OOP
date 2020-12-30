/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hotel.DTO.services;

/**
 *
 * @author Admin
 */
import java.sql.Timestamp;

public class Service {

    private int id;
    private String name;
    private String unit;
    private int price;

    public Service() {

    }

    public Service(String name, String unit, int price) {
        this.name = name;
        this.unit = unit;
        this.price = price;
    }

    public Service(int id, String name, String unit, int price) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.price = price;
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
