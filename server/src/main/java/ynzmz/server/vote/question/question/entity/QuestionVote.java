package ynzmz.server.vote.question.question.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ynzmz.server.member.entity.Member;
import ynzmz.server.question.question.entity.Question;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class QuestionVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionVoteId;

    @Enumerated(EnumType.STRING)
    private VoteStatus voteStatus = VoteStatus.NONE;
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
    @ManyToOne
    @JoinColumn(name = "member_id")
    @JsonBackReference
    private Member member;

    public QuestionVote (Question question, Member member, VoteStatus voteStatus){
        this.question = question;
        this.member = member;
        this.voteStatus = voteStatus;
    }


    public enum VoteStatus{
        UP,
        NONE,
        DOWN
    }
}
