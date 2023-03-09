package ynzmz.server.auths;

import io.jsonwebtoken.io.Decoders;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.rules.Timeout;
import ynzmz.server.security.auths.jwt.JwtTokenizer;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class JwtTokenizerTest {
    private static JwtTokenizer jwtTokenizer;
    private String secretkey;
    private String base64EncodedSecretkey;

    @BeforeAll
    public void init() {
        jwtTokenizer = new JwtTokenizer();
        secretkey = "davin1234123412341234123412341234";

        base64EncodedSecretkey = jwtTokenizer.encodeBase64SecretKey(secretkey);
    }

    @Test
    public void encodeBase64SecretKeyTest() {
        System.out.println(base64EncodedSecretkey);

        assertThat(secretkey, is(new String(Decoders.BASE64.decode(base64EncodedSecretkey))));
    }

    @Test
    public void generateAccessTokenTest() {
        Map<String,Object> claims = new HashMap<>();
        claims.put("memberId", 1);
        claims.put("roles", List.of("USER"));

        String subject = "test access token";
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 10);
        Date expiration = calendar.getTime();

        String accessToken = jwtTokenizer.generateAccessToken(claims, subject, expiration, base64EncodedSecretkey);

        System.out.println(accessToken);

        assertThat(accessToken, notNullValue());
    }

    @Test
    public void generateRefreshTokenTest() {
        String subject = "test refresh token";
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 24);
        Date expiration = calendar.getTime();

        String refreshToken = jwtTokenizer.generateRefreshToken(subject, expiration, base64EncodedSecretkey);

        System.out.println(refreshToken);

        assertThat(refreshToken, notNullValue());
    }
}
