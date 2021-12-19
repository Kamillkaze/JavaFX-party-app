package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sample.data.Guest;
import sample.data.RuntimeGuestsData;

import java.util.HashSet;
import java.util.List;

public class FoodAndDrinkWindowController {

    @FXML
    private Label foodLabel;

    @FXML
    private Label drinksLabel;

    private final HashSet<String> food = new HashSet<>();
    private final HashSet<String> drinks = new HashSet<>();

    @FXML
    private void initialize() {
        ObservableList<Guest> guests = RuntimeGuestsData.getInstance().getGuests();

        for (Guest guest: guests) {
            food.add(guest.getFood());
            drinks.add(guest.getDrink());
        }

        for (String food: food) {
            int quantity = 0;
            for (Guest guest: guests) {
                if (guest.getFood().equals(food)) {
                    quantity++;
                }
            }
            foodLabel.setText(foodLabel.getText().trim() + "\n" + food + " -   " + quantity);
        }

        for (String drink: drinks) {
            int quantity = 0;
            for (Guest guest: guests) {
                if (guest.getDrink().equals(drink)) {
                    quantity ++;
                }
            }
            drinksLabel.setText(drinksLabel.getText().trim() + "\n" + drink + " -  " + quantity);
        }
    }
}
