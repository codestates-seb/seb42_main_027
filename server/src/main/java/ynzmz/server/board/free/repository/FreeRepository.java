package ynzmz.server.board.free.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ynzmz.server.board.free.entity.Free;
import ynzmz.server.board.review.lecture.entity.LectureReview;

public interface FreeRepository extends JpaRepository<Free,Long> {
    @Query(value = "SELECT i FROM Free i WHERE i.member.memberId = :memberId")
    Page<Free> findByMemberId(long memberId, Pageable pageable); //다빈 추가
}
