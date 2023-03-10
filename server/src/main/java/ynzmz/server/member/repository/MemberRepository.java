package ynzmz.server.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ynzmz.server.member.entity.Member;
@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
}
