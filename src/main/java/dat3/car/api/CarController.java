package dat3.car.api;

import dat3.car.dto.CarRequestDto;
import dat3.car.dto.CarResponseDto;
import dat3.car.service.CarService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
@CrossOrigin
public class CarController {

    final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }
    //Security Admin
    @GetMapping
    List<CarResponseDto> getCars(){
        return carService.getCars(true);
    }

    //Security Admin
    @GetMapping(path = "/{id}")
    CarResponseDto getCarById(@PathVariable Long id) {
        return carService.getCar(id, false);
    }

    //Security Unauthorized
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    CarResponseDto addCar(@RequestBody CarRequestDto body) {
        return carService.addCar(body);
    }

    //Security Car
    @PutMapping("/{id}")
    ResponseEntity<Boolean> editCar(@RequestBody CarRequestDto body, @PathVariable Long id) {
        return carService.updateCar(body, id);
    }

    //Security Admin
    @PatchMapping("/discount/{id}/{value}")
    void setBestDiscountForCar(@PathVariable Long id, @PathVariable int value) {
        carService.setCarBestDiscount(id, value);
    }

    // Security Admin
    @DeleteMapping("/{id}")
    void deleteCarById(@PathVariable Long id) {
        carService.deleteCar(id);
    }
}
