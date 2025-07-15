package common.method;

public class CalculatePremium {
	public static double CalculatePremiumByAge_Gender(double input, int age, String gender) {
		
		if (gender.equals("M")) input *= 1.05;
		
		return input * (1 + age/100.0);
	}
}
