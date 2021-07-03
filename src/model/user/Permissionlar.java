package model.user;

import model.base.BaseEntity;
import model.base.EntityInterface;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Permissionlar extends BaseEntity implements EntityInterface {
    @Transient
    private static final long serialVersionUID = 8519614275574074195L;
    @ManyToOne
    private Groups grup;
    private String zoneNames;
    private boolean c;
    private boolean r;
    private boolean u;
    private boolean d;

    public Permissionlar() {
    }

    public Permissionlar(Groups grup, String zoneNames, boolean c, boolean r, boolean u, boolean d) {
        this.grup = grup;
        this.zoneNames = zoneNames;
        this.c = c;
        this.r = r;
        this.u = u;
        this.d = d;
    }

    public Groups getGrup() {
        return grup;
    }

    public void setGrup(Groups grup) {
        this.grup = grup;
    }

    public String getZoneNames() {
        return zoneNames;
    }

    public void setZoneNames(String zoneNames) {
        this.zoneNames = zoneNames;
    }

    public boolean isC() {
        return c;
    }

    public void setC(boolean c) {
        this.c = c;
    }

    public boolean isR() {
        return r;
    }

    public void setR(boolean r) {
        this.r = r;
    }

    public boolean isU() {
        return u;
    }

    public void setU(boolean u) {
        this.u = u;
    }

    public boolean isD() {
        return d;
    }

    public void setD(boolean d) {
        this.d = d;
    }

    @Override
    public String toString() {
        return "Permissionlar{" +
                "grup=" + grup +
                ", zoneNames='" + zoneNames + '\'' +
                ", c=" + c +
                ", r=" + r +
                ", u=" + u +
                ", d=" + d +
                ", id=" + id +
                '}';
    }
}
