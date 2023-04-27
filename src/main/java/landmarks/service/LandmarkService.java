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
        LandmarkRs landmarkRs = new LandmarkRs();
        List<Landmark> landmarkList = landmarkRepository.findAll();
        if (landmarkList.isEmpty()){
            Landmark landmark = createLandmark(landmarkRq);
            landmarkRepository.save(landmark);
            landmarkRs = createResponse(landmark);
        }
        else{
            for (Landmark lm : landmarkList){
                if (lm.equals(landmarkRq.getName())){
                    landmarkRs.setDescription("Объект не создан, такая запись уже есть в базе");
                    break;
                }
            }
            if (!landmarkRs.getDescription().equals("Объект не создан, такая запись уже есть в базе")){
                landmarkRepository.save(createLandmark(landmarkRq));
                landmarkRs = createResponse(createLandmark(landmarkRq));
            }
        }

        return landmarkRs;
    }

    public LandmarkRs updateLandmark (int id, LandmarkRq landmarkRq){
        Landmark landmark = landmarkRepository.findById(id).get();
        landmark.setName(landmarkRq.getName());
        landmark.setCity(cityRepository.findById(landmarkRq.getCityId()).get());
        landmark.setDescription(landmarkRq.getDescription());
        landmarkRepository.save(landmark);
        return createResponse(landmark);

    }

    public LandmarkRs deleteLandmark(int id){
        Landmark landmark = landmarkRepository.findById(id).get();
        LandmarkRs landmarkRs = new LandmarkRs();
        if (landmark.equals(null)){
            landmarkRs.setDescription("Запись с таким Id не найдена");
        }
        else {
            landmarkRepository.deleteById(id);
            System.out.println("запись с именем " + landmark.getName() + " удалена!!!");
            landmarkRs.setDescription("Запись удалена");
        }
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
    landmark.setDescription(landmarkRq.getDescription());
    return landmark;
}
}
