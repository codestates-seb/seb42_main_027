package ynzmz.server.board.qna.question.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import ynzmz.server.comment.qna.entity.QnaComment;
import ynzmz.server.member.entity.Member;
import ynzmz.server.board.qna.answer.entity.Answer;
import ynzmz.server.vote.Vote;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Question implements Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long questionId;
    String title;
    String content;
    String category;
    long viewCount;
    long voteCount;
    private long answerCount;
    String createdAt;
    String modifiedAt;
    private long adoptAnswerId;

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

}
