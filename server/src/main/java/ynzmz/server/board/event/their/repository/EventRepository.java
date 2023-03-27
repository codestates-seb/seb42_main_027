package ynzmz.server.board.event.their.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ynzmz.server.board.event.their.entity.Event;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {


    @Query(value = "SELECT e FROM Event e where e.source = :source")
    List<Event> findEventsBySource(String source);

    @Query(value = "SELECT e FROM Event e where e.source = :source")
    Page<Event> findEventsBySourcePage(String source, Pageable pageable);
}
