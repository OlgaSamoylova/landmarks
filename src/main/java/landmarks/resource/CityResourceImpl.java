package landmarks.resource;

import landmarks.request.CityRq;
import landmarks.response.CityRs;
import landmarks.service.CityService;
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
public class CityResourceImpl implements CityResource{
    private final CityService cityService;

    @Override
    public ResponseEntity<List<CityRs>> getCities() {
        return ResponseEntity.ok(cityService.getCities());
    }

    @Override
    public ResponseEntity<CityRs> getCity(int id) {
        return ResponseEntity.ok(cityService.getCity(id));
    }

    @Override
    public ResponseEntity<CityRs> getCityLandmark(int id) {
        return ResponseEntity.ok(cityService.getCityLandmarks(id));
    }

    @Override
    public ResponseEntity<CityRs> deleteCity(int id) {
        return ResponseEntity.ok(cityService.deleteCity(id));
    }

    @Override
    public ResponseEntity<CityRs> createCity(CityRq cityRq) {
        return ResponseEntity.ok(cityService.addCity(cityRq));
    }

    @Override
    public ResponseEntity<CityRs> updateCity(int id, CityRq cityRq) {
        return ResponseEntity.ok(cityService.updateCity(id, cityRq));
    }
}
