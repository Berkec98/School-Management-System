package utility;

import utility.error.ErrorMessage;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import controller.SelectStudent;
import controller.board.UIBarController;
import utility.enums.WPATH;

import java.awt.*;


public class MyStagesShower {
    public static UIBarController uiBarController;
    public static Stage primaryStage;
    private double xOffset, yOffset;
    Stage stageUIBar;



    private Stage showFxml(final WPATH wpath) {
        final Stage stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(wpath.getFxmlPath() + wpath.getFxmlFileName() + ".fxml"));
            Parent root = loader.load();
            if (primaryStage==null) {
                primaryStage=new Stage();
                primaryStage.setOpacity(0.0);
                primaryStage.initStyle(StageStyle.UTILITY);
                primaryStage.show();
            }
            stage.initOwner(primaryStage);
            Scene scene = new Scene(root);
            stage.setScene(scene);
         //   stage.getTitle().
            setXY(root, stage);
            if (wpath == WPATH.uiBar) {
                scene.setFill(Color.TRANSPARENT);
                uiBarController = loader.getController();
            } else {
                stage.initModality(Modality.APPLICATION_MODAL);
            }
            //primaryStage.close();
        } catch (Exception e) {
            ErrorMessage.IO_FORM_LOAD.printErrorMessages("MyStagesShower class showFxmlAsDialog method:", e);
            Platform.exit();
        }
        return stage;
    }




    /**
     * Özellikleri:
     * pencere başlığı ve kenarlıkları yok,  transparan
     * başlangıç pozisyonu (0,0)
     * ve simgesi system traye ekleniyor
     * kullanan FXML ler örnek: Sadece UIBar
     */
    public void showFXML_UIBar(final WPATH wpath) {
        final Stage stage = showFxml(wpath);
        stage.sizeToScene();   //Set the width and height of this Window to match the size of the content of this Window's Scene
        stage.setX(0);
        stage.setY(0);
        doTransparent(stage);
        stage.show();
        javax.swing.SwingUtilities.invokeLater(this::addAppToTray);
        stageUIBar = stage;
    }




    /**
     * Özellikleri:
     * pencere başlığı ve kenarlıkları yok,  transparan
     * başlangıç pozisyonu ekranın ortası
     * kullanan FXML ler örnek: sadece login ekranı
     */
    public void showFXML_CenterAndTransparent(final WPATH wpath) {
        final Stage stage = showFxml(wpath);
        doTransparent(stage);
        stage.centerOnScreen();
        stage.showAndWait();
    }




    /**
     * Özellikleri:
     * pencere başlığı ve kenarlıkları ve iconu mmevcut
     * başlangıç pozisyonu ekranın tamamen kaplamış
     */
    public void showFXML_Maximized(final WPATH wpath) {
        final Stage stage = showFxml(wpath);
        stage.setMaximized(true);
        setLogoAndTitle(stage, wpath);
        stage.showAndWait();
    }




    /**
     * Özellikleri:
     * pencere başlığı ve kenarlıkları  var
     * sadece kapatma iconu mevcut
     * pencere yeniden boyutlandırma kapalı
     * pencere boyutunda değişiklik yapılmıyor olduğu gibi ekranın ortasında gösteriliyor
     * <p>
     * kullanan FXML ler örnek: Setting
     */
    public void showFXML_NoResizable(final WPATH wpath) {
        final Stage stage = showFxml(wpath);
        setLogoAndTitle(stage, wpath);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.showAndWait();
    }




    /**
     * Özellikleri:
     * pencere başlığı ve kenarlıkları  var
     * sadece kapatma iconu mevcut
     * pencere yeniden boyutlandırılmakta height olarak her ekrana reponsive tam sığdırılmakta
     */

    double responsiveOrani=-1;

    public int getResponsiveOrani() {
        final double screenHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        final double responsiveOrani=screenHeight;
        //if(responsiveOrani==-1)
        return 0;
    }





    public void showFXML_FitHeight(final WPATH wpath) {
        final Stage stage = showFxml(wpath);
        setLogoAndTitle(stage, wpath);
        final double screenHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        System.out.println(screenHeight);
        stage.setHeight(screenHeight - 100);
        stage.centerOnScreen();
        stage.showAndWait();
    }






    private void setXY(final Parent root, final Stage stage) {
        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
    }




    public void showFXML_ActiveGerekiyor(final WPATH wpath, final boolean isMaximized) {
        if (SelectStudent.getActiveStudent() == null) {
            showFXML_FitHeight(WPATH.select);
        }
        if (SelectStudent.getActiveStudent() == null) return;
        if (isMaximized)
            showFXML_Maximized(wpath);
        else
            showFXML_FitHeight(wpath);
    }




    private void setLogoAndTitle(final Stage stage, final WPATH wpath) {
        stage.getIcons().add(new javafx.scene.image.Image("/resources/icons/logo.png"));
        stage.setTitle(wpath.getDescription());
    }



    private void doTransparent(Stage stage){
       stage.initStyle(StageStyle.TRANSPARENT);
    }




    public void addAppToTray() {
        if (!SystemTray.isSupported()) {
            System.out.println("System tray is not supported");
            return;
        }

        //Değişkenler
        final SystemTray systemTray = SystemTray.getSystemTray();
        final java.awt.Image image = Toolkit.getDefaultToolkit().getImage("src/resources/icons/logo.png");
        final PopupMenu trayPopupMenu = new PopupMenu();
        final MenuItem cikis = new MenuItem("Exiting the Program");
        cikis.addActionListener(e -> System.exit(0));//e -> System.exit(0)
        trayPopupMenu.add(cikis);
        final TrayIcon trayIcon = new TrayIcon(image, "Program Ver 1.1", trayPopupMenu);
        trayIcon.setImageAutoSize(true);

        try {
            systemTray.add(trayIcon);
        } catch (AWTException awtException) {
            awtException.printStackTrace();
        }
    }
}
