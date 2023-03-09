package ynzmz.server.teacher.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ynzmz.server.teacher.entity.Teacher;
@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long> {
    @Query("SELECT t FROM Teacher t JOIN t.teacherTags tt JOIN tt.tag tg where tg.type = :type")
    Page<Teacher> findByTagType(String type, Pageable pageable);
}
