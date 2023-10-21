import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Door is a class that creates a door object. It extends JPanel and implements
 * ActionListener. It has a method that checks if the thief is colliding with
 * the door. If so, it sends the thief to the next room when the player presses the spacebar.
 *
 */
public class Door extends JPanel implements ActionListener {
    int x; 
    int y;
    int width;
    int height;
    Thief thief;
    Room sendToRoom;
    boolean spacePressed = false;
    Image image;

    /**
     * Creates a new Door object.
     * @param x is the x coordinate of the door
     * @param y is the y coordinate of the door
     * @param width is the width of the door
     * @param height is the height of the door
     * @param imageUrl is the URL of the image of the door
     * @param sendToRoom is the room that the door sends the thief to
     */
    public Door(int x, int y, int width, int height, URL imageUrl, Room sendToRoom) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.sendToRoom = sendToRoom;

        ImageIcon doorIcon = new ImageIcon(imageUrl);
        Image scaledImage = doorIcon.getImage().getScaledInstance(50, 100, Image.SCALE_SMOOTH);
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
        this.repaint();
        if (this.getBounds().intersects(thief.getBounds())) {
            thief.onDoor = true;
            if (thief.space) {
                thief.getCurrentRoom().remove(thief);
                thief.getCurrentRoom().updateRoom();
                thief.setCurrentRoom(sendToRoom);
                sendToRoom.add(thief);
                sendToRoom.setThiefToStartingPoint(thief);
                thief.repaint();
                sendToRoom.updateRoom();
                thief.requestFocus(true);
                thief.space = false;
            }
        } else {
            thief.onDoor = false;
        }
    }
}