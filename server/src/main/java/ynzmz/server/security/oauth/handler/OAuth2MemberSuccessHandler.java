package ynzmz.server.security.oauth.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import ynzmz.server.member.entity.Member;
import ynzmz.server.member.repository.MemberRepository;
import ynzmz.server.member.service.MemberService;
import ynzmz.server.security.auths.jwt.JwtTokenizer;
import ynzmz.server.security.auths.utils.CustomAuthorityUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.*;

@RequiredArgsConstructor
public class OAuth2MemberSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils customAuthorityUtils;
    private final MemberRepository memberRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        var oAuth2User = (OAuth2User)authentication.getPrincipal();
        String email = String.valueOf(oAuth2User.getAttributes().get("email"));
        String displayName = String.valueOf(oAuth2User.getName());

        List<String> authorities = customAuthorityUtils.createRoles(email);

        redirect(request,response,email,displayName,authorities);
    }

    private void saveMember(String email, String displayName){
        Member member = new Member();
        member.setEmail(email);
        member.setDisplayName(displayName);
        memberRepository.save(member);
    }

    private void redirect(HttpServletRequest request, HttpServletResponse response, String username,String displayName, List<String> authorities
                          ) throws IOException{

        String accessToken = delegateAccessToken(username, displayName, authorities);
        String refreshToken = delegateRefreshToken(username);

        response.setHeader("Authorization", "Bearer " + accessToken);
        response.setHeader("Refresh", refreshToken);

        Member member = memberRepository.findByEmail(username).get();
        String uri = createURI("Bearer" + accessToken, refreshToken).toString();
        getRedirectStrategy().sendRedirect(request,response,uri);
    }

    private String delegateAccessToken(String username, String displayName, List<String> authorities){
        Map<String,Object> claims = new HashMap<>();
        claims.put("displayName", displayName);
        claims.put("roles", authorities);
        String subject = username;
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getAccessTokenExpirationMinutes());
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());
        String accessToken = jwtTokenizer.generateAccessToken(claims,subject,expiration,base64EncodedSecretKey);
        System.out.println("엑세스토큰 생성됨★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
        return accessToken;
    }

    private String delegateRefreshToken(String username){
        String subject = username;
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getRefreshTokenExpirationMinutes());
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());
        String refreshToken = jwtTokenizer.generateRefreshToken(subject,expiration,base64EncodedSecretKey);
        return refreshToken;
    }

    private URI createURI(String accessToken, String refreshToken){
        MultiValueMap<String,String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("access_token", accessToken);
        queryParams.add("refresh_token", refreshToken);

        return UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host("localhost")
                //.host("1ta.info/")
                .port(8080)
                .path("/")
                .queryParams(queryParams)
                .build()
                .toUri();
    }
}
