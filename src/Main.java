
import javax.swing.*;
import java.awt.*;

public class Main { 
    static JFrame f;
    static MyPanel p = new MyPanel();

    public static void display() {
        f = new JFrame();
        Container c = f.getContentPane();
        c.setLayout(new BorderLayout());
        c.add(p);
        f.setSize(800, 800);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main() {
        display();
    }
}