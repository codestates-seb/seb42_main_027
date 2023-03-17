package ynzmz.server.member.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class MemberPatchDto {

    private long memberId;
    @NotBlank(message = "휴대폰번호는 필수 입력 값입니다.")
    private String phoneNumber;
    @NotBlank(message = "패스워드는 필수 입력 값입니다.")
    private String password;
    @NotBlank(message = "닉네임은 필수 입력 값입니다.")
    private String displayName;

    private String iconImageUrl;


    public void setMemberId(long memberId){
        this.memberId = memberId;
    }

}
