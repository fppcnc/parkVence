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
        //initialize the garage with 2 levels, each with 3 spots
        garage = new ParkingGarage(2, 3);
    }

    //test for parking
    @Test
    public void testParkVehicleSuccessfully() throws Exception {
        Vehicle car = new VehicleCar("ABC123");
        String result = garage.parkVehicle(car);
        assertTrue(result.contains("Fahrzeug geparkt auf Etage"));
    }

    //test for parking same vehicle twice
    @Test
    public void testParkVehicleTwice() throws Exception {
        Vehicle car = new VehicleCar("ABC123");
        garage.parkVehicle(car);
        String result = garage.parkVehicle(car);
        assertTrue(result.contains("Fahrzeug mit Kennzeichen ABC123 ist bereits geparkt."));
    }

    //test for parking a different vehicle with the same plate while the first one is currently parked
    @Test
    public void testParkDifferentVehicleWithSameLicense() throws Exception {
        Vehicle car = new VehicleCar("ABC123");
        garage.parkVehicle(car);
        Vehicle motorcycle = new VehicleMotorcycle("ABC123");
        String result = garage.parkVehicle(motorcycle);
        assertEquals("Fahrzeug mit Kennzeichen ABC123 ist bereits geparkt.", result);
    }

    //test to unpark unparked vehicle
    @Test
    public void testRemoveVehicleNotParked() throws Exception {
        Vehicle car = new VehicleCar("XYZ987");
        String result = garage.removeVehicle(car.getLicensePlate());
        assertEquals("Das Fahrzeug mit dem Kennzeichen XYZ987 ist nicht in der Garage geparkt.", result);
    }

    //unpark
    @Test
    public void testRemoveParkedVehicle() throws Exception {
        Vehicle car = new VehicleCar("XYZ987");
        garage.parkVehicle(car);  // First park the vehicle
        String result = garage.removeVehicle(car.getLicensePlate());
        assertEquals("Fahrzeug mit Kennzeichen XYZ987 erfolgreich ausgeparkt.", result);
    }


    //park motorcycle using an unparked Car´s plate
    @Test
    public void testParkUnparkCarThenMotorcycleWithSameLicense() throws Exception {
        Vehicle car = new VehicleCar("SAME789");
        garage.parkVehicle(car);  // First park the car
        garage.removeVehicle("SAME789");  // Then unpark the car
        Vehicle motorcycle = new VehicleMotorcycle("SAME789");
        String result = garage.parkVehicle(motorcycle);
        assertEquals("Das Kennzeichen SAME789 ist bereits einem anderen Fahrzeugtyp zugeordnet.", result);
    }

    //get info for not parked Vehicle
    @Test
    public void testGetInfoForVehicleNotParked() throws Exception {
        String result = garage.getVehicleInfo("NOCAR1");
        assertEquals("Das Fahrzeug mit dem Kennzeichen NOCAR1 ist nicht in der Garage geparkt.", result);
    }

    //get info about parked vehicle
    @Test
    public void testGetInfoForParkedVehicle() throws Exception {
        Vehicle car = new VehicleCar("INFO123");
        garage.parkVehicle(car);
        String result = garage.getVehicleInfo("INFO123");
        assertTrue(result.contains("Das Fahrzeug mit dem Kennzeichen INFO123 ist geparkt im Etage") && result.contains("Platz"));
    }

    //get total spots
    @Test
    public void testGetTotalSpots() {
        int totalSpots = garage.getTotalSpots();
        assertEquals(6, totalSpots);
    }

    //get available spots
    @Test
    public void testGetAvailableSpots() throws Exception {
        int initialAvailableSpots = garage.getTotalAvailableSpots();
        Vehicle car = new VehicleCar("SPOT123");
        garage.parkVehicle(car);
        int updatedAvailableSpots = garage.getTotalAvailableSpots();
        assertEquals(initialAvailableSpots - 1, updatedAvailableSpots);
    }
}