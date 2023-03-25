package ynzmz.server.tag.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ynzmz.server.board.lecture.entity.Lecture;
import ynzmz.server.tag.entity.lecture.LectureSubjectTag;

@Repository
public interface LectureSubjectTagRepository extends JpaRepository<LectureSubjectTag,Long> {
    void deleteAllLectureSubjectTagByLecture(Lecture lecture);
}
