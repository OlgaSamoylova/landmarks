package landmarks.resource;

import landmarks.request.CountryRq;
import landmarks.response.CountryRs;
import landmarks.service.CountryService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Setter
@Getter
@RestController
@RequiredArgsConstructor
public class CountryResourceImpl implements CountryResource{

    private final CountryService countryService;

    @Override
    public ResponseEntity<List<CountryRs>> getCountries() {
        return ResponseEntity.ok(countryService.getCountries());
    }

    @Override
    public ResponseEntity<CountryRs> getCountry(int id) {
        return ResponseEntity.ok(countryService.getCountry(id));
    }

    @Override
    public ResponseEntity<CountryRs> getCountryCities(int id) {
        return ResponseEntity.ok(countryService.getCountryCities(id));
    }

    @Override
    public ResponseEntity<CountryRs> deleteCountry(int id) {
        return ResponseEntity.ok(countryService.deleteCountry(id));
    }

    @Override
    public ResponseEntity<CountryRs> addCountry(CountryRq countryRq) {
        return ResponseEntity.ok(countryService.addCountry(countryRq));
    }

    @Override
    public ResponseEntity<CountryRs> updateCountry(int id, CountryRq countryRq) {
        return ResponseEntity.ok(countryService.updateCountry(id, countryRq));
    }
}
