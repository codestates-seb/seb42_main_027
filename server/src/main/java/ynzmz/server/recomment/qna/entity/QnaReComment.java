package ynzmz.server.recomment.qna.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import ynzmz.server.board.qna.answer.entity.Answer;
import ynzmz.server.board.qna.question.entity.Question;
import ynzmz.server.comment.qna.entity.QnaComment;
import ynzmz.server.member.entity.Member;
import ynzmz.server.vote.Vote;

import javax.persistence.*;

@Entity
@Getter @Setter
public class QnaReComment implements Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qnaReCommentId;
    private String content;
    private String createdAt;
    private String modifiedAt;
    private long voteCount;
    @ManyToOne
    @JoinColumn(name = "qna_comment_id")
    @JsonBackReference
    private QnaComment qnaComment;

    @ManyToOne
    @JoinColumn(name = "member_id")
    @JsonBackReference
    private Member member;

}
