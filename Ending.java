import javax.swing.*;
import java.awt.*;

public class Ending {
    public static void showZeroStars() {
        showStars("https://png.pngtree.com/png-vector/20230831/ourmid/pngtree-three-stars-review-rating-design-vector-png-image_9968825.png");
    }

    public static void showOneStar() {
        showStars("one_star.pnghttps://png.pngtree.com/png-vector/20230831/ourmid/pngtree-three-stars-review-rating-design-vector-png-image_9968825.png");
    }

    public static void showTwoStars() {
        showStars("https://png.pngtree.com/png-vector/20230831/ourmid/pngtree-three-stars-review-rating-design-vector-png-image_9968825.pngg");
    }

    public static void showThreeStars() {
        showStars("https://png.pngtree.com/png-vector/20230831/ourmid/pngtree-three-stars-review-rating-design-vector-png-image_9968825.png");
    }

    private static void showStars(String imageName) {
        JFrame frame = new JFrame("Congratulations!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        JPanel panel = new JPanel();
        frame.add(panel);

        JLabel label = new JLabel("You collected stars:");
        panel.add(label);

        ImageIcon starIcon = new ImageIcon(imageName);

        if (starIcon != null) {
            panel.add(new JLabel(starIcon));
        }

        frame.setVisible(true);
    }

}
