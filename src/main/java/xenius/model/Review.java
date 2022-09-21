package xenius.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JsonBackReference // pga. cirkulær reference i @Restcontroller
    @EqualsAndHashCode.Exclude // pga. hashCode() fra @Data
    private Event event;
    private String text;
    private int rating;
    @OneToOne
    private User user;

    public void addUser(User user){
        this.user = user;
    }

    public void addEvent(Event event){
        this.event = event;
    }


}
