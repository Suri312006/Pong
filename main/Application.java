package main;

import test.SmoothMoves;

import javax.swing.*;
import java.awt.*;

public class Application {
    private static void createAndShowGUI() {
        JFrame f = new JFrame("Smooth Moves");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(CONSTANTS.WIDTH, CONSTANTS.HEIGHT);
        Board component = new Board();
        f.add(component);
        f.setVisible(true);
        //f.addKeyListener(component);
    }

    public static void main(String[] args) {
        Runnable doCreateAndShowGUI = new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        };
        SwingUtilities.invokeLater(doCreateAndShowGUI);
    }
}
