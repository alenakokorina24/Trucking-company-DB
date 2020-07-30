package ru.nsu.truckcomp.model;

import java.io.Serializable;

public class TransportArea implements Serializable {
    private int id;
    private Garage garage;
    private String brand;
    private Area area;

    public TransportArea() {

    }

    public TransportArea(int id, String brand, Garage garage, Area area) {
        this.id = id;
        this.garage = garage;
        this.brand = brand;
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

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
}
