package ru.nsu.truckcomp.usermanagement.acl.entity;

import javax.persistence.*;

@Entity
@Table(name = "ACL_OBJECT_IDENTITY")
public class AclObjectIdentity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int oidId;

    @OneToOne
    @JoinColumn(name = "class_id")
    private AclClass objectIdClass;

    // wuuuuuuuuuuuuut
    private Object objectIdIdentity;

    // wuuuuuuuuuuuuut
    private Object parentObject;

    @OneToOne
    @JoinColumn(name = "sid_id")
    private AclSecurityIdentity ownerSid;

    private int entriesInheriting;
}
