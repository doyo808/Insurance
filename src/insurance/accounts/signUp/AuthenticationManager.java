package insurance.accounts.signUp;

import java.util.Calendar;
import java.util.Scanner;

public class AuthenticationManager {
	// 본인인증 하는 곳 (가상 SMS로 처리)
	
	public static String[] getAuthentication() {
		Scanner sc = new Scanner(System.in);
		System.out.println();
		System.out.println("본인인증을 시작합니다.");
		// 1. 유저가 이름, 주민등록번호, 전화번호 입력하면 (이때 이미 가입했는지 체크하고, 했다면 아이디 보여줌.)
		/// TODO: 주민등록번호 암호화 필요
		
		String name = null;
		String personal_id = null;
		String phone_number = null;
		try {
			// 이름 입력 및 검증
			while (true) {
				System.out.print("이름을 입력하세요: ");
				name = sc.next();
				if (isValidName(name)) break;
				System.out.println("잘못된 이름입니다. 다시 입력해주세요.");
			}

			// 주민등록번호 입력 및 검증
			while (true) {
				System.out.print("주민등록번호를 입력하세요: ");
				personal_id = sc.next();
				if (isValidPersonal_id(personal_id)) break;
				System.out.println("잘못된 주민등록번호입니다. 다시 입력해주세요.");
			}

			// 전화번호 입력 및 검증
			while (true) {
				System.out.print("전화번호를 입력하세요 (숫자만 입력): ");
				phone_number = sc.next();
				if (isValidPhoneNumber(phone_number)) {
					phone_number = formatPhoneNumber(phone_number);
					break;
				}
				System.out.println("잘못된 전화번호입니다. 다시 입력해주세요.");
			}

			// 인증 코드 생성 및 가상 발송
			int code = getRandomCode();
			System.out.println("\n[문자메시지] 인증번호가 " + code + " 입니다.");

			// 인증 코드 입력 (기회 3번)
			boolean verified = false;
			for (int i = 1; i <= 3; i++) {
				System.out.print("인증번호를 입력하세요 (" + i + "/3): ");
				int inputCode = sc.nextInt();
				if (inputCode == code) {
					System.out.println("인증에 성공했습니다!");
					verified = true;
					break;
				} else {
					System.out.println("인증번호가 일치하지 않습니다.");
				}
			}

			if (!verified) {
				System.out.println("인증에 3회 실패하였습니다. 처음부터 다시 시도해주세요.");
				return null;
			}

		} catch (Exception e) {
			System.out.println("인증 과정 중 오류 발생: " + e.getMessage());
		}
		
		return new String[] {name, personal_id, phone_number};
	}
		
	
	
	// 한글이름 검증기
	static boolean isValidName(String name) {
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
	// 주민등록번호 검증기
	static Calendar now = Calendar.getInstance();
	static int curr_year = now.get(Calendar.YEAR);
	static int curr_month = now.get(Calendar.MONTH);
	static int curr_day = now.get(Calendar.DAY_OF_MONTH);
	static boolean isValidPersonal_id(String personal_id) {
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
			Calendar dob = Calendar.getInstance();
			dob.setLenient(false); // 유효하지 않은 날짜면 예외 발생
			dob.set(year, month - 1, day); // Calendar의 month는 0부터 시작
			dob.getTime(); // 이 호출에서 예외 발생할 수 있음
		} catch (Exception e) {
			return false;
		}

		// 4. 만 14세 이상인지 확인
		Calendar dob = Calendar.getInstance();
		dob.set(year, month - 1, day);

		Calendar minDate = Calendar.getInstance();
		minDate.add(Calendar.YEAR, -14); // 14년 전
		if (dob.after(minDate)) {
			return false;
		}

		return true;
	}
	
	// 전화번호 검증기 (숫자만, 10~11자리)
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
	
	// 코드 생성기
	static int codeLength = 4;
	public static int getRandomCode() {
		int code = 0;
		
		for (int i = 0; i < codeLength; i++) {
			code *= 10;
			int temp = (int)(Math.random() * 9 + 1);
			code += temp;
		}
		return code;
	}
	
}
