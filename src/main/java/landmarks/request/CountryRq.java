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
}
