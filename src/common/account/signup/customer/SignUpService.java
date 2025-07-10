package common.account.signup.customer;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Base64;

import common.database.dao.CustomerDAO;
import common.database.model.CustomerModel;
import common.method.InsuranceTeamConnector;

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
		
		/// TODO: 선택항목도 구현해야함
		if (!AgreementManager.getAuthTerms()) {
			return;
		}
		
		/// TODO: 주민등록번호 암호화 필요
		String[] info = AuthenticationManager.getAuthentication();
		if (info[0] == null) {
			System.out.println("본인인증이 실패하였습니다.");
			return;
		}
		String name = info[0];
		String personal_id = info[1];
		String phone_number = info[2];
		
		/// TODO: 인증이 끝나면, 아이디 있나 체크하고 로그인 시키기
		try (Connection conn = InsuranceTeamConnector.getConnection()) {
			CustomerModel c = CustomerDAO.getCustomerByPersonalId(personal_id, conn);
			if (c == null) {
				System.out.println("아이디와 비밀번호를 설정해주세요");
			} else {
				String loginId = c.getLogin_id();
				System.out.printf("이미 가입하셨습니다. 아이디는 %s입니다. 로그인하시겠습니까?\n", loginId);
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		// id 검증받기
		String login_id = null;
		try {
			login_id = LoginIdValidator.getVerifiedLoginId();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// 비밀번호 검증받기
		String password = PasswordValidator.validatePassword(login_id);
		
		// 검증받은 비밀번호를 암호화해서 hash와 salt를 저장하기
		byte[] salt = PasswordEncryptor.generateSalt();
		String encodedSalt = Base64.getEncoder().encodeToString(salt);
		String password_hash = null;
		try {
			password_hash = PasswordEncryptor.hashPassword(password, salt);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		// 입력받은 정보를 토대로 객체생성
		CustomerModel c = new CustomerModel(name, personal_id, phone_number
										, login_id, password_hash, encodedSalt);
		
		/// TODO: 위의 CONN과 합치기
		/// 객체를 DB에 저장
		try (Connection conn = InsuranceTeamConnector.getConnection()) {
		
			CustomerDAO.addCustomer(c, conn);
			System.out.printf("가입완료!! %d고객님 가입 축하드립니다\n", name);
			
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}







