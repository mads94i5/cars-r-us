package dat3.car.repository;

import dat3.car.entity.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    boolean dataIsArranged = false;

    @BeforeEach
    public void setup() {
        if (!dataIsArranged) {
            List<String> members = new ArrayList<>(Arrays.asList("user1", "user2"));
            List<Member> memberEntities = members.stream().map(m -> new Member(m, "1234", "e@mail.com", "Firstname", "Lastname", "Streetname 69", "Copenhagen", "1150")).toList();
            memberRepository.saveAll(memberEntities);
            dataIsArranged = true;
        }
    }

    @Test
    public void testGetAll() {
        List<Member> members = memberRepository.findAll();
        assertEquals(2, members.size());
    }

}