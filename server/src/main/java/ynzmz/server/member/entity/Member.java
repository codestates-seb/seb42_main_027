package ynzmz.server.member.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Member {

    private String username;

    private String phoneNumber;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    @Column(unique = true)
    private String email;
    private String displayName;
    private String password;
    private String iconImageUrl;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();


    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private MemberState memberState = MemberState.STUDENT;



    public enum MemberState{
        STUDENT("학생"),
        TEACHER("강사");

        @Getter
        private String memberState;

        MemberState(String memberState) {
            this.memberState = memberState;
        }
    }

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private MemberStatus memberStatus = MemberStatus.MEMBER_ACTIVE;

    public enum MemberStatus{
        MEMBER_ACTIVE("활동중"),
        MEMBER_SLEEP("휴면 상태"),
        MEMBER_DELETE("탈퇴 상태");

        @Getter
        private String status;

        MemberStatus(String status){
           this.status = status;
       }
    }


}
