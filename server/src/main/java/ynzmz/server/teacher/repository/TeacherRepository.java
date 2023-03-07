package ynzmz.server.teacher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ynzmz.server.teacher.entity.Teacher;
@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long> {
}
