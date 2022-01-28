
import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {
    static int[] percolationArr = new int[1000];
    int square_len = 20;
    static int size;

    @Override
    public void paintComponent(Graphics g) {
        int current_x = 0;
        int current_y = 0;
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        int k = 0;

        for (int i = 0; i < size; i++) {
            current_x = 0;
            for (int j = 0; j < size; j++) {
                g2d.setColor(Color.black);
                g2d.drawRect(current_x, current_y, square_len, square_len);
                if (percolationArr[k] == 1)
                    g2d.setColor(Color.white);
                else
                    g2d.setColor(Color.gray);
                g2d.fillRect(current_x, current_y, square_len, square_len);
                current_x = current_x + square_len;
                k++;
            }
            current_y = current_y + square_len;
        }
    }

    public static void setPercolationArr(int[] arr, int n) {
        percolationArr = arr;
        size = n;
    }
}