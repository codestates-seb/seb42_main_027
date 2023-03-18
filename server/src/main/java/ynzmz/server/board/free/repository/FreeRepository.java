package ynzmz.server.board.free.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ynzmz.server.board.free.entity.Free;
@Repository
public interface FreeRepository extends JpaRepository<Free,Long> {
}
