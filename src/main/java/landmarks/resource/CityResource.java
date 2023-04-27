package landmarks.resource;

import landmarks.request.CityRq;
import landmarks.request.CountryRq;
import landmarks.response.CityRs;
import landmarks.response.CountryRs;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/cities/")
public interface CityResource {
    @GetMapping("")
    ResponseEntity<List<CityRs>> getCities();

    @GetMapping("{id}")
    ResponseEntity<CityRs> getCity(@PathVariable(name = "id") int id);

    @GetMapping("{id}/landmarks")
    ResponseEntity<CityRs> getCityLandmark(@PathVariable(name = "id") int id);

    @DeleteMapping("{id}")
    ResponseEntity<CityRs> deleteCity(@PathVariable(name = "id") int id);

    @PostMapping("new")
    ResponseEntity<CityRs> createCity(@RequestBody CityRq cityRq);

    @PutMapping("{id}")
    ResponseEntity <CityRs> updateCity(@PathVariable(name = "id") int id, @RequestBody CityRq CityRq);
}
