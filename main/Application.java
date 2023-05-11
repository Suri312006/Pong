package main;

import javax.swing.*;
import java.awt.*;

public class Application extends JFrame {

    public Application() {

        initUI();
    }

    private void initUI() {

        add(new Board());
        setSize(CONSTANTS.WIDTH, CONSTANTS.HEIGHT);
        Ball lmao = new Ball(5,5, 50);


        setTitle("do the rump shaker dude");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setBackground(Color.BLACK);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            Application ex = new Application();
            ex.setVisible(true);
        });
    }
}
