//package insurance.payments.token;
//
//import io.jsonwebtoken.*;
//import io.jsonwebtoken.security.Keys;
//
//import java.security.Key;
//import java.util.Date;
//
//public class JwtUtill {
//    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // 자동 키 생성
//    private static final long EXPIRATION_MS = 1000 * 60 * 60; // 1시간
//
//    public static String generateToken(String userId) {
//        return Jwts.builder()
//                .setSubject(userId)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
//                .signWith(key)
//                .compact();
//    }
//
//    public static String validateToken(String token) {
//        try {
//            return Jwts.parserBuilder()
//                    .setSigningKey(key)
//                    .build()
//                    .parseClaimsJws(token)
//                    .getBody()
//                    .getSubject(); // userId 반환
//        } catch (JwtException e) {
//            return null;
//        }
//    }
//}
