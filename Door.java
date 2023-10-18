import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Door extends JPanel implements ActionListener {
    int x; 
    int y;
    int width;
    int height;
    boolean collision = false;
    Thief thief;
    Room sendToRoom;

    public Door(int x, int y, int width, int height, URL imageUrl, Room sendToRoom) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.sendToRoom = sendToRoom;

        ImageIcon doorIcon = new ImageIcon(imageUrl);
        Image scaledImage = doorIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        doorIcon = new ImageIcon(scaledImage);
        this.add(new JLabel(doorIcon));
    }

    public boolean checkCollision(Thief thief, Door door) {
        this.thief = thief;
        Timer timer = new Timer(10, (ActionListener) this);
        timer.start();
        return collision;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.getBounds().intersects(thief.getBounds())) {
            thief.getCurrentRoom().remove(thief);
            thief.getCurrentRoom().updateRoom();
            thief.setCurrentRoom(sendToRoom);
            sendToRoom.add(thief);
            thief.setBounds(0, 0, 50, 50);
            sendToRoom.updateRoom();
            thief.requestFocus(true);
        }
    }

    public JPanel createDoor() {
        return this;
    }
}