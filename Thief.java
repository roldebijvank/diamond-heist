import java.net.URL;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Image;

/**
 * Creates Thief object. Sets behaviour.
 */

public class Thief extends JPanel implements KeyListener {
    private boolean isDetected;
    private ArrayList<String> items;
    public int x;
    public int y;
    private JLabel thiefLabel;
    private Room currentRoom;
    public boolean onDoor = false;
    public boolean onLadder = false;
    public boolean up = false;
    public boolean down = false;

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
        Image scaledImage = icon.getImage().getScaledInstance(50, 100, Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaledImage);
        
        thiefLabel = new JLabel(icon);
        this.add(thiefLabel);

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        
    }

    public void moveRight() {
        x += 10;
        this.setBounds(x, y, thiefLabel.getWidth(), thiefLabel.getHeight());
    }

    public void moveLeft() {
        x -= 10;
        this.setBounds(x, y, thiefLabel.getWidth(), thiefLabel.getHeight());
    }

    public void moveUp() {
        y -= 1;
        this.setBounds(x, y, thiefLabel.getWidth(), thiefLabel.getHeight());
    }

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
            System.out.println(onLadder);
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
            onDoor = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

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