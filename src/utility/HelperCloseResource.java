package utility;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HelperCloseResource {

    private HelperCloseResource() {
    }

/*    public static void helperCloseImg(Node node) {
        ImageView img=null;
        try {
             img = (ImageView) node;
        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage window = (Stage) img.getScene().getWindow();
        window.close();
    }*/

    public static void helperCloseBtn(Node node){
        Button btn=null;
        try {
            btn = (Button) node;
        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage window = (Stage) btn.getScene().getWindow();
        window.close();
    }
    public static  void helperClosePlatform(){
        System.exit(0);
    }
}
