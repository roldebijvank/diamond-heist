import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * The Guard class represents a guard in the game.
 * Sets the behaviour of the guards.
 */
public class Guard extends JPanel {
    private int width = 100;
    private int height = 150;
    private int x;
    private int direction; 
    private Thief thief;
    private JLabel guardLabel;
    private boolean gameEnded;

    /**
     * Initializes a new Guard object.
     * @param thief is the thief in the game.
     * @param guardImageUrl is the guard's image url.
     */
    public Guard(Thief thief, URL guardImageUrl) {
        this.thief = thief;
        this.direction = 1; //starts by going right
        this.gameEnded = false;

        ImageIcon guardIcon = new ImageIcon(guardImageUrl);
        Image scaledImage = guardIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        guardIcon = new ImageIcon(scaledImage);
        guardLabel = new JLabel(guardIcon);
        this.add(guardLabel);
        this.setOpaque(false);

        Timer timer = new Timer(100, new ActionListener() {
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
     * Moves the guard within the room.
     * Flipping direction and image when the guard reaches the edge of its room.
     */
    public void move() {
        if (direction == 1) {
            x += 10;
            if (x >= getParent().getWidth() - 100) {
                direction = -1; // Change direction 
                guardLabel.setIcon(flipImageVertically(guardLabel.getIcon()));
            }
        } else {
            x -= 10;
            if (x <= 0) {
                direction = 1;
                guardLabel.setIcon(flipImageVertically(guardLabel.getIcon()));
            }
        }

        this.setBounds(x, this.getY(), width, height);
    }

    /**
     * Checks whether the thief is seen by the guard.
     * Ends the game is the thief is caught.
     */
    public void checkForThief() {
        if (getParent() == thief.getCurrentRoom()) {
            if (direction == -1 && x > thief.getX()) {
                endGame();
            } else if (direction == 1 && x < thief.getX()) {
                endGame();
            }
        }
    }   
    
    /**
     * Flips an of the guard image vertically.
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
     * End the game if appropriate.
     * Create a game-over message.
     */
    public void endGame() {
        gameEnded = true;
        JOptionPane.showMessageDialog(null, "Game Over! You've been caught!");
        System.exit(0);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
