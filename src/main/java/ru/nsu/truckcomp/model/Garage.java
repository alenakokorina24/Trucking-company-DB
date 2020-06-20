package ru.nsu.truckcomp.model;

import javax.persistence.*;

@Entity
@Table(name = "GARAGES")
public class Garage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int garageId;

    @ManyToOne
    @JoinColumn(name = "areaId")
    private Area area;

    public int getGarageId() {
        return garageId;
    }

    public void setGarageId(int garageId) {
        this.garageId = garageId;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
}
