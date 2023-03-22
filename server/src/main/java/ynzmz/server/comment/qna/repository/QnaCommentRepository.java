package ynzmz.server.comment.qna.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ynzmz.server.comment.free.entity.FreeComment;
import ynzmz.server.comment.qna.entity.QnaComment;

import java.util.List;

@Repository
public interface QnaCommentRepository extends JpaRepository<QnaComment,Long> {
    @Query(value = "SELECT qc FROM QnaComment qc WHERE qc.member.memberId = :memberId")
    List<QnaComment> findByMemberId(long memberId);
}
