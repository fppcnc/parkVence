package com.garageapp.ui;

import com.garageapp.model.ParkingGarage;

import javax.swing.*;
import java.awt.*;

public class GarageInfoFrame extends JFrame {
    private ParkingGarage garage;

    public GarageInfoFrame(ParkingGarage garage) {
        this.garage = garage;

        //frame setup
        setTitle("Informationen Garage");
        setSize(500, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 2));

        //left side: Information display
        JPanel infoPanel = new JPanel(new GridLayout(3, 1));
        infoPanel.add(new JLabel("Etagen: " + garage.getTotalLevels()));
        infoPanel.add(new JLabel("Stellplätze: " + garage.getTotalSpots()));
        infoPanel.add(new JLabel("Freie Stellplätze: " + garage.availableSpots()));
        add(infoPanel);

        //right side: Action buttons
        JPanel actionPanel = new JPanel(new GridLayout(3, 1));
        JButton parkVehicleButton = new JButton("Park Vehicle");
            parkVehicleButton.addActionListener(e -> new VehicleActionFrame(garage, VehicleActionFrame.ActionType.PARK).setVisible(true));
        JButton unParkVehicleButton = new JButton("Unpark Vehicle");
            unParkVehicleButton.addActionListener(e -> new VehicleActionFrame(garage, VehicleActionFrame.ActionType.UNPARK).setVisible(true));
        JButton findVehicleButton = new JButton("Find Vehicle");
            findVehicleButton.addActionListener(e -> new VehicleActionFrame(garage, VehicleActionFrame.ActionType.FIND).setVisible(true));
        actionPanel.add(parkVehicleButton);
        actionPanel.add(unParkVehicleButton);
        actionPanel.add(findVehicleButton);
        add(actionPanel);
    }
}




