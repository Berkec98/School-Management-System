package model.base;

import model.user.Users;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class UpdateEntity {
    //Serial UID bunda olmalı mı, olur mu? - Araştır
    @ManyToOne
    private Users whoUp;
    private long upTime;

    public UpdateEntity() {
    }

    public UpdateEntity(Users whoUp, long upTime) {
        this.whoUp = whoUp;
        this.upTime = upTime;
    }

    public Users getWhoUp() {//durumu null ise null kalmalı
        return whoUp;// = whoUp == null ? new Users() : whoUp;
    }

    public void setWhoUp(Users whoUp) {
        this.whoUp = whoUp;
    }

    public long getUpTime() {
        return upTime;
    }

    public void setUpTime(long upTime) {
        this.upTime = upTime;
    }

}
