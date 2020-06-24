package ru.nsu.truckcomp.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TRANSPORT")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "transport_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Transport {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @ManyToOne
    @JoinColumn(name = "garage_id")
    private Garage garage;

    @OneToMany(mappedBy = "transport", targetEntity = Driver.class)
    private Set<Driver> drivers = new HashSet<>();

    private String brand;
    private String state;
    private Date acquirementDate;
    private Date decommissionDate;

    public Transport() {

    }

    public Transport(Garage garage, String brand, Date acquirementDate, Date decommissionDate) {
        this.garage = garage;
        this.brand = brand;
        this.acquirementDate = acquirementDate;
        this.decommissionDate = decommissionDate;
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
