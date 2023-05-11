import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Tester extends Panel {


    /**
     *
     */
    private static final long serialVersionUID = 1L;

    Panel ballPanel = new Panel();
    Label ballLabel = new Label();

    int nowX, nowY;


    public Tester() {



        MouseAdapter ml = new MouseAdapter() {

            @Override
            public void mouseMoved(MouseEvent e) {

                nowX = e.getX();
                nowY = e.getY();
                repaint();
                super.mouseMoved(e);
            }

        };


        setLayout(new GridLayout(1, 1));
        setBackground(Color.WHITE);
        addMouseMotionListener(ml);
        setVisible(true);
        setSize(400, 400);
    }


    @Override
    public void paint(Graphics g) {
        Graphics2D gg = (Graphics2D) g;
        gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gg.setColor(Color.BLUE);
        gg.fillOval(nowX, nowY, 20, 20);

    }

}



