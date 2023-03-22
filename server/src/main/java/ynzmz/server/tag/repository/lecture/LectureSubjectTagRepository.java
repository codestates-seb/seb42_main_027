package ynzmz.server.tag.repository.lecture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ynzmz.server.lecture.entity.Lecture;
import ynzmz.server.tag.mappingtable.lecture.LectureSubjectTag;

@Repository
public interface LectureSubjectTagRepository extends JpaRepository<LectureSubjectTag,Long> {
    void deleteAllLectureSubjectTagByLecture(Lecture lecture);
}
