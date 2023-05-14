package main;

import java.awt.*;

public class Ball {
    int xpos;
    int ypos;
    double xspeed;
    int yspeed;

    int diameter;
    public Ball(int xpos, int ypos, int diameter){
        this.xpos = xpos;
        this.ypos = ypos;
        this.diameter = diameter;
        this.xspeed =10;
        this.yspeed=0;
    }

    public int getxpos(){
        return xpos;
    }
    public int getypos(){
        return ypos;
    }
    public int getD(){
        return diameter;
    }

public void moveBall(){
        xpos  += xspeed;

    }

}

