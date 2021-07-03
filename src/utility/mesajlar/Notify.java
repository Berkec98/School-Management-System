package utility.mesajlar;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import java.util.ArrayList;
import java.util.List;

public class Notify {

    private static List<String> notifyList = new ArrayList<>();

    private Notifications notificate(String title, String text, int beklemeSuresi) {
        return Notifications.create()
                .darkStyle()
                .title(title)
                .text(text)
                .graphic(new Rectangle(200, 200, Color.BLUE)) // sets node to display
                .hideAfter(Duration.seconds(beklemeSuresi));
    }


    public void showInfoNotify(String title, String text, int beklemeSuresi) {
        notificate(title, text, beklemeSuresi).showInformation();
        addNotifyList(title + ":" + text);
    }


    public void showErrorNotify(String title, String text) {
        notificate(title, text, 15).showError();
        addNotifyList(title + ":" + text);
    }

    public static List<String> getNotifyList() {
        return notifyList;
    }

    public static void addNotifyList(String notify) {
        notifyList.add(notify);
    }
}
