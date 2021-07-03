package model.event;

import model.base.EntityInterface;
import jdk.nashorn.internal.ir.annotations.Immutable;
import utility.MyDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Entity
@Immutable  //salt okunur ör:view
@Table(name = "log_view")
public class LOG_View implements EntityInterface {

    @Id
    private String id;
    private Date eventDate;
    private String currentdata;
    private String olddata;
    private String eventName; //save, update, delete
    private String tableName;
    private String username;
    @Transient
    private String eventDateTARIH;
    @Transient
    private String eventDateSAAT;

    public String getId() {
        return id;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public String getEventDateSAAT() {
        return new MyDate(eventDate).getMyDateAsString("HH:mm:ss");
    }

    public String getEventDateTARIH(){
        return new MyDate(eventDate).getMyDateAsString("dd/MM/yyyy");
    }
    public String getCurrentdata() {return currentdata;}

    public String getOlddata() { return olddata;}

    public String getEventName() { return eventName;}

    public String getTableName() {
        return tableName;
    }

    public String getUsername() {
        return username;
    }
}
