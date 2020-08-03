package ru.nsu.truckcomp.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "RIDES")
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rideId;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    @ManyToOne
    @JoinColumn(name = "id")
    private Transport transport;

    private Date date;

    public Ride() {

    }

    public Ride(Route route, Transport transport, Date date) {
        this.route = route;
        this.transport = transport;
        this.date = date;
    }

    public int getRideId() {
        return rideId;
    }

    public void setRideId(int rideId) {
        this.rideId = rideId;
    }

    public Route getRoute() {
        return route;
    }

    public Transport getTransport() {
        return transport;
    }

    public Date getDate() {
        return date;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
