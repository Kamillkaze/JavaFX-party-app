package com.manhpd;

//import com.manhpd.data.Guest;
//import com.manhpd.data.RuntimeGuestsData;
//import javafx.fxml.FXML;
//import javafx.scene.control.TextField;
//
//import java.util.List;
//import java.util.stream.Collectors;

public class NewguestwindowController {
//    @FXML
//    private TextField nameTextField;
//
//    @FXML
//    private TextField foodTextField;
//
//    @FXML
//    private TextField drinkTextField;
//
//    @FXML
//    private TextField phoneTextField;
//
//    @FXML
//    public Guest processChanges() {
//        boolean shouldSave = true;
//
//        String name = nameTextField.getText().trim();
//        String food = foodTextField.getText().trim();
//        String drink = drinkTextField.getText().trim();
//        String phone = phoneTextField.getText().trim();
//
//        Guest newGuest;
//
//        if (name.isEmpty() || food.isEmpty() || drink.isEmpty() || phone.isEmpty()) {
//            shouldSave = false;
//
//        } else {
//            for (int i = 0; i < phone.length(); i++) {
//                char digit = phone.charAt(i);
//
//                switch (digit) {
//                    case '1', '2', '3', '4', '5', '6', '7', '8', '9', '0':
//                        continue;
//                    default:
//                        shouldSave = false;
//                        break;
//                }
//            }
//            if (shouldSave) {
//                List<String> phoneNumbers = RuntimeGuestsData
//                                            .getInstance()
//                                            .getGuests()
//                                            .stream()
//                                            .map(Guest::getPhone)
//                                            .filter(phoneObj -> phoneObj.equals(phone))
//                                            .collect(Collectors.toList());
//
//                if (!phoneNumbers.isEmpty()) {
//                    shouldSave = false;
//                }
//            }
//        }
//
//        if (shouldSave) {
//            newGuest = new Guest(name, food, drink, phone);
//        } else {
//            newGuest = null;
//        }
//
//        return newGuest;
//    }
}
