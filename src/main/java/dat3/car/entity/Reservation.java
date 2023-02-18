package dat3.car.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
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
    @CreationTimestamp
    private LocalDateTime reservationDate;
    private LocalDate rentalDate;
    @ManyToOne
    private Car car;
    @ManyToOne
    private Member member;

    public Reservation(Member member, Car car, LocalDate date) {
        this.member = member;
        this.car = car;
        this.rentalDate = date;
    }
}
