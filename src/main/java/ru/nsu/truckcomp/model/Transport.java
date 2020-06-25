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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(name = "transport_type", updatable = false, insertable = false)
    private String transportType;

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

    public Set<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(Set<Driver> drivers) {
        this.drivers = drivers;
    }

    public Date getDecommissionDate() {
        return decommissionDate;
    }

    public void setDecommissionDate(Date decommissionDate) {
        this.decommissionDate = decommissionDate;
    }

    public String getTransportType() {
        return transportType;
    }
}
