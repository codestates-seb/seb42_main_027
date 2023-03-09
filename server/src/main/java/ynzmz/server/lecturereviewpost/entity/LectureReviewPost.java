package ynzmz.server.lecturereviewpost.entity;

import lombok.Getter;
import lombok.Setter;
import ynzmz.server.member.entity.Member;
import ynzmz.server.lecture.entity.Lecture;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    private Lecture lecture;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
