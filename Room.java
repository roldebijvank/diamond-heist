import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Room is a class that creates a JPanel with a given width and height.
 */
public class Room extends JPanel {
    private int width;
    private int height;
    private int startingx;
    private int startingy;
    private Image backgroundImage;

    /**
     * Creates a new Room with a given width and height.
     */
    public Room(int width, int height, int startingx, int startingy, URL backgroundImageURL) {
        this.setLayout(null);
        this.width = width;
        this.height = height;
        this.startingx = startingx;
        this.startingy = startingy;

        ImageIcon backgroundIcon = new ImageIcon(backgroundImageURL);
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

    /**
     * Creates a new Room with a given width and height.
     */
    public Room(int width, int height, int startingx, int startingy) {
        this.setLayout(null);
        this.width = width;
        this.height = height;
        this.startingx = startingx;
        this.startingy = startingy;
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

    public int getStartingx() {
        return startingx;
    }

    public int getStartingy() {
        return startingy;
    }

    /**
     * Sets the thief to the starting point of the room.
     * @param thief is the thief that is set to the starting point
     */
    public void setThiefToStartingPoint(Thief thief) {
        thief.x = startingx;
        thief.y = startingy;
        thief.setBounds(0, 100, thief.getWidth(), thief.getHeight());
        repaint();
        thief.repaint();
    }
}
