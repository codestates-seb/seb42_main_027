package ynzmz.server.events.yjevent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ynzmz.server.events.yjevent.entity.YjEvent;


@Repository
public interface YjEventRepository extends JpaRepository<YjEvent,Long> {
}
