package ynzmz.server.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CorsFilter;
import ynzmz.server.member.repository.MemberRepository;
import ynzmz.server.security.auths.filter.JwtAuthenticationFilter;
import ynzmz.server.security.auths.filter.JwtVerificationFilter;
import ynzmz.server.security.auths.handler.MemberAccessDeniedHandler;
import ynzmz.server.security.auths.handler.MemberAuthenticationEntryPoint;
import ynzmz.server.security.auths.handler.MemberAuthenticationFailureHandler;
import ynzmz.server.security.auths.handler.MemberAuthenticationSuccessHandler;
import ynzmz.server.security.auths.jwt.JwtTokenizer;
import ynzmz.server.security.auths.utils.CustomAuthorityUtils;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
//@EnableGlobalMethodSecurity(prePostEnabled = true) //특정주소로 접근하면 권한및 인증을 미리 체크
public class SecurityConfiguration {


    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils authorityUtils;
    private final CorsFilter corsFilter;
    private final MemberRepository memberRepository;



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .headers().frameOptions().sameOrigin() //H2 웹 콘솔을 정상적으로 사용할 수 있도록. 동일 출처로부터 들어오는 request만 페이지 렌더링을 허용
                .and()
                .csrf().disable()
                .cors(withDefaults()) //corsConfigurationSource라는 이름으로 등록된 Bean을 이용합니다.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                .exceptionHandling()
                .authenticationEntryPoint(new MemberAuthenticationEntryPoint())
                .accessDeniedHandler(new MemberAccessDeniedHandler())
                .and()
                .apply(new CustomFilterConfigurer())
                .and()
                    .authorizeRequests(authorize -> authorize
                        .anyRequest().permitAll());



        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    public class CustomFilterConfigurer extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity>{
        @Override //configure() 메서드를 오버라이드 Configuration을 커스터마이징.
        public void configure(HttpSecurity builder) throws Exception {
            AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);

            JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager, jwtTokenizer);
            jwtAuthenticationFilter.setFilterProcessesUrl("/auth/login");
            jwtAuthenticationFilter.setAuthenticationSuccessHandler(new MemberAuthenticationSuccessHandler());
            jwtAuthenticationFilter.setAuthenticationFailureHandler(new MemberAuthenticationFailureHandler());

            JwtVerificationFilter jwtVerificationFilter = new JwtVerificationFilter(jwtTokenizer, authorityUtils);

            builder
                    .addFilter(corsFilter)
                    .addFilter(jwtAuthenticationFilter)//Authentication 이후에 verification 동작.
                    .addFilterAfter(jwtVerificationFilter, JwtAuthenticationFilter.class);

        }
    }

//    @Bean
//    public ClientRegistrationRepository clientRegistrationRepository(){
//        var googleClientRegistration = clientRegistration();
//        return new InMemoryClientRegistrationRepository(googleClientRegistration);
//    }
//
//    private ClientRegistration clientRegistration() {
//        System.out.println("Client Registration");
//        return ClientRegistration.withRegistrationId("google")
//                .clientId(googleClientId)
//                .clientSecret(googleClientSecret)
//                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//                .redirectUri("http://localhost:8080/login/oauth2/code/google")
//                .scope("profile","email")
//                .authorizationUri("https://accounts.google.com/o/oauth2/auth")
//                .tokenUri("https://oauth2.googleapis.com/token")
//                .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
//                .userNameAttributeName(IdTokenClaimNames.SUB)
//                .jwkSetUri("https://www.googleapis.com/oauth2/v1/certs")
//                .clientName("Google")
//                .build();
//    }


}
