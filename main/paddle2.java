package main;


import main.Ball;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class paddle2 extends JComponent implements ActionListener, KeyListener {

    Paddle p2;

    final static int CYCLE_TIME = 2000;

    long cycleStart;
    Timer timer = null; // animation Timer
    int currentResolution = 50; // current Timer resolution
    public paddle2() {
        cycleStart = System.nanoTime() / 1000000;
        startTimer(currentResolution);
        initBoard();
        repaint();
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
        setPreferredSize(new Dimension(3, 0));

    }


    @Override
    public void paintComponent(Graphics g) {
        Graphics2D gg = (Graphics2D) g;
        gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        gg.setColor(Color.WHITE);
        //Paddle 1

        //Paddle 2
        gg.fillRect(p2.getXpos(), p2.getYpos(), p2.getWidth(), p2.getHeight());
        //Ball
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

    public synchronized void keyPressed(KeyEvent e) {

        int keyCode = e.getKeyCode();
//        if(keyCode == KeyEvent.VK_W){
//            p1.move(-1);
//            repaint();
//
//        }
//        if(keyCode == KeyEvent.VK_S){
//            p1.move(1);
//            repaint();
//
//        }

        if(keyCode == KeyEvent.VK_UP){
            p2.move(-1);
            repaint();

        }
        if(keyCode == KeyEvent.VK_DOWN){
            p2.move(1);
            repaint();

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
}

