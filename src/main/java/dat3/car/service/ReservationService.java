package dat3.car.service;

import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import dat3.car.repository.CarRepository;
import dat3.car.repository.MemberRepository;
import dat3.car.repository.ReservationRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    MemberRepository memberRepository;
    CarRepository carRepository;
    ReservationRepository reservationRepository;

    public ReservationService(MemberRepository memberRepository, CarRepository carRepository, ReservationRepository reservationRepository) {
        this.memberRepository = memberRepository;
        this.carRepository = carRepository;
        this.reservationRepository = reservationRepository;
    }

    public void reserveCar(String username, Long carId, LocalDate date) {
        Member member = memberRepository.findById(username).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member with username: '" + username + "' not found"));
        Optional<Car> car = carRepository.findUnreservedCarById(carId, date);
        if (car.isPresent()) {
            Reservation reservation = new Reservation(member, car.get(), date);
            reservationRepository.save(reservation);
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Car is either already reserved or not found: '" + car + "'");
    }
}