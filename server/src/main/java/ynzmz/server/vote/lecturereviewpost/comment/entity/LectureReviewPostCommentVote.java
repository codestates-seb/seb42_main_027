package ynzmz.server.vote.lecturereviewpost.comment.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ynzmz.server.comment.lecturereviewpost.entity.LectureReviewPostComment;
import ynzmz.server.member.entity.Member;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class LectureReviewPostCommentVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectureReviewPostCommentVoteId;
    @Enumerated(EnumType.STRING)
    private VoteStatus voteStatus = VoteStatus.NONE;
    @ManyToOne
    @JoinColumn(name = "lecture_review_post_comment_id")
    private LectureReviewPostComment lectureReviewPostComment;
    @ManyToOne
    @JoinColumn(name = "member_id")
    @JsonBackReference
    private Member member;
    public LectureReviewPostCommentVote(LectureReviewPostComment lectureReviewPostComment, Member member, VoteStatus voteStatus){
        this.lectureReviewPostComment = lectureReviewPostComment;
        this.member = member;
        this.voteStatus = voteStatus;
    }

    public enum VoteStatus{
        UP,
        NONE,
        DOWN
    }
}
