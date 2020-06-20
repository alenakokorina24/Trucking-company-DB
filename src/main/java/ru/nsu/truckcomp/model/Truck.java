package ru.nsu.truckcomp.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "TRUCK")
public class Truck extends Transport {
    private int carryingCapacity;
    private int weight;
    private String carcassType;

    public Truck() {

    }

    public Truck(Garage garage, String brand, Date acquirementDate, int carryingCapacity, int weight, String carcassType) {
        super(garage, brand, acquirementDate);
        this.carryingCapacity = carryingCapacity;
        this.weight = weight;
        this.carcassType = carcassType;
    }

    public int getCarryingCapacity() {
        return carryingCapacity;
    }

    public void setCarryingCapacity(int carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getCarcassType() {
        return carcassType;
    }

    public void setCarcassType(String carcassType) {
        this.carcassType = carcassType;
    }
}