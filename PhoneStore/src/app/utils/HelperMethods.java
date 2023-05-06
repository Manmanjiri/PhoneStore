package app.utils;

import javafx.scene.control.Alert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
     * This class provides various helper methods which will me used
     * throughout the entire application.
     * @author     Manjiri J
    */
public class HelperMethods {

    /**
     * This method is used to create an alertBox
     */
    public static void alertBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(String.valueOf(infoMessage));
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

}
