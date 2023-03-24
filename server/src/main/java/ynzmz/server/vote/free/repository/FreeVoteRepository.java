package ynzmz.server.vote.free.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ynzmz.server.board.free.entity.Free;
import ynzmz.server.comment.free.entity.FreeComment;
import ynzmz.server.member.entity.Member;
import ynzmz.server.vote.Vote;
import ynzmz.server.vote.free.entity.FreeVote;

import java.util.Optional;

@Repository
public interface FreeVoteRepository extends JpaRepository<FreeVote, Long> {
    Optional<FreeVote> findByFreeAndMemberAndTarget(Free free, Member member, Vote.Target target);
    Optional<FreeVote> findByFreeCommentAndMemberAndTarget(FreeComment freeComment, Member member, Vote.Target target);
}

