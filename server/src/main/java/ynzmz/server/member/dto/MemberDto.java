package ynzmz.server.member.dto;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto{
    private Long memberId;
    private String email;
    private String password;
    private String displayName;
    private String IconImageUrl;
    private LocalDateTime createdAt;


}
