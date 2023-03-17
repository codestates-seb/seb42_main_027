package ynzmz.server.board.qna.answer.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import ynzmz.server.board.qna.question.entity.Question;
import ynzmz.server.member.entity.Member;
import ynzmz.server.vote.Vote;

import javax.persistence.*;

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
    private Question question;
    @ManyToOne
    @JoinColumn(name = "member_id")
    @JsonBackReference
    private Member member;

    public enum AdoptStatus{
        TRUE,
        FALSE
    }

}
