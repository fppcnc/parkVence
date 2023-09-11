package com.garageapp.ui;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.NumberFormat;

public class StaticGarageSetup extends JFrame {

    //NumberFormatter to ensure only int are accepted
    private JFormattedTextField numLevelsField;
    private JFormattedTextField spotsPerLevelField;
    JPanel levelsPanel = new JPanel();
    JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

    public StaticGarageSetup() {

        setTitle("Statische Garage Setup");
        setSize(500, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //numberFormatter to ensure only int are accepted
        NumberFormat format = NumberFormat.getInstance();

        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        //will go ahead only on valid inputs
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);



        levelsPanel.setLayout(new BoxLayout(levelsPanel, BoxLayout.Y_AXIS));
        add(levelsPanel, BorderLayout.CENTER);
        JLabel numLevelsLabel = new JLabel("Anzahl der Etagen:");
        numLevelsField = new JFormattedTextField(formatter);


        JLabel spotsPerLevelLabel = new JLabel("Parkplätze pro Etage:");
        spotsPerLevelField = new JFormattedTextField(formatter);


        JButton setupButton = new JButton("Garage Setup");
        setupButton.addActionListener(e -> EventHandlers.handleStaticSetup(
                ((Number) numLevelsField.getValue()).intValue(),
                ((Number) spotsPerLevelField.getValue()).intValue(),
                this
        ));

        //re open GarageSetupSelection frame
        JButton backButton = new JButton("Zurück");
        backButton.addActionListener(e -> {
            this.dispose();
            new GarageSetupSelection().setVisible(true);
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));

        panel.add(numLevelsLabel);
        panel.add(numLevelsField);
        panel.add(spotsPerLevelLabel);
        panel.add(spotsPerLevelField);
        levelsPanel.add(panel);

        buttonsPanel.add(backButton);
        buttonsPanel.add(setupButton);

        //buttons panel to the SOUTH region ensures it stays at the bottom
        add(buttonsPanel, BorderLayout.SOUTH);




    }

}

