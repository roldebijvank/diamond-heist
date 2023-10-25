import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * CollectableItem is an abstract class that represents an item that can be
 * collected by the thief.
 */
public abstract class CollectableItem extends JPanel {
    private JLabel itemLabel;
    protected boolean collected;
    public int x;
    public int y;
    public int width;
    public int height;

    /**
     * Creates an instance of CollectableItem.
     * @param imageUrl is the URL of the image
     * @param x is the x coordinate of the item
     * @param y is the y coordinate of the item
     * @param width is the width of the item
     * @param height is the height of the item
     */
    public CollectableItem(URL imageUrl, int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        ImageIcon icon = new ImageIcon(imageUrl);
        Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaledImage);
        itemLabel = new JLabel(icon);
        this.add(itemLabel);
        this.setOpaque(false);
    }

    public boolean isCollected() {
        return collected;
    }

    /**
     * Sets collected to true and hides the item.
     */
    public void collect() {
        collected = true;
        setVisible(false);
    }
}
