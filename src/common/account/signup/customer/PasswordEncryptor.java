package common.account.signup.customer;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordEncryptor {
	// SHA-256 + Salt 방식으로 암호화
	// 입력받은 비밀번호를 바로 해시화하면 무차별공격에 취약하다
	// 따라서 무작위 문자열을 첨가하는데 이를 salt라 부른다
	// salt는 무작위성이 중요해서 byte배열을 쓴다 (secureRandom 함수는 무작위성이 뛰어나다)
	// 또한 해시를 생성하는 해시함수도 바이트배열을 입력받는다(문자열이면 바이트배열로 변환해야함)
	/// TODO: 반복해싱 도입해서 일부러 해시화속도를 늦추기(브루트포스 방어)
	
	/// salt 생성
    public static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16]; // 128비트, 16바이트
        random.nextBytes(salt);
        return salt;
    }
    
    /// 해시 생성
    public static String hashPassword(String password, byte[] salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");	// SHA-256방식 암호화해주는 인스턴스 생성
        md.update(salt); 											// 먼저 salt 넣기
        byte[] hashed = md.digest(password.getBytes());				// 비밀번호도 바이트배열로 변환해서 넣기
        return Base64.getEncoder().encodeToString(hashed);			// 바이트배열을 인코딩해서 문자열로 바꾸기
    }   
    
    /// Salt와 해시를 함께 저장 (예시)
    public static void main(String[] args) throws Exception {
        String password = "mySecretPassword";

        byte[] salt = generateSalt();
        String hash = hashPassword(password, salt);

        String encodedSalt = Base64.getEncoder().encodeToString(salt);

        System.out.println("Salt: " + encodedSalt);
        System.out.println("Hash: " + hash);

        // 저장 시 → DB에 salt, hash 둘 다 저장
    }
}
