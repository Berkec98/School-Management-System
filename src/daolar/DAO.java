package daolar;

import model.base.EntityInterface;
import factory.EntityFactory;
import utility.enums.WPATH;

public abstract class DAO {


    /**************************** WPATH  *******************************/
    private static WPATH activeModulo;

    protected WPATH getActiveModulo() {
        return this.activeModulo;
    }

    protected void setActiveModulo(WPATH activeModulo) { //private çünkü sadece changestate içinde set edilecek
        this.activeModulo = activeModulo;
    }
    /**
     *
     * STATE PATTERN Design kullanılmıştır
     **/
    protected void changeState(WPATH modul) {
        this.setActiveModulo(modul);
        this.setEntityInterface(getNewBlankEntity());
    }


    /******************** DAO REPOSITORY **********(Set Metoduna gerek yok)*********************/

    private DaoRepositoryImp<EntityInterface> daoRepositoryImp;

    protected DaoRepositoryImp getDaoRepositoryImp() {
        if (this.daoRepositoryImp == null)
            this.daoRepositoryImp = new DaoRepositoryImp(getNewBlankEntity().getClass());
        return daoRepositoryImp;
    }
    public DaoRepositoryImp getNewDao(EntityInterface entity){
           return new DaoRepositoryImp(entity.getClass());
    }


    /************************** ENTITY INTERFACE ****************************************/
    protected EntityInterface entityInterface;

    /**
     * GET
     **/
    public EntityInterface getEntityInterface() {
        if ((this.entityInterface == null) || (!this.entityInterface.getClass().toString().contains(getActiveModulo().getEntityName()))) {//string karşılaştırma
            this.setEntityInterface(getNewBlankEntity());
        }
        return entityInterface;
    }


    /**
     * SET
     **/
    public void setEntityInterface(EntityInterface entityInterface) {
        this.entityInterface = entityInterface;
    }

    /**
     * New Blank
     **/
     EntityInterface getNewBlankEntity() {
        return getEntityFactory().createEntity(getActiveModulo());
    }

    /******************** ENTITY FACTORY **********(Set Metodu yok)*********************/
    private EntityFactory entityFactory;

    public EntityFactory getEntityFactory() {
        return entityFactory == null ? this.entityFactory = new EntityFactory() : this.entityFactory;
    }
}
