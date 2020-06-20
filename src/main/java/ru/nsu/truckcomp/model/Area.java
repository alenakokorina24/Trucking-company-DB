package ru.nsu.truckcomp.model;

import javax.persistence.*;

@Entity
@Table(name = "AREAS")
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int areaId;

    @OneToOne
    @JoinColumn(name = "empId")
    private Employee chief;

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
