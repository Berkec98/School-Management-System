package entities.event;


import model.event.EventData;
import model.event.EventTableDesc;
import model.event.EventType;
import org.junit.Test;

import javax.persistence.*;


public class EventsTestSAVES {
    private EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("eSistemPU");
    private EntityManager entityManager = emFactory.createEntityManager();
    private EntityTransaction transaction = entityManager.getTransaction();
 /*     Query q = entityManager.createNativeQuery("select count(e) from eventtype e ");
        Long count = (Long) q.getSingleResult();
        System.out.println("dönen değer:"+count);*/


    public boolean isTypeInEventTypeTable(String data) {
        Query query = entityManager.createNativeQuery("select count(e) from eventtype e where UPPER(name)=UPPER(?)");
        query.setParameter(1, data);
        long sonuc = (long) query.getSingleResult();
        if (sonuc > 0) return true;
        else return false;
    }

    @Test
    public void saveEventData() {
        EventData eventData = new EventData("Ahmet,YILDIZ,500", "Ahmet YILDIZ,100");
        transaction.begin();
        entityManager.persist(eventData);
        transaction.commit();
    }

    @Test
    public void saveEventType() {
        EventType eventType1 = new EventType("Create");
        System.out.println("Kaydedilecek data:" + eventType1);
        if (!isTypeInEventTypeTable(eventType1.getEventName())) {
                transaction.begin();
                entityManager.persist(eventType1);
                transaction.commit();
        } else  System.out.println("Kaydetme işlemi kayıt zaten mevcut gerçekleşmedi");
    }

    @Test
    public void saveTableNames() {
        EventTableDesc tableNames0 = new EventTableDesc("User");
        EventTableDesc tableNames1 = new EventTableDesc("Zones");
        EventTableDesc tableNames2 = new EventTableDesc("Permissions");
        EventTableDesc tableNames3 = new EventTableDesc("Groups");
        EventTableDesc tableNames4 = new EventTableDesc("Event");
        transaction.begin();
        entityManager.persist(tableNames0);
        entityManager.persist(tableNames1);
        entityManager.persist(tableNames2);
        entityManager.persist(tableNames3);
        entityManager.persist(tableNames4);
        transaction.commit();
    }

    @Test
    public void SaveEvents() {
        // User user=entityManager.find(User.class,1);
/*        EventType eventType = entityManager.find(EventType.class, 1);
        EventData eventData = entityManager.find(EventData.class, 1);
        EventTableDesc tableNames = entityManager.find(EventTableDesc.class, 1);
        Events events = new Events(new Date(), new Date(), null,eventType,eventData,null);
        events.setTableNames(tableNames);
        events.setEventData(eventData);
        events.setEventType(eventType);
        transaction.begin();
        entityManager.persist(events);
        transaction.commit();*/
    }

    @Test
    public void viewQueryTest() {

    }

}