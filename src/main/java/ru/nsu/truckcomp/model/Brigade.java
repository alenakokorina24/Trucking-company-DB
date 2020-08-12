package ru.nsu.truckcomp.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "BRIGADES")
public class Brigade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int brigadeId;

    @ManyToOne
    @JoinColumn(name = "area_id")
    private Area area;

    @OneToOne
    @JoinColumn(name = "emp_id")
    private Employee brigadier;

    @OneToMany(mappedBy = "brigade", targetEntity = RepairList.class, cascade = CascadeType.ALL)
    private Set<RepairList> repairLists = new HashSet<>();

    @OneToMany(mappedBy = "brigade", targetEntity = Driver.class, cascade = CascadeType.ALL)
    private Set<Driver> drivers = new HashSet<>();

    @OneToMany(mappedBy = "brigade", targetEntity = ServiceStaff.class, cascade = CascadeType.ALL)
    private Set<ServiceStaff> serviceStaff = new HashSet<>();

    public Brigade() {

    }

    public Brigade(Area area, Employee brigadier) {
        this.area = area;
        this.brigadier = brigadier;
    }

    public int getBrigadeId() {
        return brigadeId;
    }

    public void setBrigadeId(int brigadeId) {
        this.brigadeId = brigadeId;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Employee getBrigadier() {
        return brigadier;
    }

    public void setBrigadier(Employee brigadier) {
        this.brigadier = brigadier;
    }
}
