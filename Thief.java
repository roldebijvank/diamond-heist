import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
// import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Creates Thief object. Sets behaviour.
 */

public class Thief extends JPanel implements KeyListener, ActionListener {
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
    private boolean left = false;
    private boolean right = false;
    public boolean space = false;
    Timer timer = new Timer(10, this);

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

    /**
     * Moves the thief to the right.
     */
    public void moveRight() {
        if (this.getX() + this.getWidth() + 3 <= currentRoom.getWidth()) {
            x += 3;
            this.setBounds(x, y, thiefLabel.getWidth(), thiefLabel.getHeight());
        }
    }

    /**
     * Moves the thief to the left.
     */
    public void moveLeft() {
        if (this.getX() - 3 >= 0) {
            x -= 3;
            this.setBounds(x, y, thiefLabel.getWidth(), thiefLabel.getHeight());
        }
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
        if (keyCode == KeyEvent.VK_LEFT && !right && !up && !down) {
            left = true;
            timer.start();
        } else if (keyCode == KeyEvent.VK_RIGHT && !left && !up && !down) {
            right = true;
            timer.start();
        } else if (keyCode == KeyEvent.VK_UP && !down && !left && !right) {
            System.out.println(onLadder);
            if (onLadder) {
                up = true;
                down = false;
            } else {
                up = false;
                down = false;
            }
        } else if (keyCode == KeyEvent.VK_DOWN && !up && !left && !right) {
            if (onLadder) {
                down = true;
                up = false;
            } else {
                down = false;
                up = false;
            }
        } else if (keyCode == KeyEvent.VK_SPACE && !left && !right && !up && !down) {
            if (onDoor) {
                space = true;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT) {
            left = false;
            timer.stop();
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            right = false;
            timer.stop();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (left) {
            moveLeft();
        } else if (right) {
            moveRight();
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