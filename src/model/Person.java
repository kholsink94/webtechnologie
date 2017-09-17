package model;

public abstract class Person {
    /*
    Username and password attributes.
     */
    private String username;
    private String password;

    /*
    Constructor.
     */
    public Person(String username, String password) {
        assert !username.isEmpty() : "Empty username";
        assert username != null : "Null username";
        assert !password.isEmpty() : "Empty password";
        assert password != null : "Null password";

        this.username = username;
        this.password = password;
    }

    /*
    Getters for username and password.
     */
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
