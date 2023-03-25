package ynzmz.server.member.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ynzmz.server.member.entity.Member;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    Optional<Member> findByUsername(String username);


    Optional<Member> findByDisplayName(String displayName);

    Optional<Member> findByUsernameAndPhoneNumber(String username, String phoneNumber);
    Optional<Member> findByUsernameAndEmailAndPhoneNumber(String username, String email, String phoneNumber);

    @Query(value = "SELECT i FROM Member i WHERE i.memberId = :memberId")
    Optional<Member> findById(long memberId);

    @Modifying
    @Query(value = "UPDATE Member SET password = :memberPassword WHERE memberId = :memberId")
    void updatePassword(@Param("memberId") Long memberId, @Param("memberPassword") String memberPassword);


//    @Query("UPDATE Member m SET m.password = :newPassword WHERE m.memberId = :memberId")
//    void updateMemberPassword(@Param("memberId") Long memberId,
//                                     @Param("newPassword") String newPassword);
}
