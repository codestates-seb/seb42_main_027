package ynzmz.server.tag.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ynzmz.server.tag.entity.SubjectTag;

@Repository
public interface SubjectTagRepository extends JpaRepository<SubjectTag,Long> {
    SubjectTag findTagBySubject(SubjectTag.Subject subject);
}
