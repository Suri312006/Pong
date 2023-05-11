
import java.awt.*;

public class Board extends Panel {
    Ball ball;
    public Board() {
        initBoard();
        ball = new Ball(0,0,30);
    }

    private void initBoard() {
        setPreferredSize(new Dimension(300, 300));
    }


    @Override
    public void paint(Graphics g) {
        Graphics2D gg = (Graphics2D) g;
        gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gg.setColor(Color.BLUE);
        gg.fillOval(ball.getxpos(), ball.getypos(), ball.getD(), ball.getD());

    }
}
