import java.net.URL;

/**
 * Coins is a class that represents a coin that can be collected by the thief.
 */
public class Coin extends CollectableItem {
    
    public Coin(URL imageUrl, int x, int y) {
        super(imageUrl, x, y, 50, 50);
    }

    @Override
    public void collect() {
        collected = true;
        setVisible(false); // Hide the coin
    }
}
