package entities.user;

import model.user.Groups;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;



public class UserTestSAVES {
    EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("eSistemPU");
    EntityManager entityManager = emFactory.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();

    @Test
    public void saveGrup() {
        Groups grup1 = new Groups("Admin");
        Groups grup2 = new Groups("Yetkili");
        Groups grup3 = new Groups("NormalNormalNormalNormal");
        Groups grup4 = new Groups(null);
        Groups grup5 = new Groups("Admin");
        System.out.println(grup1);System.out.println(grup2);
        System.out.println(grup3);
        transaction.begin();
        entityManager.persist(grup1);
        entityManager.persist(grup2);
        if(grup3.getGrupName().length()>20){
           // System.out.println();
        }
        entityManager.persist(grup3);
        entityManager.persist(grup4);
        entityManager.persist(grup5);

        transaction.commit();
    }

    @Test
    public void saveUser() {
   /*     GrupName grp=entityManager.find(GrupName.class,1);
       // System.out.println("Aranan grup kaydı bulundu:"+grp);
        User user=new User("BT","1123",grp);
        transaction.begin();
        entityManager.persist(user);
        transaction.commit();*/
    }
}
