import java.awt.*;
import javax.swing.*;

class AsteroidDemo extends JPanel {
    public void paintComponent(Graphics g) {
        int[] asteroid1_x_points = {40, 46, 52, 56, 56, 52, 48, 48, 44, 40, 44, 40};
        int[] asteroid1_y_points = {46, 40, 40, 46, 52, 56, 56, 50, 56, 50, 48, 46};
        g.drawPolygon(asteroid1_x_points, asteroid1_y_points, 11);
        
        int[] asteroid2_x_points = {60, 64, 68, 72, 76, 72, 76, 72, 66, 64, 60, 62, 60};
        int[] asteroid2_y_points = {44, 40, 42, 40, 44, 46, 50, 56, 54, 56, 52, 48, 44};
        g.drawPolygon(asteroid2_x_points, asteroid2_y_points, 13);
        
        int[] asteroid3_x_points = {80, 86, 84, 90, 96, 96, 90, 96, 92, 90, 82, 80, 80};
        int[] asteroid3_y_points = {44, 44, 40, 40, 44, 46, 48, 50, 56, 54, 56, 50, 44};
        g.drawPolygon(asteroid3_x_points, asteroid3_y_points, 11);
        
        int[] ship_x_points = {116, 100, 104, 128, 132, 116};
        int[] ship_y_points = {80, 128, 116, 116, 128, 80};
        g.drawPolygon(ship_x_points, ship_y_points, 6);
    }

    public static void main( String args[] ) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Asteroid Templates");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.white);
        frame.setSize(300, 200);

        AsteroidDemo panel = new AsteroidDemo();

        frame.add(panel);

        frame.setVisible(true);
    }
}