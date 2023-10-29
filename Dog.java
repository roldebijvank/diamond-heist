import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * The Dog class represents a dog in the game.
 * Sets the behavior of the dogs.
 */
public class Dog extends JPanel {
    private int x;
    private int direction;
    private Thief thief;
    private boolean gameEnded;
    private JLabel dogLabel;
    private int width = 80;
    private int height = 50;

    /**
     * Initializes a new Dog object.
     * @param thief is the thief in the game.
     */
    public Dog(Thief thief) {
        this.thief = thief;
        this.direction = 1;
        this.gameEnded = false;

        ImageIcon dogIcon = new ImageIcon("img/dog.png");
        Image scaledImage = dogIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        dogIcon = new ImageIcon(scaledImage);
        dogLabel = new JLabel(dogIcon);
        this.add(dogLabel);
        this.setOpaque(false);

        Timer timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!gameEnded) {
                    move();
                    checkForThief();
                }
            }
        });
        timer.start();
    }

    /**
     * Moves the dog within the room.
     * Flipping direction and image when the dog reaches the edge of its room.
     */
    
    public void move() {
        if (direction == 1) {
            x += 5; 
            if (x >= getParent().getWidth() - 60) {
                direction = -1;
                dogLabel.setIcon(flipImageVertically(dogLabel.getIcon()));
            }
        } else {
            x -= 5;
            if (x <= 0) {
                direction = 1;
                dogLabel.setIcon(flipImageVertically(dogLabel.getIcon()));
            }
        }

        this.setBounds(x, this.getY(), width, height);
    }

    /**
     * Checks whether the dog has caught the thief.
     * Ends the game if the dog makes contact with the thief.
     */
    public void checkForThief() {
        if (getParent() == thief.getCurrentRoom()
            && this.getBounds().intersects(thief.getBounds())) {
            endGame();
        }
    }
    /**
     * Flips an of the dog image vertically.
     *
     * @param icon is the input icon to be flipped.
     * @return The vertically flipped ImageIcon.
     */

    private ImageIcon flipImageVertically(Icon icon) {
        if (icon instanceof ImageIcon) {
            ImageIcon imageIcon = (ImageIcon) icon;
            Image image = imageIcon.getImage();
            int width = image.getWidth(null);
            int height = image.getHeight(null);
            BufferedImage flippedImage = new BufferedImage(width,
                                                           height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = flippedImage.createGraphics();
            g.drawImage(image, 0, 0, width, height, width, 0, 0, height, null);
            g.dispose();
            return new ImageIcon(flippedImage);
        }
        return null;
    }

    /**
     * End the game if the dog catches the thief.
     * Create a game-over message.
     */
    public void endGame() {
        thief.timer.stop();
        gameEnded = true;
        Ending.showCaughtDialog(Ending.DOG);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
