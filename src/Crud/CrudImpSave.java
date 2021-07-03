package Crud;

import daolar.DaoRepositoryImp;
import utility.enums.OpType;
import model.base.EntityInterface;
import model.event.EventType;

public class CrudImpSave extends CrudI<EntityInterface, DaoRepositoryImp<EntityInterface>> {

    /**
      SAVE İşlemleri
      @param currentEntity     Kaydedilecek entity
      @param oldEntity         eski entity - ama anlamsız çünkü burada null durumda eskisi yok
      @param daoRepositoryImpl işlemi gerçekleştirecek dao sınıfı
      @return Kaydedilen entity geri döndürülür
     */

    @Override
    public EntityInterface executeRequest(EntityInterface currentEntity, String oldEntity, DaoRepositoryImp<EntityInterface> daoRepositoryImpl) {
        return daoRepositoryImpl.save(currentEntity);
    }

    @Override
    public void islemBasariliMsg(EntityInterface entity) {
        super.islemBasariliMsg(OpType.SAVE, entity);
    }

    @Override
    public void logKaydiAl(EntityInterface currentEntity, String oldEntity) {
        super.logKaydiAl(currentEntity, oldEntity, new EventType("SAVE"));
    }
}

