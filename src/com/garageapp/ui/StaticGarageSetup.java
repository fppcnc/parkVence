package com.garageapp.ui;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class StaticGarageSetup extends JFrame {

    //accept only int
    private JFormattedTextField numLevelsField;
    private JFormattedTextField spotsPerLevelField;

    public StaticGarageSetup() {

        setTitle("Statische Garage Setup");
        setSize(500, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        //numberFormatter to ensure only int are accepted
        NumberFormat format = NumberFormat.getInstance();

        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);

        //will go through only on valid inputs. int in this case
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);

        add(new JLabel()); //dummy for positioning

        JLabel numLevelsLabel = new JLabel("Anzahl der Etagen:");
        numLevelsField = new JFormattedTextField(formatter);
        add(numLevelsLabel);
        add(numLevelsField);

        JLabel spotsPerLevelLabel = new JLabel("Parkplätze pro Etage:");
        spotsPerLevelField = new JFormattedTextField(formatter);
        add(spotsPerLevelLabel);
        add(spotsPerLevelField);

        JButton setupButton = new JButton("Garage Setup");
        setupButton.addActionListener(e -> EventHandlers.handleStaticSetup(
                ((Number) numLevelsField.getValue()).intValue(),
                ((Number) spotsPerLevelField.getValue()).intValue()
        ));




        add(setupButton);
    }

}

