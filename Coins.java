import java.net.URL;

public class Coins extends CollectableItem {
    public Coins(URL imageUrl, int x, int y) {
        super(imageUrl, x, y, 50, 50);
    }

    @Override
    public void collect() {
        collected = true;
        setVisible(false); // Hide the coin
        }


}
