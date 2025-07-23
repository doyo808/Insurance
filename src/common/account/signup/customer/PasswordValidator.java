package common.account.signup.customer;



public class PasswordValidator {
	public static String specialChars = "!@#$%^&*-_=+;:,.?~`";
	

    public static String validatePassword(String loginId, String password) {
    	String message;
    	if (password == null) {
    		message = "비밀번호를 입력하세요.(8~20자, 특수문자 및 숫자 포함): ";
    		return message;
    	}

        int length = password.length();
        if (length < 8 || length > 20) {
        	message = "비밀번호 길이는 8자 이상 20자 이하이어야 합니다.";
        	return message;
        }
        
        if (password.contains(loginId)) {
        	message = "비밀번호에 아이디는 포함될 수 없습니다.";
        	return message;
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
        	message = "허용되지 않는 문자 포함: [" + invalidChars.toString().trim() + "]";
        	return message;
        }
        if (!hasDigit) {
        	message = "숫자가 포함되어야 합니다.";
        	return message;
        }
        if (!hasSpecial) {
        	message = "특수문자가 최소 1개 포함되어야 합니다.";
        	return message;
        }
        message = "사용가능한 비밀번호 입니다.";
        return message;
    }
}


