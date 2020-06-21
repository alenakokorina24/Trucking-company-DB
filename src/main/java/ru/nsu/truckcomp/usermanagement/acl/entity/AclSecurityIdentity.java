package ru.nsu.truckcomp.usermanagement.acl.entity;

import javax.persistence.*;

@Entity
@Table(name = "ACL_SID")
public class AclSecurityIdentity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sidId;

    private String sid;
    private int principal;

    public AclSecurityIdentity() {

    }

    public AclSecurityIdentity(String sid, int principal) {
        this.sid = sid;
        this.principal = principal;
    }

    public int getSidId() {
        return sidId;
    }

    public void setSidId(int sidId) {
        this.sidId = sidId;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public int getPrincipal() {
        return principal;
    }

    public void setPrincipal(int principal) {
        this.principal = principal;
    }
}
