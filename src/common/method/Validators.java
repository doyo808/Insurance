package common.method;

import java.util.Calendar;

public class Validators {
	/// FIXME: 로그인아이디 검증기
	public static boolean isValidLoginId(String login_id) {
	    return login_id != null && login_id.matches("[a-zA-Z0-9]{4,12}");
	}
	
	/// FIXME: 한글이름 검증기
	public static boolean isValidName(String name) {
		if (name == null) {
			return false;
		}
		name = name.trim();
		
		int len = name.length();
		if (len <= 1 || len > 10) {
			return false;
		}
		
		char[] chars = name.toCharArray();
		for (char c : chars) {
			if (c >= '가' && c <= '힣') {
				continue;
			} else {
				return false;
			}
		}
		return true;
	}
	
	/// FIXME: 주민등록번호 검증기 (만 14세 이상)
	public static boolean isValidPersonal_id(String personal_id) {
		
		if (personal_id == null || !personal_id.matches("\\d{6}-\\d{7}")) {
			return false;
		}

		String[] splited_id = personal_id.split("-");
		String left = splited_id[0];   // 생년월일
		String right = splited_id[1];  // 성별 및 기타 정보

		// 1. 생년월일 유효성 체크
		int year = Integer.parseInt(left.substring(0, 2));
		int month = Integer.parseInt(left.substring(2, 4));
		int day = Integer.parseInt(left.substring(4, 6));

		// 2. 뒷자리 첫 숫자로 세기 판별
		char centuryCode = right.charAt(0);
		if (centuryCode < '1' || centuryCode > '4') return false;

		if (centuryCode == '1' || centuryCode == '2') {
			year += 1900;
		} else if (centuryCode == '3' || centuryCode == '4') {
			year += 2000;
		} else {
			return false;
		}

		// 3. 생년월일이 유효한지 확인 (날짜 유효성)
		try {
			Calendar now = Calendar.getInstance();
			now.setLenient(false); // 유효하지 않은 날짜면 예외 발생
			now.set(year, month - 1, day); // Calendar의 month는 0부터 시작
			now.getTime(); // 이 호출에서 예외 발생할 수 있음
		} catch (Exception e) {
			return false;
		}

		// 4. 만 14세 이상인지 확인
		Calendar now = Calendar.getInstance();
		now.set(year, month - 1, day);

		Calendar minDate = Calendar.getInstance();
		minDate.add(Calendar.YEAR, -14); // 14년 전
		if (now.after(minDate)) {
			return false;
		}

		return true;
	}
	
	/// FIXME: 전화번호 검증기 (숫자만, 10~11자리)
	static boolean isValidPhoneNumber(String phone) {
		return phone != null && phone.matches("^\\d{10,11}$");
	}
	
	// 전화번호 - 붙이기 (저장용)
	public static String formatPhoneNumber(String rawNumber) {
		if (rawNumber.length() == 11) {
			return rawNumber.substring(0, 3) + "-" +
			       rawNumber.substring(3, 7) + "-" +
			       rawNumber.substring(7);
		}
		return rawNumber; // 형식 안 맞으면 원본 반환
	}
	
	
}
