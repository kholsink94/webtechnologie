package model;

public abstract class Person {
    private String username;
    private String password;

    public Person(String username, String password) {
        assert !username.isEmpty() : "Username is empty";
        assert !password.isEmpty() : "Password is empty";
        assert username != null : "Username is null";
        assert password != null : "Password is null";

        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
