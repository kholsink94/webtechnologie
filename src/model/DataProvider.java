package model;

import java.util.ArrayList;

public class DataProvider {
    /*
    Attributes for the model instance, Arraylists for persons and rooms.
     */
    private static DataProvider ourInstance;
    public ArrayList<Person> persons;
    public ArrayList<Room> rooms;

    /*
    Get method to get a instance of our DataProvider.
     */
    public static DataProvider getInstance() {
        if (ourInstance == null) {
            ourInstance = new DataProvider();
        }
        return ourInstance;
    }

    /*
    DataProvider constructor where we initialise persons and rooms.
     */
    private DataProvider() {
        persons = new ArrayList<>();
        rooms = new ArrayList<>();
        generateDummyUsers();
        generateDummyRooms();
    }

    /*
    Creating dummy data for the users and rooms
     */
    private void generateDummyUsers() {
        persons.add(new Owner("Owner", "Password"));
        persons.add(new Hirer("Hirer", "Password"));
        persons.add(new Owner("Hans", "Password"));
        persons.add(new Hirer("Chef", "Password"));
        persons.add(new Owner("Jochem", "Password"));
        persons.add(new Hirer("Brend", "Password"));
    }

    private void generateDummyRooms() {
        rooms.add(new Room(10, 220, "Ootmarsum", "Hans"));
        rooms.add(new Room(15, 280, "Ootmarsum", "Hans"));
        rooms.add(new Room(17, 300, "Ootmarsum", "Owner"));
        rooms.add(new Room(20, 350, "Ootmarsum", "Owner"));
        rooms.add(new Room(20, 350, "Ootmarsum", "Niet"));
        rooms.add(new Room(25, 370, "Ootmarsum", "Jochem"));
        rooms.add(new Room(30, 500, "Ootmarsum", "Brend"));
        rooms.add(new Room(32, 550, "Ootmarsum", "Niet"));
    }

    /*
    Method to check if someone is a hirer (if not, he is a owner)
     */
    public boolean isHirer(String username) {
        assert username != null : "Null username";
        assert !username.isEmpty() : "Empty username";
        for (Person p : persons) {
            if (p.getUsername().equals(username)) {
                if (p instanceof Hirer) {
                    return true;
                }
            }
        }
        return false;
    }

    /*
    Getter for the person arraylists.
     */
    public ArrayList<Person> getPersons() {
        return persons;
    }

    /*
    Method to add person with a username, password and a role.
     */
    public void addPerson(String username, String password, String role) {

        assert !username.isEmpty() : "empty username";
        assert username != null : "null username";
        assert !password.isEmpty() : "empty password";
        assert password != null : "null password";
        assert !role.isEmpty() : "empty role";
        assert role != null : "null role";

        if (role.equals("Owner")) {
            persons.add(new Owner(username, password));
        } else {
            persons.add(new Hirer(username, password));
        }
    }

    /*
    Method to add a room with square meters, rent price, location and the owner.
     */
    public void addRoom(int squareMeters, int rentPrice, String location, String owner) {

        assert squareMeters > 0 : "Negative square meters";
        assert rentPrice > 0 : "Negative rent price";
        assert squareMeters < 500 : "Unrealistic square meters";
        assert rentPrice < 2000 : "Unrealistic rent price";
        assert !location.isEmpty() : "Empty location";
        assert location != null : "Null location";
        assert !owner.isEmpty() : "Emtpy owner";
        assert owner != null : "Null owner";

        rooms.add(new Room(squareMeters, rentPrice, location, owner));
    }

    /*
    Method to get all the rooms from a specific owner.
     */
    public ArrayList<Room> getSpecificRooms(String owner) {

        assert !owner.isEmpty() : "Empty owner";
        assert owner != null : "Null owner";

        ArrayList<Room> specificRooms = new ArrayList<>();
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getOwner().equals(owner)) {
                specificRooms.add(rooms.get(i));
            }
        }
        return specificRooms;
    }

    /*
    Method to check if a specific user exists.
     */
    public boolean doesPersonExist(String username) {
        assert !username.isEmpty() : "Empty username";
        assert username != null : "Null username";

        for (int i = 0; i < persons.size(); i++) {
            if (persons.get(i).getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    /*
    Method to get specific rooms with a minimum square meters, maximum rent price and a location.
     */
    public ArrayList<Room> getSpecificRooms(int squareMeters, int rentPrice, String location) {
        assert squareMeters > 0 : "Negative square meters";
        assert rentPrice > 0 : "Negative rent price";
        assert !location.isEmpty() : "Empty location";
        assert location != null : "Null location";
        ArrayList<Room> specificRooms = new ArrayList<>();
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getSquareMeters() >= squareMeters && rooms.get(i).getRentPrice() <= rentPrice && rooms.get(i).getLocation().equals(location)) {
                specificRooms.add(rooms.get(i));
            }
        }
        return specificRooms;
    }
}
