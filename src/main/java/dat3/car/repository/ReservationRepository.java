package dat3.car.repository;

import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByMember(Member member);
    int countByMember(Member member);
    boolean existsByCarIdAndRentalDate(Long carId, LocalDate date);
}
