package main;

import main.Ball;

import java.awt.*;

public class Board extends Panel {
    Ball ball;
    Paddle p1;
    public Board() {

        initBoard();
        ball = new Ball(CONSTANTS.WIDTH/2 - 15,CONSTANTS.HEIGHT/2 - 15,30);
        p1 = new Paddle(30, 400, 10, 100);
    }

    private void initBoard() {
        setPreferredSize(new Dimension(300, 300));
        setBackground(Color.black);
    }


    @Override
    public void paint(Graphics g) {
        Graphics2D gg = (Graphics2D) g;
        gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gg.setColor(Color.WHITE);
        gg.fillOval(ball.getxpos(), ball.getypos(), ball.getD(), ball.getD());
        gg.fillRect(p1.getXpos(), p1.getYpos(), p1.getWidth(), p1.getHeight());
        gg.fillRect(70, p1.getYpos(), p1.getWidth(), p1.getHeight());
    }
}
