package ynzmz.server.event.inner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ynzmz.server.event.inner.entity.YjEvent;


@Repository
public interface YjEventRepository extends JpaRepository<YjEvent,Long> {
}
