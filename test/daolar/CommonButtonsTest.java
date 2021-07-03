package daolar;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.junit.Test;

public class CommonButtonsTest {
    private void notification(){
        Notifications.create()
                .darkStyle()
                .title("Title")
                .text("deneme")
                .graphic(new Rectangle(600, 400, Color.RED)) // sets node to display
                .hideAfter(Duration.seconds(3))
                .show();
    }
    @Test
    public void toMoney() {
        notification();
    }
}