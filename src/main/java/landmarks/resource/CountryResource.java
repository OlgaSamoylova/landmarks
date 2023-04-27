package landmarks.resource;

import landmarks.request.CountryRq;
import landmarks.response.CountryRs;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/countries/")
public interface CountryResource {

    @GetMapping("")
    ResponseEntity<List<CountryRs>> getCountries();

    @GetMapping("{id}")
    ResponseEntity<CountryRs> getCountry(@PathVariable(name = "id") int id);

    @GetMapping("{id}/cities")
    ResponseEntity<CountryRs> getCountryCities(@PathVariable(name = "id") int id);

    @DeleteMapping("{id}")
    ResponseEntity<CountryRs> deleteCountry(@PathVariable(name = "id") int id);

    @PostMapping("new")
    ResponseEntity<CountryRs> createCountry(@RequestBody CountryRq countryRq);

    @PutMapping("{id}")
    ResponseEntity <CountryRs> updateCountry(@PathVariable(name = "id") int id, @RequestBody CountryRq countryRq);
}
