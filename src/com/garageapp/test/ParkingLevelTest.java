package com.garageapp.test;

import com.garageapp.model.ParkingLevel;
import com.garageapp.model.Vehicle;
import com.garageapp.model.VehicleCar;
import com.garageapp.model.VehicleMotorcycle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParkingLevelTest {

    private static ParkingLevel level;

    @BeforeEach
    public void setup() {
        level = new ParkingLevel(5);
    }

    @Test
    public void testConstructor() {
        assertEquals(5, level.getSpots().size(), "Parking level should have 5 spots");
    }

    @Test
    public void testParkUnparkVehicle() throws Exception {
        Vehicle v1 = new VehicleCar("XYZ126");
        assertTrue(level.parkVehicle(v1), "com.garageapp.models.Vehicle XYZ126 should be parked successfully");

        Vehicle v2 = new VehicleMotorcycle("ABC988");
        assertTrue(level.parkVehicle(v2), "com.garageapp.models.Vehicle ABC988 should be parked successfully");

        assertTrue(level.unParkVehicle(v1), "com.garageapp.models.Vehicle XYZ126 should be unparked successfully");
    }

    @Test
    public void testAvailableSpots() throws Exception {
        Vehicle v1 = new VehicleCar("XYZ127");
        level.parkVehicle(v1);

        Vehicle v2 = new VehicleMotorcycle("ABC989");
        level.parkVehicle(v2);

        assertEquals(3, level.availableSpots(), "There should be 3 available spots after parking 2 vehicles");
    }

}