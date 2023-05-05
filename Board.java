
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Board extends JPanel {

    public Board() {
        initBoard();
    }

    private void initBoard() {
        setPreferredSize(new Dimension(300, 300));
    }


    @Override
    public void paintComponent(Graphics g) {

    }
}
