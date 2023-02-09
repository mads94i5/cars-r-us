package dat3.car.service;

import dat3.car.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CarServiceMockitoTest {
    @Mock
    CarRepository carRepository;

    CarService carService;

    @BeforeEach
    void setUp() {
        carService = new CarService(carRepository);
    }

}