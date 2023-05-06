package app;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Datasource;

public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/user/main-dashboard.fxml"));
        primaryStage.setTitle("Mobile Store");
        primaryStage.getIcons().add(new Image("/view/resources/img/brand/fav.png"));
        primaryStage.setScene(new Scene(root, 1280, 800));
        primaryStage.show();

    }

    /**
     * Manjiri J
     * This method initializes the application and opens the connection to the database.
     * @throws Exception      If an input or exception occurred.
     */
    @Override
    public void init() throws Exception {
        super.init();

    }

    /**
     * Manjiri J
     * This method stops the application and closes the connection to the database.
     * @throws Exception      If an input or exception occurred.
     */
    @Override
    public void stop() throws Exception {
        super.stop();

    }


}
/**
 * The mobile software testing team has 10 mobile phones that it needs
 * to share for testing purposes
 * - Samsung Galaxy S9
 * - 2x Samsung Galaxy S8
 * - Motorola Nexus 6
 * - Oneplus 9
 * - Apple iPhone 13
 * - Apple iPhone 12
 * - Apple iPhone 11
 * - iPhone X
 * - Nokia 3310
 * • Please create an application that allows a phone to be booked /
 * returned
 * • The following information should also be available for each phone
 * - Availability (Yes / No)
 * - When it was booked
 * - Who booked the phone
 */