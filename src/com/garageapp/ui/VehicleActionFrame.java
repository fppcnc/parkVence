package com.garageapp.ui;

import com.garageapp.model.ParkingGarage;
import com.garageapp.model.Vehicle;
import com.garageapp.model.VehicleCar;
import com.garageapp.model.VehicleMotorcycle;

import javax.swing.*;
import java.awt.*;

public class VehicleActionFrame extends JFrame {

    private ParkingGarage garage;
    private ActionType actionType;
    private JComboBox<String> vehicleTypeBox;

    public VehicleActionFrame(ParkingGarage garage, ActionType actionType) {
        this.garage = garage;
        this.actionType = actionType;

        // Basic setup for the frame
        setTitle("Vehicle Action");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1));

        JLabel licenseLabel = new JLabel("Enter Vehicle License Plate:");
        JTextField licenseField = new JTextField();
        vehicleTypeBox = new JComboBox<>(new String[]{"Car", "Motorcycle"}); // JComboBox for vehicle type selection
        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(e -> {
            String licensePlate = licenseField.getText();
            String vehicleType = (String) vehicleTypeBox.getSelectedItem();
            Vehicle vehicle;



            String message = "";
            switch (actionType) {
                case PARK:
                    if ("Car".equals(vehicleType)) {
                        try {
                            vehicle = new VehicleCar(licensePlate);
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    } else {
                        try {
                            vehicle = new VehicleMotorcycle(licensePlate);
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    message = garage.parkVeichle(vehicle);
                    break;
                case UNPARK:
                    message = garage.unParkVehicle(licensePlate);  // Assuming you can unpark using the base Vehicle type
                    break;
                case FIND:
                    message = garage.findVehicleSpot(licensePlate);
                    break;
            }

            // Display feedback
            JOptionPane.showMessageDialog(this, message);
            new GarageInfoFrame(garage).setVisible(true); // Open new GarageInfoFrame with updated info
            dispose(); // Close the current frame
        });

        add(licenseLabel);
        add(licenseField);
        add(vehicleTypeBox); // Add the JComboBox to the frame
        add(submitButton);
    }

    public enum ActionType {
        PARK, UNPARK, FIND
    }
}
