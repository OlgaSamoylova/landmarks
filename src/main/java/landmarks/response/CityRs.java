package landmarks.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import landmarks.model.Landmark;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CityRs {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int countryId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private float population;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Landmark> landmarkList;
}
