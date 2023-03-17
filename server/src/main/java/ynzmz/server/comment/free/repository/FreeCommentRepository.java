package ynzmz.server.comment.free.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ynzmz.server.comment.free.entity.FreeComment;

@Repository
public interface FreeCommentRepository extends JpaRepository<FreeComment,Long> {
    Page<FreeComment> findFreeCommentsByFreeId(long FreeId, Pageable pageable);

}
