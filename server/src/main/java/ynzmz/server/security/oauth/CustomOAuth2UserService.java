package ynzmz.server.security.oauth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import ynzmz.server.error.exception.CustomException;
import ynzmz.server.error.exception.ExceptionCode;
import ynzmz.server.member.entity.Member;
import ynzmz.server.member.repository.MemberRepository;
import ynzmz.server.security.auths.userdetails.PrincipalDetails;
import ynzmz.server.security.auths.utils.CustomAuthorityUtils;

import java.util.*;


@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final MemberRepository memberRepository;
    private final CustomAuthorityUtils customAuthorityUtils;
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest,OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        //OAuth2 서비스 id 구분코드(구글,카카오,네이버)
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        //OAuth2 로그인 진행시 키가 되는 필드값
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();


        //OAuth2UserService
        OAuthAttributes attributes = OAuthAttributes.of(registrationId,userNameAttributeName,oAuth2User.getAttributes());


        Optional<Member> verifiedByEmail = memberRepository.findByEmail(attributes.getEmail());
        Optional<Member> verifiedByDisplayName = memberRepository.findByDisplayName(attributes.getDisplayName());


        if(verifiedByEmail.isPresent()){
            Member verifiedMember = verifiedByEmail.orElseThrow(() -> new CustomException(ExceptionCode.MEMBER_NOT_FOUND));

            return new PrincipalDetails(verifiedMember, attributes.getAttributes());

        }else if (verifiedByDisplayName.isPresent()){
            Member verifiedMember = verifiedByDisplayName.orElseThrow(() -> new CustomException(ExceptionCode.MEMBER_NOT_FOUND));

            return new PrincipalDetails(verifiedMember, attributes.getAttributes());

        }else {
            Member verifiedMember = save(attributes);

            return new PrincipalDetails(verifiedMember, attributes.getAttributes());

        }
    }


    private Member save(OAuthAttributes attributes) {
        Member member = new Member();
        member.setEmail(attributes.getEmail());
        member.setDisplayName(attributes.getDisplayName());
        List<String> authorities = customAuthorityUtils.createRoles(member.getEmail());
        member.setRoles(authorities);
        return memberRepository.save(member);
    }
}
