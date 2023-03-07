package ynzmz.server.reviewpost.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ynzmz.server.reviewpost.entity.ReviewPost;
@Repository
public interface ReviewPostRepository extends JpaRepository<ReviewPost,Long> {
}
