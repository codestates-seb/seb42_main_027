package ynzmz.server.recomment.qna.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ynzmz.server.comment.free.entity.FreeComment;
import ynzmz.server.recomment.qna.entity.QnaReComment;

@Repository
public interface QnaReCommentRepository extends JpaRepository<QnaReComment,Long> {
    @Query(value = "SELECT qrc FROM QnaReComment qrc WHERE qrc.member.memberId = :memberId")
    Page<QnaReComment> findByMemberId(long memberId, Pageable pageable);
}
