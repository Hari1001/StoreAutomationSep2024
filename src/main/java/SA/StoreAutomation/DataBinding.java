package SA.StoreAutomation;

public class DataBinding {
	
	static String price;
	
	
	public static  String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "DataBinding [price=" + price + "]";
	}
}
