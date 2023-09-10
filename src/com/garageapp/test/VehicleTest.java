package com.garageapp.test;

import com.garageapp.model.Vehicle;
import com.garageapp.model.VehicleCar;
import com.garageapp.model.VehicleMotorcycle;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VehicleTest {

    private static Vehicle car;
    private static Vehicle motorcycle;

    @BeforeAll
    public static void setup() throws Exception {
        car = new VehicleCar("XYZ123");
        motorcycle = new VehicleMotorcycle("ABC987");
    }

    @Test
    public void testLicensePlate() {
        assertEquals("XYZ123", car.getLicensePlate(), "Car's license plate should be XYZ123");
        assertEquals("ABC987", motorcycle.getLicensePlate(), "Motorcycle's license plate should be ABC987");
    }

    @Test
    public void testUniqueLicensePlates() {
        assertTrue(Vehicle.getRegisteredLicensePlates().containsKey("XYZ123"), "License plate " + car.getLicensePlate() + " should be registered");
        assertTrue(Vehicle.getRegisteredLicensePlates().containsKey("ABC987"), "License plate " + motorcycle.getLicensePlate() + " should be registered");
    }

    @Test
    public void testDuplicateLicensePlate() throws Exception {
        assertThrows(Exception.class, () -> new VehicleCar("XYZ123"), "Exception should be thrown for duplicate license plate " + car.getLicensePlate());
    }

}