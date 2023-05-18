package main;

import java.awt.*;
import java.util.Formatter;
/**
 * The bouncing ball.
 */
public class Ball {
    float x, y;           // Ball's center x and y (package access)
    float speedX, speedY; // Ball's speed per step in x and y (package access)
    float radius;         // Ball's radius (package access)
    private Color color;  // Ball's color
    private static final Color DEFAULT_COLOR = Color.WHITE;

    /**
     * Constructor: For user friendliness, user specifies velocity in speed and
     * moveAngle in usual Cartesian coordinates. Need to convert to speedX and
     * speedY in Java graphics coordinates for ease of operation.
     */
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
        this(x, y, 15, 5, 10, DEFAULT_COLOR);
    }

    /** Draw itself using the given graphics context. */
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval((int)(x - radius), (int)(y - radius), (int)(2 * radius), (int)(2 * radius));
    }

    /**
     * Make one move, check for collision and react accordingly if collision occurs.
     *
     * @param box: the container (obstacle) for this ball.
     */
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
            if ((ballMinX < boxMaxX)&& (ballMinY > boxMinY) && (ballMinY < boxMaxY)) {
                speedX = -speedX;
                speedY = -speedY;// Reflect along normal
                // Re-position the ball at the edge
            }
        }
        if ( paddleNum == 2){
            if ((ballMaxX > boxMinX) && (ballMaxY > boxMinY) && (ballMinY < boxMaxY)) {
                speedX = -speedX;
                speedY = -speedY;// Reflect along normal
                // Re-position the ball at the edge
            }
        }

    }
    //TODO need a collision detection for paddles

    //Return the magnitude of speed
    public float getSpeed() {
        return (float)Math.sqrt(speedX * speedX + speedY * speedY);
    }

    //Return the direction of movement in degrees (counter-clockwise)
    public float getMoveAngle() {
        return (float)Math.toDegrees(Math.atan2(-speedY, speedX));
    }


}