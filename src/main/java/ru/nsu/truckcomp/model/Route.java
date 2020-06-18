package ru.nsu.truckcomp.model;

import javax.persistence.*;

@Entity
@Table(name = "ROUTES")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int routeId;

    private int length;

    public int getRouteId() {
        return routeId;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
