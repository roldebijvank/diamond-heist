import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Door is a class that creates a door object. It extends JPanel and implements
 * ActionListener. It has a method that checks if the thief is colliding with
 * the door. If so, it sends the thief to the next room when the player presses the spacebar.
 *
 */
public class Door extends JPanel {
    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private final Room sendToRoom;
    private final Room currentRoom;
    private final Point sendToPoint;
    private final boolean requiresDiamond;

    /**
     * Creates a new Door object.
     * @param x is the x coordinate of the door
     * @param y is the y coordinate of the door
     * @param width is the width of the door
     * @param height is the height of the door
     * @param sendToRoom is the room that the door sends the thief to
     * @param requiresDiamond decides whether the door requires a key to be opened.  
     */
    public Door(int x, int y, int width, int height,
                Room sendToRoom, Room currentRoom, Point sendToPoint, boolean requiresDiamond) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.sendToRoom = sendToRoom;
        this.currentRoom = currentRoom;
        this.sendToPoint = sendToPoint;
        this.requiresDiamond = requiresDiamond;

        ImageIcon doorIcon;
        if (requiresDiamond) {
            doorIcon = new ImageIcon("img/exit_door.png");
        } else {
            doorIcon = new ImageIcon("img/door.png");
        }
        Image scaledImage = doorIcon.getImage().getScaledInstance(width, height,
                Image.SCALE_SMOOTH);
        doorIcon = new ImageIcon(scaledImage);
        JLabel doorLabel = new JLabel(doorIcon);
        this.add(doorLabel);
        this.setOpaque(false);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    public boolean getRequiresDiamond() {
        return this.requiresDiamond;
    }

    public Room getSendToRoom() {
        return this.sendToRoom;
    }

    public Point getSendToPoint() {
        return this.sendToPoint;
    }
}