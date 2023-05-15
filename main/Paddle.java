package main;

public class Paddle {
    int xpos;
    int ypos;
    int height;
    int width;

    ContainerBox lol;

    static int moveSpeed = 3;

    public Paddle(int xpos, int ypos, int width, int height) {
        this.xpos = xpos;
        this.ypos = ypos;
        this.height = height;
        this.width = width;
    }
    public int getXpos(){
        return xpos;
    }
    public int getYpos(){
        return ypos;
    }
    public int getHeight(){
        return height;
    }
    public int getWidth(){
        return width;
    }

    public void move(double scale){
        this.ypos += moveSpeed*scale;
    }
}
