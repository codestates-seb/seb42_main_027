package ynzmz.server.board.qna.question.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ynzmz.server.board.qna.question.entity.Question;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query(value = "SELECT q FROM Question q WHERE q.member.memberId = :memberId")
    Page<Question> findByMemberId(long memberId,Pageable pageable);
    @Query(value = "SELECT q FROM Question q WHERE q.member.memberId = :memberId")
    List<Question> findByMemberId(long memberId);

    @Query("SELECT DISTINCT q FROM Question q " +
            "WHERE (:category IS NULL OR q.category = :category) " +
            "AND (q.category <> '공지')" +
            "AND (:title IS NULL OR q.title LIKE CONCAT('%', :title, '%'))")
    Page<Question> findQuestionByCategory(String category, String title, Pageable pageable);

    @Query(value = "SELECT DISTINCT q " +
            "FROM Question q " +
            "WHERE (q.category = '공지')")
    List<Question> findNoticeListQuestions(Pageable pageable);

    @Query(value = "SELECT DISTINCT q " +
            "FROM Question q " +
            "WHERE (q.category = '공지')")
    Page<Question> findNoticePageQuestions(Pageable pageable);

    @Query("SELECT DISTINCT q FROM Question q " +
            "WHERE (q.category <> '공지') " +
            "AND (:title IS NULL OR q.title LIKE CONCAT('%', :title, '%'))")
    Page<Question> findAllQuestions(String title, Pageable pageable);

}
