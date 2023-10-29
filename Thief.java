import java.awt.Component;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Creates Thief object. Sets behaviour.
 */

public class Thief extends JPanel implements KeyListener {
    private ArrayList<CollectableItem> collectedItems = new ArrayList<>();
    private int x;
    private int y;
    private static int coinsCollected = 0;
    private int width = 70;
    private int height = 120;
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
    public boolean jumps = false;
    private int numCollectedCoins = 0;

    /**
     * creates an instance of Thief.
     * @param x is the x coordinate of the thief
     * @param y is the y coordinate of the thief
     */
    public Thief(int x, int y, Room currentRoom) {
        this.x = x;
        this.y = y;
        this.currentRoom = currentRoom;

        ImageIcon icon = new ImageIcon("img/thief.png");
        Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaledImage);
        
        thiefLabel = new JLabel(icon);
        this.add(thiefLabel);
        this.setOpaque(false);
        this.addKeyListener(this);
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
            repaint();
        }
    }

    /**
     * Moves the thief up.
     */
    public void moveUp() {
        y -= 1;
        this.setBounds(x, y, thiefLabel.getWidth(), thiefLabel.getHeight());
        repaint();
    }

    /**
     * Moves the thief down.
     */
    public void moveDown() {
        y += 1;
        this.setBounds(x, y, thiefLabel.getWidth(), thiefLabel.getHeight());
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        boolean leftKey = keyCode == KeyEvent.VK_LEFT;
        boolean rightKey = keyCode == KeyEvent.VK_RIGHT;
        boolean upKey = keyCode == KeyEvent.VK_UP;
        boolean downKey = keyCode == KeyEvent.VK_DOWN;
        boolean spaceKey = keyCode == KeyEvent.VK_SPACE;

        if (leftKey) {
            left = true;
            right = false;
        } else if (rightKey) {
            right = true;
            left = false;
        } else if (upKey && !down) {
            if (!right && !left) {
                up = true;
            }
            if (!onLadder && !jumps) {
                jumps = true;
            }
        } else if (downKey && !up) {
            if (!right && !left) {
                down = true;
            }
        } else if (spaceKey) {
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
            
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            right = false;
        } else if (keyCode == KeyEvent.VK_SPACE) {
            space = false;
            doorClicked = false;
            buttonPressed = false;

            onCoin();
            onDiamond();
            onKey();
        } else if (keyCode == KeyEvent.VK_UP) {
            up = false;
        } else if (keyCode == KeyEvent.VK_DOWN) {
            down = false;
        }
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
        numCollectedCoins++;
        coinsCollected++;
        
    }

    public static int getNumberOfCoins() {
        return coinsCollected;
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

    /**
     * Sets the position of the thief.
     * @param point is the point to set the thief to
     */
    public void setCurrentPoint(Point point) {
        this.x = point.x;
        this.y = point.y;
        this.setBounds(x, y, thiefLabel.getWidth(), thiefLabel.getHeight());
    }

    /**
     * Checks if the thief has a key.
     * @return true if the thief has a key, false otherwise.
     */
    public boolean hasKey() {
        for (CollectableItem item : collectedItems) {
            if (item instanceof Key) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the thief has a diamond.
     * @return true if the thief has a diamond, false otherwise.
     */
    public boolean hasDiamond() {
        for (CollectableItem item : collectedItems) {
            if (item instanceof Diamond) {
                return true;
            }
        }
        return false;
    }

    public int getNumCollectedCoins() {
        return numCollectedCoins;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public boolean getLeft() {
        return left;
    }

    public boolean getRight() {
        return right;
    }

    public boolean getUp() {
        return up;
    }

    public boolean getDown() {
        return down;
    }

    public boolean getOnLadder() {
        return onLadder;
    }

    public boolean getJumps() {
        return jumps;
    }

    public void setJumps(boolean jumps) {
        this.jumps = jumps;
    }

    public boolean getButtonPressed() {
        return buttonPressed;
    }
}