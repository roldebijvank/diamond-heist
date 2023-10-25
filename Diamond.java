import java.net.URL;

/**
 * Creates Diamond object which extends CollectableItem.
 */
public class Diamond extends CollectableItem {
    public Diamond(URL imageUrl) {
        super(imageUrl, 1000, 100, 60, 60); 
    }
}
