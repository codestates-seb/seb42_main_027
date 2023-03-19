package ynzmz.server.vote.qna.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ynzmz.server.board.qna.answer.entity.Answer;
import ynzmz.server.comment.qna.entity.QnaComment;
import ynzmz.server.member.entity.Member;
import ynzmz.server.board.qna.question.entity.Question;
import ynzmz.server.recomment.qna.entity.QnaReComment;
import ynzmz.server.vote.Vote;
import ynzmz.server.vote.review.lecture.entity.ReviewVote;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class QnaVote{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qnaVoteId;

    @Enumerated(EnumType.STRING)
    private Vote.Status voteStatus;
    @Enumerated(EnumType.STRING)
    private Vote.Target target;
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
    @ManyToOne
    @JoinColumn(name = "answer_id")
    private Answer answer;
    @ManyToOne
    @JoinColumn(name = "qna_comment_id")
    private QnaComment qnaComment;
    @ManyToOne
    @JoinColumn(name = "qna_re_comment_id")
    private QnaReComment qnaReComment;

    @ManyToOne
    @JoinColumn(name = "member_id")
    @JsonBackReference
    private Member member;

    public QnaVote(Question question, Member member, Vote.Status voteStatus, Vote.Target target) {
        this.question = question;
        this.member = member;
        this.voteStatus = voteStatus;
        this.target = target;
    }

    public QnaVote(Answer answer, Member member, Vote.Status voteStatus, Vote.Target target) {
        this.answer = answer;
        this.member = member;
        this.voteStatus = voteStatus;
        this.target = target;
    }

    public QnaVote(QnaComment qnaComment, Member member, Vote.Status voteStatus, Vote.Target target) {
        this.qnaComment = qnaComment;
        this.member = member;
        this.voteStatus = voteStatus;
        this.target = target;
    }

    public QnaVote(QnaReComment qnaReComment, Member member, Vote.Status voteStatus, Vote.Target target) {
        this.qnaReComment = qnaReComment;
        this.member = member;
        this.voteStatus = voteStatus;
        this.target = target;
    }

}
