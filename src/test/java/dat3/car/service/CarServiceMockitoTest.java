package dat3.car.service;

import dat3.car.dto.CarRequestDto;
import dat3.car.dto.CarResponseDto;
import dat3.car.entity.Car;
import dat3.car.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class CarServiceMockitoTest {
    @Mock
    CarRepository carRepository;

    CarService carService;

    @BeforeEach
    void setUp() {
        carService = new CarService(carRepository);
    }

    @Test
    void getCars() {
        Car c1 = new Car("Tesla", "Cybertruck", 1000, 15);
        Car c2 = new Car("Opel", "Kadet", 500, 25);

        Mockito.when(carRepository.findAll()).thenReturn(List.of(c1, c2));

        List<CarResponseDto> cars = carService.getCars(false);
        assertEquals(2, cars.size());
    }
    @Test
    void getCar() {
        Car c1 = new Car("Tesla", "Cybertruck", 1000, 15);

        Mockito.when(carRepository.findById(1L)).thenReturn(Optional.of(c1));

        CarResponseDto car = carService.getCar(1L, false);
        assertEquals("Tesla", car.getBrand());
    }
    @Test
    void addCar() {
        Car c1 = new Car("Tesla", "Cybertruck", 1000, 15);

        Mockito.when(carRepository.save(any(Car.class))).thenReturn(c1);

        CarRequestDto carRequest = new CarRequestDto(c1);
        CarResponseDto carResponse = carService.addCar(carRequest);
        assertEquals("Tesla", carResponse.getBrand());
    }
}