package dat3.car.repository;

import dat3.car.entity.RentalCompany;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalCompanyRepository extends JpaRepository<RentalCompany, Integer> {
}
