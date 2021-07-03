package utility;

import daolar.DaoRepositoryImp;
import model.base.EntityInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import java.util.List;

public class ComboboxDoldur {

    private static <T extends EntityInterface> ObservableList<T> getObservableListFromDao(Class<T> clazz) {
        DaoRepositoryImp<T> dao = new DaoRepositoryImp<>(clazz);
        List<T> lst = dao.getAll();
        if (lst == null) return null;
         return FXCollections.observableArrayList(lst);
    }

    private static <T extends EntityInterface,N extends EntityInterface> ObservableList<T> getObservableListFromDao(Class<T> clazz, String colName, N colData) {
        DaoRepositoryImp<T> dao = new DaoRepositoryImp<>(clazz);
        List<T> lst = dao.getAll(new MyPredicateCreator(colName,colData));
        if (lst == null) return null;
        return FXCollections.observableArrayList(lst);
    }


    /**
     * belirtilen Classtaki tüm dataları veritabanından alarak comboboxa yükler
     * @param comboBox yüklemenin yapılacağı combobox
     * @param clazz datalar hangi entitiden çekilecekse o entiti adı
     * @param <T> dataların cinsi Object olmak zorunda EntityInterface olarak girilebilir
     */
    public static <T extends EntityInterface> void comboboxPopulateFromDao(ComboBox<T> comboBox, Class<T> clazz) {
        ObservableList<T> zObsList = getObservableListFromDao(clazz);
        comboBox.setItems(zObsList);
    }



    //bir comboboxın içeriğini diğer bir combobox ile sınırlama yani bir comboboxtaki veri değiştiğinde bu da değişir.
    public static <T extends EntityInterface, N extends EntityInterface> void comboboxPopulateFromDao(ComboBox<T> comboBox, Class<T> clazz,String colName,N colData) {
        ObservableList<T> zObsList = getObservableListFromDao(clazz,colName,colData);
        comboBox.setItems(zObsList);
    }


    /**
     *  T cinsinden verilen valueleri combobox listesine ekler
     * @param comboBox yüklemenin yapılacağı combobox
     * @param datalar yüklenecek datalar ör: Pazartesi,Salı,Çarşamba ....
     * @param <T> dataların cinsi Object olmak zorunda örneğin: String,Integer vs
     */

    @SafeVarargs
    public static <T> void comboboxPopulate(ComboBox<T> comboBox, T... datalar) {
        if (datalar == null) return;
        ObservableList<T> observableList = FXCollections.observableArrayList(datalar);
        comboBox.getItems().setAll(observableList);
    }
}
