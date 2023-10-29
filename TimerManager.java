import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * TimerManager is a class that manages the timers for every object in the game
 * that uses a timer.
 */
public class TimerManager implements ActionListener {
    public static Timer globalTimer;
    private static Thief thief;
    private int thiefIndex = 0;
    private static Camera camera;
    private int cameraIndex = 0;
    private static CameraButton cameraButton1;
    private static CameraButton cameraButton2;
    private static Guard guardBottomRoom2;
    private static Guard guardMiddleRoom1;
    private static Dog dog;
    private static Door doorBottomRoom1;
    private static Door doorBottomRoom2;
    private static Door doorMiddleRoom1;
    private static Door doorMiddleRoom2;
    private static Door exitDoor;
    private static Ladder ladderBottomRoom2;
    private static Ladder ladderMiddleRoom1;

    /**
     * Starts the global timer.
     */
    public TimerManager(Thief thief, Camera camera, CameraButton cameraButton1,
                        CameraButton cameraButton2, Guard guardBottomRoom2, Guard guardMiddleRoom1,
                        Dog dog, Door doorBottomRoom1, Door doorBottomRoom2, Door doorMiddleRoom1,
                        Door doorMiddleRoom2, Door exitDoor, Ladder ladderBottomRoom2,
                        Ladder ladderMiddleRoom1) {
        globalTimer = new Timer(16, this);
        TimerManager.thief = thief;
        TimerManager.camera = camera;
        TimerManager.cameraButton1 = cameraButton1;
        TimerManager.cameraButton2 = cameraButton2;
        TimerManager.guardBottomRoom2 = guardBottomRoom2;
        TimerManager.guardMiddleRoom1 = guardMiddleRoom1;
        TimerManager.dog = dog;
        TimerManager.doorBottomRoom1 = doorBottomRoom1;
        TimerManager.doorBottomRoom2 = doorBottomRoom2;
        TimerManager.doorMiddleRoom1 = doorMiddleRoom1;
        TimerManager.doorMiddleRoom2 = doorMiddleRoom2;
        TimerManager.exitDoor = exitDoor;
        TimerManager.ladderBottomRoom2 = ladderBottomRoom2;
        TimerManager.ladderMiddleRoom1 = ladderMiddleRoom1;

        globalTimer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Thief actions
        // Jumping mechanic
        if (thief.getJumps()) {
            if (thiefIndex < 40) {
                thief.moveUp();
                thief.moveUp();
                thief.moveUp();
                thiefIndex++;
            } else if (thiefIndex < 80) {
                thief.moveDown();
                thief.moveDown();
                thief.moveDown();
                thiefIndex++;
            } else {
                thief.setJumps(false);
                thiefIndex = 0;
            }
        }
    
        // Walking mechanic
        if (thief.getLeft()) {
            thief.moveLeft();
        } else if (thief.getRight()) {
            thief.moveRight();
        }

        // Camera actions
        if (camera.getBounds().intersects(thief.getBounds()) && camera.getOn()
            && thief.getCurrentRoom() == camera.getCurrentRoom()) {
            globalTimer.stop();
            Ending.showCaughtDialog(Ending.CAMERA);
        }

        // Camera button actions
        if (cameraButton1.getBounds().intersects(thief.getBounds())
            && thief.getCurrentRoom() == cameraButton1.getCurrentRoom()) {
            thief.onButton = true;

            if (thief.getButtonPressed()) {
                cameraButton1.changeImage("img/button_pressed.png");
                camera.turnOff();
            } else {
                cameraButton1.changeImage("img/button.png");
            }
        }

        if (cameraButton2.getBounds().intersects(thief.getBounds())
            && thief.getCurrentRoom() == cameraButton2.getCurrentRoom()) {
            thief.onButton = true;

            if (thief.getButtonPressed()) {
                cameraButton2.changeImage("img/button_pressed.png");
                camera.turnOff();
            } else {
                cameraButton2.changeImage("img/button.png");
            }
        }

        // Camera on or off
        if (!camera.getOn()) {
            cameraIndex++;
            if (cameraIndex == 200) {
                camera.turnOn();
                cameraIndex = 0;
            }
        }

        // Guard actions
        if (!guardBottomRoom2.getGameEnded() || !guardMiddleRoom1.getGameEnded()) {
            guardBottomRoom2.move();
            guardBottomRoom2.checkForThief();

            guardMiddleRoom1.move();
            guardMiddleRoom1.checkForThief();
        }

        // Dog actions
        if (!dog.getGameEnded()) {
            dog.move();
            dog.checkForThief();
        }

        // Door actions
        doorAction(doorBottomRoom1);
        doorAction(doorBottomRoom2);
        doorAction(doorMiddleRoom1);
        doorAction(doorMiddleRoom2);
        doorAction(exitDoor);

        // Ladder actions
        ladderAction(ladderBottomRoom2);
        ladderAction(ladderMiddleRoom1);
    }

    private static void ladderAction(Ladder ladder) {
        boolean intersects = ladder.getBounds().intersects(thief.getBounds())
                             && thief.getCurrentRoom() == ladder.getCurrentRoom();

        if (thief.down
            && thief.getCurrentRoom() == ladder.getSendToRoom()
            && thief.getBounds().intersects(ladder.getSendToPoint().x,
                                            ladder.getSendToPoint().y, 70, 233)) {
            thief.onLadder = true;
            ladder.setFocusable(true);
            ladder.requestFocus();
            thief.getCurrentRoom().remove(thief);
            thief.getCurrentRoom().updateRoom();
            thief.setCurrentRoom(ladder.getCurrentRoom());
            thief.setCurrentPoint(new Point(ladder.getX(), 0));
            ladder.getCurrentRoom().add(thief);
            ladder.getCurrentRoom().updateRoom();
        } else if (intersects) {
            thief.onLadder = true;
            if (thief.up) {
                ladder.setFocusable(true);
                ladder.requestFocus();
                thief.moveUp();
                if (thief.getY() <= ladder.getY()) {
                    thief.up = false;
                    thief.onLadder = false;
                    thief.getCurrentRoom().remove(thief);
                    thief.getCurrentRoom().updateRoom();
                    thief.setCurrentRoom(ladder.getSendToRoom());
                    thief.setCurrentPoint(ladder.getSendToPoint());
                    ladder.getSendToRoom().add(thief);
                    ladder.getSendToRoom().updateRoom();
                    ladder.setFocusable(false);
                    thief.setFocusable(true);
                }
            } else if (thief.down) {
                thief.moveDown();
                if (thief.getY() >= 105) {
                    thief.down = false;
                    thief.onLadder = false;
                    thief.setCurrentPoint(new Point(ladder.getX(), 105));
                    thief.getCurrentRoom().add(thief);
                    thief.getCurrentRoom().updateRoom();
                    ladder.setFocusable(false);
                    thief.setFocusable(true);
                }
            }
        } else {
            ladder.setFocusable(false);
            thief.setFocusable(true);
        }
    }

    private static void doorAction(Door door) {
        if (door.getBounds().intersects(thief.getBounds())
            && thief.getCurrentRoom() == door.getCurrentRoom()) {
            thief.onDoor = true;
            if (thief.doorClicked) {
                if (door.getRequiresDiamond() && !thief.hasDiamond()) {
                    String message = "You need a diamond to exit the game!";
                    String title = "Exit Requires Diamond";
                    JOptionPane.showMessageDialog(null, 
                                    message, title, JOptionPane.INFORMATION_MESSAGE);
                }
                thief.getCurrentRoom().remove(thief);
                thief.getCurrentRoom().updateRoom();
                thief.setCurrentRoom(door.getSendToRoom());
                thief.setCurrentPoint(door.getSendToPoint());
                door.getSendToRoom().add(thief);
                door.getSendToRoom().updateRoom();
                thief.requestFocus(true);
                thief.doorClicked = false;

                if (door.getRequiresDiamond() && thief.hasDiamond()) {
                    Ending.showEndGameDialog(thief.getNumCollectedCoins());
                }
            }
        }
    }

    public void start() {
        globalTimer.start();
    }
}