package main;

import java.awt.*;
import java.util.Random;
import java.util.random.RandomGenerator;


public class Ball {
    float x, y;
    float speedX, speedY;
    float baseSpeed = 5;
    float radius;
    private Color color;
    private static final Color DEFAULT_COLOR = Color.WHITE;

    private int angleInDegree;


    public Ball(float x, float y, float radius, float speed, float angleInDegree,
                Color color) {
        this.x = x;
        this.y = y;
        // Convert (speed, angle) to (x, y), with y-axis inverted
        this.speedX = (float)(speed * Math.cos(Math.toRadians(angleInDegree)));
        this.speedY = (float)(-speed * (float)Math.sin(Math.toRadians(angleInDegree)));
        this.radius = radius;
        this.color = color;
    }
    /** Constructor with the default color */
    public Ball(float x, float y, float radius, float speed, float angleInDegree) {
        this(x, y, radius, speed, angleInDegree, DEFAULT_COLOR);
    }
    public Ball(float x, float y) {
        this(x, y, 20, 5, 10, DEFAULT_COLOR);
    }

    /** Draw itself using the given graphics context. */
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval((int)(x - radius), (int)(y - radius), (int)(2 * radius), (int)(2 * radius));
    }

    public void BackgroundCollisionDetection(BoundBox box) {
        // Get the ball's bounds, offset by the radius of the ball
        float ballMinX = box.minX + radius;
        float ballMinY = box.minY + radius;
        float ballMaxX = box.maxX - radius;
        float ballMaxY = box.maxY - radius;

        // Calculate the ball's new position
        x += speedX;
        y += speedY;
        // Check if the ball moves over the bounds. If so, adjust the position and speed.
        if (x < ballMinX) {
            speedX = -speedX; // Reflect along normal
            x = ballMinX;     // Re-position the ball at the edge
        } else if (x > ballMaxX) {
            speedX = -speedX;
            x = ballMaxX;
        }
        // May cross both x and y bounds
        if (y < ballMinY) {
            speedY = -speedY;
            y = ballMinY;
        } else if (y > ballMaxY) {
            speedY = -speedY;
            y = ballMaxY;
        }
    }
    public void PaddleCollisionDetection(BoundBox box, int paddleNum) {
        // Get the box's bounds, offset by the radius of the ball
        Random lol = new Random();
        float randAngleMult = lol.nextFloat(160, 230);
        float boxMinX = box.minX + radius;
        float boxMinY = box.minY + radius;
        float boxMaxX = box.maxX - radius;
        float boxMaxY = box.maxY - radius;

        float ballMinX = x - 2*radius;
        float ballMinY = y - radius;
        float ballMaxX = x + 2*radius;
        float ballMaxY = y + radius;

        // Check if the ball moves over the bounds. If so, adjust the position and speed.     
        if (paddleNum == 1) {
            if ((ballMinX < boxMaxX)&& (ballMaxY > boxMinY) && (ballMinY < boxMaxY)) {
                //speedX = -speedX*randAngleMult;
                this.speedX = baseSpeed+(float)(speedX * Math.cos(Math.toRadians(randAngleMult)));
                x = boxMaxX + radius*2 + 1;;
                this.speedY = baseSpeed+(float)(speedY * Math.sin(Math.toRadians(randAngleMult)));


                //this.speedY = (float)(-speed * (float)Math.sin(Math.toRadians(angleInDegree)))

                // Re-position the ball at the edge
            }
        }
        if ( paddleNum == 2){
            if ((ballMaxX > boxMinX)&& (ballMaxY > boxMinY) && (ballMinY < boxMaxY)) {

              this.speedX = baseSpeed+(float)(speedX * Math.cos(Math.toRadians(randAngleMult)));
              x = boxMaxX - radius*2 - 1;;
              this.speedY = baseSpeed+(float)(-1*speedY * Math.sin(Math.toRadians(randAngleMult)));
            }

        }

    }

}