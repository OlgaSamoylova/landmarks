package landmarks.service;

import landmarks.model.City;
import landmarks.model.Landmark;
import landmarks.repository.CityRepository;
import landmarks.repository.CountryRepository;
import landmarks.repository.LandmarkRepository;
import landmarks.request.CityRq;
import landmarks.response.CityRs;
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
public class CityService {

    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    private final LandmarkRepository landmarkRepository;

    public CityRs addCity(CityRq cityRq){
        CityRs cityRs = new CityRs();
        City city = createCity(cityRq);
        List<City> cityList = cityRepository.findAll();
        if (cityList.isEmpty()){
            cityRepository.save(city);
            cityRs = createCityResponse(city);
        }
        else {
            for (City c : cityList){
                if(c.getName().equals(cityRq.getName())){
                    cityRs.setDescription("запись уже существует");
                    break;
                }
            }
            if (!cityRq.getDescription().equals("запись уже существует")){
                cityRepository.save(city);
                cityRs = createCityResponse(city);
            }
        }
        return cityRs;
    }

    public CityRs deleteCity(int id){
        City city = cityRepository.findById(id).orElseThrow(NoSuchFieldError :: new);
        cityRepository.delete(city);
        CityRs cityRs = new CityRs();
        cityRs.setDescription("Запись удалена");
        return cityRs;
    }

    public List<CityRs> getCities(){
        List<CityRs> cityRsList = new ArrayList<>();
        List<City> cityList = cityRepository.findAll();
        for (City city : cityList){
            cityRsList.add(createCityResponse(city));
        }
        return cityRsList;
    }

    public CityRs getCity(int id){
        City city = cityRepository.findById(id).orElseThrow(NoSuchFieldError :: new);
        return createCityResponse(city);
    }

    public CityRs updateCity(int id, CityRq cityRq){
        City city = cityRepository.findById(id).orElseThrow(NoSuchFieldError :: new);
        if(cityRq.getName() != null){
            city.setName(cityRq.getName());
        }
        if(cityRq.getCountryId() != null){
            city.setCountry(countryRepository.findById(cityRq.getCountryId()).orElseThrow(NoSuchFieldError :: new));
        }

        if(cityRq.getDescription() != null){
            city.setDescription(cityRq.getDescription());
        }
        if(cityRq.getPopulation() != null){
            city.setPopulation(cityRq.getPopulation());
        }
        cityRepository.save(city);
        return createCityResponse(city);
    }

    public CityRs getCityLandmarks(int id){
        City city = cityRepository.findById(id).orElseThrow();
        List<Landmark> landmarkList = city.getLandmarkList();
        List<LandmarkRs> landmarkRsList = new ArrayList<>();
        for (Landmark l : landmarkList){
            LandmarkRs landmarkRs = new LandmarkRs();
            landmarkRs.setName(l.getName());
            landmarkRs.setAddress(l.getAddress());
            landmarkRs.setCity(l.getCity().getName());
            landmarkRs.setDescription(l.getDescription());
            landmarkRsList.add(landmarkRs);
        }
        CityRs cityRs = createCityResponse(city);
        cityRs.setLandmarkRsList(landmarkRsList);
        return cityRs;
    }

    private CityRs createCityResponse(City city){
        CityRs cityRs = new CityRs();
        cityRs.setName(city.getName());
        cityRs.setDescription(city.getDescription());
        cityRs.setPopulation(city.getPopulation());
        cityRs.setCountryId(city.getCountry().getId());
        return cityRs;
    }

    private City createCity(CityRq cityRq){
        City city = new City();
        city.setName(cityRq.getName());
        city.setDescription(cityRq.getDescription());
        city.setPopulation(cityRq.getPopulation());
        city.setCountry(countryRepository.findById(cityRq.getCountryId()).get());
        return city;
    }
}
