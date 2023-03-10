package ynzmz.server.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
public class MemberPostDto {

    @NotBlank(message = "이메일 : 값이 필요합니다.")
    @Email(message = "이메일 형식으로 작성해주세요.")
    private String email;

    @NotBlank(message = "패스워드 : 값이 필요합니다.")
    private String password;

    @NotBlank(message = "닉네임 : 값이 필요합니다.")
    private String displayName;

}
