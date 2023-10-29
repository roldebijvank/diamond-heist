import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Creates a camera button that will temporarily turn the camera off.
 */
public class CameraButton extends JPanel {
    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private final Room currentRoom;
    private ImageIcon buttonIcon;
    private final JLabel buttonLabel;
    private CameraButton relatedButton;

    /**
     * Creates an instance of CameraButton.
     * @param x is the x coordinate of the button
     * @param y is the y coordinate of the button
     * @param width is the width of the button
     * @param height is the height of the button
     */
    public CameraButton(int x, int y, int width, int height, CameraButton relatedButton, Room currentRoom) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.relatedButton = relatedButton;
        this.currentRoom = currentRoom;
        
        
        buttonIcon = new ImageIcon("img/button.png");
        Image scaledImage = buttonIcon.getImage().getScaledInstance(width, height,
                                                                    Image.SCALE_SMOOTH);
        buttonIcon = new ImageIcon(scaledImage);
        buttonLabel = new JLabel(buttonIcon);
        this.setLayout(null);
        buttonLabel.setBounds(0, 0, width, height);
        this.add(buttonLabel);
        this.setOpaque(false);
    }

    /**
     * Changes the image of the button based on whether it is pressed or not.
     */
    public void changeImage(String fileName) {
        buttonIcon = new ImageIcon(fileName);
        Image scaledImage = buttonIcon.getImage().getScaledInstance(width, height,
                                                                    Image.SCALE_SMOOTH);
        buttonIcon = new ImageIcon(scaledImage);
        buttonLabel.setIcon(buttonIcon);
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

    public void setRelatedButton(CameraButton relatedButton) {
        this.relatedButton = relatedButton;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }
}