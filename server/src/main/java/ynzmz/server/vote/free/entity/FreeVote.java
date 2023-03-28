package ynzmz.server.vote.free.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import ynzmz.server.board.free.entity.Free;
import ynzmz.server.comment.free.entity.FreeComment;
import ynzmz.server.member.entity.Member;
import ynzmz.server.recomment.free.entity.FreeReComment;
import ynzmz.server.recomment.qna.entity.QnaReComment;
import ynzmz.server.vote.Vote;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class FreeVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long freeVoteId;
    @Enumerated(EnumType.STRING)
    private Vote.Status status;
    @Enumerated(EnumType.STRING)
    private Vote.Target target;
    @ManyToOne
    @JoinColumn(name = "free_id")
    @JsonBackReference
    private Free free;

    @ManyToOne
    @JoinColumn(name = "free_comment_id")
    @JsonBackReference
    private FreeComment freeComment;
    @ManyToOne
    @JoinColumn(name = "member_id")
    @JsonBackReference
    private Member member;

    @ManyToOne
    @JoinColumn(name = "free_re_comment_id")
    private FreeReComment freeReComment;
    public FreeVote(Free free, Member member, Vote.Status status, Vote.Target target){
        this.free = free;
        this.member = member;
        this.status = status;
        this.target = target;
    }

    public FreeVote(FreeComment freeComment, Member member, Vote.Status status, Vote.Target target){
        this.freeComment = freeComment;
        this.member = member;
        this.status = status;
        this.target = target;
    }

}
