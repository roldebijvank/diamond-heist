import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Creates a new Ladder object. The thief can go up and down this ladder.
 */
public class Ladder extends JPanel implements ActionListener {
    private int width = 70;
    private int height = 233;
    private Thief thief;
    private Timer timer;
    private Room sendToRoom;
    private Room currentRoom;
    private Point sendToPoint;

    /**
     * Creates a new Ladder object.
     * @param sendToRoom is the room that the ladder sends the thief to
     */
    public Ladder(Room currentRoom, Room sendToRoom, Point sendToPoint) {
        this.sendToRoom = sendToRoom;
        this.sendToPoint = sendToPoint;
        this.currentRoom = currentRoom;
        
        ImageIcon ladderIcon = new ImageIcon("img/ladder.png");
        Image scaledImage = ladderIcon.getImage()
                            .getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ladderIcon = new ImageIcon(scaledImage);
        this.add(new JLabel(ladderIcon));
        this.setOpaque(false);
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
        boolean intersects = this.getBounds().intersects(thief.getBounds())
                             && thief.getCurrentRoom() == this.currentRoom;

        if (intersects) {
            thief.onLadder = true;
            if (thief.up) {
                this.setFocusable(true);
                this.requestFocus();
                thief.moveUp();
                if (thief.getY() < this.getY()) {
                    thief.up = false;
                    thief.onLadder = false;
                    thief.getCurrentRoom().remove(thief);
                    thief.getCurrentRoom().updateRoom();
                    thief.setCurrentRoom(sendToRoom);
                    thief.setCurrentPoint(sendToPoint);
                    sendToRoom.add(thief);
                    sendToRoom.updateRoom();
                    this.setFocusable(false);
                    thief.setFocusable(true);
                    timer.stop();
                }
            } else if (thief.down
                    && thief.getCurrentRoom() == sendToRoom
                    && thief.getX() == sendToPoint.getX()) {
                System.out.println("yo");
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
