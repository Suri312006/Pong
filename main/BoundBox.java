package main;

import java.awt.*;
/**
 * A rectangular container box, containing the bouncing ball.
 */
public class BoundBox {
    int minX, maxX, minY, maxY;  // Box's bounds (package access)
    private Color colorFilled;   // Box's filled color (background)
    private static final Color DEFAULT_COLOR_FILLED = Color.WHITE;

    private static int yspeed = 10;

    /** Constructors */
    public BoundBox(int x, int y, int width, int height, Color colorFilled) {
        minX = x;
        minY = y;
        maxX = x + width;
        maxY = y + height ;
        this.colorFilled = colorFilled;
        ;
    }

    /** Constructor with the default color */
    public BoundBox(int x, int y, int width, int height) {
        this(x, y, width, height, DEFAULT_COLOR_FILLED);
    }

    /** Set or reset the boundaries of the box. */
    public void set(int x, int y, int width, int height) {
        minX = x;
        minY = y;
        maxX = x + width;
        maxY = y + height;
    }

    /** Draw itself using the given graphic context. */
    public void draw(Graphics g) {
        g.setColor(colorFilled);
        g.fillRect(minX, minY, maxX - minX , maxY - minY);
    }

    public void move(double increment){
        this.minY += increment*yspeed;
        this.maxY += increment*yspeed;
    }
}