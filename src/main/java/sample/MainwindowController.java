package com.manhpd;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Optional;

public class MainwindowController {

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private ListView guestsListView;

    @FXML
    private Label foodFromData;

    @FXML
    private Label drinkFromData;

    @FXML
    private Label phoneFromData;

    @FXML
    private void initialize() {

    }

    @FXML
    private void showNewGuestDialog() {

        Dialog<ButtonType> dialog = getDefaultDialog("New persons dialogue",
                "Add a new person");

        FXMLLoader fxmlLoader = getDefaultFXMLLoader("newguestwindow.fxml", dialog);

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();

        if (result.isPresent() && result.get().equals(ButtonType.OK)) {
            NewguestwindowController controller = fxmlLoader.getController();
//            Guest newGuest = controller.processChanges();

//            if (newGuest != null) {
//                RuntimeGuestsData.getInstance().addGuest(newGuest);
//            }
        } else {
//            showNotAddedDialog();
        }
    }

    @FXML
    private Dialog<ButtonType> getDefaultDialog(String title, String headerText) {
        Dialog<ButtonType> dialog = new Dialog<>();

        dialog.setTitle(title);
        dialog.setHeaderText(headerText);
        dialog.initOwner(mainBorderPane.getScene().getWindow());

        return dialog;
    }

    private FXMLLoader getDefaultFXMLLoader(String fxml, Dialog<ButtonType> dialog) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(fxml));

        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fxmlLoader;
    }

    @FXML
    private void showNotAddedDialog() {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Error");
        alert.setHeaderText("Action didn't proceed:");
        alert.setContentText("Text fields cannot be empty." +
                "\nPhone number must contain only numbers, without spaces." +
                "\nPhone number must be different than other guests numbers.");
        alert.showAndWait();
    }
}
