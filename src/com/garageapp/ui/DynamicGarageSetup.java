package com.garageapp.ui;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;


//eventHandlers for this Dynamic scenario are managed internally in respective methods in this class
public class DynamicGarageSetup {

    private JFormattedTextField numLevelsField;
    private List<JFormattedTextField> spotsField;

    //numberFormatter to ensure only int are accepted
    NumberFormat format = NumberFormat.getInstance();
    NumberFormatter formatter = new NumberFormatter(format);


    public class DynamicGarageLevelSetup extends JFrame {

        public DynamicGarageLevelSetup() {

            JFormattedTextField numLevelsField;

            setTitle("Dynamische Garage Setup");
            setSize(500, 100);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new GridLayout(2, 2));

            formatter.setValueClass(Integer.class);
            formatter.setMinimum(0);
            formatter.setMaximum(Integer.MAX_VALUE);

            //will go ahead only on valid inputs
            formatter.setAllowsInvalid(false);
            formatter.setCommitsOnValidEdit(true);


            JLabel numLevelsLabel = new JLabel("Anzahl der Etagen:");
            numLevelsField = new JFormattedTextField(formatter);


            JButton nextButton = new JButton("Weiter");
            nextButton.addActionListener(e -> {
                int numberOfLevels = Integer.parseInt(numLevelsField.getText());
                new DynamicGarageSpotSetup(numberOfLevels).setVisible(true);
                this.dispose();
            });

            add(numLevelsLabel);
            add(numLevelsField);
            //for positioning
            add(new JLabel());
            add(nextButton);
        }
    }

    public class DynamicGarageSpotSetup extends JFrame {

        List<JFormattedTextField> spotsFields = new ArrayList<>();

        public DynamicGarageSpotSetup(int numberOfLevels) {
            setTitle("Dynamische Garage Stellplätze");
            setSize(500, 50 * numberOfLevels);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //+1 for the button at the end
            setLayout(new GridLayout(numberOfLevels + 1, 2));

            for (int i = 0; i < numberOfLevels; i++) {
                JLabel label = new JLabel("Parkplätze für Etage " + (i + 1) + ":");


                formatter.setValueClass(Integer.class);
                formatter.setMinimum(0);
                formatter.setMaximum(Integer.MAX_VALUE);

                //will go ahead only on valid inputs
                formatter.setAllowsInvalid(false);
                formatter.setCommitsOnValidEdit(true);


                JLabel spotsPerLevelLabel = new JLabel("Parkplätze pro Etage:");
                JFormattedTextField spotsField = new JFormattedTextField(formatter);

                spotsFields.add(spotsField);
                add(label);
                add(spotsField);
            }


            JButton setupButton = new JButton("Garage Setup");
            setupButton.addActionListener(e -> {
                List<Integer> spotsPerLevelList = new ArrayList<>();
                for (JFormattedTextField field : spotsFields) {
                    spotsPerLevelList.add(((Number) field.getValue()).intValue());
                }
                try {
                    EventHandlers.handleDynamicSetup(
                            numberOfLevels,
                            spotsPerLevelList,
                            this);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            });
            //for positioning
            add(new JLabel());
            add(setupButton);
        }
    }
}
