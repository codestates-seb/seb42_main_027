package ynzmz.server.security.auths.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoder;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@Getter
@Component
@Slf4j
public class JwtTokenizer {

    @Value("${jwt.key}")
    private String secretKey;


    @Value("${jwt.access-token-expiration-minutes}")
    private int accessTokenExpirationMinutes;


    @Value("${jwt.refresh-token-expiration-minutes}")
    private int refreshTokenExpirationMinutes;



    public String encodeBase64SecretKey(String secretKey){
        return Encoders.BASE64.encode(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(Map<String, Object> Claims,
                                      String subject,
                                      Date expiration,
                                      String base64EncodedSecretKey) {
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        return Jwts.builder()
                .setClaims(Claims) //JWT에 포함 시킬 Custom Claims를 추가. Custom Claims에는 주로 인증된 사용자와 관련된 정보를 추가
                .setSubject(subject) //제목추가
                .setIssuedAt(Calendar.getInstance().getTime()) //jwt발행일자 파라미터타입 : java.util.date
                .setExpiration(expiration) //만료날짜
                .signWith(key) // 서명을위한 key
                .compact(); //jwt생성후 직렬화
    }

    public String generateRefreshToken(String subject, Date expiration, String base64EncodedSecretKey){
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(Calendar.getInstance().getTime())
                .setExpiration(expiration)
                .signWith(key)
                .compact(); //별도의 Custom Claims 필요없음. AccessToken을 새로발급해주는 것이므로. 갱신개념
    }

    //JWT 를 파싱하여 Claim 정보를 추출
    public Jws<Claims> getClaims(String jws, String base64EncodedSecretKey){
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        log.info("key : " + key.toString());

        Jws<Claims> claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jws);
        return claims;
    }

    public Date getTokenExpiration(int expirationMinutes){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE,expirationMinutes);
        Date expiration = calendar.getTime();

        return expiration;
    }


    public Key getKeyFromBase64EncodedKey(String base64EncodedSecretKey){
        byte [] keyBytes = Decoders.BASE64.decode(base64EncodedSecretKey);
        Key key = Keys.hmacShaKeyFor(keyBytes);

        return key;
    }

    public void verifySignature (String jws, String base64EncodedSecretKey) {
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jws);
    }
}
