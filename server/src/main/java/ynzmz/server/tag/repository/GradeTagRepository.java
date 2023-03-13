package ynzmz.server.tag.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ynzmz.server.tag.entity.GradeTag;

@Repository
public interface GradeTagRepository extends JpaRepository<GradeTag,Long> {
    GradeTag findTagByGrade(GradeTag.Grade grade);
}
