package common.account.signup.customer;

import java.util.Scanner;

public class PasswordValidator {
	static String specialChars = "!@#$%^&*-_=+;:,.?~`";
	
	public static void main(String[] args) {
		validatePassword("abc12345");
	}

    public static String validatePassword(String loginId) {
    	Scanner sc = new Scanner(System.in);
    	String password = null;
    	
    	System.out.println();
    	while (true) {
    		System.out.println("비밀번호를 입력하세요.(8~20자, 특수문자 및 숫자 포함): ");
    		password = sc.next();
    		
	        if (password == null) {
	            System.out.println("비밀번호 입력이 실패했습니다.");
	            continue;
	        }
	
	        int length = password.length();
	        if (length < 8 || length > 20) {
	        	System.out.println("비밀번호 길이는 8자 이상 20자 이하이어야 합니다.");
	        	continue;
	        }
	        
	        if (password.contains(loginId)) {
	        	System.out.println("비밀번호에 아이디는 포함될 수 없습니다.");
	        	continue;
	        }
	
	        boolean hasSpecial = false;
	        boolean hasDigit = false;
	
	        StringBuilder invalidChars = new StringBuilder();
	
	        for (char c : password.toCharArray()) {
	            if (Character.isDigit(c)) {
	                hasDigit = true;
	            } else if (specialChars.indexOf(c) >= 0) {
	                hasSpecial = true;
	            } else if (Character.isLetter(c)) {
	                continue;
	            } else {
	                invalidChars.append(c).append(' ');
	            }
	        }
	
	        if (invalidChars.length() > 0) {
	        	System.out.println("허용되지 않는 문자 포함: [" + invalidChars.toString().trim() + "]");
	        	continue;
	        }
	        if (!hasDigit) {
	        	System.out.println("숫자가 포함되어야 합니다.");
	        	continue;
	        }
	        if (!hasSpecial) {
	        	System.out.println("특수문자가 최소 1개 포함되어야 합니다.");
	        	continue;
	        }
	        System.out.println("사용가능한 비밀번호 입니다.");
	        System.out.println("비밀번호를 다시 한 번 입력해주세요.");
	        String passwordSecond = sc.next();
	        if (password.equals(passwordSecond)) {
	        	System.out.println("비밀번호 검증이 끝났습니다.");
	        	break;
	        } else {
	        	System.out.println("입력된 비밀번호가 서로 다릅니다.");
	        }
    	}
    	return password;
    }
    
 
}


