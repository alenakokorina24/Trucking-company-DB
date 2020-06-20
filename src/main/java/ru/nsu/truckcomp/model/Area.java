package ru.nsu.truckcomp.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "AREAS")
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int areaId;

    @OneToOne
    @JoinColumn(name = "empId")
    private Employee chief;

    @OneToMany(mappedBy = "area", targetEntity = Garage.class, cascade = CascadeType.ALL)
    private Set<Garage> garages = new HashSet<>();

    @OneToMany(mappedBy = "area", targetEntity = Brigade.class, cascade = CascadeType.ALL)
    private Set<Brigade> brigades = new HashSet<>();

    public Area() {

    }

    public Area(Employee chief) {
        this.chief = chief;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int id) { areaId = id; }

    public Employee getChief() {
        return chief;
    }

    public void setChief(Employee chief) {
        this.chief = chief;
    }
}
