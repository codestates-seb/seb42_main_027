package ynzmz.server.board.review.lecture.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import ynzmz.server.comment.review.lecture.entity.LectureReviewComment;
import ynzmz.server.board.lecture.entity.Lecture;
import ynzmz.server.member.entity.Member;
import ynzmz.server.vote.Vote;
import ynzmz.server.vote.review.lecture.entity.ReviewVote;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class LectureReview implements Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectureReviewId;
    private String title;
    private int starPoint;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String content;
    private String createdAt;
    private String modifiedAt;
    private long viewCount;
    @Column
    @ColumnDefault("0")
    private long voteCount;
    private long totalCommentCount;
    @ElementCollection(targetClass=String.class)
    @Column
    private List<String> uploadImages = new ArrayList<>();
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

    @OneToMany(mappedBy = "lectureReview", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<ReviewVote> reviewVotes = new ArrayList<>();


}
