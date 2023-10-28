import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
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
        bottomRoom1 = new Room(300, 233, "img/bottomRoom1_image.jpg");
        bottomRoom1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        bottomRoom1.setBounds(0, 467, bottomRoom1.getWidth(), bottomRoom1.getHeight());

        bottomRoom2 = new Room(800, 233, "img/bottomRoom2_image.jpg");
        bottomRoom2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        bottomRoom2.setBounds(300, 467, bottomRoom2.getWidth(), bottomRoom2.getHeight());

        middleRoom1 = new Room(550, 233, "img/middleRoom1_image.jpg");
        middleRoom1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        middleRoom1.setBounds(0, 234, middleRoom1.getWidth(), middleRoom1.getHeight());

        middleRoom2 = new Room(550, 233, "img/middleRoom2_image.jpg");
        middleRoom2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        middleRoom2.setBounds(550, 234, middleRoom2.getWidth(), middleRoom2.getHeight());
        
        topRoom = new Room(1100, 234, "img/topRoom_image.jpg");
        topRoom.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        topRoom.setBounds(0, 0, topRoom.getWidth(), topRoom.getHeight());

        frame.add(bottomRoom1);
        frame.add(bottomRoom2);
        frame.add(middleRoom1);
        frame.add(middleRoom2);
        frame.add(topRoom);
    }

    private static void setupThief() {
        thief = new Thief(1, 105, bottomRoom1);
        thief.setBounds(thief.getX(), thief.getY(), thief.getWidth(), thief.getHeight());

        bottomRoom1.add(thief);
    }

    private static void setupDoors() {
        Door doorBottomRoom1 = new Door(240, 132, 50, 100,
                                        bottomRoom2, bottomRoom1, new Point(0, 105));
        doorBottomRoom1.checkCollision(thief);
        doorBottomRoom1.setBounds(doorBottomRoom1.getX(), doorBottomRoom1.getY(),
                                  doorBottomRoom1.getWidth(), doorBottomRoom1.getHeight());

        Door doorBottomRoom2 = new Door(10, 132, 50, 100,
                                        bottomRoom1, bottomRoom2, new Point(230, 105));
        doorBottomRoom2.checkCollision(thief);
        doorBottomRoom2.setBounds(doorBottomRoom2.getX(), doorBottomRoom2.getY(),
                                  doorBottomRoom2.getWidth(), doorBottomRoom2.getHeight());

        Door doorMiddleRoom2 = new Door(10, 132, 50, 100,
                                        middleRoom1, middleRoom2, new Point(480, 105));
        doorMiddleRoom2.checkCollision(thief);
        doorMiddleRoom2.setBounds(doorMiddleRoom2.getX(), doorMiddleRoom2.getY(),
                                  doorMiddleRoom2.getWidth(), doorMiddleRoom2.getHeight());

        Door doorMiddleRoom1 = new Door(490, 132, 50, 100,
                                        middleRoom2, middleRoom1, new Point(0, 105));
        doorMiddleRoom1.checkCollision(thief);
        doorMiddleRoom1.setBounds(doorMiddleRoom1.getX(), doorMiddleRoom1.getY(),
                                  doorMiddleRoom1.getWidth(), doorMiddleRoom1.getHeight());

        bottomRoom1.add(doorBottomRoom1);
        bottomRoom2.add(doorBottomRoom2);
        middleRoom2.add(doorMiddleRoom2);
        middleRoom1.add(doorMiddleRoom1);
    }

    /**
     * Sets up the ladders in the rooms.
     */
    private static void setupLadders() {
        Ladder ladderBottomRoom2 = new Ladder(bottomRoom2, middleRoom2, new Point(100, 105));
        ladderBottomRoom2.checkCollision(thief);
        ladderBottomRoom2.setBounds(350, 1,
                                    ladderBottomRoom2.getWidth(), ladderBottomRoom2.getHeight());

        Ladder ladderMiddleRoom1 = new Ladder(middleRoom1, topRoom, new Point(200, 105));
        ladderMiddleRoom1.checkCollision(thief);
        ladderMiddleRoom1.setBounds(200, 1,
                                    ladderMiddleRoom1.getWidth(), ladderMiddleRoom1.getHeight());

        bottomRoom2.add(ladderBottomRoom2);
        middleRoom1.add(ladderMiddleRoom1);
    }

    private static void setupGuards() {
        Guard guard1 = new Guard(thief);
        guard1.setBounds(0, 80, guard1.getWidth(), guard1.getHeight());
        bottomRoom2.add(guard1);

        Guard guard2 = new Guard(thief);
        guard2.setBounds(0, 80, guard2.getWidth(), guard2.getHeight());
        middleRoom1.add(guard2);
    }

    private static void setupDog() {    
        Dog dog = new Dog(thief);
        dog.setBounds(0, 153, dog.getWidth(), dog.getHeight());
        topRoom.add(dog);
    }

    private static void setupCamera() {
        Camera camera = new Camera(250, 0, middleRoom2);
        camera.checkCollision(thief);
        camera.setBounds(camera.getX(), camera.getY(), camera.getWidth(), camera.getHeight());

        CameraButton cameraButtonLeft = new CameraButton(140, 100, 60, 60, camera, middleRoom2);
        cameraButtonLeft.checkCollision(thief);
        cameraButtonLeft.setBounds(cameraButtonLeft.getX(), cameraButtonLeft.getY(),
                                   cameraButtonLeft.getWidth(), cameraButtonLeft.getHeight());

        CameraButton cameraButtonRight = new CameraButton(450, 100, 60, 60, camera, middleRoom2);
        cameraButtonRight.checkCollision(thief);
        cameraButtonRight.setBounds(cameraButtonRight.getX(), cameraButtonRight.getY(),
                                    cameraButtonRight.getWidth(), cameraButtonRight.getHeight());

        middleRoom2.add(cameraButtonLeft);
        middleRoom2.add(cameraButtonRight);
        middleRoom2.add(camera);
    }

    private static void setupDiamond() {
        Diamond diamond = new Diamond(1000, 100);
        diamond.setBounds(diamond.x, diamond.y, diamond.width, diamond.height);
        topRoom.add(diamond);
    }

    private static void setupCoins() {
        Coin coin1 = new Coin(150, 100);
        coin1.setBounds(coin1.x, coin1.y, coin1.width, coin1.height);
        bottomRoom2.add(coin1);

        Coin coin2 = new Coin(500, 100);
        coin2.setBounds(coin2.x, coin2.y, coin2.width, coin2.height);
        middleRoom2.add(coin2);

        Coin coin3 = new Coin(450, 100);
        coin3.setBounds(coin3.x, coin3.y, coin3.width, coin3.height);
        middleRoom1.add(coin3);
    }

    private static void setupKey() {
        Key key = new Key(20, 170);
        key.setBounds(key.x, key.y, key.width, key.height);
        topRoom.add(key);
    }

    public static void main(String[] args) {
        frame = new JFrame("Diamond Heist");
        frame.setLayout(null);
        frame.getRootPane().setPreferredSize(new Dimension(1100, 700));

        setupRooms();
        setupThief();
        setupDoors();
        setupLadders();
        setupGuards();
        setupDog();
        setupCamera();
        setupDiamond();
        setupCoins();
        setupKey();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }
}