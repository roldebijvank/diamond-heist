import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Creates a new Ladder object. The thief can go up and down this ladder.
 */
public class Ladder extends JPanel implements ActionListener {
    private int width = 50;
    private int height = 233;
    private Thief thief;
    private Timer timer;
    private Room sendToRoom;

    /**
     * Creates a new Ladder object.
     * @param imageUrl is the URL of the image of the ladder
     */
    public Ladder(URL imageUrl, Room sendToRoom) {
        this.sendToRoom = sendToRoom;
        ImageIcon ladderIcon = new ImageIcon(imageUrl);
        Image scaledImage = ladderIcon.getImage()
                            .getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING);
        ladderIcon = new ImageIcon(scaledImage);
        this.add(new JLabel(ladderIcon));
    }

    /**
     * Checks if the thief is on the ladder. Timer is used to make sure the thief
     * moves smoothly and with a normal speed.
     * @param thief is the thief object that is on the ladder.
     */
    public void checkCollision(Thief thief) {
        timer = new Timer(10, this);
        this.thief = thief;
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.getBounds().intersects(thief.getBounds())) {
            thief.onLadder = true;
            thief.repaint();
        }
        if (this.getBounds().intersects(thief.getBounds()) && thief.up) {
            this.setFocusable(true);
            this.requestFocus();
            thief.moveUp();
            if (thief.getY() < this.getY()) {
                thief.up = false;
                thief.getCurrentRoom().remove(thief);
                thief.getCurrentRoom().updateRoom();
                thief.setCurrentRoom(sendToRoom);
                sendToRoom.add(thief);
                sendToRoom.updateRoom();
                this.setFocusable(false);
                thief.setFocusable(true);
                timer.stop();
            }
        } else if (this.getBounds().intersects(thief.getBounds()) && thief.down) {
            this.setFocusable(true);
            this.requestFocus();
            thief.moveDown();
            if (thief.getY() > this.getY()) {
                thief.down = false;
                timer.stop();
            }
        } else {
            this.setFocusable(false);
            thief.setFocusable(true);
        }
    }
}
