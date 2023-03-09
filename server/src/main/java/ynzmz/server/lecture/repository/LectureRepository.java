package ynzmz.server.lecture.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ynzmz.server.lecture.entity.Lecture;
import ynzmz.server.teacher.entity.Teacher;

@Repository
public interface LectureRepository extends JpaRepository<Lecture,Long> {
    @Query("SELECT l FROM Lecture l JOIN l.lectureTags lt JOIN lt.tag tg where tg.type = :tag")
    Page<Lecture> findByTagType(String tag, Pageable pageable);
}
