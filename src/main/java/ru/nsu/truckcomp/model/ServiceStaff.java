package ru.nsu.truckcomp.model;

import javax.persistence.*;

@Entity
@Table(name = "SERVICE_STAFF")
public class ServiceStaff extends Employee {
    @ManyToOne
    @JoinColumn(name = "brigade_id")
    private Brigade brigade;

    public ServiceStaff() {

    }

    public ServiceStaff(String name, String position, Brigade brigade) {
        super(name, position);
        this.brigade = brigade;
    }

    public Brigade getBrigade() {
        return brigade;
    }

    public void setBrigade(Brigade brigade) {
        this.brigade = brigade;
    }
}
