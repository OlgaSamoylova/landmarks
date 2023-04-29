package landmarks.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import landmarks.model.City;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CountryRs {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Float square;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Float population;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String language;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<CityRs> cityRsList;
}
