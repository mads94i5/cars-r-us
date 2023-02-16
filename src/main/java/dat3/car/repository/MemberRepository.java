package dat3.car.repository;

import dat3.car.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    Optional<Member> findByUsername(String username);

    void deleteByUsername(String username);

}
