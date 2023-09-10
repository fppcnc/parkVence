package com.garageapp.model;

public class ParkingSpot {
    private int spotNumber;
    private Vehicle parkedVehicle;

    public ParkingSpot(int spotNumber) {
        this.spotNumber = spotNumber;
        this.parkedVehicle = null;
    }

    public boolean isOccupied() {
        return parkedVehicle != null;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    public void parkVehicle(Vehicle vehicle) {
        this.parkedVehicle = vehicle;
    }

    public void removeVehicle() {
        this.parkedVehicle = null;
    }

    public int getSpotNumber() {
        return spotNumber;
    }
}