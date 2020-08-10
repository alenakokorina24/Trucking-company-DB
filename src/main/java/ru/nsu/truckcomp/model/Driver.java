package ru.nsu.truckcomp.model;

import javax.persistence.*;

@Entity
@Table(name = "DRIVERS")
public class Driver extends Employee {
    @ManyToOne
    @JoinColumn(name = "id")
    private Transport transport;

    @ManyToOne
    @JoinColumn(name = "brigade_id")
    private Brigade brigade;

    public Driver() {

    }

    public Driver(String name, String position, Transport transport, Brigade brigade, Employee boss) {
        super(name, position, boss);
        this.transport = transport;
        this.brigade = brigade;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public Brigade getBrigade() {
        return brigade;
    }

    public void setBrigade(Brigade brigade) {
        this.brigade = brigade;
    }
}
