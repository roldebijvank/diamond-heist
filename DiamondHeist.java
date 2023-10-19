import java.awt.Color;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.JFrame;

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
        bottomRoom1 = new Room(300, 233, 0, 232);
        bottomRoom1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        bottomRoom1.setBounds(0, 467, bottomRoom1.getWidth(), bottomRoom1.getHeight());

        bottomRoom2 = new Room(850, 233, 0, 232);
        bottomRoom2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        bottomRoom2.setBounds(300, 467, bottomRoom2.getWidth(), bottomRoom2.getHeight());

        middleRoom1 = new Room(550, 233, 0, 232);
        middleRoom1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        middleRoom1.setBounds(0, 234, middleRoom1.getWidth(), middleRoom1.getHeight());

        middleRoom2 = new Room(550, 233, 0, 232);
        middleRoom2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        middleRoom2.setBounds(550, 234, middleRoom2.getWidth(), middleRoom2.getHeight());
        
        topRoom = new Room(1100, 234, 0, 232);
        topRoom.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        topRoom.setBounds(0, 0, topRoom.getWidth(), topRoom.getHeight());

        frame.add(bottomRoom1);
        frame.add(bottomRoom2);
        frame.add(middleRoom1);
        frame.add(middleRoom2);
        frame.add(topRoom);
    }

    private static void setupThief() throws MalformedURLException {
        thief = new Thief(1, 105, new URL("https://t4.ftcdn.net/jpg/04/79/15/87/360_F_479158723_yY9DzwsXi9ahEKC1lLNkEeg4qCE2a7f7.jpg"), bottomRoom1);
        thief.setBounds(thief.getX(), thief.getY(), 50, 100);

        bottomRoom1.add(thief);
    }

    private static void setupDoors() throws MalformedURLException {
        Door doorBottomRoom1 = new Door(150, 105, 50, 100, new URL("https://creazilla-store.fra1.digitaloceanspaces.com/cliparts/33142/door-clipart-md.png"), bottomRoom2);
        doorBottomRoom1.checkCollision(thief);
        doorBottomRoom1.setBounds(doorBottomRoom1.x, doorBottomRoom1.y,
                                  doorBottomRoom1.width, doorBottomRoom1.height);

        Door doorBottomRoom2 = new Door(10, 105, 50, 100, new URL("https://creazilla-store.fra1.digitaloceanspaces.com/cliparts/33142/door-clipart-md.png"), bottomRoom1);
        doorBottomRoom2.checkCollision(thief);
        doorBottomRoom2.setBounds(doorBottomRoom2.x, doorBottomRoom2.y,
                                  doorBottomRoom2.width, doorBottomRoom2.height);

        bottomRoom1.add(doorBottomRoom1);
        bottomRoom2.add(doorBottomRoom2);
    }

    /**
     * Sets up the ladders in the rooms.
     * @throws MalformedURLException if the URL is invalid
     */
    public static void setupLadders() throws MalformedURLException {
        Ladder ladderBottomRoom2 = new Ladder(new URL("https://static.vecteezy.com/system/resources/thumbnails/000/645/132/small/illust58-696.jpg"), middleRoom2);
        ladderBottomRoom2.checkCollision(thief);
        ladderBottomRoom2.setBounds(400, 0,
                                    30, 210);

        bottomRoom2.add(ladderBottomRoom2);
    }

    public static void main(String[] args) throws MalformedURLException {
        frame = new JFrame("Diamond Heist");
        frame.setLayout(null);

        setupRooms();
        setupThief();
        setupDoors();
        setupLadders();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100, 700);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}