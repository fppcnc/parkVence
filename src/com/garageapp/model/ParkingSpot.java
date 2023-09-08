package com.garageapp.model;

public class ParkingSpot {
    private Vehicle vehicle;
    private boolean isOccupied;

    public ParkingSpot() {
        this.vehicle = null;
        this.isOccupied = false;
    }

    public boolean park(Vehicle vehicle) {
        if (isOccupied) {
            return false;
        }
        this.vehicle = vehicle;
        this.isOccupied = true;
        return true;
    }

    public boolean unPark() {
        if (!isOccupied) {
            return false;
        }
        this.vehicle = null;
        this.isOccupied = false;
        return true;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public boolean isOccupied() {
        return isOccupied;
    }
}