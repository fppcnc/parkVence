package com.garageapp.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingGarage {
    private List<ParkingLevel> levels;
    private Map<String, VehicleType> licenseToTypeMap;

    //constructor for the scenario spots number is the same for every level
    public ParkingGarage(int numberOfLevels, int spotsPerLevel) {
        this.levels = new ArrayList<>(numberOfLevels);
        for (int i = 0; i < numberOfLevels; i++) {
            //1-based level numbering
            levels.add(new ParkingLevel(i + 1, spotsPerLevel));
        }
        licenseToTypeMap = new HashMap<>();
    }

    //constructor for the scenario spots number varies from level to level
    //the size of the spotsPerLevel list itself determines the number of levels
    public ParkingGarage(List<Integer> spotsPerLevel) {
        this.levels = new ArrayList<>(spotsPerLevel.size());
        for (int i = 0; i < spotsPerLevel.size(); i++) {
            levels.add(new ParkingLevel(i + 1, spotsPerLevel.get(i)));
        }
        licenseToTypeMap = new HashMap<>();
    }

    public String parkVehicle(Vehicle vehicle) {
        //check if any vehicle with the given license plate is already parked
        for (ParkingLevel level : levels) {
            for (ParkingSpot spot : level.getSpots()) {
                if (spot.isOccupied() && spot.getParkedVehicle().getLicensePlate().equals(vehicle.getLicensePlate())) {
                    return "Vehicle with license plate " + vehicle.getLicensePlate() + " is already parked.";
                }
            }
        }
        //check if the vehicle's license plate is associated with a different type
        if (licenseToTypeMap.containsKey(vehicle.getLicensePlate()) &&
                licenseToTypeMap.get(vehicle.getLicensePlate()) != vehicle.getVehicleType()) {
            return "License plate " + vehicle.getLicensePlate() + " is already associated with a different type of vehicle.";
        }
        //if vehicle is matching type-plate or is a new vehicle, go ahead and park it
        for (ParkingLevel level : levels) {
            String parkingResult = level.parkVehicle(vehicle);
            if (!parkingResult.contains("No available spot")) {
                licenseToTypeMap.put(vehicle.getLicensePlate(), vehicle.getVehicleType());
                return parkingResult;
            }
        }
        return "No available spot in the entire garage for vehicle with license: " + vehicle.getLicensePlate();
    }

    //unpark parked vehicles
    public String removeVehicle(String licensePlate) {
        for (ParkingLevel level : levels) {
            for (ParkingSpot spot : level.getSpots()) {
                if (spot.isOccupied() && spot.getParkedVehicle().getLicensePlate().equals(licensePlate)) {
                    spot.removeVehicle();
                    licenseToTypeMap.remove(licensePlate); // This line is crucial
                    return "Vehicle with license plate " + licensePlate + " successfully unparked";
                }
            }
        }
        return "Vehicle with license plate " + licensePlate + " is not parked in the garage.";
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
