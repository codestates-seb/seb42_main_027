package ynzmz.server.recomment.qna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ynzmz.server.recomment.qna.entity.QnaReComment;

@Repository
public interface QnaReCommentRepository extends JpaRepository<QnaReComment,Long> {

}
