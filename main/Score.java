package main;

import java.awt.*;

public class Score {

    private int score;

    int xpos;
    int ypos;

    public Score(int xpos, int ypos){
        this.score = 0;
        this.xpos = xpos;
        this.ypos = ypos;
    }

    public void addPoint(Ball x){
        score++;
        x.reset();
    }

    public void reset(){
        score = 0;
    }

    public int getScore(){
        return score;
    }

    public String toString(){
        return "Score: "+score;
    }

    public void draw(Graphics2D g){

        g.setColor(Color.WHITE);
        Font f = new Font("Bit5x3", Font.BOLD, 144);
        g.setFont(f);
        g.drawString(""+score, xpos, ypos);

    }
}
