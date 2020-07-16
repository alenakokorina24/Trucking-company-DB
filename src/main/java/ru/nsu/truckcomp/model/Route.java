package ru.nsu.truckcomp.model;

import javax.persistence.*;

@Entity
@Table(name = "ROUTES")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int routeId;

    private int routeLength;

    public Route() {

    }

    public Route(int length) {
        this.routeLength = length;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public int getLength() {
        return routeLength;
    }

    public void setLength(int length) {
        this.routeLength = length;
    }
}
