package ynzmz.server.events.eventboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ynzmz.server.events.eventboard.entity.Event;
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
