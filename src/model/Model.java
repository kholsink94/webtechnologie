package model;

import java.util.ArrayList;

public class Model {
    private static Model ourInstance;
    public ArrayList<Person> persons;

    public static Model getInstance() {
        if (ourInstance == null){
            ourInstance = new Model();
        }
        return ourInstance;
    }

    private Model() {
        persons = new ArrayList<>();
        generateDummyUsers();
    }

    private void generateDummyUsers() {
        persons.add(new Owner("Owner", "Password"));
        persons.add(new Hirer("Hirer", "Password"));
    }

    public ArrayList<Person> getPersons() {
        return persons;
    }
}
