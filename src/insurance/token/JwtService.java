package insurance.token;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

public class JwtService {

    // !!! 경고 !!!
    // 실제 운영 환경에서는 이 키를 코드에 하드코딩하면 안 됩니다.
    // 외부 설정 파일이나 환경 변수에서 안전하게 불러와야 합니다.
    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // 토큰 만료 시간 (예: 1시간)
    private static final long EXPIRATION_TIME = 1000 * 60 * 60;

    /**
     * 사용자의 아이디를 기반으로 JWT를 생성합니다.
     * @param username 사용자 아이디
     * @return 생성된 JWT 문자열
     */
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username) // 토큰의 주체 (사용자 아이디)
                .setIssuedAt(new Date(System.currentTimeMillis())) // 발급 시간
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // 만료 시간
                .signWith(SECRET_KEY) // 서명 키
                .compact();
    }

    /**
     * 토큰이 유효한지 검증합니다.
     * @param token 검증할 JWT
     * @param username 확인할 사용자 아이디
     * @return 유효하면 true, 아니면 false
     */
    public boolean validateToken(String token, String username) {
        try {
            final String extractedUsername = extractUsername(token);
            return (extractedUsername.equals(username) && !isTokenExpired(token));
        } catch (Exception e) {
            // 서명 오류, 만료 등 모든 예외를 처리
            return false;
        }
    }

    /**
     * 토큰에서 사용자 아이디를 추출합니다.
     * @param token JWT
     * @return 사용자 아이디
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * 토큰에서 만료 시간을 추출합니다.
     * @param token JWT
     * @return 만료 시간 Date 객체
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * 토큰이 만료되었는지 확인합니다.
     * @param token JWT
     * @return 만료되었으면 true
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * 토큰의 모든 클레임(정보)을 추출하는 범용 메서드
     * @param token JWT
     * @param claimsResolver 클레임을 추출하는 함수
     * @return 추출된 클레임
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * 토큰을 파싱하여 모든 클레임을 반환합니다.
     * @param token JWT
     * @return 클레임 객체
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}

