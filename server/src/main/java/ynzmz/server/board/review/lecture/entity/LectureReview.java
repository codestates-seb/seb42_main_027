package ynzmz.server.board.review.lecture.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import ynzmz.server.comment.review.lecture.entity.LectureReviewComment;
import ynzmz.server.lecture.entity.Lecture;
import ynzmz.server.member.entity.Member;
import ynzmz.server.vote.Vote;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class LectureReview implements Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long LectureReviewId;
    private String title;
    private int starPoint;
    private String content;
    private String createdAt;
    private String modifiedAt;
    private long viewCount;
    private long voteCount;
    @ManyToOne
    @JoinColumn(name = "lecture_id")
    @JsonBackReference
    private Lecture lecture;
    @ManyToOne
    @JoinColumn(name = "member_id")
    @JsonBackReference
    private Member member;
    @OneToMany(mappedBy = "lectureReview", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<LectureReviewComment> comments = new ArrayList<>();

}
