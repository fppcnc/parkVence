package com.garageapp.model;

import java.util.ArrayList;
import java.util.List;

public class ParkingGarage {
    private List<ParkingLevel> levels;

    public int getNumberOfLevels() {
        return levels.size();
    }

    private int totalSpots;

    //getter
    public int getTotalSpots() {
        return totalSpots;
    }

    //constructor for the scenario spots number varies from level to level
    public ParkingGarage(int numberOfLevels, int spotsPerLevel) {
        this.levels = new ArrayList<>();
        int total = 0;
        for (int i = 0; i < numberOfLevels; i++) {
            this.levels.add(new ParkingLevel(spotsPerLevel));
            total += spotsPerLevel;
        }
        this.totalSpots = total;
    }
    //constructor for the scenario every floor has different number of floors
    public ParkingGarage(int numberOfLevels, List<Integer> spotsPerLevelList) throws Exception {
    //values passed in the constructor need to be valid
        if (numberOfLevels != spotsPerLevelList.size()) {
            throw new Exception("Die Anzahl von Etagen muss mit den Angaben für 'spotsPerLevel' übereinstimmen.");
        }
        this.levels = new ArrayList<>();
        int total = 0;
        for (int i = 0; i < numberOfLevels; i++) {
            //fill each level with as many spots as assigned
            int spotsForThisLevel = spotsPerLevelList.get(i);
            this.levels.add(new ParkingLevel(spotsForThisLevel));
            total += spotsForThisLevel;
        }
        this.totalSpots = total;
    }

    public String parkVeichle(Vehicle vehicle) {
        for (ParkingLevel level : levels) {
            if (level.parkVehicle(vehicle)) {
                if (availableSpots() == 0) {
                    return "Fahrzeug " + vehicle.getLicensePlate() + " erfolgreich eingeparkt. Das Parkhaus ist jetzt voll!";
                }
                return "Fahrzeug " + vehicle.getLicensePlate() + " erfolgreich eingeparkt.";
            }
        }
        return "Leider, gibt´s kein freier Parkplatz für Fahrzeug " + vehicle.getLicensePlate() + ".";
    }

    public String unParkVehicle(Vehicle vehicle) {
        for (ParkingLevel level : levels) {
            if (level.unParkVehicle(vehicle)) {
                return "Fahrzeug " + vehicle.getLicensePlate() + " erfolgreich ausgeparkt.";
            }
        }
        return "Fahrzeug " + vehicle.getLicensePlate() + " nicht gefunden.";
    }


    public String findVehicleSpot(String licensePlate) {
        //loop every parking floor
        for (int levelIndex = 0; levelIndex < levels.size(); levelIndex++) {
            //assign for every looped level an Index nr.
            ParkingLevel level = levels.get(levelIndex);
            //com.example.parkvencefx.com.garageapp.models.ParkingLevel getter for spots
            List<ParkingSpot> levelSpots = level.getSpots();
            for (int spotIndex = 0; spotIndex < levelSpots.size(); spotIndex++) {
                ParkingSpot spot = levelSpots.get(spotIndex);
                if (spot.isOccupied() && spot.getVehicle().getLicensePlate().equals(licensePlate)) {
                    return "Fahrzeug " + licensePlate + " befindet sich am " + (levelIndex + 1) + ". Etage am Platz nr." + (spotIndex + 1) + ".";
                }
            }
        }
        return "Fahrzeug " + (licensePlate) + " nicht gefunden.";
    }

    public int availableSpots() {
        int count = 0;
        for (ParkingLevel level : levels) {
            count += level.availableSpots();
        }
        return count;
    }
}
