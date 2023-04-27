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
public class LandmarkRq {

    private String name;

    private int cityId;

    private String address;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;
}
