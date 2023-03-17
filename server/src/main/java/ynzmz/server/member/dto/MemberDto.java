package ynzmz.server.member.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ynzmz.server.review.lecture.dto.LectureReviewDto;
import ynzmz.server.review.lecture.entity.LectureReview;
import ynzmz.server.vote.question.answer.dto.LoginUserAnswerVoteResponseDto;
import ynzmz.server.vote.question.question.entity.QuestionVote;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class MemberDto{
    @Getter
    @Setter
    @AllArgsConstructor
    public static class Post {
        @NotBlank(message = "이름은 필수 입력 값입니다.")
        private String username;
        @NotBlank(message = "휴대폰번호는 필수 입력 값입니다.")
        @Pattern(regexp = "(^[0-9]{10,11}$)", message = "휴대폰번호는 10~11 길이의 숫자로 입력해주세요.")
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
        private String State;
        private String createdAt;
        @AssertTrue(message = "패스워드가 일치하지 않습니다.")
        private boolean isPasswordConfirmed() {
            return password.equals(confirmPassword);
        }

    }

    @Getter
    @AllArgsConstructor
    public static class Patch {
        @NotBlank(message = "휴대폰번호는 필수 입력 값입니다.")
        private String phoneNumber;
        @NotBlank(message = "패스워드는 필수 입력 값입니다.")
        private String password;
        @NotBlank(message = "닉네임은 필수 입력 값입니다.")
        private String displayName;
        private String iconImageUrl;

    }
    @Getter
    @Setter
    @AllArgsConstructor
    public static class SimpleInfoResponse{
        private Long memberId;
        private String displayName;
        private String IconImageUrl;
        private String state;
    }
    @Getter
    @Setter
    @AllArgsConstructor
    public static class Response{
        private String username;
        private String phoneNumber;
        private Long memberId;
        private String email;
        private String displayName;
        private String IconImageUrl;
        private String state;
        private LocalDateTime createdAt;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VoteInfo {
        private Long memberId;
        private String email;
        private Long questionId;
        private QuestionVote.VoteStatus questionvoteStatus;
        private List<LoginUserAnswerVoteResponseDto> answerVoteStatus = new ArrayList<>();

    }

    @Getter @Setter
    @NoArgsConstructor @AllArgsConstructor
    public static class MyLectureReview {
        private List<LectureReviewDto.ListPageResponse> lectureReview = new ArrayList<>();
    }



}
