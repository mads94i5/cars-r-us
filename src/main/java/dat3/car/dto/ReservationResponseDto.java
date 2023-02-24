package dat3.car.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.car.entity.Reservation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationResponseDto {
    Long id;
    String memberUsername;
    Long carId;
    String carBrand;

    @JsonFormat(pattern = "dd-MM-yyyy",shape = JsonFormat.Shape.STRING)
    private LocalDate rentalDate;

    public ReservationResponseDto(Reservation reservation) {
        this.id = reservation.getId();
        this.memberUsername = reservation.getMember().getUsername();
        this.carId = reservation.getCar().getId();
        this.carBrand = reservation.getCar().getBrand();
    }
}
