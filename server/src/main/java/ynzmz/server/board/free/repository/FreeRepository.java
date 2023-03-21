package ynzmz.server.board.free.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ynzmz.server.board.free.entity.Free;

import java.util.List;

@Repository
public interface FreeRepository extends JpaRepository<Free,Long> {
    @Query(value = "SELECT f FROM Free f WHERE f.member.memberId = :memberId")
    List<Free> findByMemberId(long memberId); //다빈 추가
}
