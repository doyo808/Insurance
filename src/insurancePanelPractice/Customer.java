package insurancePanelPractice;

import java.util.List;

public class Customer {
	private String name;
	private String phone_number;
	private List<String> subscribedProducts;
	
	
	public Customer(String name, String phone_number, List<String> subscribedProducts) {
		super();
		this.name = name;
		this.phone_number = phone_number;
		this.subscribedProducts = subscribedProducts;
	}
	
	@Override
	public String toString() {
		return "Customer [name=" + name + ", phone_number=" + phone_number + ", subscribedProducts="
				+ subscribedProducts + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public List<String> getSubscribedProducts() {
		return subscribedProducts;
	}
	public void setSubscribedProducts(List<String> subscribedProducts) {
		this.subscribedProducts = subscribedProducts;
	}
	
	
}
