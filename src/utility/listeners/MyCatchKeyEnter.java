package utility.listeners;

import daolar.CommonButtons;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

//bu sınıf enter tuşu ile nodeler arasında gezmeyi sağlar
public class MyCatchKeyEnter implements EventHandler<KeyEvent> {

    private final Object clazz;
    private Node[] focusOrder;

    public MyCatchKeyEnter(Object clazz, Node[] focusOrder) {
        this.clazz = clazz;
        this.focusOrder = focusOrder;
    }



    @Override
    public void handle(KeyEvent event) {
        if (event.getCode() == KeyCode.F2 && (!((CommonButtons) clazz).getBtnSave().isDisabled())) {                 //Kaydet amaçlı kullanılacak hazırlamayı unutma
            ((TextField)event.getSource()).setText(((TextField)event.getSource()).getText().trim().toUpperCase());
            if (clazz instanceof CommonButtons)
                ((CommonButtons) clazz).save();
        }



        if (event.getCode() == KeyCode.ENTER) {          //enter tuşuna basıldıysa bir sonraki node ye focuslanacak
            int focusLength = focusOrder.length - 1;
            for (int j = 0; j <= focusLength; j++) {
                if (focusOrder[j].isFocused()) {
                    if (j == focusLength) j = -1;
                    focusOrder[j + 1].requestFocus();
                    return;
                }
            }
        }
    }
}