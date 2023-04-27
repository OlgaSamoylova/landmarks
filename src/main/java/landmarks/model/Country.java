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
@Table(name = "country")
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private float square;

    @Column
    private float population;

    @Column
    private String language;

    @Column
    private String description;

    @OneToMany(mappedBy = "country", fetch = FetchType.EAGER)
    private List<City> cityList;


}
