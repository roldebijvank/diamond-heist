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
    private int x;
    private int y;
    private int width;
    private int height;
    private Thief thief;
    private Camera camera;
    private Room currentRoom;
    private ImageIcon buttonIcon;
    private JLabel buttonLabel;
    private CameraButton relatedButton;
    private boolean pressed = false;

    /**
     * Creates an instance of CameraButton.
     * @param x is the x coordinate of the button
     * @param y is the y coordinate of the button
     * @param width is the width of the button
     * @param height is the height of the button
     */
    public CameraButton(int x, int y, int width, int height,
                        Camera camera, CameraButton relatedButton, Room currentRoom) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = width;      
        this.camera = camera;
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

        // timer = new Timer(4000, new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         camera.turnOn();
        //         pressed = false;
        //         timer.stop();
        //     }
        // });
    }

    /**
     * Checks if the thief has collided with the camera button.
     * @param thief is the thief
     */
    public void checkCollision(Thief thief) {
        this.thief = thief;
    }

    // @Override
    // public void actionPerformed(ActionEvent e) {
    //     if (this.getBounds().intersects(thief.getBounds())
    //         && thief.getCurrentRoom() == currentRoom) {
    //         thief.onButton = true;

    //         if (thief.buttonPressed) {
    //             timer.stop();
    //             pressed = true;
    //             changeImage("img/button_pressed.png");
    //             if (relatedButton.getPressed()) {
    //                 relatedButton.timer.stop();
    //                 timer.stop();
    //             }
    //             camera.turnOff();
    //             timer.start();
    //         } else {
    //             changeImage("img/button.png");
    //         }
    //     }
    // }

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

    public boolean getPressed() {
        return pressed;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    
    }

    public void setRelatedButton(CameraButton relatedButton) {
        this.relatedButton = relatedButton;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public CameraButton getRelatedButton() {
        return relatedButton;
    }
}