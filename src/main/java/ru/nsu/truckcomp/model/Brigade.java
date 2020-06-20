package ru.nsu.truckcomp.model;

import javax.persistence.*;

@Entity
@Table(name = "BRIGADES")
public class Brigade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int brigadeId;

    @ManyToOne
    @JoinColumn(name = "areaId")
    private Area area;

    @OneToOne
    @JoinColumn(name = "empId")
    private Employee brigadier;

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
