package main;

public class Score {

    private int score;

    public Score(){
        this.score = 0;
    }

    public void addPoint(){
        score++;
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
}
