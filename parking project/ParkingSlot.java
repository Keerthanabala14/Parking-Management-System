package com.model;

public class ParkingSlot {

    private String slotId;
    private String location;
    private boolean isAvailable;
    private double price;

    public ParkingSlot(String slotId, String location, boolean isAvailable) {
        this.slotId = slotId;
        this.location = location;
        this.isAvailable = isAvailable;
        this.price = 10.0;  
    }

    public ParkingSlot() {
        this.price = 10.0; // default for no-arg constructor
    }

    public String getSlotId() {
        return slotId;
    }

    public void setSlotId(String slotId) {
        this.slotId = slotId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Slot ID: " + slotId + ", Location: " + location + ", Is Available: " + isAvailable + ", Price: " + price;
    }
}
