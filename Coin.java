/**
 * Coins is a class that represents a coin that can be collected by the thief.
 */
public class Coin extends CollectableItem {
    
    public Coin(int x, int y) {
        super("img/coin.png", x, y, 50, 50);
    }

    @Override
    public void collect() {
        collected = true;
        setVisible(false); // Hide the coin
    }
}
