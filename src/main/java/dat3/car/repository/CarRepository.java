package dat3.car.repository;

import dat3.car.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {
    Optional<Car> findById(Long id);
    void deleteById(Long id);
    List<Car> findAllByBrandAndModel(String brand, String model);
    @Query("SELECT AVG(c.pricePrDay) FROM Car c")
    double findAvgPricePrDay();
    @Query("SELECT c FROM Car c WHERE c.bestDiscount = (SELECT MAX(c2.bestDiscount) FROM Car c2)")
    List<Car> findAllWithBestDiscount();
    @Query("SELECT c FROM Car c WHERE c.reservations IS EMPTY")
    List<Car> findAllByReservationsIsEmpty();
    @Query("SELECT c FROM Car c LEFT JOIN FETCH c.reservations res WHERE c.id = :carId AND (res IS EMPTY OR res.rentalDate <> :date)")
    Optional<Car> findUnreservedCarById(Long carId, LocalDate date);
}
