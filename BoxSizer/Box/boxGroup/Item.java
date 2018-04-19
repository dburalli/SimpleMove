package boxGroup;

public class Item {
	double height;
	double width;
	double length;
	String name;
	
	public Item(double length, double width, double height, String name) {
		this.height = height;
		this.width = width;
		this.length = length;
		this.name = name;
	}
	
	public static double[] getItemSize(double l, double w, double h) {
		//can't assume width x height x length, as items may be irregular and fit a certain way
		double[] size = null;
		
		return size;
	}
	public double getSimpleVolume() {
		return height*width*length;
	}
	
	
	
	
	public double getHeight() {
		return height;
	}
	public double getWidth() {
		return width;
	}
	public double getLength() {
		return length;
	}
	public String getName() {
		return name;
	}
	public void setHeight(double h) {
		this.height = h;
	}
	public void setWidth(double w) {
		this.width = w;
	}
	public void setLength(double l) {
		this.length = l;
	}
	public void setName(String s) {
		this.name = s;
	}
}
