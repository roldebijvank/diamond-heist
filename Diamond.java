import java.net.URL;

public class Diamond extends CollectableItem {
    public Diamond(URL imageUrl) {
        super(imageUrl, 1000, 100, 60, 60); 
    }

    // add code that allows to exit only after the diamond is collected. @TODO: create exit door
}
