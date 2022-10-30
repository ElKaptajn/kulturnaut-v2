package xenius.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JsonBackReference // pga. cirkulær reference i @Restcontroller
    private Event event;
    private String text;
    private int rating;
    @ManyToOne
    @JsonBackReference // pga. cirkulær reference i @Restcontroller
    private User user;

    public void addUser(User user){
        this.user = user;
    }

    public void addEvent(Event event){
        this.event = event;
    }


}
