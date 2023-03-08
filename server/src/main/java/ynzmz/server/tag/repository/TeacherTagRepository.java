package ynzmz.server.tag.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ynzmz.server.tag.entity.TeacherTag;

@Repository
public interface TeacherTagRepository extends JpaRepository<TeacherTag,Long> {
}
