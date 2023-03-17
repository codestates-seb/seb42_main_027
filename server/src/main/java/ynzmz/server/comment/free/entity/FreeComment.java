package ynzmz.server.comment.free.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import ynzmz.server.free.entity.Free;
import ynzmz.server.member.entity.Member;
import ynzmz.server.review.lecture.entity.LectureReview;
import ynzmz.server.vote.VoteCount;

import javax.persistence.*;

@Entity
@Getter @Setter
public class FreeComment implements VoteCount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long freeCommentId;
    private String content;
    private String createdAt;
    private String modifiedAt;
    private long voteCount;
    @ManyToOne
    @JoinColumn(name = "free_comment_post_id")
    @JsonBackReference
    private Free free;
    @ManyToOne
    @JoinColumn(name = "member_id")
    @JsonBackReference
    private Member member;


}
