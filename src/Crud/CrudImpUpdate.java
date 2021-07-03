package Crud;

import daolar.DaoRepositoryImp;
import utility.enums.OpType;
import model.base.EntityInterface;
import model.event.EventType;

public class CrudImpUpdate extends CrudI<EntityInterface, DaoRepositoryImp<EntityInterface>> {

    /**
     UPDATE İşlemleri
     @param currentEntity     güncellenecek entity
     @param oldEntity         eski dataların olduğu entity
     @param daoRepositoryImpl işlemi gerçekleştirecek dao sınıfı
     @return UPDATE entity geri döndürülür
     */
    @Override
    public EntityInterface executeRequest(EntityInterface currentEntity, String oldEntity, DaoRepositoryImp<EntityInterface> daoRepositoryImpl) {
        return daoRepositoryImpl.update(currentEntity);
    }

    @Override
    public void islemBasariliMsg(EntityInterface entity) {
        super.islemBasariliMsg(OpType.UPDATE, entity);
    }

    @Override
    public void logKaydiAl(EntityInterface currentEntity, String oldEntity) {
        super.logKaydiAl(currentEntity, oldEntity, new EventType("UPDATE"));
    }
}


