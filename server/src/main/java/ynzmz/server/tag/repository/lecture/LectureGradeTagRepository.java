package ynzmz.server.tag.repository.lecture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ynzmz.server.lecture.entity.Lecture;
import ynzmz.server.tag.mappingtable.lecture.LectureGradeTag;

@Repository
public interface LectureGradeTagRepository extends JpaRepository<LectureGradeTag,Long> {
    void deleteAllTeacherGradeTagByLecture(Lecture lecture);
}
