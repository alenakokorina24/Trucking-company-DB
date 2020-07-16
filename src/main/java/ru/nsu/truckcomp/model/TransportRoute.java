package ru.nsu.truckcomp.model;

import java.io.Serializable;

public class TransportRoute implements Serializable {
    private int id;
    private int routeId;
    private int routeLength;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public int getRouteLength() {
        return routeLength;
    }

    public void setRouteLength(int routeLength) {
        this.routeLength = routeLength;
    }

    public TransportRoute() {

    }

    public TransportRoute(int id, int routeId, int routeLength) {
        this.id = id;
        this.routeId = routeId;
        this.routeLength = routeLength;
    }
}
