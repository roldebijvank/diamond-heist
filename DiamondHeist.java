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

    private static void setupFrame() {
        frame = new JFrame("Diamond Heist");
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100, 700);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private static void setupRooms() {
        Room bottomRoom1 = new Room(300, 233);
        JPanel bottomRoom1Panel = bottomRoom1.createRoom();
        bottomRoom1Panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        bottomRoom1Panel.setBounds(0, 467, bottomRoom1.getWidth(), bottomRoom1.getHeight());

        Room bottomRoom2 = new Room(850, 233);
        JPanel bottomRoom2Panel = bottomRoom2.createRoom();
        bottomRoom2Panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        bottomRoom2Panel.setBounds(300, 467, bottomRoom2.getWidth(), bottomRoom2.getHeight());

        Room middleRoom1 = new Room(550, 233);
        JPanel middleRoom1Panel = middleRoom1.createRoom();
        middleRoom1Panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        middleRoom1Panel.setBounds(0, 234, middleRoom1.getWidth(), middleRoom1.getHeight());

        Room middleRoom2 = new Room(550, 233);
        JPanel middleRoom2Panel = middleRoom2.createRoom();
        middleRoom2Panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        middleRoom2Panel.setBounds(550, 234, middleRoom2.getWidth(), middleRoom2.getHeight());
        
        Room topRoom = new Room(1100, 234);
        JPanel topRoomPanel = topRoom.createRoom();
        topRoomPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        topRoomPanel.setBounds(0, 0, topRoom.getWidth(), topRoom.getHeight());

        frame.add(bottomRoom1Panel);
        frame.add(bottomRoom2Panel);
        frame.add(middleRoom1Panel);
        frame.add(middleRoom2Panel);
        frame.add(topRoomPanel);
    }

    private static void setupThief() throws MalformedURLException {
        Thief thief = new Thief(50, 50, new URL("https://www.vecteezy.com/free-vector/thief"));
        JPanel thiefPanel = thief.createThief();
        thiefPanel.setBounds(100, 100, 100, 100);
        frame.add(thief);
    }

    public static void main(String[] args) throws MalformedURLException {
        setupFrame();
        setupRooms();
        setupThief();
    }
}