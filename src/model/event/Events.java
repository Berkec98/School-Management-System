package model.event;

import model.base.BaseEntity;
import model.base.EntityInterface;
import model.user.Users;
import utility.MyDate;
import javax.persistence.*;
import java.util.Date;

@Entity
public class Events extends BaseEntity implements EntityInterface {

    @Transient
    private static final long serialVersionUID = -8854027452555682287L;

    private Date eventDate;             //Tarih için mümkünse temporal kullanma  Bu anotasyon eski java.util.Date veya java.util.Calendar sınıfları için var
    @ManyToOne
    // şimdi ise Java 8 veya JPA 2.2 den sonra java.time.LocalDateTime veya java.time.ZonedDateTime
    private EventTableDesc tableNames;  // dateTime.plusHours(2);
    @ManyToOne                          // dateTime.minusHours(4);
    private Users user;
    @ManyToOne
    private EventType eventType;
    @OneToOne(cascade = {CascadeType.ALL})
    private EventData eventData;
    private int entityID;

    public Events() {
    }



    public Events(Date eventDate, EventTableDesc tableNames, Users user, EventType eventType, EventData eventData, int entityID) {
        this.eventDate = eventDate;
        this.tableNames = tableNames;
        this.user = user;
        this.eventType = eventType;
        this.eventData = eventData;
        this.entityID = entityID;
    }

    public int getId() {
        return id;
    }

    public int getEntityID() {
        return entityID;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public String getEventDateSAAT() {
        return new MyDate(eventDate).getMyDateAsString("HH:mm:ss");
    }

    public String getEventDateTARIH() {
        return new MyDate(eventDate).getMyDateAsString("dd/MM/yyyy");
    }


    public void setEntityID(int entityID) {
        this.entityID = entityID;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }



    public EventTableDesc getTableNames() {
        if (tableNames == null)
            tableNames = new EventTableDesc();
        return tableNames;
    }

    public void setTableNames(EventTableDesc tableNames) {
        this.tableNames = tableNames;
    }
    public void setEventData(EventData eventData) {
        this.eventData = eventData;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public Users getUser() { //null kontrolü özellikle yapmadım gerekçe sisteme null user giremez
        if (user == null)
            user = new Users();
        return user;
    }

    public EventType getEventType() {
        if (eventType == null)
            eventType = new EventType();
        return eventType;
    }


    public EventData getEventData() {
        if (eventData == null)
            eventData = new EventData();
        return eventData;
    }

    @Override
    public String toString() {
        return "Events{" +
                "eventDate=" + eventDate +
                ", tableNames=" + tableNames +
                ", user=" + user +
                ", eventType=" + eventType +
                ", eventData=" + eventData +
                ", entityID=" + entityID +
                ", id=" + id +
                '}';
    }
}
