package utility.listeners;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import java.awt.*;


public class MyDigitAndDotAllowed implements EventHandler<KeyEvent> {

    public void handle(KeyEvent t) {

        char basilanTus = t.getCharacter().charAt(0);
        if ((!Character.isDigit(basilanTus)) && (basilanTus != '.')) {
            t.consume();
            if(basilanTus!=13&&basilanTus!=8)
            Toolkit.getDefaultToolkit().beep();
        }
    }
}

