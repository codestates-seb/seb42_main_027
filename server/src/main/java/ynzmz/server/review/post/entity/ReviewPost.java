package ynzmz.server.review.post.entity;

import lombok.Getter;
import lombok.Setter;
import ynzmz.server.member.entity.Member;
import ynzmz.server.review.lecture.entity.Lecture;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class ReviewPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewPostId;
    private String title;
    private int starPoint;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private long viewCount;
    private long voteCount;
    @ManyToOne
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
