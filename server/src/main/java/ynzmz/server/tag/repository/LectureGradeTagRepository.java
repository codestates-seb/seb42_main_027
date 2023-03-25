package ynzmz.server.tag.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ynzmz.server.board.lecture.entity.Lecture;
import ynzmz.server.tag.entity.lecture.LectureGradeTag;

@Repository
public interface LectureGradeTagRepository extends JpaRepository<LectureGradeTag,Long> {
    void deleteAllTeacherGradeTagByLecture(Lecture lecture);
}
