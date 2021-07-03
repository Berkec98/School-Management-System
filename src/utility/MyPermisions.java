package utility;

import daolar.DaoRepositoryImp;
import model.user.Groups;
import model.user.Permissionlar;
import javafx.scene.Node;
import controller.LoginController;
import utility.enums.CommandTipi;

import java.util.List;


public class MyPermisions {

    /**
     * aktif kullanıcının ilgili bölümde ilgili yetkisinin olup olmadığına bakılır
     * eğer return herhangi bir kayıt döndürürse yetkisi var anlamındadır.
     *
     * @param activeZoneNames=      bölüm adı
     * @param crudFieldName=String türünden c,r,u,d harflerinden herhangi biri olmalıdır
     * @return true veya false  (yani kullanıcının ilgili yetkisi var veya yok)
     * NOT: veri tabanında zone (bölüm adı) veya CRUD data ları bulunamazsa false dönecektir yani yetkisi yok gibi işlem görecektir.
     */
    private boolean hasPermissionFor(String activeZoneNames, String crudFieldName) {
        //zonename olarak gelen string veritabanında aranıyor
        DaoRepositoryImp<Permissionlar> permissionsDao = new DaoRepositoryImp<>(Permissionlar.class);
        Groups activeUserGr = LoginController.getActiveUser().getGroups();
        List<Permissionlar> lst= permissionsDao.getAll(
                new MyPredicateCreator("grup", activeUserGr),
                new MyPredicateCreator("zoneNames", activeZoneNames, CommandTipi.Equal),
                new MyPredicateCreator(crudFieldName, "true", CommandTipi.Equal));
        return lst!=null?lst.size() > 0:false;
    }

    /**
     * bu fonksiyon aktif kullanıcının belitilen bölümde belirtilen işlemi yapmaya yetkisi var mı kontrol eder
     * eğer yetkili ise bu nodenin visible özelliğini true yapar
     * @param node görüntülenecek veya gizlenecek olan node
     * @param activeZoneNames kontrol edilecek bölüm adı
     * @param crudFieldName c,r,u,d terimlerinden herhangi biri
     */
    private void checkPermissionsForThisNode(Node node,String activeZoneNames, String crudFieldName){
       if(node==null) return;
        if(hasPermissionFor(activeZoneNames, crudFieldName)) {
           node.setVisible(true);
           node.managedProperty().set(true);
       } else{
           node.setVisible(false);
           node.managedProperty().set(false);
       }
    }



    public void checkPermissionsForThisNodes(Node[] nodes, String[] activeZoneNames, String crudFieldName){
        for (int i=0;i<nodes.length;i++) {
            checkPermissionsForThisNode(nodes[i], activeZoneNames[i],crudFieldName);
        }
    }
}
