package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import sample.data.Guest;
import sample.data.RuntimeGuestsData;

import java.io.IOException;
import java.util.Optional;

public class MainwindowController {

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private ListView<Guest> guestsListView;

    @FXML
    private Label foodFromData;

    @FXML
    private Label drinkFromData;

    @FXML
    private Label phoneFromData;

    @FXML
    private ContextMenu listContextMenu;

    @FXML
    private void initialize() {

        listContextMenu = new ContextMenu();
        MenuItem deleteItem = new MenuItem();
        deleteItem.setText("Delete");
        deleteItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showDeleteGuestDialog();
            }
        });
        listContextMenu.getItems().add(deleteItem);

        guestsListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Guest>() {
            @Override
            public void changed(ObservableValue<? extends Guest> observableValue, Guest oldValue, Guest newValue) {
                if (newValue != null) {
                    foodFromData.setText(newValue.getFood());
                    drinkFromData.setText(newValue.getDrink());
                    phoneFromData.setText(newValue.getPhone());
                } else {
                    foodFromData.setText("");
                    drinkFromData.setText("");
                    phoneFromData.setText("");
                }
            }
        });

        guestsListView.setItems(RuntimeGuestsData.getInstance().getGuests());
        guestsListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        guestsListView.getSelectionModel().selectFirst();

        guestsListView.setCellFactory(new Callback<ListView<Guest>, ListCell<Guest>>() {
            @Override
            public ListCell<Guest> call(ListView<Guest> guestListView) {
                ListCell<Guest> cell = new ListCell<>() {
                    @Override
                    protected void updateItem(Guest guest, boolean empty) {
                        super.updateItem(guest, empty);
                        if (empty) {
                            setText(null);
                        } else {
                            setText(guest.getName());
                        }
                    }
                };
                cell.emptyProperty().addListener(
                        (obs, wasEmpty, isNowEmpty) -> {
                            if (isNowEmpty) {
                                cell.setContextMenu(null);
                            } else {
                                cell.setContextMenu(listContextMenu);
                            }
                        }
                );
                return cell;
            }
        });

    }

    @FXML
    private void showFoodAndDrinkDialog() {
        Dialog<ButtonType> dialog = getDefaultDialog("Food and drinks dialog", "List of food and drinks:");

        getDefaultFXMLLoader("foodanddrinkwindow.fxml", dialog);

        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);

        dialog.showAndWait();
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
            Guest newGuest = controller.processChanges();

            if (newGuest != null) {
                RuntimeGuestsData.getInstance().addGuest(newGuest);
                guestsListView.getSelectionModel().select(newGuest);
            } else {
                showNotAddedDialog();
            }
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

    @FXML
    private void showDeleteGuestDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("If you confirm this action guest will be deleted permanently");

        Guest selectedGuest = guestsListView.getSelectionModel().getSelectedItem();

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            RuntimeGuestsData.getInstance().deleteGuest(selectedGuest);
        }
    }

    @FXML
    private void showFindGuestDialog() {
        Dialog<ButtonType> dialog = getDefaultDialog("Find Guest",
                "Type phone number below to find a guest.");
        getDefaultFXMLLoader("findwindow.fxml", dialog);

        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);

        dialog.showAndWait();
    }

    @FXML
    private void showUpdateGuestDialog() {
        Dialog<ButtonType> dialog = getDefaultDialog("Update guest dialog",
                "Fill the text fields to update guest info");
        FXMLLoader fxmlLoader = getDefaultFXMLLoader("updateguestwindow.fxml", dialog);

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();

        if (result.isPresent() && result.get().equals(ButtonType.OK)) {

            UpdateGuestWindowController controller = fxmlLoader.getController();
            Guest updated = controller.processChanges();
            Guest old = controller.getOldGuest();

            if (updated != null) {
                RuntimeGuestsData.getInstance().updateGuest(old, updated);
            } else {
                showNotAddedDialog();
            }
        }
    }

    @FXML
    private void handleDelPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.DELETE)) {
            showDeleteGuestDialog();
        }
    }
}
