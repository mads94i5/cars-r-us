package dat3.car.service;

import dat3.car.dto.ReservationResponseDto;
import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import dat3.car.repository.CarRepository;
import dat3.car.repository.MemberRepository;
import dat3.car.repository.ReservationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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
        Car car = carRepository.findById(carId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"No car with this id found"));
        if(reservationRepository.existsByCarIdAndRentalDate(car.getId(),date)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Car is already reserved on this date");
        }
        Reservation reservation = new Reservation(member, car, date);
        reservationRepository.save(reservation);
    }

    public List<ReservationResponseDto> getReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations.stream().map(res -> new ReservationResponseDto(res)).collect(Collectors.toList());
    }
}