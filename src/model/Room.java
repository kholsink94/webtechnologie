package model;

public class Room {

    private int squareMeters;
    private int rentPrice;
    private String location;

    public Room(int squareMeters, int rentPrice, String location) {
        this.squareMeters = squareMeters;
        this.rentPrice = rentPrice;
        this.location = location;
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
}
