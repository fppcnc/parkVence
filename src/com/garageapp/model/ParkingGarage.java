package com.garageapp.model;

import java.util.ArrayList;
import java.util.List;

public class ParkingGarage {
    private List<ParkingLevel> levels;

//    public int getTotalLevels() {
//        return levels.size();
//    }

//    private int totalSpots;


//    public int getTotalSpots() {
//        return totalSpots;
//    }

    //constructor for the scenario spots number is the same for every level
    public ParkingGarage(int numberOfLevels, int spotsPerLevel) {
        this.levels = new ArrayList<>(numberOfLevels);
        for (int i = 0; i < numberOfLevels; i++) {
            //1-based level numbering
            levels.add(new ParkingLevel(i + 1, spotsPerLevel));
        }
    }

    //constructor for the scenario spots number varies from level to level
    //the size of the spotsPerLevel list itself determines the number of levels
    public ParkingGarage(List<Integer> spotsPerLevel) {
        this.levels = new ArrayList<>(spotsPerLevel.size());
        for (int i = 0; i < spotsPerLevel.size(); i++) {
            levels.add(new ParkingLevel(i + 1, spotsPerLevel.get(i)));
        }
    }

    public String parkVehicle(Vehicle vehicle) {

        for (ParkingLevel level : levels) {
            String parkingResult = level.parkVehicle(vehicle);
            if (!parkingResult.contains("No available spot")) {
                return parkingResult;
            }
        }
        return "No available spot in the entire garage for vehicle with license: " + vehicle.getLicensePlate();
    }

    public String removeVehicle(String licensePlate) {
        for (ParkingLevel level : levels) {
            for (ParkingSpot spot : level.getSpots()) {
                if (spot.isOccupied() && spot.getParkedVehicle().getLicensePlate().equals(licensePlate)) {
                    spot.removeVehicle();
                }
            }
        }
        return "Vehicle with license plate " + licensePlate + " successfully unparked";
    }

    public String getVehicleInfo(String licensePlate) {
        for (ParkingLevel level : levels) {
            for (ParkingSpot spot : level.getSpots()) {
                if (spot.isOccupied() && spot.getParkedVehicle().getLicensePlate().equals(licensePlate)) {
                    return "Vehicle with license plate " + licensePlate + " is parked at Level " + level.getLevelNumber() + ", Spot " + spot.getSpotNumber();
                }
            }
        }
        return "Vehicle with license plate " + licensePlate + " is not parked in the garage.";
    }

    public int getTotalAvailableSpots() {
        int total = 0;
        for (ParkingLevel level : levels) {
            total += level.getAvailableSpots();
        }
        return total;
    }

    public int getTotalOccupiedSpots() {
        int total = 0;
        for (ParkingLevel level : levels) {
            total += (level.getSpots().size() - level.getAvailableSpots());
        }
        return total;
    }


    public int getTotalLevels() {
        return levels.size();
    }

    public int getTotalSpots() {
        int totalSpots = 0;
        for (ParkingLevel level : levels) {
            totalSpots += level.getSpots().size();
        }
        return totalSpots;
    }
}
