package xenius.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @ManyToMany
    @JoinTable(
        name = "venue_like",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "venue_id")
    )
    @JsonBackReference
    private Set<Venue> venuesLiked = new HashSet<>();

    @OneToMany(mappedBy = "review")
    private Set<Review> reviews = new HashSet<>();
}
