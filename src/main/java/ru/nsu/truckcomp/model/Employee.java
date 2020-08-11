package ru.nsu.truckcomp.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "EMPLOYEES")
@Inheritance(strategy = InheritanceType.JOINED)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int empId;

    private String name;
    private String position;

    @ManyToOne
    @JoinColumn(name="boss_id")
    private Employee boss;

    @OneToMany(mappedBy = "boss", targetEntity = Employee.class, cascade = CascadeType.ALL)
    private Set<Employee> subordinates = new HashSet<>();

    public Employee() {

    }

    public Employee(String name, String position, Employee boss) {
        this.name = name;
        this.position = position;
        this.boss = boss;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Employee getBoss() {
        return boss;
    }

    public void setBoss(Employee boss) {
        this.boss = boss;
    }

    public Set<Employee> getSubordinates() {
        return subordinates;
    }
}
