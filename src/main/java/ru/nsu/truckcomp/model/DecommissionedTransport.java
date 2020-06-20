package ru.nsu.truckcomp.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "DECOMMISSIONED_TRANSPORT")
public class DecommissionedTransport {
    @Id
    @OneToOne
    @JoinColumn(name = "id")
    private int id;

    private Date decommissionDate;

    public DecommissionedTransport(int id, Date decommissionDate) {
        this.id = id;
        this.decommissionDate = decommissionDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDecommissionDate() {
        return decommissionDate;
    }

    public void setDecommissionDate(Date decommissionDate) {
        this.decommissionDate = decommissionDate;
    }
}
