package dat3.car.config;

import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import dat3.car.repository.CarRepository;
import dat3.car.repository.MemberRepository;
import dat3.car.repository.ReservationRepository;
import dat3.car.service.ReservationService;
import dat3.security.entity.Role;
import dat3.security.entity.UserWithRoles;
import dat3.security.repository.UserWithRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
@EnableJpaRepositories(basePackages = {"dat3.security.repository", "dat3.car.repository"})
@ComponentScan(basePackages = {"dat3.security", "dat3.car"})
public class DeveloperData implements ApplicationRunner {
    final CarRepository carRepository;

    final MemberRepository memberRepository;

    final UserWithRolesRepository userWithRolesRepository;

    final ReservationService reservationService;
    final String passwordUsedByAll = "test12";

    public DeveloperData(CarRepository carRepository, MemberRepository memberRepository, UserWithRolesRepository userWithRolesRepository, ReservationRepository reservationRepository, @Autowired ReservationService reservationService) {
        this.carRepository = carRepository;
        this.memberRepository = memberRepository;
        this.userWithRolesRepository = userWithRolesRepository;
        this.reservationService = reservationService;
    }

    private void makeTestData() {
        System.out.println("***************TEST DATA****************");
        carRepository.save(new Car("Opel", "Cadet", 200, 15));
        carRepository.save(new Car("Dodge", "Viper", 400, 5));

        LocalDate date = LocalDate.now();
        String formattedDate = date.format(DateTimeFormatter.ISO_DATE);
        // reservationService.reserveCar("user1", 1L, date);

/*
        Member member1 = new Member("User1", "1234", "e@mail.com", "Firstname", "Lastname", "Kultorvet", "København", "1150");
        member1.setFavoriteCarColors(List.of("Rød", "Blå"));
        Map<String, String> member1phone = new HashMap<>();
        member1phone.put("mobile", "12345");
        member1phone.put("work", "45678");
        member1.setPhone(member1phone);
        memberRepository.save(member1);

        Member member2 = new Member("User2", "1234", "e2@mail.com", "Firstname", "Lastname", "Kultorvet", "København", "1150");
        member2.setFavoriteCarColors(List.of("Grøn", "Gul"));
        Map<String, String> member2phone = new HashMap<>();
        member2phone.put("mobile", "12345");
        member2phone.put("work", "45678");
        member2.setPhone(member2phone);
        memberRepository.save(member2);

 */
    }

    /*****************************************************************************************
     NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL
     iT'S ONE OF THE TOP SECURITY FLAWS YOU CAN DO
     *****************************************************************************************/
    private void setupUserWithRoleUsers() {

        System.out.println("******************************************************************************");
        System.out.println("******* NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL ************");
        System.out.println("******* REMOVE THIS BEFORE DEPLOYMENT, AND SETUP DEFAULT USERS DIRECTLY  *****");
        System.out.println("**** ** ON YOUR REMOTE DATABASE                 ******************************");
        System.out.println("******************************************************************************");
        UserWithRoles user1 = new UserWithRoles("user1", passwordUsedByAll, "user1@a.dk");
        UserWithRoles user2 = new UserWithRoles("user2", passwordUsedByAll, "user2@a.dk");
        UserWithRoles user3 = new UserWithRoles("user3", passwordUsedByAll, "user3@a.dk");
        UserWithRoles user4 = new UserWithRoles("user4", passwordUsedByAll, "user4@a.dk");
        user1.addRole(Role.USER);
        user1.addRole(Role.ADMIN);
        user2.addRole(Role.USER);
        user3.addRole(Role.ADMIN);
        //No Role assigned to user4
        userWithRolesRepository.save(user1);
        userWithRolesRepository.save(user2);
        userWithRolesRepository.save(user3);
        userWithRolesRepository.save(user4);
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("DeveloperData was run.");
        setupUserWithRoleUsers();
        makeTestData();
    }

}
