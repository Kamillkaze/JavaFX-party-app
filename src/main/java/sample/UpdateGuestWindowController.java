package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import sample.data.Guest;
import sample.data.RuntimeGuestsData;

import java.util.List;
import java.util.stream.Collectors;

public class UpdateGuestWindowController {

    @FXML
    private TextField phoneToUpdateTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField foodTextField;

    @FXML
    private TextField drinkTextField;

    @FXML
    private TextField phoneTextField;

    @FXML
    private void clearFields() {
        nameTextField.clear();
        foodTextField.clear();
        drinkTextField.clear();
        phoneTextField.clear();

        phoneToUpdateTextField.clear();
        phoneToUpdateTextField.setDisable(false);
    }

    @FXML
    private void initialize() {
        nameTextField.setDisable(true);
        foodTextField.setDisable(true);
        drinkTextField.setDisable(true);
        phoneTextField.setDisable(true);
    }

    @FXML
    private void findGuestToUpdate() {
        Guest guest = getOldGuest();

        if (guest != null) {
            phoneToUpdateTextField.setDisable(true);

            nameTextField.setDisable(false);
            foodTextField.setDisable(false);
            drinkTextField.setDisable(false);
            phoneTextField.setDisable(false);

            nameTextField.setText(guest.getName());
            foodTextField.setText(guest.getFood());
            drinkTextField.setText(guest.getDrink());
            phoneTextField.setText(guest.getPhone());
        } else {
            initialize();
            proceedNotFound();
        }
    }

    @FXML
    public Guest getOldGuest() {
        String phoneToUpdate = phoneToUpdateTextField.getText().trim();

        return RuntimeGuestsData.getInstance().findGuest(phoneToUpdate);
    }

    @FXML
    private void proceedNotFound() {
        nameTextField.setText("Not found");
        foodTextField.setText("Not found");
        drinkTextField.setText("Not found");
        phoneTextField.setText("Not found");
    }

    @FXML
    public Guest processChanges() {
        boolean shouldSave = true;

        String name = nameTextField.getText().trim();
        String food = foodTextField.getText().trim();
        String drink = drinkTextField.getText().trim();
        String phone = phoneTextField.getText().trim();

        Guest updateGuest;

        if (name.isEmpty() || food.isEmpty() || drink.isEmpty() || phone.isEmpty()) {
            shouldSave = false;

        } else {
            for (int i = 0; i < phone.length(); i++) {
                char digit = phone.charAt(i);

                switch (digit) {
                    case '1', '2', '3', '4', '5', '6', '7', '8', '9', '0':
                        continue;
                    default:
                        shouldSave = false;
                        break;
                }
            }
        }

        if (shouldSave) {
            updateGuest = new Guest(name, food, drink, phone);
        } else {
            updateGuest = null;
        }

        return updateGuest;
    }
}
