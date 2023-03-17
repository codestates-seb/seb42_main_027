package ynzmz.server.member.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class MemberDto{
    private String username;
    private String phoneNumber;
    private Long memberId;
    private String email;
    private String password;
    private String displayName;
    private String IconImageUrl;
    private String memberState;
    private LocalDateTime createdAt;


}
