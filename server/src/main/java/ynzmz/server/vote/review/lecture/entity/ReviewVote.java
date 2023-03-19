package ynzmz.server.vote.review.lecture.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ynzmz.server.comment.review.lecture.entity.LectureReviewComment;
import ynzmz.server.board.review.lecture.entity.LectureReview;
import ynzmz.server.member.entity.Member;
import ynzmz.server.vote.Vote;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class ReviewVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewVoteId;
    @Enumerated(EnumType.STRING)
    private Vote.Status status;
    @Enumerated(EnumType.STRING)
    private Vote.Target target;
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
    public ReviewVote(LectureReview lectureReview, Member member, Vote.Status status, Vote.Target target){
        this.lectureReview = lectureReview;
        this.member = member;
        this.status = status;
        this.target = target;
    }

    public ReviewVote(LectureReviewComment lectureReviewComment, Member member, Vote.Status status, Vote.Target target){
        this.lectureReviewComment = lectureReviewComment;
        this.member = member;
        this.status = status;
        this.target = target;
    }

}
