package ru.nsu.truckcomp.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@DiscriminatorValue(value = "truck")
public class Truck extends Transport {
    private int carryingCapacity;
    private int weight;
    private String carcassType;

    public Truck() {

    }

    public Truck(Garage garage, String brand, Date acquirementDate, Date decommissionDate, int carryingCapacity, int weight, String carcassType) {
        super(garage, brand, acquirementDate, decommissionDate);
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