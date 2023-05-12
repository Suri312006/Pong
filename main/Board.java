package main;

import main.Ball;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Board extends Panel {
    Ball ball;
    Paddle p1;
    Paddle p2;
    public Board() {

        initBoard();
        ball = new Ball(CONSTANTS.WIDTH/2 - 15,CONSTANTS.HEIGHT/2 - 15,30);
        repaint();
        p1 = new Paddle(800, 350, 10, 100);
        p2 = new Paddle(80, 350, 10, 100);

        MouseAdapter ml = new MouseAdapter() {

            @Override
            public void mouseMoved(MouseEvent e) {

                p1.ypos = e.getX();
                p1.ypos = e.getY();
                repaint();
                super.mouseMoved(e);
            }

        };
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
        gg.fillRect(p1.getXpos(), p1.getYpos(), p1.getWidth(), p1.getHeight());
        gg.fillRect(p2.getXpos(), p1.getYpos(), p1.getWidth(), p1.getHeight());
        gg.fillOval(ball.getxpos(), ball.getypos(), ball.getD(), ball.getD());
        ball.moveBall();


        if(ball.getxpos() == p1.getXpos()){
                ball.xspeed *= -1;
        }
        if(ball.getxpos() == p2.getXpos()){
                ball.xspeed *= -1;
        }
        }
    }

