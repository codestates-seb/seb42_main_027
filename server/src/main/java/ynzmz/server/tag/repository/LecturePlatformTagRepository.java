package ynzmz.server.tag.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ynzmz.server.board.lecture.entity.Lecture;
import ynzmz.server.tag.entity.lecture.LecturePlatformTag;

@Repository
public interface LecturePlatformTagRepository extends JpaRepository<LecturePlatformTag,Long> {
    void deleteAllLecturePlatformTagByLecture(Lecture lecture);
}
