package customer.contract.method;

public class SelectedProductName {
	private static String product_name;

	public static String getProduct_name() {
		return product_name;
	}

	public static void setProduct_name(String product_name) {
		SelectedProductName.product_name = product_name;
	}
	
	public static void clear() {
		product_name = null;
	}
}
