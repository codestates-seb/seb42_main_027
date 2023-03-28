package ynzmz.server.recomment.free.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.lang.Nullable;
import ynzmz.server.comment.free.entity.FreeComment;
import ynzmz.server.member.entity.Member;
import ynzmz.server.vote.Vote;
import ynzmz.server.vote.free.entity.FreeVote;
import ynzmz.server.vote.qna.entity.QnaVote;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "freeReComment", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<FreeVote> freeVotes = new ArrayList<>();


}
