package ynzmz.server.member.dto;

import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.parameters.P;
import ynzmz.server.member.entity.Member;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
public class MemberPostDto {

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String username;
    @NotBlank(message = "휴대폰번호는 필수 입력 값입니다.")
    @Pattern(regexp = "(010-([0-9]{3,4})-([0-9]{4})$)", message = "휴대폰번호는 000-0000-0000 형식으로 입력해주세요.")
    private String phoneNumber;

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식으로 작성해주세요.")
    private String email;

    @NotBlank(message = "패스워드는 필수 입력 값입니다.")
//    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
    @Pattern(regexp = "(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,20}$",
            message = "비밀번호는 숫자, 특수문자가 각각 최소 1개이상 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
    private String password;

    @NotBlank(message = "패스워드를 다시 입력해주세요.")
    private String confirmPassword;

    @NotBlank(message = "닉네임은 필수 입력 값입니다.")
    private String displayName;

    private String memberState;

    @AssertTrue(message = "패스워드가 일치하지 않습니다.")
    private boolean isPasswordConfirmed() {
        return password.equals(confirmPassword);
    }

}
