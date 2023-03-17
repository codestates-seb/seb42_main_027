package ynzmz.server.board.qna.answer.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ynzmz.server.board.qna.answer.entity.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    @Query(value = "SELECT a FROM Answer a WHERE a.member.memberId = :memberId")
    Page<Answer> findByMemberId(long memberId, Pageable pageable);
}
