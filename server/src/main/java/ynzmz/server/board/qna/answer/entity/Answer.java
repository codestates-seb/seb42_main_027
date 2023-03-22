package ynzmz.server.board.qna.answer.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import ynzmz.server.board.qna.question.entity.Question;
import ynzmz.server.comment.qna.entity.QnaComment;
import ynzmz.server.member.entity.Member;
import ynzmz.server.vote.Vote;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Answer implements Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;
    private String content;
    @Column
    @ColumnDefault("0")
    private long voteCount;
    private String createdAt;
    private String modifiedAt;
    @Enumerated(EnumType.STRING)
    private AdoptStatus adoptStatus = AdoptStatus.FALSE;

    @ManyToOne
    @JoinColumn(name = "question_id")
    @JsonBackReference
    private Question question;
    @ManyToOne
    @JoinColumn(name = "member_id")
    @JsonBackReference
    private Member member;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<QnaComment> comments = new ArrayList<>();

    public enum AdoptStatus{
        TRUE,
        FALSE
    }

}
