package ynzmz.server.board.event.their.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ynzmz.server.board.event.their.entity.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
