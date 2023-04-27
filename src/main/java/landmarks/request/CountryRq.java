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
public class CountryRq {
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private float square;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private float population;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String language;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;
}
