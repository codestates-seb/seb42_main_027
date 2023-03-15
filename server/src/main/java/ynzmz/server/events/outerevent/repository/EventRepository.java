package ynzmz.server.events.outerevent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ynzmz.server.events.outerevent.entity.Event;
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
