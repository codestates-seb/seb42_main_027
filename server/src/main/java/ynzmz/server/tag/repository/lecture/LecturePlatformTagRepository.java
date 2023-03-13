package ynzmz.server.tag.repository.lecture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ynzmz.server.lecture.entity.Lecture;
import ynzmz.server.tag.mappingtable.lecture.LecturePlatformTag;

@Repository
public interface LecturePlatformTagRepository extends JpaRepository<LecturePlatformTag,Long> {
    void deleteAllLecturePlatformTagByLecture(Lecture lecture);
}
