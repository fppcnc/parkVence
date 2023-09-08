package com.garageapp.ui;

import com.garageapp.model.ParkingGarage;

import javax.swing.*;
import java.awt.*;

public class GarageInfoFrame extends JFrame {
    private ParkingGarage garage;

    public GarageInfoFrame(ParkingGarage garage) {
        this.garage = garage;

        //frame setup
        setTitle("Garage Information");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 2)); // Split the frame into two sections

        //left side: Information display
        JPanel infoPanel = new JPanel(new GridLayout(5, 1)); // Adjust as needed
        infoPanel.add(new JLabel("Total Levels: " + garage.getTotalLevels()));
        infoPanel.add(new JLabel("Total Spots: " + garage.getTotalSpots()));
        infoPanel.add(new JLabel("Available Spots: " + garage.availableSpots()));
        add(infoPanel);

        //right side: Action buttons
        JPanel actionPanel = new JPanel(new GridLayout(3, 1));
        JButton parkVehicleButton = new JButton("Park Vehicle");
        parkVehicleButton.addActionListener(e -> {
            //redirect to a frame for parking a vehicle
        });
        JButton unParkVehicleButton = new JButton("Unpark Vehicle");
        unParkVehicleButton.addActionListener(e -> {
            //redirect to a frame for unparking a vehicle
        });
        JButton findVehicleButton = new JButton("Find Vehicle");
        findVehicleButton.addActionListener(e -> {
            //redirect to a frame for finding a vehicle
        });
        actionPanel.add(parkVehicleButton);
        actionPanel.add(unParkVehicleButton);
        actionPanel.add(findVehicleButton);
        add(actionPanel);
    }
}