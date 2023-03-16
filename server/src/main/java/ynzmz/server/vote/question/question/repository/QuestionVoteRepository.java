package ynzmz.server.vote.question.question.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ynzmz.server.member.entity.Member;
import ynzmz.server.question.question.entity.Question;
import ynzmz.server.vote.question.question.entity.QuestionVote;

import java.util.Optional;

@Repository
public interface QuestionVoteRepository extends JpaRepository<QuestionVote,Long> {
    Optional<QuestionVote> findByQuestionAndMember(Question question, Member member);

}
