package com.garageapp.ui;


import javax.swing.*;
import java.awt.*;

public class GarageSetupSelection extends JFrame {

    public GarageSetupSelection() {
        setTitle("Garage Vence");
        setSize(500, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //label
        JLabel instructionLabel = new JLabel("Garage Vence");
        instructionLabel.setHorizontalAlignment(JLabel.CENTER);
        add(instructionLabel, BorderLayout.NORTH);

        //section for buttons and description
        JPanel panelMain = new JPanel(new BorderLayout());
        add(panelMain, BorderLayout.CENTER);

        //radio buttons for static/dynamic
        ButtonGroup group = new ButtonGroup();
        JRadioButton dynamicGarageButton = new JRadioButton("Dynamische Garage");
        group.add(dynamicGarageButton);
        JRadioButton staticGarageButton = new JRadioButton("Statische Garage");
        group.add(staticGarageButton);

        JPanel panelRadio = new JPanel();
        panelRadio.setLayout(new BoxLayout(panelRadio, BoxLayout.Y_AXIS));
        panelRadio.add(Box.createVerticalGlue());
        panelRadio.add(dynamicGarageButton);
        panelRadio.add(staticGarageButton);
        panelRadio.add(Box.createVerticalGlue());
        panelMain.add(panelRadio, BorderLayout.WEST);


        //explaining text on the right
        JLabel explanationLabel = new JLabel("Wählen Sie den gewünschten Garagetyp");
        explanationLabel.setVerticalAlignment(JLabel.CENTER);
        explanationLabel.setHorizontalAlignment(JLabel.CENTER);
        panelMain.add(explanationLabel, BorderLayout.EAST);
        //explaining text change with changing radiobutton selection
        dynamicGarageButton.addActionListener(e -> explanationLabel.setText("Die Anzahl der Stellplätze kann auf jeder Etage variieren"));
        staticGarageButton.addActionListener(e -> explanationLabel.setText("Die Anzahl der Stellplätze ist auf jeder Etage gleich"));

        JButton continueButton = new JButton("Weiter");
        panelMain.add(continueButton, BorderLayout.SOUTH);
        //pass values to method in EventHandlers and "this" same window, so it can be disposed
        continueButton.addActionListener(e -> EventHandlers.handleButtonSelection(staticGarageButton, dynamicGarageButton, explanationLabel, this));


        setVisible(true);
    }
}

