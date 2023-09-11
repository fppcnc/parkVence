package com.garageapp.ui;

import com.garageapp.model.ParkingGarage;

import javax.swing.*;
import java.util.List;

public class EventHandlers {

    //radio button´s selection when button is pressed
    public static void handleButtonSelection(JRadioButton staticGarageButton, JRadioButton dynamicGarageButton, JLabel explanationLabel, GarageSetupSelection garageSetupSelection) {
        if (staticGarageButton.isSelected()) {
            handleStaticGarage();
            garageSetupSelection.dispose();
        } else if (dynamicGarageButton.isSelected()) {
            handleDynamicGarage();
            garageSetupSelection.dispose();
        } else {
            explanationLabel.setText("Sie haben keinen Typ ausgewählt!");
        }
    }

    private static void handleStaticGarage() {
        StaticGarageSetup staticGarage = new StaticGarageSetup();
        staticGarage.setVisible(true);
    }

    private static void handleDynamicGarage() {
        DynamicGarageSetup dynamicGarage = new DynamicGarageSetup();
        dynamicGarage.setVisible(true);
    }

    static void handleStaticSetup(int numLevels, int spotsPerLevel, StaticGarageSetup staticGarageSetup) {
        ParkingGarage garage = new ParkingGarage(numLevels, spotsPerLevel);
        new GarageInfoFrame(garage).setVisible(true);
        staticGarageSetup.dispose();
    }

    static void handleDynamicSetup(List<Integer> spotsPerLevelList, DynamicGarageSetup dynamicGarageSetup) throws Exception {
        ParkingGarage garage = new ParkingGarage(spotsPerLevelList);
        new GarageInfoFrame(garage).setVisible(true);
        dynamicGarageSetup.dispose();
    }

}