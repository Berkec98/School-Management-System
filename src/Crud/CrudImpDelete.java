package Crud;

import daolar.DaoRepositoryImp;
import utility.enums.OpType;
import model.base.EntityInterface;
import model.event.EventType;

public class CrudImpDelete extends CrudI<EntityInterface, DaoRepositoryImp<EntityInterface>> {


    /**
      DELETE İşlemleri
      @param currentEntity     silinecek entity
      @param oldEntity         eski entity
      @param daoRepositoryImpl işlemi gerçekleştirecek dao sınıfı
      @return Silinen entity geri döndürülür
     */

    @Override
    public EntityInterface executeRequest(EntityInterface currentEntity, String oldEntity, DaoRepositoryImp<EntityInterface> daoRepositoryImpl) {
        return daoRepositoryImpl.delete(currentEntity);
    }

    @Override
    public void islemBasariliMsg(EntityInterface entity) {
        super.islemBasariliMsg(OpType.DELETE, entity);
    }


    @Override
    public void logKaydiAl(EntityInterface currentEntity, String oldEntity) {
        super.logKaydiAl(currentEntity, oldEntity, new EventType("DELETE"));
    }

}