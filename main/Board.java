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
    BoundBox p1;
    BoundBox p2;

    BoundBox background;

    private final Set<Integer> pressedKeys = new HashSet<>();

    private final static int UPDATE_RATE = 60;


    long cycleStart;
    Timer timer = null; // animation Timer
    int currentResolution = 50; // current Timer resolution
    public Board() {

        initBoard();

        ball = new Ball(CONSTANTS.WIDTH/2 - 15,CONSTANTS.HEIGHT/2 - 15);
        repaint();
        p1 = new BoundBox(80, 350, 10, 100);
        p2 = new BoundBox(800, 350, 10, 100);
        background = new BoundBox(0,0, CONSTANTS.WIDTH, CONSTANTS.HEIGHT);

        gameStart();

    }

    private void initBoard() {
        setPreferredSize(new Dimension(300, 300));

    }

    public void gameStart() {
        // Run the game logic in its own thread.
        Thread gameThread = new Thread() {
            public void run() {
                while (true) {
                    // Execute one time-step for the game
                    gameUpdate();
                    // Refresh the display
                    repaint();
                    // Delay and give other thread a chance
                    try {
                        Thread.sleep(1000 / UPDATE_RATE);
                    } catch (InterruptedException ex) {}
                }
            }
        };
        gameThread.start();  // Invoke GaemThread.run()
    }

    public void gameUpdate() {
        ball.moveOneStepWithCollisionDetection(background);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D gg = (Graphics2D) g;
        gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        gg.setColor(Color.BLACK);
        gg.fillRect(0,0, CONSTANTS.WIDTH, CONSTANTS.HEIGHT);

        //Paddle 1
        p1.draw(g);
        //Paddle 2
        p2.draw(g);
        //ball
        ball.draw(g);
    }


    @Override
    public synchronized void keyPressed(KeyEvent e) {
        pressedKeys.add(e.getKeyCode());
        Point offset = new Point();
        for (Iterator<Integer> it = pressedKeys.iterator(); it.hasNext(); ) {
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
    @Override
    public void keyReleased(KeyEvent e) {
        pressedKeys.remove(e.getKeyCode());
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
