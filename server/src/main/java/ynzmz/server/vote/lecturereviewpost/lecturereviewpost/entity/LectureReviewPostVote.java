package ynzmz.server.vote.lecturereviewpost.lecturereviewpost.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ynzmz.server.lecturereviewpost.entity.LectureReviewPost;
import ynzmz.server.member.entity.Member;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class LectureReviewPostVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectureReviewPostVoteId;
    @Enumerated(EnumType.STRING)
    private VoteStatus voteStatus = VoteStatus.NONE;
    @ManyToOne
    @JoinColumn(name = "lecture_review_post_id")
    private LectureReviewPost lectureReviewPost;
    @ManyToOne
    @JoinColumn(name = "member_id")
    @JsonBackReference
    private Member member;
    public LectureReviewPostVote (LectureReviewPost lectureReviewPost, Member member, VoteStatus voteStatus){
        this.lectureReviewPost = lectureReviewPost;
        this.member = member;
        this.voteStatus = voteStatus;
    }

    public enum VoteStatus{
        UP,
        NONE,
        DOWN
    }
}
