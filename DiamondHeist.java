import java.awt.Color;
import java.awt.Container;
import java.awt.Point;
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

    private static void setupRooms() throws MalformedURLException {
        bottomRoom1 = new Room(300, 233, 0, 105, new URL("https://img.freepik.com/free-vector/gallery-exhibition-realistic-mockup-with-pictures-wall-visitors-couch-vector-illustration_1284-76858.jpg?size=626&ext=jpg&ga=GA1.1.1825464540.1697495894"));
        bottomRoom1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        bottomRoom1.setBounds(0, 467, bottomRoom1.getWidth(), bottomRoom1.getHeight());

        bottomRoom2 = new Room(800, 233, 10, 105, new URL("https://img.freepik.com/premium-vector/gallery-realistic-mockup-with-blank-picture-frames-visitors-couch-vector-illustration_1284-76855.jpg?size=626&ext=jpg&ga=GA1.1.1825464540.1697495894"));
        bottomRoom2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        bottomRoom2.setBounds(300, 467, bottomRoom2.getWidth(), bottomRoom2.getHeight());

        middleRoom1 = new Room(550, 233, 0, 105, new URL("https://img.freepik.com/premium-vector/galleru-exhibition-realistic-mockup-with-empty-picture-frame-lightning-equipment-vector-illustration_1284-76846.jpg?size=626&ext=jpg&ga=GA1.1.1825464540.1697495894"));
        middleRoom1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        middleRoom1.setBounds(0, 234, middleRoom1.getWidth(), middleRoom1.getHeight());

        middleRoom2 = new Room(550, 233, 10, 105, new URL("https://img.freepik.com/premium-vector/gallery-museum-realistic-mockup-with-exhibition-picture-frames-vector-illustration_1284-76851.jpg?size=626&ext=jpg&ga=GA1.1.1825464540.1697495894&semt=ais"));
        middleRoom2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        middleRoom2.setBounds(550, 234, middleRoom2.getWidth(), middleRoom2.getHeight());
        
        topRoom = new Room(1100, 234, 0, 232, new URL("https://img.freepik.com/premium-vector/exhibition-museum-paintings-vertical-positioning-wall-with-spot-light-red-barrier-blank-space-you-realistic-wooden-floor-sunlight_134830-276.jpg?w=1060"));
        topRoom.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        topRoom.setBounds(0, 0, topRoom.getWidth(), topRoom.getHeight());

        frame.add(bottomRoom1);
        frame.add(bottomRoom2);
        frame.add(middleRoom1);
        frame.add(middleRoom2);
        frame.add(topRoom);
    }

    private static void setupThief() throws MalformedURLException {
        thief = new Thief(1, 75, new URL("https://static.vecteezy.com/system/resources/previews/021/978/524/original/cartoon-stealthy-thief-free-png.png"), bottomRoom1);
        thief.setBounds(thief.getX(), thief.getY(), 70, 120);

        bottomRoom1.add(thief);
    }

    private static void setupDoors() throws MalformedURLException {
        Door doorBottomRoom1 = new Door(240, 105, 50, 100, new URL("https://creazilla-store.fra1.digitaloceanspaces.com/cliparts/33142/door-clipart-md.png"), bottomRoom2);
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
        ladderBottomRoom2.setBounds(400, 1,
                                    30, 210);

        bottomRoom2.add(ladderBottomRoom2);
    }

    private static void setupGuards() throws MalformedURLException {
        URL guardImageUrl = new URL("https://images.rawpixel.com/image_transparent_png_800/cHJpdmF0ZS9sci9pbWFnZXMvd2Vic2l0ZS8yMDIzLTA0L2pvYjk2OS0xMjgtcC5wbmc.png");

        Guard guard1 = new Guard(thief, guardImageUrl);
        guard1.setBounds(0, 49, guard1.getWidth(), guard1.getHeight());
        bottomRoom2.add(guard1);

        Guard guard2 = new Guard(thief, guardImageUrl);
        guard2.setBounds(0, 82, guard2.getWidth(), guard2.getHeight());
        middleRoom1.add(guard2);
    }

    private static void setupDog() throws MalformedURLException {
        URL dogImageUrl = new URL("https://png.pngtree.com/png-vector/20220816/ourmid/pngtree-cartoon-angry-dog-chasingvector-illustration-canine-fight-bark-vector-png-image_38428812.png");
    
        Dog dog = new Dog(thief, dogImageUrl);
        dog.setBounds(0, 153, dog.getWidth(), dog.getHeight());
        topRoom.add(dog);
    }

    private static void setupCamera() throws MalformedURLException {
        URL cameraImageUrl = new URL("https://png.pngtree.com/png-vector/20231019/ourmid/pngtree-security-camera-illustration-in-minimal-style-png-image_10210822.png");
        
        Point cameraPosition = new Point(100, 1); // Adjust the camera position
        Point buttonPosition1 = new Point(50, 50);   // Adjust button 1 position
        Point buttonPosition2 = new Point(150, 50);  // Adjust button 2 position

        Camera camera = new Camera(cameraImageUrl, cameraPosition, 
                                                   buttonPosition1, buttonPosition2);
        camera.setBounds(cameraPosition.x, cameraPosition.y, 50, 50);
        middleRoom2.add(camera);
    }

    public static void main(String[] args) throws MalformedURLException {
        frame = new JFrame("Diamond Heist");
        frame.setLayout(null);

        setupRooms();
        setupThief();
        setupDoors();
        setupLadders();
        setupGuards();
        setupDog();
        setupCamera();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100, 700);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}