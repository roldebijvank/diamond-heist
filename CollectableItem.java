import java.awt.Color;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CollectableItem extends JPanel {
    private JLabel itemLabel;
    protected boolean collected;
    public int x;
    public int y;
    public int width;
    public int height;

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

    public void collect() {
        collected = true;
        setVisible(false);
    }
}
