package ynzmz.server.tag.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ynzmz.server.lecture.entity.Lecture;
import ynzmz.server.tag.mappingtable.lecture.LectureTag;

@Repository
public interface LectureTagRepository extends JpaRepository<LectureTag,Long> {
    void deleteAllLectureTagByLecture(Lecture lecture);
}
