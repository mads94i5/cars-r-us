package dat3.car.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
public class RentalCompany {
    @Id
    private String name;
    @OneToMany(mappedBy = "rentalCompany", cascade = CascadeType.ALL)
    private List<Car> cars;
}
