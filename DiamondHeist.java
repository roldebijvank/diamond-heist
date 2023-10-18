import java.awt.Color;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * DiamondHeist is the main class of the game. It creates the frame and adds the
 * rooms to it.
 * 
 * @author Rik Olde Bijvank
 * @author Marta Stepien
 */
public class DiamondHeist {
    private static JFrame frame;
    private static Room bottomRoom1;
    private static Room bottomRoom2;
    private static Room middleRoom1;
    private static Room middleRoom2;
    private static Room topRoom;

    private static Thief thief;

    private static void setupRooms() {
        bottomRoom1 = new Room(300, 233);
        bottomRoom1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        bottomRoom1.setBounds(0, 467, bottomRoom1.getWidth(), bottomRoom1.getHeight());
        bottomRoom1.setLayout(null);

        bottomRoom2 = new Room(850, 233);
        bottomRoom2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        bottomRoom2.setBounds(300, 467, bottomRoom2.getWidth(), bottomRoom2.getHeight());
        bottomRoom2.setLayout(null);

        middleRoom1 = new Room(550, 233);
        middleRoom1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        middleRoom1.setBounds(0, 234, middleRoom1.getWidth(), middleRoom1.getHeight());
        middleRoom1.setLayout(null);

        middleRoom2 = new Room(550, 233);
        middleRoom2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        middleRoom2.setBounds(550, 234, middleRoom2.getWidth(), middleRoom2.getHeight());
        middleRoom2.setLayout(null);
        
        topRoom = new Room(1100, 234);
        topRoom.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        topRoom.setBounds(0, 0, topRoom.getWidth(), topRoom.getHeight());
        topRoom.setLayout(null);

        frame.add(bottomRoom1);
        frame.add(bottomRoom2);
        frame.add(middleRoom1);
        frame.add(middleRoom2);
        frame.add(topRoom);
    }

    private static void setupThief() throws MalformedURLException {
        thief = new Thief(1, 155, new URL("https://t4.ftcdn.net/jpg/04/79/15/87/360_F_479158723_yY9DzwsXi9ahEKC1lLNkEeg4qCE2a7f7.jpg"), bottomRoom1);
        thief.setBounds(thief.getX(), thief.getY(), 50, 50);

        bottomRoom1.add(thief);
    }

    private static void setupDoor() throws MalformedURLException {
        Door door = new Door(150, 133, 50, 50, new URL("https://png.pngtree.com/png-clipart/20190215/ourmid/pngtree-the-door-is-opening-png-image_521423.jpg"), bottomRoom2);
        door.checkCollision(thief, door);
        JPanel doorPanel = door.createDoor();
        doorPanel.setBounds(door.x, door.y, door.width, door.height);

        bottomRoom1.add(doorPanel);
    }

    public static void main(String[] args) throws MalformedURLException {
        frame = new JFrame("Diamond Heist");
        frame.setLayout(null);

        setupRooms();
        setupThief();
        setupDoor();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100, 700);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}