package ynzmz.server.event.outer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ynzmz.server.event.outer.entity.Event;
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
