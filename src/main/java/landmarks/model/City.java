package landmarks.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "city")
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @ManyToOne
    //@Column (name = "country_id")
    private Country country;

    @Column
    private float population;

    @Column
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "city", fetch = FetchType.EAGER)
    private List<Landmark> landmarkList = Collections.EMPTY_LIST;
}
