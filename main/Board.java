package main;

import main.Ball;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Board extends JPanel implements ActionListener, KeyListener {
    Ball ball;
    BoundBox p1;
    BoundBox p2;
    BoundBox background;

    private Drawer artist;
    private final Set<Integer> pressedKeys = new HashSet<>();
    private final static int UPDATE_RATE = 60;

    public Board() {

        ball = new Ball(CONSTANTS.WIDTH/2 - 15,CONSTANTS.HEIGHT/2 - 15);
        p1 = new BoundBox(80, 350, 10, 100);
        p2 = new BoundBox(800, 350, 10, 100);
        background = new BoundBox(0,0, CONSTANTS.WIDTH, CONSTANTS.HEIGHT, Color.BLACK);

        artist = new Drawer();
        this.setLayout(new BorderLayout());
        this.add(artist, BorderLayout.CENTER);


        repaint();
        gameStart();

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
        gameThread.start();  // Invoke GameThread.run()
    }

    public void gameUpdate() {
        ball.BackgroundCollisionDetection(background);
        ball.PaddleCollisionDetection(p1, 1);
        ball.PaddleCollisionDetection(p2, 2);
        System.out.println(ball.x + " " + ball.y);
        System.out.println(p1.maxX);
        System.out.println(p2.minX);
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
    public class Drawer extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D)g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//            super.paintComponent(g);    // Paint background
            // Draw the box and the ball
            background.draw(g2d);
            p1.draw(g2d);
            p2.draw(g2d);
            ball.draw(g2d);
        }

        /** Called back to get the preferred size of the component. */
        @Override
        public Dimension getPreferredSize() {
            return (new Dimension(CONSTANTS.WIDTH, CONSTANTS.HEIGHT));
        }
    }


}
