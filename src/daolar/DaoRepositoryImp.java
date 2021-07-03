package daolar;

import model.base.EntityInterface;
import utility.enums.OpType;
import utility.error.ErrorMessage;
import utility.MyPredicateCreator;

import javax.persistence.EntityTransaction;
import javax.persistence.RollbackException;
import java.util.List;

public class DaoRepositoryImp<T extends EntityInterface> extends BaseDAOImp {

    private Class<T> t;
    private EntityTransaction transaction;
    public DaoRepositoryImp(Class<T> t) {
        this.t = t;
    }


    private EntityTransaction getTransaction() {
        if (this.transaction == null)
            this.transaction = this.getEntityManager().getTransaction();
        return transaction;
    }


    public T save(final T entityInterface) {
        this.transaction_Begin();
        try {
            if (getEntityManager().contains(entityInterface)) {
                this.getEntityManager().merge(entityInterface);
            } else {
                this.getEntityManager().persist(entityInterface);
            }
            this.getTransaction().commit();
            return entityInterface;
        } catch (RuntimeException e) {
            showAlertCrudError(e, OpType.SAVE);
            return null;
        }
    }




    public T update(final T entityInterface) {
        try {
            this.transaction_Begin();
            this.getEntityManager().merge(entityInterface);
            this.getEntityManager().flush();
            this.getTransaction().commit();
            return entityInterface;
        } catch (RuntimeException e) {
            showAlertCrudError(e, OpType.UPDATE);
            return null;
        }
    }



    public T delete(final T entity) {
        T silinen;
        try {
            this.transaction_Begin();
            silinen = getEntityManager().contains(entity) ? entity : getEntityManager().merge(entity);
            getEntityManager().remove(silinen);
            this.getTransaction().commit();
            return silinen;
        } catch (RuntimeException e) {
            showAlertIliskiliDosyaSilmeHatasi(e);
            return null;
        }
    }


    public List<T> getAll(final MyPredicateCreator... parameters) {
        return new DaoFindHelper<>(t).getAll(parameters);
    }


    public <N extends Number> N getMax(Class<N> aggregateClass, final MyPredicateCreator... parameters) {
        return new DaoFindHelper<>(t).getAggregateResult(aggregateClass, parameters);
    }


    public T getSingleStringResult(final MyPredicateCreator... parameters) {
        return new DaoFindHelper<>(t).getSingleStringResult(parameters);
    }


    private void transaction_Begin() {
        if (!this.getTransaction().isActive())
            this.getTransaction().begin();
    }

    private void doRollback() {
        try {
            this.transaction_Begin();
            this.getTransaction().rollback();
        } catch (RollbackException ex) {
            ErrorMessage.ROLLBACKEXCEPTION.printErrorMessages("doRollback", ex);
        }
    }



    private void showAlertCrudError(RuntimeException e, OpType opType) {
        ErrorMessage.RUNTIMEEXCEPTION.printErrorMessages("DaoRepositoryImp class" + opType.getStrType() + " method \n\ndetails are below", e);
        doRollback();
    }

    private void showAlertIliskiliDosyaSilmeHatasi(RuntimeException e) {
        ErrorMessage.RELATED.printErrorMessages("\n\ndetails are below: \n", e);
        doRollback();
    }

}
