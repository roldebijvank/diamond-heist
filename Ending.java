import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Ending is a class that creates a frame that shows the number of stars the
 * player collected. It has four methods that show the frame with zero, one,
 * two or three stars.
 */
public class Ending {
    // https://png.pngtree.com/png-vector/20230831/ourmid/pngtree-three-stars-review-rating-design-vector-png-image_9968825.png

    public static void showStars(int amountOfStars) {
        JFrame frame = new JFrame("Congratulations!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        frame.add(panel);

        JLabel label = new JLabel("You collected stars:\n");
        panel.add(label);

        if (amountOfStars == 3) {
            ImageIcon starIcon = new ImageIcon("img/three_stars.png");
            panel.add(new JLabel(starIcon));
        } else if (amountOfStars == 2) {
            ImageIcon starIcon = new ImageIcon("img/three_stars.png");
            panel.add(new JLabel(starIcon));
        } else if (amountOfStars == 1) {
            ImageIcon starIcon = new ImageIcon("img/three_stars.png");
            panel.add(new JLabel(starIcon));
        } else {
            ImageIcon starIcon = new ImageIcon("img/three_stars.png");
            panel.add(new JLabel(starIcon));
        }

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
