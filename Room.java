import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class Room extends JPanel {
    private int width;
    private int height;

    public Room(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public JPanel createRoom() {
        JPanel room = new JPanel();
        return room;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}