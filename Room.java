import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Room is a class that creates a JPanel with a given width and height.
 */
public class Room extends JPanel {
    private int width;
    private int height;
    private Image backgroundImage;


    /**
     * Creates a new Room with a given width and height.
     */
    public Room(int width, int height, String backgroundImagePath) {
        this.setLayout(null);
        this.width = width;
        this.height = height;

        ImageIcon backgroundIcon = new ImageIcon(backgroundImagePath);
        this.backgroundImage = backgroundIcon.getImage().getScaledInstance(this.width,
                                                                        this.height,
                                                                        Image.SCALE_SMOOTH);
    }

    /**
     * Paints the background image.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    
        // Draw the background image.
        g.drawImage(backgroundImage, 0, 0, this);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void updateRoom() {
        repaint();
    }
}
