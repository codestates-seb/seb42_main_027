package ynzmz.server.tag.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ynzmz.server.tag.entity.PlatformTag;

@Repository
public interface PlatformTagRepository extends JpaRepository<PlatformTag,Long> {
    PlatformTag findTagByPlatform(PlatformTag.Platform platform);
}
