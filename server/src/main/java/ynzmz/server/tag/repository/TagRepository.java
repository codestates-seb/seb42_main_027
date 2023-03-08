package ynzmz.server.tag.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ynzmz.server.tag.Tag;
@Repository
public interface TagRepository extends JpaRepository<Tag,Long> {
    Tag findTagByType(Tag.Type type);
}
