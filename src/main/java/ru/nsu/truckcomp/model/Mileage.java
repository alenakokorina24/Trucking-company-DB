package ru.nsu.truckcomp.model;

public class Mileage {
    private int id;
    private long mileage;

    public Mileage() {
    }

    public Mileage(int id, long mileage) {
        this.id = id;
        this.mileage = mileage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getMileage() {
        return mileage;
    }

    public void setMileage(long mileage) {
        this.mileage = mileage;
    }
}
