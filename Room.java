import javax.swing.JPanel;

/**
 * Room is a class that creates a JPanel with a given width and height.
 */
public class Room extends JPanel {
    private int width;
    private int height;
    private int startingx;
    private int startingy;

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
