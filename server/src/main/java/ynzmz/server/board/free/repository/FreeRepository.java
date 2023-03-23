package ynzmz.server.board.free.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ynzmz.server.board.free.entity.Free;
import ynzmz.server.lecture.entity.Lecture;
import ynzmz.server.tag.entity.GradeTag;
import ynzmz.server.tag.entity.PlatformTag;
import ynzmz.server.tag.entity.SubjectTag;

import java.util.List;

@Repository
public interface FreeRepository extends JpaRepository<Free,Long> {
    @Query(value = "SELECT f FROM Free f WHERE f.member.memberId = :memberId")
    List<Free> findByMemberId(long memberId); //다빈 추가


    @Query(value = "SELECT f FROM Free f "+ "WHERE (:category IS NULL OR f.category = :category)"
            + "AND f.category <> '공지' "
            )
    Page<Free> findFreesByCategory(String category,Pageable pageable);

    @Query(value = "SELECT f FROM Free f "+ "WHERE (:category IS NULL OR f.category = :category)"
            + "AND f.category <> '공지' "
            + " And (:title IS NULL OR f.title LIKE CONCAT('%', :title, '%'))")
    Page<Free> findFreesByCategory(String category,Pageable pageable,String title);


    @Query(value = "SELECT f FROM Free f "+ "WHERE :title IS NULL OR f.title LIKE CONCAT('%', :title, '%')")
    Page<Free> findFreesBySort(Pageable pageable,String title);


    @Query(value = "SELECT f FROM Free f "+ "WHERE (:category IS NULL OR f.category = :category)"
            + "AND f.category <> '공지' "
            + " And (:title IS NULL OR f.title LIKE CONCAT('%', :title, '%'))")
    Page<Free> findFreesOutNotice(String category,Pageable pageable,String title);

    @Query(value = "SELECT f FROM Free f "+ "WHERE (:category IS NULL OR f.category = :category)"
            + "AND f.category <> '공지' "
            )
    Page<Free> findFreesOutNotice(String category,Pageable pageable);
}
