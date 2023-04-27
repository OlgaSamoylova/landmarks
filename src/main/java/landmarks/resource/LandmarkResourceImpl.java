package landmarks.resource;

import landmarks.response.LandmarkRs;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import landmarks.request.LandmarkRq;
import landmarks.service.LandmarkService;


import java.util.List;


@Setter
@Getter
@RestController
@RequiredArgsConstructor
public class LandmarkResourceImpl implements LandmarkResource{

    private final LandmarkService landmarkService;


    @Override
    public ResponseEntity<List<LandmarkRs>> getLandmarks() {
        return ResponseEntity.ok(landmarkService.findAll());
    }

    @Override
    public ResponseEntity<LandmarkRs> getLandmark(int id) {
        return ResponseEntity.ok(landmarkService.findById(id));
    }

    @Override
    public ResponseEntity<LandmarkRs> deleteLandmark(int id) {
        return ResponseEntity.ok(landmarkService.deleteLandmark(id));
    }

    @Override
    public ResponseEntity<LandmarkRs> createLandmark(LandmarkRq landmarkRq) {
        return ResponseEntity.ok(landmarkService.putLandmark(landmarkRq));
    }

    @Override
    public ResponseEntity<LandmarkRs> updateLandmark(int id, LandmarkRq landmarkRq) {
        return ResponseEntity.ok(landmarkService.updateLandmark(id, landmarkRq));
    }
}
