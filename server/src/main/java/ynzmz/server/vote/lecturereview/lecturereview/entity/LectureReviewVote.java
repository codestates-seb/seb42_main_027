package ynzmz.server.vote.lecturereview.lecturereview.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ynzmz.server.lecturereview.entity.LectureReview;
import ynzmz.server.member.entity.Member;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class LectureReviewVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectureReviewVoteId;
    @Enumerated(EnumType.STRING)
    private VoteStatus voteStatus = VoteStatus.NONE;
    @ManyToOne
    @JoinColumn(name = "lecture_review_post_id")
    private LectureReview lectureReview;
    @ManyToOne
    @JoinColumn(name = "member_id")
    @JsonBackReference
    private Member member;
    public LectureReviewVote(LectureReview lectureReview, Member member, VoteStatus voteStatus){
        this.lectureReview = lectureReview;
        this.member = member;
        this.voteStatus = voteStatus;
    }

    public enum VoteStatus{
        UP,
        NONE,
        DOWN
    }
}
