package customer.contract.method;

public class CalculatePremium {
	public static double CalculatePremiumByAge(double input, int age) {
		return input * (1 + age/100.0);
	}
}
