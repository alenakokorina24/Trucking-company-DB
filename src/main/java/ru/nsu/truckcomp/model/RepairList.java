package ru.nsu.truckcomp.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "REPAIR_LIST")
public class RepairList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int repId;

    @ManyToOne
    @JoinColumn(name = "brigadeId")
    private Brigade brigade;

    @ManyToOne
    @JoinColumn(name = "id")
    private Transport transport;

    private String sparePart;
    private int cost;
    private Date received;
    private Date returned;

    public RepairList(Brigade brigade, Transport transport, String sparePart, int cost, Date received, Date returned) {
        this.brigade = brigade;
        this.transport = transport;
        this.sparePart = sparePart;
        this.cost = cost;
        this.received = received;
        this.returned = returned;
    }

    public int getRepId() {
        return repId;
    }

    public Brigade getBrigade() {
        return brigade;
    }

    public void setBrigade(Brigade brigade) {
        this.brigade = brigade;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public String getSparePart() {
        return sparePart;
    }

    public void setSparePart(String sparePart) {
        this.sparePart = sparePart;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Date getReceived() {
        return received;
    }

    public void setReceived(Date received) {
        this.received = received;
    }

    public Date getReturned() {
        return returned;
    }

    public void setReturned(Date returned) {
        this.returned = returned;
    }
}
