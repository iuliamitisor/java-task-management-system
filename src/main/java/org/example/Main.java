package org.example;

import org.example.presentation.GUI;

import javax.swing.*;

public class Main extends JPanel {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            GUI gui = new GUI();
        });
    }
}
