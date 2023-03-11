package ynzmz.server.member.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class MemberPatchDto {

    private long memberId;
    private String password;
    private String displayName;

    public void setMemberId(long memberId){
        this.memberId = memberId;
    }

}
