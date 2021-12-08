package sample.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RuntimeGuestsData {
    private static RuntimeGuestsData instance = new RuntimeGuestsData();
    private ObservableList<Guest> guests = FXCollections.observableArrayList();

    private RuntimeGuestsData(){}

    public static RuntimeGuestsData getInstance() {
        return instance;
    }

    public ObservableList<Guest> getGuests() {
        return guests;
    }

    void saveFromDB(Guest guest) {
        guests.add(guest);
    }

    public void addGuest(Guest newGuest) {
        guests.add(newGuest);
        Database.addGuestToDB(newGuest);
    }

    public void deleteGuest(Guest guestToDelete) {
        guests.remove(guestToDelete);
        Database.removeGuestFromDB(guestToDelete.getPhone());
    }

    public void updateGuest(String phone, Guest updated) {
        guests.set(findIndex(phone), updated);
        Database.updateGuestInDB(phone, updated);
    }

    public int findIndex(String phone) {
        for (int i = 0; i < guests.size(); i++) {
            if (guests.get(i).getPhone().equals(phone)) {

                return i;
            }
        }
        return -1;
    }
}
