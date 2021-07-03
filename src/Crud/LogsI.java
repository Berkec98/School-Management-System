package Crud;

import daolar.DaoRepositoryImp;
import model.base.BaseEntity;
import model.event.EventData;
import model.event.EventTableDesc;
import model.event.EventType;
import model.event.Events;
import controller.LoginController;
import utility.MyPredicateCreator;
import utility.enums.CommandTipi;

import java.util.Date;

/**
 Log Kaydı DAO sınıfının içinde yapılmaktadır orada save,update ve delete metodlarının her birinde ilgili entitiye ait 1 log kaydı alınmaktadır
 @param <T> logun alındığı entity
 */

class LogsI<T> {

    private EventType getEventType(final EventType e) {
        final DaoRepositoryImp<EventType> dao = new DaoRepositoryImp<>(EventType.class);
        final MyPredicateCreator uniqueEventAdi = new MyPredicateCreator("eventName",e.getEventName(), CommandTipi.Equal);
        final EventType eventType = dao.getSingleStringResult(uniqueEventAdi);
        return eventType == null ? dao.save(e) : eventType;
    }

    private EventData getEventData(final String oldEntity, final T currentEntity) {
        return new EventData(oldEntity, currentEntity == null ? null : currentEntity.toString());
    }

    /**
     Not: bu fonksiyon LOG işlemleri için tablo adı return eder
     bu fonksiyon  .getClass().getSimpleName() sayesinde entitinin adını alır ve tabloda arar eğer varsa nesne olarak (id vs..) geri döndürür
     eğer aradığı tabloo adı EventTableDesc adlı tabloda yoksa yeni bir satır oluşturarak kaydeder
     Tablo isimlerinin LOGlanabilmesi için EventTableDesc tablosuna bütün tablo adlarının girilmesi gerekmektedir.
     bunun için bir yönetim paneli oluşturulmadı çünkü kullanıcının yapacağı bir işlem değildir.
     @param tabloIsmi tablonun veritabanındaki adı
     @return kullanıcının üzerinde işlem yaptığı tablo adını geri döndürür
     */

    //Tablo adını döndürür
    private EventTableDesc getEventTableDesc(final String tabloIsmi) {
        final DaoRepositoryImp<EventTableDesc> dao = new DaoRepositoryImp<>(EventTableDesc.class);
        final MyPredicateCreator uniqueTabloAdi = new MyPredicateCreator("tableName",tabloIsmi,CommandTipi.Equal);
        final EventTableDesc eventTableDesc = dao.getSingleStringResult(uniqueTabloAdi);
        return eventTableDesc == null ? dao.save(new EventTableDesc(tabloIsmi)) : eventTableDesc;
    }


    private Events getNewEvent(final T currentEntity, final String oldEntity, final EventType eventType) {
        final String tabloIsmi = currentEntity.getClass().getSimpleName();
        final int entityID = ((BaseEntity) currentEntity).getId();
        return new Events(
                new Date(),
                getEventTableDesc(tabloIsmi),
                LoginController.getActiveUser(),
                getEventType(eventType),
                getEventData(oldEntity, currentEntity),
                entityID);
    }


    void saveLog(final T currentEntity, final String oldEntity, final EventType eventType) {//buda dışarıdan çağrılacak
        final Events event = getNewEvent(currentEntity, oldEntity, eventType);
        final DaoRepositoryImp<Events> dao = new DaoRepositoryImp<>(Events.class);
        dao.save(event);
    }

}






