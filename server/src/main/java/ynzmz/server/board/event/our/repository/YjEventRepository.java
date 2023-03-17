package ynzmz.server.board.event.our.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ynzmz.server.board.event.our.entity.YjEvent;


@Repository
public interface YjEventRepository extends JpaRepository<YjEvent,Long> {
}
