package dat3.car.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.car.entity.Car;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)

public class CarResponseDto {
    private Long id;
    private String brand;
    private String model;
    private double pricePrDay;
    private int bestDiscount;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
    LocalDateTime created;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
    LocalDateTime edited;

    public CarResponseDto(Car c, boolean includeAll) {
        this.id = c.getId();
        this.brand = c.getBrand();
        this.model = c.getModel();
        this.pricePrDay = c.getPricePrDay();
        if (includeAll) {
            this.bestDiscount = c.getBestDiscount();
        }
    }
}
