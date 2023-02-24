package dat3.car.api;

import dat3.car.dto.ReservationRequestDto;
import dat3.car.dto.ReservationResponseDto;
import dat3.car.service.ReservationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("api/reservations")
public class ReservationController {

    ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    // Admin
    @GetMapping("/all")
    public List<ReservationResponseDto> getReservations() {
        List<ReservationResponseDto> response = reservationService.getReservations();
        return response;
    }

    // Member
    @PostMapping
    public void makeReservation(@RequestBody ReservationRequestDto requestDto) {
        reservationService.reserveCar(requestDto.getMember().getUsername(), requestDto.getCarId(), requestDto.getDate());
    }


}
