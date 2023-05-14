package main;

import main.Ball;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Board extends JComponent implements ActionListener, KeyListener {
    Ball ball;
    Paddle p1;
    Paddle p2;

    private final Set<Integer> pressedKeys = new HashSet<>();

    final static int CYCLE_TIME = 2000;

    long cycleStart;
    Timer timer = null; // animation Timer
    int currentResolution = 50; // current Timer resolution
    public Board() {

        cycleStart = System.nanoTime() / 1000000;
        startTimer(currentResolution);

        initBoard();
        ball = new Ball(CONSTANTS.WIDTH/2 - 15,CONSTANTS.HEIGHT/2 - 15,30);
        repaint();
        p1 = new Paddle(80, 350, 10, 100);
        p2 = new Paddle(800, 350, 10, 100);

    }

    private void startTimer(int resolution) {
        if (timer != null) {
            timer.stop();
            timer.setDelay(resolution);
        } else {
            timer = new Timer(resolution, this);
        }
        timer.start();
    }

    private void initBoard() {
        setPreferredSize(new Dimension(300, 300));

    }


    @Override
    public void paintComponent(Graphics g) {
        Graphics2D gg = (Graphics2D) g;
        gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        gg.setColor(Color.BLACK);
        g.fillRect(0,0, CONSTANTS.WIDTH, CONSTANTS.HEIGHT);

        gg.setColor(Color.WHITE);
        //Paddle 1
        gg.fillRect(p1.getXpos(), p1.getYpos(), p1.getWidth(), p1.getHeight());
        //Paddle 2
        gg.fillRect(p2.getXpos(), p2.getYpos(), p2.getWidth(), p2.getHeight());
        //Ball
        gg.fillOval(ball.getxpos(), ball.getypos(), ball.getD(), ball.getD());

        if(ball.getxpos() == p1.getXpos()){
            ball.xspeed *= -1;
        }
        if(ball.getxpos() == p2.getXpos()){
            ball.xspeed *= -1;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // calculate the fraction elapsed of the animation and call animate()
        // to alter the values accordingly
        long currentTime = System.nanoTime() / 1000000;
        long totalTime = currentTime - cycleStart;
        if (totalTime > CYCLE_TIME) {
            cycleStart = currentTime;
        }
        float fraction = (float) totalTime / CYCLE_TIME;
        fraction = Math.min(1.0f, fraction);
        fraction = 1 - Math.abs(1 - (2 * fraction));
        //animate(fraction);
    }
    @Override
    public synchronized void keyPressed(KeyEvent e) {
        pressedKeys.add(e.getKeyCode());
        Point offset = new Point();
        if (!pressedKeys.isEmpty()) {
            for (Iterator<Integer> it = pressedKeys.iterator(); it.hasNext();) {
                switch (it.next()) {
                    case KeyEvent.VK_W:
                        p1.move(-1);
                        repaint();
                        break;
                    case KeyEvent.VK_UP:
                        p2.move(-1);
                        repaint();
                        break;

                    case KeyEvent.VK_S:
                        p1.move(1);
                        repaint();
                        break;
                    case KeyEvent.VK_DOWN:
                        p2.move(1);
                        repaint();
                        break;
                }
            }
        }
        System.out.println(offset); // Do something with the offset.
    }
    @Override
    public void keyReleased(KeyEvent e) {
        pressedKeys.remove(e.getKeyCode());
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
}
