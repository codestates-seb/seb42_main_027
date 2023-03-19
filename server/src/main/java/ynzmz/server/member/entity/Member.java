package ynzmz.server.member.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ynzmz.server.board.qna.answer.entity.Answer;
import ynzmz.server.board.qna.question.entity.Question;
import ynzmz.server.board.review.lecture.entity.LectureReview;
import ynzmz.server.vote.qna.entity.QnaVote;
import ynzmz.server.vote.review.lecture.entity.ReviewVote;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    private String username;
    private String phoneNumber;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String displayName;
    private String password;
    private String iconImageUrl;
    private String createdAt;


    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private State state;

    public enum State {
        STUDENT("학생"),
        TEACHER("강사"),
        ADMIN("관리자");

        @Getter
        private String memberState;

        State(String memberState) {
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

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<LectureReview> lectureReviews = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Answer> answers = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<ReviewVote> reviewVotes = new ArrayList<>();
    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<QnaVote> qnaVotes = new ArrayList<>();

    //양방향 맵핑 더 추가되야할것
    //리뷰게시판의 추천상태 테이블
    //자유게시판글
    //자유게시판,질문,답변,리뷰 글의 댓글
    //자유게시판,질문,답변,리뷰 글의 대댓글
}
