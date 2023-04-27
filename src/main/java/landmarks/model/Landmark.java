package landmarks.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "landmark")
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Landmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @ManyToOne
    //@Column (name = "city_id")
    private City city;

    @Column
    private String address;

    @Column
    private String description;
}
