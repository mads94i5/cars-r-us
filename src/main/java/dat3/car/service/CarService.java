package dat3.car.service;

import dat3.car.dto.CarRequestDto;
import dat3.car.dto.CarResponseDto;
import dat3.car.entity.Car;
import dat3.car.repository.CarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }
    public List<CarResponseDto> getCars(boolean includeAll) {
        List<Car> cars = carRepository.findAll();
        List<CarResponseDto> carResponse = cars.stream().map(c -> new CarResponseDto(c, includeAll)).toList();
        return carResponse;
    }

    public CarResponseDto getCar(Long id, boolean includeAll) {
        Optional<Car> member = carRepository.findById(id);
        List<CarResponseDto> carResponse = member.stream().map(c -> new CarResponseDto(c, includeAll)).toList();
        return carResponse.get(0);
    }

    public CarResponseDto addCar(CarRequestDto carRequest) {
        Car newCar = CarRequestDto.getCarEntity(carRequest);
        newCar = carRepository.save(newCar);
        return new CarResponseDto(newCar, false);
    }

    public void setCarBestDiscount(Long id, int value) {
        Optional<Car> car = carRepository.findById(id);
        if (car.isPresent()) {
            Car newCar = car.get();
            newCar.setBestDiscount(value);
            carRepository.save(newCar);
        }
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    public ResponseEntity<Boolean> updateCar(CarRequestDto body, Long id) {


        Car car = carRepository.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Car with this ID does not exist"));

        car.setBrand(body.getBrand());
        car.setModel(body.getModel());
        car.setPricePrDay(body.getPricePrDay());
        carRepository.save(car);

        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
