import java.net.URL;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Graphics;

/**
 * Creates Thief object. Sets behaviour.
 */

public class Thief extends JPanel {
    private boolean isDetected;
    private ArrayList<String> items;
    private int x;
    private int y;
    private JLabel thiefLabel;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawRect(50, 50, 100, 50); // Draws a rectangle at (50, 50) with width 100 and height 50
    }

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
        thiefLabel = new JLabel(icon);

        add(thiefLabel);
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
        JPanel thiefPanel = new JPanel();
        return thiefPanel;
    }

    public JLabel getThiefLabel() {
        return thiefLabel;
    }
}
