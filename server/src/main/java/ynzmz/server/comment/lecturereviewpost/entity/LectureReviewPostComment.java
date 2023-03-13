package ynzmz.server.comment.lecturereviewpost.entity;

import lombok.Getter;
import lombok.Setter;
import ynzmz.server.lecturereviewpost.entity.LectureReviewPost;
import ynzmz.server.member.entity.Member;

import javax.persistence.*;

@Entity
@Getter @Setter
public class LectureReviewPostComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectureReviewPostCommentId;
    private String content;
    private String createdAt;
    private String modifiedAt;
    private long voteCount;

    @ManyToOne
    @JoinColumn(name = "lecture_review_post_id")
    private LectureReviewPost lectureReviewPost;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;


}
