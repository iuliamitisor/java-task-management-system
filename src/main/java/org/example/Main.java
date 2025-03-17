package org.example;

import org.example.presentation.GUI;

import javax.swing.*;

public class Main extends JPanel {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            // Create the GUI and show the user all functionalities.
            GUI gui = new GUI();
        });
    }
}
