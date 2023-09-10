package com.garageapp.model;

import java.util.ArrayList;
import java.util.List;

public class ParkingLevel {
    private int levelNumber;
    private List<ParkingSpot> spots;

    private int availableSpots;

    public ParkingLevel(int levelNumber, int totalSpots) {
        this.levelNumber = levelNumber;
        this.spots = new ArrayList<>(totalSpots);
        for (int i = 0; i < totalSpots; i++) {
            //1-based spot numbering
            spots.add(new ParkingSpot(i + 1));
        }
        this.availableSpots = totalSpots;
    }

    public int getAvailableSpots() {
        int count = 0;
        for (ParkingSpot spot : spots) {
            if (!spot.isOccupied()) {
                count++;
            }
        }
        return count;
    }

    public String parkVehicle(Vehicle vehicle) {
        //check if a vehicle with the same license plate is already parked
        for (ParkingSpot spot : spots) {
            if (spot.isOccupied() && spot.getParkedVehicle().getLicensePlate().equals(vehicle.getLicensePlate())) {
                    return "A " + spot.getParkedVehicle().getVehicleType() + " with license plate " + vehicle.getLicensePlate() + " is already parked.";
            }
        }
        //if not, then find an available spot and park the vehicle
        for (ParkingSpot spot : spots) {
            if (!spot.isOccupied()) {
                spot.parkVehicle(vehicle);
                availableSpots--;
                if (availableSpots == 0) {
                    return "Vehicle parked at spot " + spot.getSpotNumber() + ". This was the last available spot on this level.";
                }
                return "Vehicle parked at spot " + spot.getSpotNumber();
            }
        }
        return "No available spots on this level.";
    }

    public void removeVehicleFromSpot(int spotNumber) {
        if (spotNumber <= spots.size() && spotNumber > 0) {
            spots.get(spotNumber - 1).removeVehicle();
        }
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public List<ParkingSpot> getSpots() {
        return spots;
    }

}