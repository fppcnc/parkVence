package com.garageapp.test;

import com.garageapp.model.ParkingGarage;
import com.garageapp.model.Vehicle;
import com.garageapp.model.VehicleCar;
import com.garageapp.model.VehicleMotorcycle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingGarageTest {

    private static ParkingGarage garage1;
    private static ParkingGarage garage2;

    @BeforeEach
    public void setup() throws Exception {
        garage1 = new ParkingGarage(3, 10);
        garage2 = new ParkingGarage(3, Arrays.asList(5, 10, 15));
    }

    @Test
    public void testConstructor1() {
        assertEquals(3, garage1.getNumberOfLevels());
        assertEquals(30, garage1.getTotalSpots());
    }

    @Test
    public void testConstructor2() {
        assertEquals(3, garage2.getNumberOfLevels());
        assertEquals(30, garage2.getTotalSpots());
    }

    @Test
    public void testParkUnparkVehicle() throws Exception {
        Vehicle v1 = new VehicleCar("XYZ128");
        String result1 = garage1.parkVeichle(v1);
        assertEquals("Fahrzeug " + v1.getLicensePlate() + " erfolgreich eingeparkt.", result1);

        Vehicle v2 = new VehicleMotorcycle("ABC123");
        String result2 = garage1.parkVeichle(v2);
        assertEquals("Fahrzeug " + v2.getLicensePlate() + " erfolgreich eingeparkt.", result2);

        String result3 = garage1.unParkVehicle(v1);
        assertEquals("Fahrzeug " + v1.getLicensePlate() + " erfolgreich ausgeparkt.", result3);
    }

    @Test
    public void testFindVehicleSpot() throws Exception {
        Vehicle v1 = new VehicleCar("XYZ125");
        garage1.parkVeichle(v1);
        String result = garage1.findVehicleSpot("XYZ125");
        assertTrue(result.contains("XYZ125"));
    }

    @Test
    public void testAvailableSpots() throws Exception {
        Vehicle v1 = new VehicleCar("XYZ120");
        garage1.parkVeichle(v1);
        assertEquals(29, garage1.availableSpots());

        Vehicle v2 = new VehicleMotorcycle("ABC120");
        garage2.parkVeichle(v2);
        assertEquals(29, garage2.availableSpots());
    }

}