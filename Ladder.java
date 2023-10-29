import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Creates a new Ladder object. The thief can go up and down this ladder.
 */
public class Ladder extends JPanel {
    private final int width = 70;
    private final int height = 233;
    private final Room sendToRoom;
    private final Room currentRoom;
    private final Point sendToPoint;

    /**
     * Creates a new Ladder object.
     * @param sendToRoom is the room that the ladder sends the thief to
     */
    public Ladder(Room currentRoom, Room sendToRoom, Point sendToPoint) {
        this.sendToRoom = sendToRoom;
        this.sendToPoint = sendToPoint;
        this.currentRoom = currentRoom;
        
        ImageIcon ladderIcon = new ImageIcon("img/ladder.png");
        Image scaledImage = ladderIcon.getImage()
                            .getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ladderIcon = new ImageIcon(scaledImage);
        this.add(new JLabel(ladderIcon));
        this.setOpaque(false);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public Room getSendToRoom() {
        return sendToRoom;
    }

    public Point getSendToPoint() {
        return sendToPoint;
    }
}