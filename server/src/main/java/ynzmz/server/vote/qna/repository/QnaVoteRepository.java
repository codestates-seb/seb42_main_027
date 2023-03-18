package ynzmz.server.vote.qna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ynzmz.server.board.qna.answer.entity.Answer;
import ynzmz.server.comment.qna.entity.QnaComment;
import ynzmz.server.member.entity.Member;
import ynzmz.server.board.qna.question.entity.Question;
import ynzmz.server.recomment.qna.entity.QnaReComment;
import ynzmz.server.vote.Vote;
import ynzmz.server.vote.qna.entity.QnaVote;

import java.util.Optional;

@Repository
public interface QnaVoteRepository extends JpaRepository<QnaVote,Long> {
    Optional<QnaVote> findByQuestionAndMemberAndTarget(Question question, Member member, Vote.Target target);
    Optional<QnaVote> findByAnswerAndMemberAndTarget(Answer answer, Member member, Vote.Target target);
    Optional<QnaVote> findByQnaCommentAndMemberAndTarget(QnaComment qnaComment, Member member, Vote.Target target);
    Optional<QnaVote> findByQnaReCommentAndMemberAndTarget(QnaReComment qnaReComment, Member member, Vote.Target target);

}
