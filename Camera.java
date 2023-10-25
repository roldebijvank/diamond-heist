import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Creates a camera that will detect the thief when inside the radius of the camera.
 * The camera will turn off when the thief clicks the corresponding buttons.
 */
public class Camera extends JPanel implements ActionListener {
    private int x;
    private int y;
    private int width;
    private int height;
    private Room currentRoom;
    private boolean isOn = true;
    private URL cameraImageUrl;
    private URL lightImageUrl;
    private Thief thief;
    private Timer timer;

    /**
     * Creates an instance of Camera.
     * @param x is the x coordinate of the camera
     * @param y is the y coordinate of the camera
     * @param width is the width of the camera
     * @param height is the height of the camera
     * @throws MalformedURLException if the URL is invalid
     */
    public Camera(int x, int y, int width, int height, Room currentRoom)
                                                throws MalformedURLException {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = width;
        this.currentRoom = currentRoom;
        this.cameraImageUrl = new URL("https://images.freeimages.com/vhq/images/previews/6ae/cctv-dome-camera-118135.png");
        this.lightImageUrl = new URL("https://png.pngtree.com/png-clipart/20230619/original/pngtree-illumination-stage-blue-lightning-spotlight-effect-vector-transparent-png-image_9190676.png");

        ImageIcon cameraIcon = new ImageIcon(cameraImageUrl);
        Image scaledCameraImage = cameraIcon.getImage().getScaledInstance(width - 200,
                                                                    height - 200,
                                                                    Image.SCALE_SMOOTH);
        cameraIcon = new ImageIcon(scaledCameraImage);
        JLabel cameraLabel = new JLabel(cameraIcon);
        cameraLabel.setBounds(0, 0, width, height);

        ImageIcon lightIcon = new ImageIcon(lightImageUrl);
        Image scaledLightImage = lightIcon.getImage().getScaledInstance(width,
                                                                    height,
                                                                    Image.SCALE_SMOOTH);
        lightIcon = new ImageIcon(scaledLightImage);
        JLabel lightLabel = new JLabel(lightIcon);
        lightLabel.setBounds(0, 0, width, height);

        this.setOpaque(false);
        this.add(cameraLabel);
        this.add(lightLabel);
    }

    /**
     * Checks if the thief has collided with the camera.
     * @param thief is the thief
     */
    public void checkCollision(Thief thief) {
        timer = new Timer(10, this);
        this.thief = thief;
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.getBounds().intersects(thief.getBounds())
            && thief.getCurrentRoom() == currentRoom) {
            endGame();
        }
    }

    /**
     * Ends the game if the thief gets detected by the camera.
     */
    private void endGame() {
        JOptionPane.showMessageDialog(null, "Game Over! You've been caught!");
        System.exit(0);
    }

    /**
     * Turns the camera on.
     */
    public void turnOn() {
        isOn = true;

        this.getComponent(1).setVisible(true);
        timer.start();
    }

    /**
     * Turns the camera off.
     */
    public void turnOff() {
        isOn = false;

        this.getComponent(1).setVisible(false);
        timer.stop();
    }

    public boolean getOn() {
        return isOn;
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
