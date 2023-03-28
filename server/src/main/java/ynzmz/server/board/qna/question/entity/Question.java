package ynzmz.server.board.qna.question.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import ynzmz.server.comment.qna.entity.QnaComment;
import ynzmz.server.member.entity.Member;
import ynzmz.server.board.qna.answer.entity.Answer;
import ynzmz.server.vote.Vote;
import ynzmz.server.vote.qna.entity.QnaVote;
import ynzmz.server.vote.review.lecture.entity.ReviewVote;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Question implements Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;
    private String title;
    @Column(columnDefinition = "MEDIUMTEXT")
    private String content;
    private String category;
    private long viewCount;
    private long voteCount;
    private long commentCount;
    private long answerCount;
    private String createdAt;
    private String modifiedAt;
    private Long adoptAnswerId;
    @ElementCollection(targetClass=String.class)
    @Column
    private List<String> uploadImages = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "member_id")
    @JsonBackReference
    private Member member;
    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Answer> answers = new ArrayList<>();

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<QnaComment> comments = new ArrayList<>();
    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<QnaVote> qnaVotes = new ArrayList<>();
}
