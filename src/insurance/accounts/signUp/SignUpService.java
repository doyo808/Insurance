package insurance.accounts.signUp;

public class SignUpService {
	// 1. 아이디를 입력받는다 -> 아이디가 db에 있는지 확인한다
	// 2. 없다면 비밀번호를 입력받는다. -> 비밀번호가 유효한 비밀번호인지 확인받는다(ex> 알파벳 + 숫자 10글자 이상 20글자 이하)
	// 3. 아이디, 비밀번호가 유효하다면 -> 개인정보 입력란으로 이동! 입력이 끝나면 가입완료.
	
	/*
	 	1. 약관 동의받기
		(14세 이상, 이용약관, 
		
		2. 본인인증하기
		(sms로 코드 보냈다고 하고, 보낸코드 콘솔에 입력하면 성공)
		이때, 이름, 주민등록번호, 전화번호를 입력한 다음 본인인증을 날린다
		
		-> 이때, 만약 db에 현 아이디가 있다면 로그인하라고 한다
		(아이디 찾기, 비밀번호 재설정도 구현해야하겠다)
		-> 없다면, 회원가입으로 이동한다. 전화번호, 이름, 주민번호는 이미 받았고
		나머지 입력사항 받고 가입시키자.
		(꼭 받을 것: 아이디(중복확인), 비밀번호, 이메일
	 */
	public static void main(String[] args) {
		SignUpService s = new SignUpService();
		s.registerUser();
	}
	
	public void registerUser() {
		boolean isAgreed = AgreementManager.getAuthTerms();
		/// TODO: AgreementManager에서 선택항목도 구현해야함
		
		if (isAgreed) {
			
		}
//		public boolean authenticate() {
//	        // 본인 인증 처리
//	    }
//
//	    public boolean validateId(String id) {
//	        // 아이디 검증
//	    }
//
//	    public boolean validatePassword(String pw) {
//	        // 비밀번호 검증
//	    }
//
//	    public String encryptPassword(String pw) {
//	        // 암호화
//	    }
//
//	    public boolean saveUser(String id, String encryptedPw) {
//	        // DB 저장
//		}
	    
	}
}







