import java.awt.*;

public class Ball {
    int xpos;
    int ypos;

    int diameter;
    public Ball(int xpos, int ypos, int diameter){
        this.xpos = xpos;
        this.ypos = ypos;
        this.diameter = diameter;
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
}
