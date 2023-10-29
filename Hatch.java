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
public class Hatch extends JPanel {
    private final int x;
    private final int y;
    private final int width = 100;
    private final int height = 50;
    private Thief thief;
    private final Room sendToRoom;
    private final Room currentRoom;


    /**
     * Creates a new Hatch object.
     * @param x is the x coordinate of the hatch
     * @param y is the y coordinate of the hatch
     * @param sendToRoom is the room that the hatch sends the thief to
     */
    public Hatch(int x, int y, Room sendToRoom, Room currentRoom) {
        this.x = x;
        this.y = y;
        this.sendToRoom = sendToRoom;
        this.currentRoom = currentRoom;

        ImageIcon hatchIcon = new ImageIcon("img/hatch.png");
        Image scaledImage = hatchIcon.getImage().getScaledInstance(width, height,
                                                                   Image.SCALE_SMOOTH);
        hatchIcon = new ImageIcon(scaledImage);
        JLabel hatchLabel = new JLabel(hatchIcon);
        this.add(hatchLabel);
        this.setOpaque(false);
    }

    /**
     * Checks if the thief is colliding with the hatch.
     */
    public void openHatch() {
        thief.getCurrentRoom().remove(thief);
        thief.getCurrentRoom().updateRoom();
        thief.setCurrentRoom(sendToRoom);
        sendToRoom.add(thief);
        thief.setCurrentPoint(new Point(0, 105));
        thief.repaint();
        sendToRoom.updateRoom();
        thief.requestFocus(true);
        thief.doorClicked = false;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }
}