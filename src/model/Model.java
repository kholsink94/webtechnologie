package model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Model {
    private static Model ourInstance;
    public ArrayList<Person> persons;
    public ArrayList<Room> rooms;

    public static Model getInstance() {
        if (ourInstance == null) {
            ourInstance = new Model();
        }
        return ourInstance;
    }

    private Model() {
        persons = new ArrayList<>();
        rooms = new ArrayList<>();
        generateDummyUsers();
        generateDummyRooms();
    }

    private void generateDummyUsers() {
        persons.add(new Owner("Owner", "Password"));
        persons.add(new Hirer("Hirer", "Password"));
    }

    private void generateDummyRooms(){
        rooms.add(new Room(100, 220, "Ootmarsum"));
        rooms.add(new Room(150, 280, "Ootmarsum"));
        rooms.add(new Room(170, 300, "Ootmarsum"));
        rooms.add(new Room(200, 350, "Ootmarsum"));
        rooms.add(new Room(200, 350, "Ootmarsum"));
        rooms.add(new Room(250, 370, "Ootmarsum"));
        rooms.add(new Room(300, 500, "Ootmarsum"));
        rooms.add(new Room(320, 550, "Ootmarsum"));
    }

    public boolean isHirer(String username) {
        for (Person p : persons) {
            if (p.getUsername().equals(username)) {
                if (p instanceof Hirer) {
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList<Person> getPersons() {
        return persons;
    }

    public void addPerson(String username, String password, String role) {
        if (role.equals("Owner")) {
            persons.add(new Owner(username, password));
        } else {
            persons.add(new Hirer(username, password));
        }
    }

    public boolean doesPersonExist(String username) {
        for (int i = 0; i < persons.size(); i++) {
            if (persons.get(i).getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Room> getSpecificRooms(int squareMeters, int rentPrice, String location) {
        ArrayList<Room> specificRooms = new ArrayList<>();
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getSquareMeters() >= squareMeters && rooms.get(i).getRentPrice() <= rentPrice && rooms.get(i).getLocation().equals(location)) {
                specificRooms.add(rooms.get(i));
            }
        }
        return specificRooms;
    }
}
