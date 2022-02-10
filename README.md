# JavaFX-party-app

JavaFX-party-app is an app designed to help with organizing parties (events)
by storing data of invited guests. Stored data could be used to plan
the expenses for particular event and to prevent food waste. 

## Description

The application allows user to add, delete, find by phone number, 
modify Guests and store data of them (in the database). It's also 
possible to display a list of drinks and food collected from all 
Guest records.

### How to use it?

To add a new Guest user must click "Add guest" button, which opens
new guest window to specify properties of a Guest.


Deleting an item can be performed in two ways:
- By pushing "delete" key on the keyboard.
- By right-clicking on Guests name and choosing "Delete" option.

In both cases there will appear confirmation window.


Finding guest could be done by clicking "Find" and inserting a 
phone number inside opened window.


"Update guest" button opens new window where user can insert phone 
number of Guest to update and then specify new properties of Guest
record.


To display a list of drinks and food user must click "Show drinks 
and food" button.


### Division of main window

#### Button section (top of the window)

There are placed 4 buttons:
- "Add guest"
- "Show drinks and food"
- "Find"
- "Update guest"

#### Left panel

Here are displayed names of stored Guests.

#### Centre-right space

There are showed Guest properties (after clicking on the name of Guest
in the left panel).  

### Guests data stored in the database 
  
  - name
  - preffered food for the party
  - preffered drink for the party
  - phone number
  
## Final version

In the final version the app will have register/login window. That 
feature will allow users to register an event and then log in as 
administrator. After registration of a party unique event ID will be 
generated. With that ID it will be possible to log in as a guest 
(only for the event which ID guest have got) and set own data and 
prefferences (from a list specified by an administrator). 
The application could also be rearranged to become a web application
with usage of Spring Boot.
