package ynzmz.server.vote.lecturereview.comment.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ynzmz.server.comment.lecturereview.entity.LectureReviewComment;
import ynzmz.server.member.entity.Member;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class LectureReviewCommentVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectureReviewCommentVoteId;
    @Enumerated(EnumType.STRING)
    private VoteStatus voteStatus = VoteStatus.NONE;
    @ManyToOne
    @JoinColumn(name = "lecture_review_comment_id")
    private LectureReviewComment lectureReviewComment;
    @ManyToOne
    @JoinColumn(name = "member_id")
    @JsonBackReference
    private Member member;
    public LectureReviewCommentVote(LectureReviewComment lectureReviewComment, Member member, VoteStatus voteStatus){
        this.lectureReviewComment = lectureReviewComment;
        this.member = member;
        this.voteStatus = voteStatus;
    }

    public enum VoteStatus{
        UP,
        NONE,
        DOWN
    }
}
