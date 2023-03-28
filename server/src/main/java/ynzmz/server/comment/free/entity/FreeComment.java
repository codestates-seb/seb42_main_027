package ynzmz.server.comment.free.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.lang.Nullable;
import ynzmz.server.board.free.entity.Free;
import ynzmz.server.member.entity.Member;
import ynzmz.server.vote.Vote;

import javax.persistence.*;

@Entity
@Getter @Setter
public class FreeComment implements Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long freeCommentId;
    private String content;
    private String createdAt;
    private String modifiedAt;
    private String freeEmoticonUrl;
    @Column
    @ColumnDefault("0")
    private long voteCount;
    @ManyToOne
    @JoinColumn(name = "free_id")//아마?
    @JsonBackReference
    private Free free;
    @ManyToOne
    @JoinColumn(name = "member_id")
    @JsonBackReference
    @Nullable
    private Member member;

    @Column
    @ColumnDefault("false")
    private boolean memberSim;


}
