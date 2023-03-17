package ynzmz.server.vote.question.answer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ynzmz.server.member.entity.Member;
import ynzmz.server.question.answer.entity.Answer;
import ynzmz.server.vote.question.answer.entity.AnswerVote;

import java.util.Optional;

@Repository
public interface AnswerVoteRepository extends JpaRepository<AnswerVote, Long> {
    Optional<AnswerVote> findByAnswerAndMember(Answer answer, Member member);
}
