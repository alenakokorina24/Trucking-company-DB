package ru.nsu.truckcomp.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "PASSENGER_TRANSPORT")
public class PassengerTransport extends Transport {
    private String type;
    private int capacity;

    public PassengerTransport() {

    }

    public PassengerTransport(Garage garage, String brand, Date acquirementDate, String type, int capacity) {
        super(garage, brand, acquirementDate);
        this.type = type;
        this.capacity = capacity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
