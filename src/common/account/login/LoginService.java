package common.account.login;

import java.sql.Connection;
import java.util.Base64;
import java.util.Scanner;

import common.account.signup.customer.PasswordEncryptor;
import common.database.dao.CustomerDAO;
import common.database.model.CustomerModel;
import common.method.InsuranceTeamConnector;

public class LoginService {
	// 아이디와 비밀번호를 입력하면, DB에서 salt를 찾아와서 salt+ 입력한pw로 암호화한다음 저장된 해시값과 맞으면 로그인
	// 테스트용 계정> 김길동 abcd1234 qwer1234!	901212-1234567
	
	public static void main(String[] args) {
		LoginService ls = new LoginService();
		
		System.out.println(ls.login());
		
	}
	
	public static CustomerModel login() {
		try (Connection conn = InsuranceTeamConnector.getConnection()) {
			
			Scanner sc = new Scanner(System.in);
			System.out.println("아이디를 입력하세요: ");
			String inputLogin_id = sc.next();
			if (inputLogin_id == null) {
				System.out.println("입력한 아이디가 없습니다.");
				return null;
			}
			
			System.out.println("비밀번호를 입력하세요: ");
			String inputPassword = sc.next();
			
			CustomerModel c = CustomerDAO.getCustomerByLoginId(inputLogin_id, conn);
			String password_salt = c.getPassword_salt();
			String password_hash = c.getPassword_hash();
			
			byte[] oriSalt = Base64.getDecoder().decode(password_salt);
			String inputPassword_hash = PasswordEncryptor.hashPassword(inputPassword, oriSalt);
			
			if (inputPassword_hash.equals(password_hash)) {
				System.out.println("로그인 성공!!");
				return c;
			} else {
				System.out.println("비밀번호를 확인하세요..");
				return null;
			}
			
		} catch (Exception e) {
			e.getStackTrace();
		}
		return null;
	}
	
	public static CustomerModel login(String login_id, String password_input) {
		try (Connection conn = InsuranceTeamConnector.getConnection()) {
			
			String inputLogin_id = login_id;
			if (inputLogin_id == null) {
				return null;
			}
			
			String inputPassword = password_input;
			
			CustomerModel c = CustomerDAO.getCustomerByLoginId(inputLogin_id, conn);
			if (c == null) {
				return null;
			}
			
			String password_salt = c.getPassword_salt();
			String password_hash = c.getPassword_hash();
			
			byte[] oriSalt = Base64.getDecoder().decode(password_salt);
			String inputPassword_hash = PasswordEncryptor.hashPassword(inputPassword, oriSalt);
			
			if (inputPassword_hash.equals(password_hash)) {
				return c;
			} else {
				return null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	
}
