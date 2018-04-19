package boxGroup;
import java.util.*;

public class Configurations {

	ArrayList<Item> configuration = new ArrayList<Item>();
	
	//pass in the current box configuration
	public void addBoxToConfiguration(Item item) {
		configuration.add(item);
	}
	
	public Item returnSmallestConfiguration() {
		double smallestVolume = 0.0;
		Item returnBox = configuration.get(0);
		for(Item object : configuration) {
			if (smallestVolume > object.getSimpleVolume()) {
				smallestVolume = object.getSimpleVolume();
				returnBox = object;
			}
		}
		return returnBox;
	}
}
