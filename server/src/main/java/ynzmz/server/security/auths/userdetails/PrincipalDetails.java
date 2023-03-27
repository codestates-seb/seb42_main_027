package ynzmz.server.security.auths.userdetails;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import ynzmz.server.member.entity.Member;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PrincipalDetails implements UserDetails, OAuth2User {
    private Member member;
    private Map<String,Object> attributes;


    //OAuth로그인
    public PrincipalDetails(Member member, Map<String,Object> attributes){
        this.member = member;
        this.attributes = attributes;
    }

    @Override
    public String getPassword(){
        return member.getPassword();
    }

    @Override
    public String getUsername(){
        return member.getEmail();
    }

    //해당 유저의 권한 리턴
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = this.member.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Map<String,Object> getAttributes(){
        return attributes;
    }

    @Override
    public <A> A getAttribute(String name){
        return OAuth2User.super.getAttribute(name);
    }

    @Override
    public String getName(){
        return member.getDisplayName();
    }


}
