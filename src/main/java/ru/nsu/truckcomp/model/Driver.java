package ru.nsu.truckcomp.model;

import javax.persistence.*;

@Entity
@Table(name = "DRIVERS")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int areaId;
}
