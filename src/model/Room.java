package model;

public class Room {

    private int squareMeters;
    private int rentPrice;
    private String location;
    private String owner;

    public Room(int squareMeters, int rentPrice, String location, String owner) {

        assert squareMeters > 0 : "Negative square meters";
        assert squareMeters < 99999 : "Unrealistic square meters";
        assert rentPrice > 0 : "Negative rent price";
        assert rentPrice < 99999 : "Unrealistic rent price";
        assert !location.isEmpty() : "Empty location";
        assert location != null : "Null location";
        assert !owner.isEmpty() : "Empty owner";
        assert owner != null : "Null owner";

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
