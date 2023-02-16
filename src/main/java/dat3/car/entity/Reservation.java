package dat3.car.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private LocalDateTime reservationDate;
    private LocalDateTime rentalDate;
    @ManyToOne(cascade = CascadeType.ALL)
    private Car car;
    @ManyToOne(cascade = CascadeType.ALL)
    private Member member;
}
