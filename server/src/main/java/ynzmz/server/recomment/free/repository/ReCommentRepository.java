package ynzmz.server.recomment.free.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ynzmz.server.recomment.free.entity.FreeReComment;

@Repository
public interface ReCommentRepository extends JpaRepository<FreeReComment,Long> {
//    Page<FreeComment> findFreeCommentsByFreeId(long freeId, Pageable pageable);  -> 이거문제
//@Query(......) 프리의 테이블 조인 ->거따 집어넣어야함
}
