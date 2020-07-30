package ru.nsu.truckcomp.model;

import javax.persistence.*;

@Entity
@Table(name = "GOVERNANCE")
public class Governance extends Employee {
    public Governance() {

    }

    public Governance(String name, String position) {
        super(name, position);
    }
}
