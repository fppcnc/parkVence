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
                    return "Fahrzeug mit Kennzeichen " + vehicle.getLicensePlate() + " ist bereits geparkt.";
                }
            }
        }
        //check if the vehicle's license plate is associated with a different type
        if (licenseToTypeMap.containsKey(vehicle.getLicensePlate()) &&
                licenseToTypeMap.get(vehicle.getLicensePlate()) != vehicle.getVehicleType()) {
            return "Das Kennzeichen " + vehicle.getLicensePlate() + " ist bereits einem anderen Fahrzeugtyp zugeordnet.";
        }
        //if vehicle is matching type-plate or is a new vehicle, go ahead and park it
        for (ParkingLevel level : levels) {
            String parkingResult = level.parkVehicle(vehicle);
            if (!parkingResult.contains("keine Plätze frei")) {
                licenseToTypeMap.put(vehicle.getLicensePlate(), vehicle.getVehicleType());
                return parkingResult;
            }
        }
        return "Kein freier Platz in der gesamten Garage für Fahrzeug mit Kennzeichen: " + vehicle.getLicensePlate();
    }

    //unpark parked vehicles
    public String removeVehicle(String licensePlate) {
        for (ParkingLevel level : levels) {
            for (ParkingSpot spot : level.getSpots()) {
                if (spot.isOccupied() && spot.getParkedVehicle().getLicensePlate().equals(licensePlate)) {
                    spot.removeVehicle();
                    return "Fahrzeug mit Kennzeichen " + licensePlate + " erfolgreich ausgeparkt.";
                }
            }
        }
        return "Das Fahrzeug mit dem Kennzeichen " + licensePlate + " ist nicht in der Garage geparkt.";
    }

    public String getVehicleInfo(String licensePlate) {
        for (ParkingLevel level : levels) {
            for (ParkingSpot spot : level.getSpots()) {
                if (spot.isOccupied() && spot.getParkedVehicle().getLicensePlate().equals(licensePlate)) {
                    return "Das Fahrzeug mit dem Kennzeichen " + licensePlate + " ist geparkt im Etage " + level.getLevelNumber() + ", Platz " + spot.getSpotNumber();
                }
            }
        }
        return "Das Fahrzeug mit dem Kennzeichen " + licensePlate + " ist nicht in der Garage geparkt.";
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
