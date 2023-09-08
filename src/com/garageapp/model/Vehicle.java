package com.garageapp.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Vehicle {
    protected String licensePlate;

    //static list will travel through different instances. new vehicles is added if no match is found
    protected static List<String> registeredLicensePlates = new ArrayList<>();

    public Vehicle(String licensePlate) throws Exception {
        if (registeredLicensePlates.contains(licensePlate)) {
            throw new Exception ("bereits verwendetes Nummernschild");
        }
        this.licensePlate = licensePlate;
        registeredLicensePlates.add(licensePlate);
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public static List<String> getRegisteredLicensePlates() {
        return registeredLicensePlates;
    }
}
