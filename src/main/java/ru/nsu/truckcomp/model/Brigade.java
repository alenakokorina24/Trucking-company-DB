package ru.nsu.truckcomp.model;

import javax.persistence.*;

@Entity
@Table(name = "BRIGADES")
public class Brigade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int brigadeId;

    @ManyToOne
    @JoinColumn(name = "areaId")
    private Area area;

    @OneToOne
    @JoinColumn(name = "empId")
    private Employee brigadier;
}
