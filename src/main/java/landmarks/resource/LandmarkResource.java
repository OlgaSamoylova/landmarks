package landmarks.resource;

import landmarks.response.LandmarkRs;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import landmarks.request.LandmarkRq;

import java.util.List;

@RequestMapping("/landmarks/")
public interface LandmarkResource {
    @GetMapping("")
    ResponseEntity<List<LandmarkRs>> getLandmarks();

    @GetMapping("{id}")
    ResponseEntity<LandmarkRs> getLandmark(@PathVariable(name = "id") int id);

    @DeleteMapping("{id}")
    ResponseEntity<LandmarkRs> deleteLandmark(@PathVariable(name = "id") int id);

    @PostMapping("new")
    ResponseEntity<LandmarkRs> createLandmark(@RequestBody LandmarkRq landmarkRq);

    @PutMapping("{id}")
    ResponseEntity <LandmarkRs> updateLandmark(@PathVariable(name = "id") int id, @RequestBody LandmarkRq landmarkRq);



}
