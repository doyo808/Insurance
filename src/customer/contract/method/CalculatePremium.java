package customer.contract.method;

public class CalculatePremium {
	public static double CalculatePremiumByAge(double input, int age) {
		double multiplier;
	    if (age < 5) {
	        multiplier = 1.5; // 어린이(영유아) 리스크 보정: 50% 추가
	    } else if (age <= 30) {
	        multiplier = 1.0; // 젊은 성인 구간 기본 보험료
	    } else if (age <= 50) {
	        multiplier = 1.2; // 중년 구간 20% 증가
	    } else if (age <= 70) {
	        multiplier = 1.7; // 노년 구간 70% 증가
	    } else {
	        multiplier = 2.5; // 고령 구간 대폭 증가
	    }
	    return input * multiplier;
	}
}
