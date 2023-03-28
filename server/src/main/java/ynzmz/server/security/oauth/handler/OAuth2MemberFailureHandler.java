package ynzmz.server.security.oauth.handler;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.yaml.snakeyaml.util.UriEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

public class OAuth2MemberFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException{
        logger.info("login fail handler");

        String errorMessage;
        if(e instanceof BadCredentialsException || e instanceof InternalAuthenticationServiceException){
            errorMessage = "아이디 또는 비밀번호가 일치하지 않습니다.";
        } else if (e instanceof UsernameNotFoundException){
            errorMessage = "존재하지 않는 아이디 입니다.";
        } else {
            errorMessage = "로그인이 되지 않습니다.";
        }

        errorMessage = URLEncoder.encode(errorMessage,"UTF-8");
        setDefaultFailureUrl("/loginForm?error=true?exception=" + errorMessage);
        super.onAuthenticationFailure(httpServletRequest,httpServletResponse,e);

    }
}
