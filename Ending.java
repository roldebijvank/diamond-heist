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
    /**
     * Displays an end-game dialog with stars collected and an option to reset the game.
     * @throws MalformedURLException to indicate that a malformed URL has occurred.
     */
    public static void showEndGameDialog() {

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
        
        ImageIcon starIcon = new ImageIcon("img/three_stars.png");
        if (starIcon != null) {
            JLabel starLabel = new JLabel(starIcon);
            starLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(starLabel);
        }

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

    /**
     * Displays a message when the thief is caught by a guard.
     * Allows the player to try again or exit the game.
     */
    public static void showGuardCaughtMessage() {
        JDialog endGameDialog = new JDialog();
        endGameDialog.setTitle("Guard Alert!");
        endGameDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        endGameDialog.setSize(400, 200);
        endGameDialog.setLocationRelativeTo(null); 
        endGameDialog.setResizable(false);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(192, 192, 192)); // Light gray background
        panel.setLayout(new BorderLayout());

        Font customFont = new Font("Arial", Font.PLAIN, 16);

        JLabel messageLabel = new JLabel("Oh no, you've been caught by a guard!");
        messageLabel.setFont(customFont);
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        panel.add(messageLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton resetButton = new JButton("Try Again");
        resetButton.setFont(customFont);
        resetButton.setBackground(new Color(192, 0, 0)); // A dark red button
        resetButton.setForeground(Color.WHITE);
        buttonPanel.add(resetButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setFont(customFont);
        exitButton.setBackground(new Color(50, 50, 50)); // A dark gray button
        exitButton.setForeground(Color.WHITE);
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
     * Displays a message when the thief is caught by a guard dog.
     * Allows the player to try again or exit the game.
     */
    public static void showDogCaughtMessage() {
        JDialog endGameDialog = new JDialog();
        endGameDialog.setTitle("Guard Dog Alert!");
        endGameDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        endGameDialog.setSize(400, 200);
        endGameDialog.setLocationRelativeTo(null);
        endGameDialog.setResizable(false);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(192, 192, 192)); // Light gray background
        panel.setLayout(new BorderLayout());

        Font customFont = new Font("Arial", Font.PLAIN, 16);

        JLabel messageLabel = new JLabel("Oops! You've been bitten by a guard dog!");
        messageLabel.setFont(customFont);
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        panel.add(messageLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton resetButton = new JButton("Try Again");
        resetButton.setFont(customFont);
        resetButton.setBackground(new Color(192, 0, 0)); // A dark red button
        resetButton.setForeground(Color.WHITE);
        buttonPanel.add(resetButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setFont(customFont);
        exitButton.setBackground(new Color(50, 50, 50)); // A dark gray button
        exitButton.setForeground(Color.WHITE);
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

        panel.add(buttonPanel, BorderLayout.SOUTH); // Add the button panel to the bottom

        endGameDialog.add(panel);
        endGameDialog.setVisible(true);
    }
}