package dat3.car.service;

import dat3.car.entity.Car;
import dat3.car.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class CarServiceH2Test {
    @Autowired
    public CarRepository carRepository;
    CarService carService;
    boolean dataIsReady = false;
    @BeforeEach
    void setUp() {
        if(!dataIsReady){  //Explain this
            carRepository.save(new Car("c1", "m1", 400, 15));
            carRepository.save(new Car("c2", "m2", 500, 25));
            dataIsReady = true;
            carService = new CarService(carRepository); //Real DB is mocked away with H2
        }
    }

}