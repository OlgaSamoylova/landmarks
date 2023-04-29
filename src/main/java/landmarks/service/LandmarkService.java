package landmarks.service;

import landmarks.model.Landmark;
import landmarks.repository.CityRepository;
import landmarks.repository.LandmarkRepository;
import landmarks.request.LandmarkRq;
import landmarks.response.LandmarkRs;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Service
@Slf4j
@RequiredArgsConstructor
public class LandmarkService {
    private final LandmarkRepository landmarkRepository;
    private final CityRepository cityRepository;

    public LandmarkRs putLandmark (LandmarkRq landmarkRq){
            Landmark landmark = createLandmark(landmarkRq);
            landmarkRepository.save(landmark);
            LandmarkRs landmarkRs = createResponse(landmark);
        return landmarkRs;
    }

    public LandmarkRs updateLandmark (int id, LandmarkRq landmarkRq){
        Landmark landmark = landmarkRepository.findById(id).get();
        if (landmarkRq.getName() != null){
            landmark.setName(landmarkRq.getName());
        }
        if (landmarkRq.getAddress() != null){
            landmark.setAddress(landmarkRq.getAddress());
        }
        if (landmarkRq.getCityId() != null){
            landmark.setCity(cityRepository.findById(landmarkRq.getCityId()).get());
        }
        if (landmarkRq.getDescription() != null){
            landmark.setDescription(landmarkRq.getDescription());
        }

        landmarkRepository.save(landmark);
        return createResponse(landmark);

    }

    public LandmarkRs deleteLandmark(int id){
        Landmark landmark = landmarkRepository.findById(id).get();
        LandmarkRs landmarkRs = new LandmarkRs();
        landmarkRepository.delete(landmark);
        landmarkRs.setDescription("Запись удалена");
        return landmarkRs;
    }

    public LandmarkRs findById(int id){
        Landmark landmark = landmarkRepository.findById(id).get();
        return createResponse(landmark);
    }

    public List<LandmarkRs> findAll(){
        List<Landmark> landmarkList = landmarkRepository.findAll();
        List<LandmarkRs> landmarkRsList = new ArrayList<>();
        for(Landmark lm : landmarkList){
            LandmarkRs rs = createResponse(lm);
            landmarkRsList.add(rs);
        }
        return  landmarkRsList;
    }

    private LandmarkRs createResponse (Landmark landmark){
    LandmarkRs landmarkRs = new LandmarkRs();
    landmarkRs.setName(landmark.getName());
    landmarkRs.setAddress(landmark.getAddress());
    landmarkRs.setCity(landmark.getCity().getName());
    landmarkRs.setDescription(landmark.getDescription());
    return landmarkRs;
}

    private Landmark createLandmark(LandmarkRq landmarkRq){
    Landmark landmark = new Landmark();
    landmark.setName(landmarkRq.getName());
    landmark.setCity(cityRepository.findById(landmarkRq.getCityId()).get());
    landmark.setAddress(landmarkRq.getAddress());
    landmark.setDescription(landmarkRq.getDescription());
    return landmark;
}
}
