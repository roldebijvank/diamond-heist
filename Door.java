import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Door is a class that creates a door object. It extends JPanel and implements
 * ActionListener. It has a method that checks if the thief is colliding with
 * the door. If so, it sends the thief to the next room when the player presses the spacebar.
 *
 */
public class Door extends JPanel implements ActionListener {
    private int x; 
    private int y;
    private int width;
    private int height;
    private Thief thief;
    private Room sendToRoom;
    private Room currentRoom;
    private Point sendToPoint;
    private boolean requiresDiamond;
    private static boolean exitDiamondMessageShown = false;

    /**
     * Creates a new Door object.
     * @param x is the x coordinate of the door
     * @param y is the y coordinate of the door
     * @param width is the width of the door
     * @param height is the height of the door
     * @param sendToRoom is the room that the door sends the thief to
     * @param requiresDiamond decides whether the door requires a key to be opened.  
     * @throws MalformedURLException to indicate that a malformed URL has occurred.
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
    
    /**
     * Checks if the thief is colliding with the door by using a timer and the actionListener class.
     * @param thief is the thief object that it checks for collision
     */
    public void checkCollision(Thief thief) {
        Timer timer = new Timer(10, this);
        this.thief = thief;
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.getBounds().intersects(thief.getBounds())
            && thief.getCurrentRoom() == currentRoom) {
            thief.onDoor = true;
            if (thief.doorClicked) {
                if (requiresDiamond && !thief.hasDiamond() && !exitDiamondMessageShown) {
                    String message = "You need a diamond to exit the game!";
                    String title = "Exit Requires Diamond";
                    JOptionPane.showMessageDialog(null, 
                                    message, title, JOptionPane.INFORMATION_MESSAGE);
                    exitDiamondMessageShown = true;
                }
                thief.getCurrentRoom().remove(thief);
                thief.getCurrentRoom().updateRoom();
                thief.setCurrentRoom(sendToRoom);
                thief.setCurrentPoint(sendToPoint);
                sendToRoom.add(thief);
                sendToRoom.updateRoom();
                thief.requestFocus(true);
                thief.doorClicked = false;

                if (requiresDiamond && thief.hasDiamond()) {
                    Ending.showEndGameDialog();
                }
            }
        }
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
}