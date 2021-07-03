package model.event;

import model.base.BaseEntity;
import model.base.EntityInterface;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Transient;


@Entity
public class EventData extends BaseEntity implements EntityInterface {

    @Transient
    private static final long serialVersionUID = 3228802857729438805L;
    @Lob
    private String oldData; //afterDataChange
    @Column(nullable = false)
    @Lob
    private String currentData; //beforeDataChange

    public EventData() {
    }

    public EventData(String oldData, String currentData) {
        this.oldData = oldData;
        this.currentData = currentData;
    }

    public String getOldData() {
        return oldData;
    }

    public void setOldData(String oldData) {
        this.oldData = oldData;
    }

    public String getCurrentData() {
        return currentData;
    }

    public void setCurrentDate(String currentData) {
        this.currentData = currentData;
    }

    @Override
    public String toString() {
        return "EventData{" +
                "oldData='" + oldData + '\'' +
                ", currentDate='" + currentData + '\'' +
                ", id=" + id +
                '}';
    }
}
