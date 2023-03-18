package ynzmz.server.comment.free.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ynzmz.server.comment.free.entity.FreeComment;

@Repository
public interface FreeCommentRepository extends JpaRepository<FreeComment,Long> {
//    Page<FreeComment> findFreeCommentsByFreeId(long freeId, Pageable pageable);  -> 이거문제
//@Query(......) 프리의 테이블 조인 ->거따 집어넣어야함
}
