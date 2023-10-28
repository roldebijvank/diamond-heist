import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
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
public class Hatch extends JPanel implements ActionListener {
    int x; 
    int y;
    int width = 100;
    int height = 50;
    Thief thief;
    Room sendToRoom;
    Room currentRoom;
    boolean spacePressed = false;
    Image image;


    /**
     * Creates a new Hatch object.
     * @param x is the x coordinate of the hatch
     * @param y is the y coordinate of the hatch
     * @param imageUrl is the URL of the image of the hatch
     * @param sendToRoom is the room that the hatch sends the thief to
     */
    public Hatch(int x, int y,
                URL imageUrl, Room sendToRoom, Room currentRoom) {
        this.x = x;
        this.y = y;
        this.sendToRoom = sendToRoom;
        this.currentRoom = currentRoom;

        ImageIcon hatchIcon = new ImageIcon(imageUrl);
        Image scaledImage = hatchIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        hatchIcon = new ImageIcon(scaledImage);
        JLabel hatchLabel = new JLabel(hatchIcon);
        this.add(hatchLabel);
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
                if (thief.hasKey()) {
                    // Thief has the key, open the hatch
                    openHatch();
                } else {
                    // Thief doesn't have the key, show a message to get one
                    JOptionPane.showMessageDialog(null, "You need a key to open the hatch!");
                }
                
            }
        }
    }

    private void openHatch() {
        thief.getCurrentRoom().remove(thief);
        thief.getCurrentRoom().updateRoom();
        thief.setCurrentRoom(sendToRoom);
        sendToRoom.add(thief);
        sendToRoom.setThiefToStartingPoint(thief);
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
}