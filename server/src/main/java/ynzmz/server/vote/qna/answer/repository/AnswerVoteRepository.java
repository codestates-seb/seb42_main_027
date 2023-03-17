package ynzmz.server.vote.qna.answer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ynzmz.server.member.entity.Member;
import ynzmz.server.board.qna.answer.entity.Answer;
import ynzmz.server.vote.qna.answer.entity.AnswerVote;

import java.util.Optional;

@Repository
public interface AnswerVoteRepository extends JpaRepository<AnswerVote, Long> {
    Optional<AnswerVote> findByAnswerAndMember(Answer answer, Member member);
}
