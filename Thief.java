import java.net.URL;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Image;
import java.awt.Color;

/**
 * Creates Thief object. Sets behaviour.
 */

public class Thief extends JPanel implements KeyListener {
    private boolean isDetected;
    private ArrayList<String> items;
    private int x;
    private int y;
    private JLabel thiefLabel;

    /**
     * creates an instance of Thief.
     * @param x is the x coordinate of the thief
     * @param y is the y coordinate of the thief
     * @param imageURL is the URL of the image of the thief
     */
    public Thief(int x, int y, URL imageURL) {
        this.x = x;
        this.y = y;

        ImageIcon icon = new ImageIcon(imageURL);
        Image scaledImage = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        
        thiefLabel = new JLabel(scaledIcon);
        this.add(thiefLabel);

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        
    }

    public void moveRight() {
        x += 10;
        thiefLabel.setBounds(x, y, thiefLabel.getWidth(), thiefLabel.getHeight());
    }

    public void moveLeft() {
        x -= 10;
        thiefLabel.setBounds(x, y, thiefLabel.getWidth(), thiefLabel.getHeight());
    }

    public void moveUp() {
        y += 10;
        thiefLabel.setBounds(x, y, thiefLabel.getWidth(), thiefLabel.getHeight());
    }

    public void moveDown() {
        y -= 10;
        thiefLabel.setBounds(x, y, thiefLabel.getWidth(), thiefLabel.getHeight());
    }

    public JPanel createThief() {
        return this;
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
            moveUp();
        } else if (keyCode == KeyEvent.VK_DOWN) {
            moveDown();
        }
        // Repaint the panel to show the updated position
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}