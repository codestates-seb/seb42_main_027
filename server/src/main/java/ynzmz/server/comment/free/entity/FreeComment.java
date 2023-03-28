package ynzmz.server.comment.free.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.lang.Nullable;
import ynzmz.server.board.free.entity.Free;
import ynzmz.server.member.entity.Member;
import ynzmz.server.vote.Vote;
import ynzmz.server.vote.free.entity.FreeVote;
import ynzmz.server.vote.qna.entity.QnaVote;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @OneToMany(mappedBy = "freeComment", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<FreeVote> freeVotes = new ArrayList<>();
    @Column
    @ColumnDefault("false")
    private boolean memberSim;


}
