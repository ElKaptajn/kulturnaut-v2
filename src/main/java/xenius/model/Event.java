package xenius.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Event implements Comparable<Event> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String venue;
    private Date date;
    @ManyToOne
    @JsonBackReference
    private Band band;
    @OneToMany(mappedBy = "event")
    private Set<Review> reviews = new HashSet<>();

    public void addReview(Review review){
        reviews.add(review);
    }

    @Override
    public int compareTo(Event event) {
        return getDate().compareTo(event.getDate());
    }

}
