package com.garageapp.ui;

import com.garageapp.model.ParkingGarage;

import javax.swing.*;

public class EventHandlers {

    //radio button´s selection when button is pressed
    public static void handleButtonSelection(JRadioButton staticGarageButton, JRadioButton dynamicGarageButton, JLabel explanationLabel, MainWindow mainWindow) {
        if (staticGarageButton.isSelected()) {
            handleStaticGarage();
            mainWindow.dispose();
        } else if (dynamicGarageButton.isSelected()) {
            handleDynamicGarage();
            mainWindow.dispose();
        } else {
            explanationLabel.setText("Sie haben keinen Typ ausgewählt!");
        }
    }

    private static void handleStaticGarage() {
       StaticGarageSetup staticGarage = new StaticGarageSetup();
       staticGarage.setVisible(true);
    }

    private static void handleDynamicGarage() {
        System.out.println("Dynamic Garage Selected");
    }

    static void handleStaticSetup(int numLevels, int spotsPerLevel, StaticGarageSetup staticGarageSetup) {
        ParkingGarage garage = new ParkingGarage(numLevels, spotsPerLevel);
        new GarageInfoFrame(garage).setVisible(true);
staticGarageSetup.dispose();
    }

}