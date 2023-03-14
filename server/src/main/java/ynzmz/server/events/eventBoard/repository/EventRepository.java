package ynzmz.server.events.eventBoard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ynzmz.server.events.eventBoard.entity.Event;
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
