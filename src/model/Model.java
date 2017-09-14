package model;

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

    public Person getPerson(String username) {
        for (int i = 0; i < persons.size(); i++) {
            if (persons.get(i).getUsername().equals(username)) {
                return persons.get(i);
            }
        }
        return null;
    }

    public void addRoom(int squareMeters, int rentPrice, String location, String owner) {
        rooms.add(new Room(squareMeters, rentPrice, location, owner));
    }

    public ArrayList<Room> getSpecificRooms(String owner) {
        ArrayList<Room> specificRooms = new ArrayList<>();
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getOwner().equals(owner)) {
                specificRooms.add(rooms.get(i));
            }
        }
        return specificRooms;
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
