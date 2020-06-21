package ru.nsu.truckcomp.usermanagement.acl.entity;

import javax.persistence.*;

@Entity
@Table(name = "ACL_ENTRY")
public class AclEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int entryId;

    @OneToOne
    @JoinColumn(name = "oid_id")
    private AclObjectIdentity aclObjectIdentity;

    private int aceOrder;

    @OneToOne
    @JoinColumn(name = "sid_id")
    private AclSecurityIdentity sid;

    private int mask;
    private int granting;
    private String audit;

    public AclEntry() {

    }

    public AclEntry(AclObjectIdentity aclObjectIdentity, AclSecurityIdentity sid, int mask, int granting, String audit) {
        this.aclObjectIdentity = aclObjectIdentity;
        this.sid = sid;
        this.mask = mask;
        this.granting = granting;
        this.audit = audit;
    }

    public int getEntryId() {
        return entryId;
    }

    public void setEntryId(int entryId) {
        this.entryId = entryId;
    }

    public AclObjectIdentity getAclObjectIdentity() {
        return aclObjectIdentity;
    }

    public void setAclObjectIdentity(AclObjectIdentity aclObjectIdentity) {
        this.aclObjectIdentity = aclObjectIdentity;
    }

    public int getAceOrder() {
        return aceOrder;
    }

    public void setAceOrder(int aceOrder) {
        this.aceOrder = aceOrder;
    }

    public AclSecurityIdentity getSid() {
        return sid;
    }

    public void setSid(AclSecurityIdentity sid) {
        this.sid = sid;
    }

    public int getMask() {
        return mask;
    }

    public void setMask(int mask) {
        this.mask = mask;
    }

    public int getGranting() {
        return granting;
    }

    public void setGranting(int granting) {
        this.granting = granting;
    }

    public String getAudit() {
        return audit;
    }

    public void setAudit(String audit) {
        this.audit = audit;
    }
}
