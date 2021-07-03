package Crud;

/* TEMPLATE PATTERN DESİGN */

import utility.enums.OpType;
import model.base.CommonEntity;
import model.event.EventType;
import utility.mesajlar.Notify;



public abstract class CrudI<T, D> {


    protected abstract T executeRequest(final T currentEntity, final String oldEntity, final D daoRepositoryImpl);
    protected abstract void islemBasariliMsg(T entity);
    protected abstract void logKaydiAl(T currentEntity, String oldEntity);


    public T islemiGerceklestir(final T currentEntity, final String oldEntity, final D daoRepositoryImpl) {
        final T islenenEntity = (T) executeRequest(currentEntity, oldEntity, daoRepositoryImpl);
        if (islenenEntity != null) {
            islemBasariliMsg(islenenEntity);
            logKaydiAl(islenenEntity, oldEntity);

        }//ELSE durumunda hata msj vermiyor çünkü hata olştuğu yerde kullanıcı mesajla bilgilendiriliyor
        return islenenEntity;
    }


    //return durumu yok çünkü log alınırken hata oluşursa kullanıcı zaten bilgilendiriliyor. başarılı durumu ise kullanıcıdan izole
    void logKaydiAl(final T currentEntity, final String oldEntity, final EventType eventType) {
        new LogsI<>().saveLog(currentEntity, oldEntity, eventType);
    }

    protected void islemBasariliMsg(OpType opType, T entity) {
        final String entityToString=entity instanceof CommonEntity?((CommonEntity)entity).toString3(): "\n\n"+entity.toString();
        new Notify().showInfoNotify(opType.getStrType().toUpperCase() + " Successful", entityToString+"\n\n\n"+ opType.getStrType() +" Process Performed Successfully.",6);
    }
}
