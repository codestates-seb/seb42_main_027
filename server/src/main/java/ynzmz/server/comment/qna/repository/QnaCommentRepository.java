package ynzmz.server.comment.qna.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ynzmz.server.comment.qna.entity.QnaComment;

@Repository
public interface QnaCommentRepository extends JpaRepository<QnaComment,Long> {

}
