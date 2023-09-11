package com.garageapp.ui;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class DynamicGarageSetup extends JFrame {

    List<JFormattedTextField> spotsFields = new ArrayList<>();
    JPanel levelsPanel = new JPanel();
    JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    //NumberFormatter to ensure only int are accepted
    NumberFormat format = NumberFormat.getInstance();
    NumberFormatter formatter = new NumberFormatter(format);

    public DynamicGarageSetup() {
        setTitle("Dynamische Garage Stellplätze");
        setSize(500, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        //will go ahead only on valid inputs
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);

        //setting up the levels panel
        levelsPanel.setLayout(new BoxLayout(levelsPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(levelsPanel);
        scrollPane.setPreferredSize(new Dimension(490, 120));
        add(scrollPane, BorderLayout.CENTER);
        addLevel();

        //add buttons to the buttons panel
        JButton addLevelButton = new JButton("Weitere Ebene hinzufügen");
        addLevelButton.addActionListener(e -> {
            addLevel();
            pack();
        });

        JButton setupButton = setupButton();

        JButton backButton = new JButton("Zurück");
        backButton.addActionListener(e -> {
            this.dispose();
            new GarageSetupSelection().setVisible(true);
        });

        buttonsPanel.add(backButton);
        buttonsPanel.add(addLevelButton);
        buttonsPanel.add(setupButton);

        //buttons panel to the SOUTH region ensures it stays at the bottom
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    private JButton setupButton() {
        JButton setupButton = new JButton("Garage Setup");
        setupButton.addActionListener(e -> {
            List<Integer> spotsPerLevelList = new ArrayList<>();
            for (JFormattedTextField field : spotsFields) {
                spotsPerLevelList.add(((Number) field.getValue()).intValue());
            }
            try {
                EventHandlers.handleDynamicSetup(
                        spotsPerLevelList,
                        this);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        return setupButton;
    }

    private void addLevel() {
        JLabel label = new JLabel("Parkplätze für Etage " + (spotsFields.size() + 1) + ":");
        JFormattedTextField spotsField = new JFormattedTextField(formatter);
        spotsFields.add(spotsField);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        panel.add(label);
        panel.add(spotsField);

        panel.setPreferredSize(new Dimension(panel.getPreferredSize().width, 25));

        levelsPanel.add(panel);
    }
}