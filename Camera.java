import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Creates a camera that will detect the thief when inside the radius of the camera.
 * The camera will turn off when the thief clicks the corresponding buttons.
 */
public class Camera extends JPanel {
    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private final Room currentRoom;
    private boolean isOn = true;

    /**
     * Creates an instance of Camera.
     * @param x is the x coordinate of the camera
     * @param y is the y coordinate of the camera
     */
    public Camera(int x, int y, Room currentRoom) {
        this.x = x;
        this.y = y;
        this.width = 150;
        this.height = 233;
        this.currentRoom = currentRoom;

        ImageIcon cameraIcon = new ImageIcon("img/camera.png");
        Image scaledCameraImage = cameraIcon.getImage().getScaledInstance(30, 30,
                                                                          Image.SCALE_SMOOTH);
        cameraIcon = new ImageIcon(scaledCameraImage);
        JLabel cameraLabel = new JLabel(cameraIcon);

        ImageIcon sightIcon = new ImageIcon("img/camera_sight.png");
        Image scaledLightImage = sightIcon.getImage().getScaledInstance(width, height,
                                                                        Image.SCALE_SMOOTH);
        sightIcon = new ImageIcon(scaledLightImage);
        JLabel lightLabel = new JLabel(sightIcon);

        this.setOpaque(false);
        this.add(cameraLabel);
        this.add(lightLabel);
    }

    /**
     * Turns the camera on.
     */
    public void turnOn() {
        isOn = true;
        this.getComponent(1).setVisible(true);
    }

    /**
     * Turns the camera off.
     */
    public void turnOff() {
        isOn = false;
        this.getComponent(1).setVisible(false);
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

    public Room getCurrentRoom() {
        return currentRoom;
    }
}
