package ynzmz.server.vote.lecturereview.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ynzmz.server.comment.lecturereview.entity.LectureReviewComment;
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
    private VoteStatus voteStatus;
    @Enumerated(EnumType.STRING)
    private Target target;
    @ManyToOne
    @JoinColumn(name = "lecture_review_id")
    @JsonBackReference
    private LectureReview lectureReview;

    @ManyToOne
    @JoinColumn(name = "lecture_review_comment_id")
    @JsonBackReference
    private LectureReviewComment lectureReviewComment;
    @ManyToOne
    @JoinColumn(name = "member_id")
    @JsonBackReference
    private Member member;
    public LectureReviewVote(LectureReview lectureReview, Member member, VoteStatus voteStatus, Target target){
        this.lectureReview = lectureReview;
        this.member = member;
        this.voteStatus = voteStatus;
        this.target = target;
    }

    public LectureReviewVote(LectureReviewComment lectureReviewComment, Member member, VoteStatus voteStatus, Target target){
        this.lectureReviewComment = lectureReviewComment;
        this.member = member;
        this.voteStatus = voteStatus;
        this.target = target;
    }

    public enum Target{
        REVIEW,
        COMMENT
    }

    public enum VoteStatus{
        UP,
        NONE,
        DOWN
    }
}
