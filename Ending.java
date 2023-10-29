import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * The Ending class provides methods to display end-game dialogs.
 * Includes star ratings and a reset button.
 */
public class Ending {
    public static final int DOG = 1;
    public static final int GUARD = 2;
    public static final int CAMERA = 3;

    /**
     * Displays an end-game dialog with a message and an option to reset the game.
     * message is based on the type of alert that ended the game.
     * @param type is the type of alert that ended the game.
     */
    public static void showCaughtDialog(int type) {
        String title = "";
        String message = "";
        if (type == DOG) {
            title = "Guard Dog Alert!";
            message = "You've been bitten by a guard dog!";
        } else if (type == GUARD) {
            title = "Guard Alert!";
            message = "You've been caught by a guard!";
        } else if (type == CAMERA) {
            title = "Camera Alert!";
            message = "You've been caught by a camera!";
        }

        JDialog endGameDialog = new JDialog();
        endGameDialog.setTitle(title);
        endGameDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        endGameDialog.setSize(400, 200);
        endGameDialog.setLocationRelativeTo(null); 
        endGameDialog.setResizable(false);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(192, 192, 192)); // Light gray background
        panel.setLayout(new BorderLayout());

        Font customFont = new Font("Arial", Font.PLAIN, 16);

        JLabel messageLabel = new JLabel(message);
        messageLabel.setFont(customFont);
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        panel.add(messageLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton resetButton = new JButton("Try Again");
        resetButton.setFont(customFont);
        resetButton.setBackground(new Color(192, 0, 0));
        resetButton.setForeground(Color.WHITE);
        resetButton.setFocusable(false);
        buttonPanel.add(resetButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setFont(customFont);
        exitButton.setBackground(new Color(50, 50, 50));
        exitButton.setForeground(Color.WHITE);
        exitButton.setFocusable(true);
        buttonPanel.add(exitButton);

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                endGameDialog.dispose();
                DiamondHeist.resetGame();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        panel.add(buttonPanel, BorderLayout.SOUTH);

        endGameDialog.add(panel);
        endGameDialog.setVisible(true);
    }

    /**
     * Displays an end-game dialog with stars collected and an option to reset the game.
     */
    public static void showEndGameDialog(int stars) {
        JDialog endGameDialog = new JDialog();
        endGameDialog.setTitle("Congratulations!");
        endGameDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        endGameDialog.setSize(700, 400);
        endGameDialog.setLocationRelativeTo(null);
        endGameDialog.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(192, 192, 192));
        
        Font customFont = new Font("Arial", Font.BOLD, 18);
        String message = "<html><div style='text-align: center;'>Congratulations!</div>"
            + "You've successfully stole the diamond without getting caught!";

        JLabel messageLabel = new JLabel("<html>" + message.replaceAll("\n", "<br>") + "</html>");
        messageLabel.setFont(customFont);
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT); 
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(messageLabel);

        ImageIcon starIcon = new ImageIcon("img/zero_stars.png");
        if (stars == 1) {
            starIcon = new ImageIcon("img/one_star.png");
        } else if (stars == 2) {
            starIcon = new ImageIcon("img/two_stars.png");
        } else if (stars == 3) {
            starIcon = new ImageIcon("img/three_stars.png");
        }

        JLabel starLabel = new JLabel(starIcon);
        starLabel.setAlignmentY(Component.TOP_ALIGNMENT);
        starLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(starLabel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton exitButton = new JButton("Exit");
        exitButton.setFont(customFont);
        exitButton.setBackground(Color.GREEN);
        exitButton.setForeground(Color.BLACK);

        JButton resetButton = new JButton("Play Again");
        resetButton.setFont(customFont);
        resetButton.setBackground(new Color(0, 128, 0));
        resetButton.setForeground(Color.WHITE);

        buttonPanel.add(exitButton);
        buttonPanel.add(resetButton);

        endGameDialog.add(panel, BorderLayout.CENTER);
        endGameDialog.add(buttonPanel, BorderLayout.SOUTH);

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                endGameDialog.dispose();
                DiamondHeist.resetGame();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        endGameDialog.setVisible(true);
    }
}