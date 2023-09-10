package com.garageapp.model;

import java.util.ArrayList;
import java.util.List;

public class ParkingLevel {
    private List<ParkingSpot> spots;

    //add parkingspots on a level
    public ParkingLevel(int numberOfSpots) {
        spots = new ArrayList<>(numberOfSpots);
        for (int i = 0; i < numberOfSpots; i++) {
            spots.add(new ParkingSpot());
        }
    }

    public boolean parkVehicle(Vehicle vehicle) {
        for (ParkingSpot spot : spots) {
            if (!spot.isOccupied()) {
                return spot.park(vehicle);
            }
        }
        return false;
    }


    public boolean unParkVehicle(String licensePlate) {
        for (ParkingSpot spot : spots) {
            if (spot.isOccupied() && spot.getVehicle().getLicensePlate().equals(licensePlate)) {
                spot.unPark();
                return true;
            }
        }
        return false;
    }

    //counts spots until occupied spot is found
    public int availableSpots() {
        int count = 0;
        for (ParkingSpot spot : spots) {
            if (!spot.isOccupied()) {
                count++;
            }
        }
        return count;
    }

    //getter
    public List<ParkingSpot> getSpots() {
        return spots;
    }
}
