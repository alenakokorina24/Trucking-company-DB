package ru.nsu.truckcomp.usermanagement.acl.entity;

import javax.persistence.*;

@Entity
@Table(name = "ACL_CLASS")
public class AclClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int classId;

    private String className;

    public AclClass() {

    }

    public AclClass(String className) {
        this.className = className;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
