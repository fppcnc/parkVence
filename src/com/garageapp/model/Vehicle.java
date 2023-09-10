package com.garageapp.model;

import java.util.HashMap;
import java.util.Map;

public abstract class Vehicle {
    protected String licensePlate;

    // Map to store registered license plates with their status
    protected static Map<String, VehicleStatus> registeredLicensePlates = new HashMap<>();

    public Vehicle(String licensePlate, String vehicleType) throws Exception {
        if (registeredLicensePlates.containsKey(licensePlate)) {
            VehicleStatus status = registeredLicensePlates.get(licensePlate);
            if (status.vehicleType.equals(vehicleType) && !status.isParked) {
                this.licensePlate = licensePlate;
            } else {
                throw new Exception("Das Nummernschild wird bereits für einen anderen Typ verwendet oder ist geparkt.");
            }
        } else {
            this.licensePlate = licensePlate;
            registeredLicensePlates.put(licensePlate, new VehicleStatus(vehicleType));
        }
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public static Map<String, VehicleStatus> getRegisteredLicensePlates() {
        return registeredLicensePlates;
    }

    public static void setParkedStatus(String licensePlate, boolean isParked) {
        if (registeredLicensePlates.containsKey(licensePlate)) {
            registeredLicensePlates.get(licensePlate).isParked = isParked;
        }
    }

    //nested class to store the status of a vehicle
    protected static class VehicleStatus {
        String vehicleType;
        boolean isParked;

        VehicleStatus(String vehicleType) {
            this.vehicleType = vehicleType;
            this.isParked = false;
        }
    }
}