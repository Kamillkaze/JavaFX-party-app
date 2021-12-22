package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import sample.data.Guest;
import sample.data.RuntimeGuestsData;

public class FindwindowController {

    @FXML
    private TextField findByNumberField;

    @FXML
    private TextField foundNameTextField;

    @FXML
    private TextField foundFoodTextField;

    @FXML
    private TextField foundDrinkTextField;

    @FXML
    private TextField foundPhoneTextField;


    @FXML
    private void findGuest() {
        String phoneNumber = findByNumberField.getText().trim();
        findByNumberField.clear();

        Guest guest = RuntimeGuestsData.getInstance().findGuest(phoneNumber);

        if (guest != null) {
            foundNameTextField.setText(guest.getName());
            foundFoodTextField.setText(guest.getFood());
            foundDrinkTextField.setText(guest.getDrink());
            foundPhoneTextField.setText(guest.getPhone());
        } else {
            proceedNotFound();
        }
    }

    @FXML
    private void proceedNotFound() {
        foundNameTextField.setText("Not found");
        foundFoodTextField.setText("Not found");
        foundDrinkTextField.setText("Not found");
        foundPhoneTextField.setText("Not found");
    }

    @FXML
    private void clearFields() {
        foundNameTextField.clear();
        foundFoodTextField.clear();
        foundDrinkTextField.clear();
        foundPhoneTextField.clear();
    }

    @FXML
    private void handleEnterPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            findGuest();
        }
    }
}
