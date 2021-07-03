package utility.listView;

import model.base.EntityInterface;
import javafx.scene.control.ListView;
import utility.enums.OpType;

public class List_ViewImpl {

    public void guncelle(ListView listView, EntityInterface entityInterface, OpType opType) {
        switch (opType) {
            case SAVE:
                listView_AddCurrentEntity(listView, entityInterface);
                break;
            case DELETE:
                listView_RemoveCurrent(listView);
                break;
            case UPDATE:
                listView_RemoveCurrent(listView);
                listView_AddCurrentEntity(listView, entityInterface);
                break;
        }
        listView_Refresh(listView);
    }

    private void listView_RemoveCurrent(ListView listView) {
        listView.getItems().remove(listView_GetCurrent(listView));
    }

    private void listView_AddCurrentEntity(ListView listView, EntityInterface entityInterface) {
        listView.getItems().add(0,(Object) entityInterface);
    }

    private void listView_Refresh(ListView listView) {
        listView.refresh();
    }

    protected Object listView_GetCurrent(ListView listView) {
        return listView.getSelectionModel().getSelectedItem();
    }
}
