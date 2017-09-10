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

    public void addPerson(String username, String password, String role){
        if(role.equals("Owner")){
            persons.add(new Owner(username, password));
        } else{
            persons.add(new Hirer(username, password));
        }
    }

    public boolean doesPersonExist(String username){
        for(int i = 0; i < persons.size(); i++){
            if(persons.get(i).getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
}
