package dat3.car.dto;

import dat3.car.entity.Car;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarRequestDto {

    private Long id;
    private String brand;
    private String model;
    private double pricePrDay;

    public static Car getCarEntity(CarRequestDto c){
        return new Car(c.brand, c.model, c.pricePrDay);
    }

    public CarRequestDto(Car car) {
        this.id = car.getId();
        this.brand = car.getBrand();
        this.model = car.getModel();
        this.pricePrDay = car.getPricePrDay();
    }
}
