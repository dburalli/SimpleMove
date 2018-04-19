package boxGroup;
import java.util.*;

	// This project is a work in progress, not complete by any means
public class Dimensions {
		//Ideally, the items would be coming from the database, with a field describing the shape (so as to calculate volume)
		// Most Sonar enabled scanners that scan items are capable of providing these measurements 
		// the database would provide Width, Height, Length, and a number corresponding to a shape, which would use an extended volume parent class
		// but here are some sample dimensions for testing's sake
	public static void main(String[] args) { 
		//if there is a single item, and the item can be shipped in its original packaging (as notified by manufacturer or asserted in the sizing process)
		// then skip the process and use API to look up shipping costs then print label.
		Item item1 = new Item(3.0,3.0,3.0,"item1");
		Item item2 = new Item(3.0,3.0,3.0,"item2");
		Item item3 = new Item(6.0,3.0,3.0,"item3");
		Item item4 = new Item(6.0,5.0,3.0,"item4");
		Item item5 = new Item(6.0,3.0,3.0,"item5");
		Item shippingBox;
		
		ArrayList<Item> itemsToPack = new ArrayList<Item>();
		itemsToPack.add(item1);
		itemsToPack.add(item2);
		itemsToPack.add(item3);
		itemsToPack.add(item4);
		itemsToPack.add(item5);
		shippingBox = createBoxFromArea(itemsToPack);
		
		Dimensions.generateData(itemsToPack);
	}
	
	private static Item createBoxFromArea(ArrayList<Item> itemsToPack) {
		double length = 0.0;
		double width = 0.0;
		double height = 0.0;
		
		//Cases -> Height is greater than  L or W
		// Length is greater than  H or W
		// Width is > than H or L
		
		for (Item object: itemsToPack) {
				
			if(object.height > height) {
				height = object.height;
			}
			if(object.width > width) {
				width = object.width;
			}
			if(object.length > length) {
				length = object.length;
			}
		}
		

		Item box = new Item(height,width,length,"packingBox");
		return box;
	}
	
	public static void generateData(ArrayList<Item> itemsToPack) {
			double maxLength = 0;
			double maxHeight = 0;
			double maxWidth = 0;
			double totalHeight = 0;
			double totalLength = 0;
			double totalWidth = 0;
			Item box;
			Item heightItem = null;
			Item lengthItem = null;
			Item widthItem = null;
			double minVolume = 0.0;
			
		for (Item object: itemsToPack) {
			totalHeight += object.height;
			totalLength += object.length;
			totalWidth += object.width;
			if(maxLength < object.getLength()) {
				maxLength = object.getLength();
				lengthItem = object;
			}
			if(maxHeight < object.getHeight()) {
				maxHeight = object.getHeight();
				heightItem = object;
			}
			if(maxWidth < object.getWidth()) {
				maxWidth = object.getWidth();
				widthItem = object;
			}
			minVolume = minVolume + object.getSimpleVolume();
		}
		
		box = new Item(totalHeight,totalLength,totalWidth,"largest box");
		heightItem = heightBox(itemsToPack,  maxLength, maxWidth, totalHeight);
		lengthItem = lengthBox(itemsToPack, totalLength, maxWidth, maxHeight);
		widthItem = widthBox(itemsToPack, maxLength, totalWidth, maxHeight);
		
		System.out.println("Largest box: " + box.getSimpleVolume());
		System.out.println("Minumum Volume: " + minVolume);
		System.out.println("__________________________");
		System.out.println("Height Box: " + heightItem.getSimpleVolume());
		System.out.println("Length Box H: " + heightItem.getHeight());
		System.out.println("Length Box L: " + heightItem.getLength());
		System.out.println("Length Box W: " + heightItem.getWidth());
			
		System.out.println("__________________________");
		System.out.println("Width Box: " + widthItem.getSimpleVolume());
		System.out.println("Length Box H: " + widthItem.getHeight());
		System.out.println("Length Box L: " + widthItem.getLength());
		System.out.println("Length Box W: " + widthItem.getWidth());
			
		System.out.println("__________________________");
		System.out.println("Length Box: " + lengthItem.getSimpleVolume());
		System.out.println("Length Box H: " + lengthItem.getHeight());
		System.out.println("Length Box L: " + lengthItem.getLength());
		System.out.println("Length Box W: " + lengthItem.getWidth());
			
		
	}
	
	public static Item heightBox(ArrayList<Item> itemsToPack, double maxLength, double maxWidth, double totalHeight ) {
			Item heightBox = new Item(maxLength,maxWidth,totalHeight,"height variation");
		return heightBox;
	}
	public static Item widthBox(ArrayList<Item> itemsToPack, double maxLength, double totalWidth, double maxHeight ) {
			Item widthBox = new Item(maxLength,totalWidth,maxHeight,"width variation");
		return widthBox;
	}
	public static Item lengthBox(ArrayList<Item> itemsToPack, double totalLength, double maxWidth, double maxHeight ) {
			Item lengthBox = new Item(totalLength,maxWidth,maxHeight,"length variation");
		return lengthBox;
	}
	public static Item oneItem(ArrayList<Item> itemsToPack) {
		//boxes length is greatest, then the height is second largest, width final (typical box layout)
		Item orientation = 	itemsToPack.get(0);
		double height = orientation.getHeight();
		double length = orientation.getLength();
		double width = orientation.getWidth();
		double[] sortedArray = {height,length,width};
		//sort the array by least to greatest
		Arrays.sort(sortedArray);
		//slide in the values according to packaging standards
		orientation.setLength(sortedArray[2]);
		orientation.setHeight(sortedArray[1]);
		orientation.setWidth(sortedArray[0]);
		
		return orientation;
	}
	public static ArrayList<Item> twoItems(ArrayList<Item> itemsToPack){
		Item itemOne = itemsToPack.get(0);
		Item itemTwo = itemsToPack.get(1);
		ArrayList<Item> returnList = new ArrayList<Item>();
		double height,length,width;
		double[] sortedArray = new double[3];
		
		for(int i = 0; i<=1;i++) {
			sortedArray[0] = itemsToPack.get(i).getHeight();
			sortedArray[1] = itemsToPack.get(i).getLength();
			sortedArray[2] = itemsToPack.get(i).getWidth();
			
			Arrays.sort(sortedArray);
			
			height = sortedArray[2];
			length = sortedArray[1];
			width = sortedArray[0];
			if(i==0) {
				itemOne.setHeight(height);
				itemOne.setWidth(width);
				itemOne.setLength(length);
				returnList.add(itemOne);
			}
			else {
				itemTwo.setHeight(height);
				itemTwo.setWidth(width);
				itemTwo.setLength(length);
				returnList.add(itemTwo);
			}
		}
		
		return returnList;
	}
}
