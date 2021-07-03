package model.event;

import model.base.BaseEntity;
import model.base.EntityInterface;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class EventType extends BaseEntity implements EntityInterface {
    @Transient
    private static final long serialVersionUID = -3117331047807303912L;
    @Column(length = 10,nullable = false,unique = true)
    private String eventName;

    public EventType() {
    }

    public EventType(String eventName) {
        this.eventName = eventName;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    @Override
    public String toString() {
        return  eventName ;
    }
}
