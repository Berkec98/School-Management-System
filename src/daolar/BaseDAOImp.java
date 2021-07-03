package daolar;

import utility.mesajlar.MyAlert;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BaseDAOImp {

    private EntityManager entityManager;


    public EntityManager getEntityManager() {
        if ((this.entityManager == null)) {
            EntityManagerFactory emFactory = null;
            try {
                emFactory = Persistence.createEntityManagerFactory("eSistemPU");
            } catch (Exception e) {
                new MyAlert().showErrorAlert("(ESistemDB) Database does not exist"+e.getMessage(),"ERROR");
               // e.printStackTrace();
            }
            this.entityManager = emFactory != null ? emFactory.createEntityManager() : null;
        }
        return entityManager;
    }
}
