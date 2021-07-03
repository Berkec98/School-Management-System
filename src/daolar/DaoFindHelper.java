package daolar;

import model.base.EntityInterface;
import utility.MyPredicateCreator;
import utility.enums.CommandTipi;
import utility.mesajlar.MyAlert;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;


public class DaoFindHelper<T extends EntityInterface> extends BaseDAOImp {
    private final CriteriaBuilder cb;
    private final CriteriaQuery<T> cq;
    private final Root<T> root;
    private final List<Predicate> pr1;


    public DaoFindHelper(Class<T> t) {
        this.cb = getEntityManager().getCriteriaBuilder();
        this.cq = cb.createQuery(t);
        this.root = cq.from(t);
        this.pr1 = new ArrayList<>();
    }


    private void pr1_ListesiniOlustur(final MyPredicateCreator... parameters) {
        final List<MyPredicateCreator> gecerliParamList = gecersizParamAyikla(parameters);
        for (MyPredicateCreator gp : gecerliParamList) {
            final Predicate predicate = birPredicateOlustur(gp);
            if (predicate != null) pr1.add(predicate);
        }
    }


    /*
     * Bu fonksiyonun sadece unique kolonlar için aramada kullanılması ve single sonuç üretmesi garantilenmelidir
     */
    public T getSingleStringResult(final MyPredicateCreator... parameters) {
        try {
            pr1_ListesiniOlustur(parameters);
            cq.select(root);
            cq.where(pr1.toArray(new Predicate[]{}));
            cq.orderBy(cb.desc(root));
            return getEntityManager().createQuery(cq).getSingleResult();
        } catch (Exception e) {
           // e.printStackTrace();
            return null;
        }
    }



    public List<T> getAll(final MyPredicateCreator... parameters) {
        try {
            pr1_ListesiniOlustur(parameters);
            cq.select(root);
            cq.where(pr1.toArray(new Predicate[]{}));
            cq.orderBy(cb.desc(root));
            return getEntityManager().createQuery(cq).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    private MyPredicateCreator detectAgregatePredicate(final MyPredicateCreator... parameters) {
        for (MyPredicateCreator p: parameters) {
           if(p.getCommand()== CommandTipi.Max) return p;
        }
        new MyAlert().showErrorAlert("that's not an aggregate predicate.","Notify to the system administrator.");
        return null;
    }


    public <N extends Number> N getAggregateResult(Class<N> n, final MyPredicateCreator... parameters) {
        try {
            MyPredicateCreator p = detectAgregatePredicate(parameters);
            if(p==null) return null;
            CriteriaQuery<N> criteria = cb.createQuery(n);
            pr1_ListesiniOlustur(parameters);
            Expression<N> accessTime = root.get(p.getColFirst());
            criteria.select(cb.max(accessTime));
            criteria.where(pr1.toArray(new Predicate[]{}));
            return getEntityManager().createQuery(criteria).getSingleResult();
        } catch (Exception e) {
            new MyAlert().showErrorAlert("ERROR DETAIL:"+e.getMessage(),"ERROR Occured");
            //e.printStackTrace();
            return null;
        }
    }



    private Predicate birPredicateOlustur(MyPredicateCreator gp) {
        switch (gp.getCommand()) {
            case Beetwen:                                                                   //Sadece LONG GEREKLİLER:FieldName, beforeLong, afterLong
                return cb.between(root.get(gp.getColFirst()), gp.getBeforeLong(), gp.getAfterLong());
            case Like:                                                                      // sadece String  GEREKLİLER:FieldName, ilkDeger
                return cb.like(cb.upper(root.get(gp.getColFirst())).as(String.class), "%" + gp.getValue() + "%");
            case Equal:                                                                     // entity ve string  GEREKLİLER: FieldName, ilkDeger/entity
                return cb.equal(root.get(gp.getColFirst()), gp.getEntity() == null ? gp.getValue() : gp.getEntity());
            case NotEqual:                                                                     // entity ve string  GEREKLİLER: FieldName, ilkDeger/entity
                return cb.notEqual(root.get(gp.getColFirst()), gp.getEntity() == null ? gp.getValue() : gp.getEntity());
            case NotIn:                                                                     // GEREKLİLER: FieldName, notin listesi
                return cb.not(root.get(gp.getColFirst()).in(gp.getListForNotIn()));
            case GreaterThan:                                                               //sadece LONG GEREKLİLER: FieldName ve
                return cb.greaterThan(root.get(gp.getColFirst()), gp.getBeforeLong());
            case LessThan:                                                               //sadece LONG GEREKLİLER: FieldName ve
                return cb.lessThan(root.get(gp.getColFirst()), gp.getBeforeLong());
            case IkiStrKolonTopla: //iki string kolon toplamı için      //AD SOYAD aramasında kullanılsın diye yaptım
                Expression<String> commercialExpression =
                        cb.concat(cb.concat(root.<String>get(gp.getColFirst()), " "), root.<String>get(gp.getColSecond()));
                return cb.like(cb.upper(commercialExpression), "%" + gp.getValue() + "%");
            case Relation:
                Join root2 = root.join(gp.getColFirst(), JoinType.LEFT);
                if (gp.getColThird() != null) {
                    Join root3 = root2.join(gp.getColSecond(), JoinType.LEFT);
                    return cb.like(cb.upper(root3.get(gp.getColThird())).as(String.class), "%" + gp.getValue() + "%");
                } else
                    return cb.like(cb.upper(root2.get(gp.getColSecond())).as(String.class), "%" + gp.getValue() + "%");
        }
        return null;
    }


    List<MyPredicateCreator> gecersizParamAyikla(final MyPredicateCreator... parameters) {
        List<MyPredicateCreator> gecerliParametreListesi = new ArrayList<>();
        final String mesaj = "DAOREPOSITORY->gecersizParemetreleriAyıkla metodu Geçersiz Parametre tespit edildi kod:";
        if (parameters == null) {
            System.out.println(mesaj + " 0");
            return gecerliParametreListesi;
        }
        for (MyPredicateCreator p : parameters) {
            if (p == null || !p.isBuPredicateGecerli() || p.getColFirst() == null || p.getColFirst().isEmpty()) {
                System.out.println(mesaj);
                continue;
            }
            gecerliParametreListesi.add(p);
        }
        return gecerliParametreListesi;
    }

}
