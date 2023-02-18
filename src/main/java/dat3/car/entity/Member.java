package dat3.car.entity;

import dat3.security.entity.UserWithRoles;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "USER_TYPE")
public class Member extends UserWithRoles {
    private String firstName;
    private String lastName;
    @ElementCollection
    private List<String> favoriteCarColors = new ArrayList<>();
    @ElementCollection
    @MapKeyColumn(name="Description")
    @Column(name="phoneNumber")
    private Map<String, String> phone = new HashMap<>();
    private String street;
    private String city;
    private String zip;
    private boolean approved;
    private int ranking;
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Reservation> reservations;
    @CreationTimestamp
    private LocalDateTime created;
    @UpdateTimestamp
    private LocalDateTime updated;

    public void addReservation(Reservation reservation){
        reservations.add(reservation);
    }

    public Member(String user, String password, String email,
                  String firstName, String lastName, String street, String city, String zip) {
        super(user, password, email);
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.zip = zip;
    }

}
