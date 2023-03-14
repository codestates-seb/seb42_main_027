package ynzmz.server.events.YjEvent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ynzmz.server.events.YjEvent.entity.YjEvent;


@Repository
public interface YjEventRepository extends JpaRepository<YjEvent,Long> {
}
