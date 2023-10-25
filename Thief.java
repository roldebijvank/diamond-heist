import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
// import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Creates Thief object. Sets behaviour.
 */

public class Thief extends JPanel implements KeyListener {
    // private boolean isDetected;
    // private ArrayList<String> items;
    public int x;
    public int y;
    private JLabel thiefLabel;
    private Room currentRoom;
    public boolean onDoor = false;
    public boolean onLadder = false;
    public boolean up = false;
    public boolean down = false;
    public boolean onButton = false;
    public boolean buttonPressed = false;
    public boolean doorClicked = false;

    /**
     * creates an instance of Thief.
     * @param x is the x coordinate of the thief
     * @param y is the y coordinate of the thief
     * @param imageURL is the URL of the image of the thief
     */
    public Thief(int x, int y, URL imageURL, Room currentRoom) {
        this.x = x;
        this.y = y;
        this.currentRoom = currentRoom;

        ImageIcon icon = new ImageIcon(imageURL);
        Image scaledImage = icon.getImage().getScaledInstance(70, 120, Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaledImage);
        
        thiefLabel = new JLabel(icon);
        this.add(thiefLabel);
        this.setOpaque(false);

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        
    }

    /**
     * Moves the thief to the right.
     */
    public void moveRight() {
        x += 10;
        this.setBounds(x, y, thiefLabel.getWidth(), thiefLabel.getHeight());
    }

    /**
     * Moves the thief to the left.
     */
    public void moveLeft() {
        x -= 10;
        this.setBounds(x, y, thiefLabel.getWidth(), thiefLabel.getHeight());
    }

    /**
     * Moves the thief up.
     */
    public void moveUp() {
        y -= 1;
        this.setBounds(x, y, thiefLabel.getWidth(), thiefLabel.getHeight());
    }

    /**
     * Moves the thief down.
     */
    public void moveDown() {
        y += 1;
        this.setBounds(x, y, thiefLabel.getWidth(), thiefLabel.getHeight());
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT) {
            moveLeft();
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            moveRight();
        } else if (keyCode == KeyEvent.VK_UP) {
            if (onLadder) {
                up = true;
                down = false;
            } else {
                up = false;
                down = false;
            }
        } else if (keyCode == KeyEvent.VK_DOWN) {
            if (onLadder) {
                down = true;
                up = false;
            } else {
                down = false;
                up = false;
            }
        } else if (keyCode == KeyEvent.VK_SPACE) {
            if (onDoor) {
                doorClicked = true;
            }

            if (onButton) {
                buttonPressed = true;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE) {
            doorClicked = false;
            buttonPressed = false;
        }
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

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }
}