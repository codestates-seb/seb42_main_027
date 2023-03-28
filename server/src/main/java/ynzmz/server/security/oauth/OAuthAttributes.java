package ynzmz.server.security.oauth;

import lombok.*;

import javax.management.relation.Role;
import java.util.Map;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OAuthAttributes {
    private Map <String, Object> attributes;
    private String nameAttributeKey;
    private String username;
    private String displayName;
    private String email;
    private Role role;

    public static OAuthAttributes of(String registrationId,
                                     String userNameAttributeName,
                                     Map<String,Object> attributes){
        //구글인지 네이버인지 카카오인지 구분하기 위한 메서드

        return ofGoogle(userNameAttributeName,attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName,
                                            Map<String,Object> attributes) {
        String str = (String) attributes.get("username");
        UUID uuid = UUID.randomUUID();
        String cut = uuid.toString().substring(0,3);
        return OAuthAttributes.builder()
                .username("Google@"+cut+"_"+str.replaceAll(" ",""))
                .email((String) attributes.get("email"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }



}
