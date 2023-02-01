package dat3.car.config;

import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.repository.CarRepository;
import dat3.car.repository.MemberRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DeveloperData implements ApplicationRunner {
    final CarRepository carRepository;

    final MemberRepository memberRepository;

    public DeveloperData(CarRepository carRepository, MemberRepository memberRepository) {
        this.carRepository = carRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("DeveloperData was run.");
        carRepository.save(new Car("Opel", "Cadet", 200, 15));
        carRepository.save(new Car("Dodge", "Viper", 400, 5));

        Member member1 = new Member("User1", "1234", "e@mail.com", "Firstname", "Lastname", "Kultorvet", "København", "1150");
        member1.setFavoriteCarColors(List.of("Rød", "Blå"));
        Map<String, String> member1phone = new HashMap<>();
        member1phone.put("mobile", "12345");
        member1phone.put("work", "45678");
        member1.setPhone(member1phone);
        memberRepository.save(member1);

        Member member2 = new Member("User2", "1234", "e@mail.com", "Firstname", "Lastname", "Kultorvet", "København", "1150");
        member2.setFavoriteCarColors(List.of("Grøn", "Gul"));
        Map<String, String> member2phone = new HashMap<>();
        member2phone.put("mobile", "12345");
        member2phone.put("work", "45678");
        member2.setPhone(member2phone);
        memberRepository.save(member2);
    }
}
