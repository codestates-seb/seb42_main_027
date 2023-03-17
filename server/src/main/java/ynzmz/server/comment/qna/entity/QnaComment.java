package ynzmz.server.comment.qna.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import ynzmz.server.board.qna.answer.entity.Answer;
import ynzmz.server.board.qna.question.entity.Question;
import ynzmz.server.board.review.lecture.entity.LectureReview;
import ynzmz.server.member.entity.Member;
import ynzmz.server.vote.Vote;

import javax.persistence.*;

@Entity
@Getter @Setter
public class QnaComment implements Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qnaCommentId;
    private String content;
    private String createdAt;
    private String modifiedAt;
    private Target target;
    private long voteCount;
    @ManyToOne
    @JoinColumn(name = "question_id")
    @JsonBackReference
    private Question question;
    @ManyToOne
    @JoinColumn(name = "answer_id")
    @JsonBackReference
    private Answer answer;
    @ManyToOne
    @JoinColumn(name = "member_id")
    @JsonBackReference
    private Member member;

    public enum Target{
        Question,
        Answer
    }


}
