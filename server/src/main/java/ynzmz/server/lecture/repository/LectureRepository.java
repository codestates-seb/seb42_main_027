package ynzmz.server.lecture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ynzmz.server.lecture.entity.Lecture;

@Repository
public interface LectureRepository extends JpaRepository<Lecture,Long> {
}
