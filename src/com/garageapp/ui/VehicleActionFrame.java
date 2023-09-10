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
    private GarageInfoFrame garageInfoFrame;

    public VehicleActionFrame(ParkingGarage garage, ActionType actionType,  GarageInfoFrame garageInfoFrame) {
        this.garage = garage;
        this.actionType = actionType;
        this.garageInfoFrame = garageInfoFrame;

        // Basic setup for the frame
        setTitle("Vehicle Action");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1));

        JLabel licenseLabel = new JLabel("Enter Vehicle License Plate:");
        JTextField licenseField = new JTextField();
        vehicleTypeBox = new JComboBox<>(new String[]{"Car", "Motorcycle"});
        JButton submitButton = new JButton("Submit");
        //display it only when park is selected
        vehicleTypeBox.setVisible(actionType == ActionType.PARK);

        submitButton.addActionListener(e -> {
            String licensePlate = licenseField.getText();
            String vehicleType = (String) vehicleTypeBox.getSelectedItem();
            Vehicle vehicle;



            String message = "";
            switch (actionType) {
                case PARK:
                    vehicleTypeBox.setVisible(true);
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
                    message = garage.parkVehicle(vehicle);
                    break;
                case UNPARK:
                    message = garage.removeVehicle(licensePlate);
                    break;
                case FIND:
                    message = garage.getVehicleInfo(licensePlate);
                    break;
            }

            //display feedback
            JOptionPane.showMessageDialog(this, message);
            //close the current frame and the GarageInfoFrame
            this.dispose();
            if (garageInfoFrame != null) {
                garageInfoFrame.dispose();
            }
            //open a new GarageInfoFrame with updated info
            new GarageInfoFrame(garage).setVisible(true);
            dispose();
        });

        add(licenseLabel);
        add(licenseField);
        add(vehicleTypeBox);
        add(submitButton);
    }

    public enum ActionType {
        PARK, UNPARK, FIND
    }
}
