package dat3.car.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity

@Getter
@Setter
@NoArgsConstructor
public class Member {
    @Id
    private String username;
    private String email;
    private String password;
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
    @CreationTimestamp
    private LocalDateTime created;
    @UpdateTimestamp
    private LocalDateTime lastEdited;

    public Member(String user, String password, String email,
                  String firstName, String lastName, String street, String city, String zip) {
        this.username = user;
        this.password= password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.zip = zip;
    }

}
