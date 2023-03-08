package ynzmz.server.lecturereviewpost.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ynzmz.server.lecturereviewpost.entity.LectureReviewPost;
@Repository
public interface LectureReviewPostRepository extends JpaRepository<LectureReviewPost,Long> {
}
