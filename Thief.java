import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Creates Thief object. Sets behaviour.
 */

public class Thief extends JPanel implements KeyListener, ActionListener {
    private ArrayList<CollectableItem> collectedItems = new ArrayList<>();
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
        if (keyCode == KeyEvent.VK_LEFT) {
            left = false;
            timer.stop();
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            right = false;
            timer.stop();
        } else if (keyCode == KeyEvent.VK_SPACE) {
            space = false;
            doorClicked = false;
            buttonPressed = false;

            onCoin();
            onDiamond();
            onKey();
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

    /**
     * Checks if the thief is on a coin in the same room.
     */
    public void onCoin() {
        Room currentRoom = getCurrentRoom();

        if (this.getParent() == currentRoom) {
            for (Component component : currentRoom.getComponents()) {
                if (component instanceof Coin 
                    && this.getBounds().intersects(component.getBounds())) {
                    collectCoin((Coin) component);
                }
            }
        }
    }

    /**
     * Checks if the thief is on a diamond in the same room.
     * @return true if the thief is on a diamond, false otherwise.
     */
    public boolean onDiamond() {
        Room currentRoom = getCurrentRoom();

        if (this.getParent() == currentRoom) {
            for (Component component : currentRoom.getComponents()) {
                if (component instanceof Diamond 
                    && this.getBounds().intersects(component.getBounds())) {
                    collectDiamond((Diamond) component);
                    return true; 
                }
            }
        }

        return false;
    }

    /**
     * Checks if the thief is on a key in the same room.
     * @return true if the thief is on a key, false otherwise.
     */
    public boolean onKey() {
        Room currentRoom = getCurrentRoom();

        if (this.getParent() == currentRoom) {
            for (Component component : currentRoom.getComponents()) {
                if (component instanceof Key 
                    && this.getBounds().intersects(component.getBounds())) {
                    collectKey((Key) component);
                    return true; // Thief is on a coin in the same room
                }
            }
        }

        return false;
    }

    /**
     * Collects the coin and removes it from the room.
     * @param coin is the coin to be collected
     */
    public void collectCoin(Coin coin) {
        Room currentRoom = getCurrentRoom();
        coin.collect();
        collectedItems.add(coin);
        currentRoom.remove(coin); 
        currentRoom.updateRoom();
        
    }

    /**
     * Collects the diamond and removes it from the room.
     * @param diamond is the diamond to be collected
     */
    public void collectDiamond(Diamond diamond) {
        Room currentRoom = getCurrentRoom();
        diamond.collect();
        collectedItems.add(diamond);
        currentRoom.remove(diamond); 
        currentRoom.updateRoom();
        
    }

    /**
     * Collects the key and removes it from the room.
     * @param key is the key to be collected
     */
    public void collectKey(Key key) {
        Room currentRoom = getCurrentRoom();
        key.collect();
        collectedItems.add(key);
        currentRoom.remove(key); 
        currentRoom.updateRoom();
        
    }

    public boolean hasKey() {
        for (CollectableItem item : collectedItems) {
            if (item instanceof Key) {
                return true;
            }
        }
        return false;
    }
}