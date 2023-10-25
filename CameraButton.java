import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Creates a camera button that will temporarily turn the camera off.
 */
public class CameraButton extends JPanel implements ActionListener {
    private final URL imageUrl;
    private int x;
    private int y;
    private int width;
    private int height;
    private Thief thief;
    private Camera camera;
    private Room currentRoom;
    private Timer timer;

    /**
     * Creates an instance of CameraButton.
     * @param x is the x coordinate of the button
     * @param y is the y coordinate of the button
     * @param width is the width of the button
     * @param height is the height of the button
     * @throws MalformedURLException if the URL is invalid
     */
    public CameraButton(int x, int y, int width, int height, Camera camera, Room currentRoom)
        throws MalformedURLException {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = width;
        this.camera = camera;
        this.currentRoom = currentRoom;
        this.imageUrl = new URL("https://cdn.pixabay.com/photo/2013/07/13/01/08/button-155149_1280.png");
        
        ImageIcon buttonIcon = new ImageIcon(imageUrl);
        Image scaledImage = buttonIcon.getImage().getScaledInstance(width - 20,
                                                                    height - 20,
                                                                    Image.SCALE_SMOOTH);
        buttonIcon = new ImageIcon(scaledImage);
        JLabel buttonLabel = new JLabel(buttonIcon);
        buttonLabel.setBounds(0, 0, width, height);
        this.setOpaque(false);
        this.add(buttonLabel);

        timer = new Timer(4000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.setRepeats(false);
                camera.turnOn();
            }
        });
    }

    /**
     * Checks if the thief has collided with the camera button.
     * @param thief is the thief
     */
    public void checkCollision(Thief thief) {
        Timer timer = new Timer(10, this);
        this.thief = thief;
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.getBounds().intersects(thief.getBounds())
            && thief.getCurrentRoom() == currentRoom) {
            thief.onButton = true;

            if (thief.buttonPressed) {
                camera.turnOff();
                timer.start();
            }
        }
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
}