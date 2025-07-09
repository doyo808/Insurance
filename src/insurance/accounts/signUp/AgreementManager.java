package insurance.accounts.signUp;

import java.util.Scanner;

public class AgreementManager {
	// 약관 동의를 받는 곳
	
	public static boolean getAuthTerms() {	// 필수 항목 동의했나 구하는 메서드
		Scanner sc = new Scanner(System.in);
		
		System.out.print("만 14세 이상입니다.\n> ");
		if (!sc.next().toUpperCase().equals("Y")) {
			System.out.println("연령제한으로 가입이 불가합니다.");
			return false;
		}
		
		System.out.print("약관에 동의합니다.\n> ");
		if (!sc.next().toUpperCase().equals("Y")) {
			System.out.println("약관에 동의하셔야 가입이 가능합니다.");
			return false;
		}
		System.out.println("약관 동의가 완료되었습니다.");
		return true;
	}
}
