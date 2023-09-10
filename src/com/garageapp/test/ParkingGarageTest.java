package com.garageapp.test;

import com.garageapp.model.ParkingGarage;
import com.garageapp.model.Vehicle;
import com.garageapp.model.VehicleCar;
import com.garageapp.model.VehicleMotorcycle;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingGarageTest {

    private static ParkingGarage garage;

    @BeforeAll
    public static void setup() {
        // Initialize the garage with 2 levels, each with 3 spots
        garage = new ParkingGarage(2, 3);
    }

    @Test
    public void testParkVehicleSuccessfully() throws Exception {
        Vehicle car = new VehicleCar("ABC123");
        String result = garage.parkVehicle(car);
        assertTrue(result.contains("Vehicle parked at"));
    }

    @Test
    public void testParkVehicleTwice() throws Exception {
        Vehicle car = new VehicleCar("ABC123");
        garage.parkVehicle(car);
        String result = garage.parkVehicle(car);
        assertTrue(result.contains("license plate ABC123 is already parked."));
    }

    @Test
    public void testParkDifferentVehicleWithSameLicense() throws Exception {
        Vehicle car = new VehicleCar("ABC123");
        garage.parkVehicle(car);
        Vehicle motorcycle = new VehicleMotorcycle("ABC123");
        String result = garage.parkVehicle(motorcycle);
        assertEquals("A CAR with license plate ABC123 is already parked.", result);
    }

    @Test
    public void testRemoveVehicleNotParked() throws Exception {
        Vehicle car = new VehicleCar("XYZ987");
        String result = garage.removeVehicle(car.getLicensePlate());
        assertEquals("Vehicle with license plate XYZ987 successfully unparked", result);
    }

    @Test
    public void testRemoveParkedVehicle() throws Exception {
        Vehicle car = new VehicleCar("XYZ987");
        garage.parkVehicle(car);  // First park the vehicle
        String result = garage.removeVehicle(car.getLicensePlate());
        assertEquals("Vehicle with license plate XYZ987 successfully unparked", result);
    }

    @Test
    public void testGetInfoForVehicleNotParked() throws Exception {
        String result = garage.getVehicleInfo("NOCAR1");
        assertEquals("Vehicle with license plate NOCAR1 is not parked in the garage.", result);
    }

    @Test
    public void testGetInfoForParkedVehicle() throws Exception {
        Vehicle car = new VehicleCar("INFO123");
        garage.parkVehicle(car);
        String result = garage.getVehicleInfo("INFO123");
        assertTrue(result.contains("Vehicle with license plate INFO123 is parked at Level") && result.contains("Spot"));
    }

    @Test
    public void testGetTotalSpots() {
        int totalSpots = garage.getTotalSpots();
        assertEquals(6, totalSpots);  // 2 levels with 3 spots each
    }

    @Test
    public void testGetAvailableSpots() throws Exception {
        int initialAvailableSpots = garage.getTotalAvailableSpots();
        Vehicle car = new VehicleCar("SPOT123");
        garage.parkVehicle(car);
        int updatedAvailableSpots = garage.getTotalAvailableSpots();
        assertEquals(initialAvailableSpots - 1, updatedAvailableSpots);
    }
}