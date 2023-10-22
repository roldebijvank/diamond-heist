import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Image;
import java.awt.Point;

public class Camera extends JPanel {
    private boolean cameraOn;
    private JLabel cameraLabel;
    private ImageIcon cameraIcon;
    private JButton button1;
    private JButton button2;

    public Camera(URL cameraImageUrl, Point cameraPosition, Point buttonPosition1, Point buttonPosition2) {
        cameraOn = true;

        cameraIcon = new ImageIcon(cameraImageUrl);
        Image scaledImage = cameraIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        cameraIcon = new ImageIcon(scaledImage);
        cameraLabel = new JLabel(cameraIcon);
        cameraLabel.setBounds(cameraPosition.x, cameraPosition.y, 50, 50);
        this.add(cameraLabel);
        this.setOpaque(false);
       

        // Create and configure buttons
        button1 = new JButton("CLICK ME");
        button1.setBounds(buttonPosition1.x, buttonPosition1.y, 120, 30);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onButtonPressed();
            }
        });
        this.add(button1);

        button2 = new JButton("CLICK ME");
        button2.setBounds(buttonPosition2.x, buttonPosition2.y, 120, 30);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onButtonPressed();
            }
        });
        this.add(button2);
    }

    public void turnOn() {
        cameraOn = true;
        cameraLabel.setIcon(cameraIcon);
    }

    public void turnOff() {
        cameraOn = false;
        cameraLabel.setIcon(null);
    }

    public void onButtonPressed() {
        if (cameraOn) {
            turnOff();
            cameraLabel.setVisible(false); // Set the label to be invisible
            Timer timer = new Timer(5000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    turnOn();
                    cameraLabel.setVisible(true); // Set the label to be visible
                    ((Timer) e.getSource()).stop();
                }
            });
            timer.setRepeats(false);
            timer.start();
        }
    }
    
}
