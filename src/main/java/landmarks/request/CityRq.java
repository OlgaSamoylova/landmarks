package landmarks.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CityRq {

    private String name;

    private int countryId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private float population;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;
}
