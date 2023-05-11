package test;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Tester2 {
    public static void main(String[] arg) {
        Tester mb = new Tester();
        Frame frame = new Frame("Test drawing");
        frame.addWindowListener(new WindowAdapter() {


            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                super.windowClosing(e);
            }


        });
        frame.setLayout(new GridLayout(1, 1));
        frame.add(mb);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }

}
