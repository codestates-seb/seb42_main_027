package ynzmz.server.lecturereviewpost.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import ynzmz.server.comment.lecturereviewpost.entity.LectureReviewPostComment;
import ynzmz.server.lecture.entity.Lecture;
import ynzmz.server.member.entity.Member;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class LectureReviewPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long LectureReviewPostId;
    private String title;
    private double starPoint;
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
    @OneToMany(mappedBy = "lectureReviewPost", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<LectureReviewPostComment> comments = new ArrayList<>();

}
