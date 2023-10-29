import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;


/**
 * The WelcomeScreen class displays a welcome message.
 * Provides instructions and hints before starting the Diamond Heist game.
 * 
 */
public class WelcomeScreen extends JFrame {
    private final DiamondHeist diamondHeist;

    /**
     * Constructs a new WelcomeScreen instance.
     * @param diamondHeist The DiamondHeist instance that manages the game.
     */
    public WelcomeScreen(DiamondHeist diamondHeist) {
        this.diamondHeist = diamondHeist;
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setLayout(null);
        getContentPane().setBackground(new Color(192, 192, 192)); // Background color

        JLabel welcomeLabel = new JLabel("$   Welcome to Diamond Heist!   $");
        welcomeLabel.setBounds(100, 20, 400, 40); // Centered and larger
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Larger font

        JLabel rulesLabel = new JLabel("Rules:");
        rulesLabel.setBounds(20, 80, 100, 20);

        JTextArea rulesText = new JTextArea(
            "1. Your mission is to steal the diamond and escape without getting caught!\n"
            + "2. Control the thief using arrow keys.\n"
            + "3. Collect valuable items by pressing the space key.\n"
            + "4. Use the space key to seamlessly pass through doors and hatches.\n"
            + "5. Ascend ladders with the up arrow key for higher treasures.\n"
            + "6. Safely descend ladders using the down arrow key."
        );
        rulesText.setBounds(20, 100, 540, 130); // Centered and slightly taller
        rulesText.setEditable(false);
        rulesText.setFont(new Font("Arial", Font.PLAIN, 16)); // Larger font
        rulesText.setBackground(new Color(192, 192, 192)); // Match the background color

        JLabel hintsLabel = new JLabel("Hints:");
        hintsLabel.setBounds(20, 250, 100, 20);

        JTextArea hintsText = new JTextArea(
                """
                        1. Avoid being spotted by human guards and security cameras.
                        2. Stay clear of the guard dogs to avoid their bites.
                        3. Locate the key to unlock the hatch for your escape.
                        4. You can only exit after successfully collecting the precious diamond.
                        5. Collect coins for a higher rating."""
        );
        hintsText.setBounds(20, 270, 540, 100); // Centered and slightly taller
        hintsText.setEditable(false);
        hintsText.setFont(new Font("Arial", Font.PLAIN, 16)); // Larger font
        hintsText.setBackground(new Color(192, 192, 192)); // Match the background color

        JButton startButton = new JButton("Start Heist");
        startButton.setBounds(250, 380, 100, 30); // Centered

        add(welcomeLabel);
        add(rulesLabel);
        add(rulesText);
        add(hintsLabel);
        add(hintsText);
        add(startButton);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeWelcomeScreen();
            }
        });
    }

    /**
     * Closes the welcome screen and starts the game.
     */
    public void closeWelcomeScreen() {
        setVisible(false);
        diamondHeist.startGame();
    }
}
