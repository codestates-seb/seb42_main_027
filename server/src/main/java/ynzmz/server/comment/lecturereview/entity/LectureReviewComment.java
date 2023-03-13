package ynzmz.server.comment.lecturereview.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import ynzmz.server.lecturereview.entity.LectureReview;
import ynzmz.server.member.entity.Member;
import ynzmz.server.vote.VoteCount;

import javax.persistence.*;

@Entity
@Getter @Setter
public class LectureReviewComment implements VoteCount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectureReviewCommentId;
    private String content;
    private String createdAt;
    private String modifiedAt;
    private long voteCount;
    @ManyToOne
    @JoinColumn(name = "lecture_review_post_id")
    @JsonBackReference
    private LectureReview lectureReview;
    @ManyToOne
    @JoinColumn(name = "member_id")
    @JsonBackReference
    private Member member;


}
