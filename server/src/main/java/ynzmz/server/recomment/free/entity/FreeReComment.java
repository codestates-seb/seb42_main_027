package ynzmz.server.recomment.free.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.lang.Nullable;
import ynzmz.server.comment.free.entity.FreeComment;
import ynzmz.server.member.entity.Member;
import ynzmz.server.vote.Vote;

import javax.persistence.*;

@Entity
@Getter @Setter
public class FreeReComment implements Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long freeReCommentId;
    private String content;
    private String createdAt;
    private String modifiedAt;

    @Column
    @ColumnDefault("0")
    private long voteCount;
    @ManyToOne
    @JoinColumn(name = "free_comment_id")//아마?
    @JsonBackReference
    private FreeComment freeComment;
    @ManyToOne
    @JoinColumn(name = "member_id")
    @JsonBackReference
    @Nullable
    private Member member;

    @Column
    @ColumnDefault("false")
    private boolean memberSim;


}
