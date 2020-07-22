package ru.nsu.truckcomp.model;

import java.io.Serializable;

public class TransportArea implements Serializable {
    private int id;
    private Garage garage;
    private String brand;
    private String state;
    private Area area;

    public TransportArea() {

    }

    public TransportArea(int id, String brand, String state, Garage garage, Area area) {
        this.id = id;
        this.garage = garage;
        this.brand = brand;
        this.state = state;
        this.area = area;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Garage getGarage() {
        return garage;
    }

    public void setGarage(Garage garage) {
        this.garage = garage;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
}
