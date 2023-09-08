package com.garageapp.test;

import com.garageapp.model.ParkingSpot;
import com.garageapp.model.Vehicle;
import com.garageapp.model.VehicleCar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParkingSpotTest {

    private static ParkingSpot spot;

    @BeforeEach
    public void setup() {
        spot = new ParkingSpot();
    }

    @Test
    public void testParkUnparkVehicle() throws Exception {
        Vehicle v1 = new VehicleCar("XYZ128");
        assertTrue(spot.park(v1), "Fahrzeug " + v1.getLicensePlate() + " sollte erfolgreich geparkt werden");
        assertTrue(spot.isOccupied(), "Platz sollte nach dem Parken eines Fahrzeugs belegt sein");

        assertTrue(spot.unPark(), "Fahrzeug sollte erfolgreich entparkt werden");
        assertFalse(spot.isOccupied(), "Der Platz sollte nach dem Ausparken nicht besetzt sein");
    }

}