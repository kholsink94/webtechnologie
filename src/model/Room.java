package model;

public class Room {

    private int squareMeters;
    private int rentPrice;
    private String location;
    private String owner;

    public Room(int squareMeters, int rentPrice, String location, String owner) {
        this.squareMeters = squareMeters;
        this.rentPrice = rentPrice;
        this.location = location;
        this.owner = owner;
    }

    public int getSquareMeters() {
        return squareMeters;
    }

    public int getRentPrice() {
        return rentPrice;
    }

    public String getLocation() {
        return location;
    }

    public String getOwner() {
        return owner;
    }
}
