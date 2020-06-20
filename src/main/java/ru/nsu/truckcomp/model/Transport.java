package ru.nsu.truckcomp.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TRANSPORT")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Transport {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @ManyToOne
    @JoinColumn(name = "garageId")
    private Garage garage;

    @OneToMany(mappedBy = "transport", targetEntity = Driver.class)
    private Set<Driver> drivers = new HashSet<>();

    private String brand;
    private String state;
    private Date acquirementDate;

    public Transport() {

    }

    public Transport(Garage garage, String brand, Date acquirementDate) {
        this.garage = garage;
        this.brand = brand;
        this.acquirementDate = acquirementDate;
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

    public Date getAcquirementDate() {
        return acquirementDate;
    }

    public void setAcquirementDate(Date acquirementDate) {
        this.acquirementDate = acquirementDate;
    }
}
