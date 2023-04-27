package landmarks.service;

import landmarks.model.City;
import landmarks.model.Landmark;
import landmarks.repository.CityRepository;
import landmarks.repository.CountryRepository;
import landmarks.repository.LandmarkRepository;
import landmarks.request.CityRq;
import landmarks.response.CityRs;
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
        City city = cityRepository.findById(id).orElseThrow();
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
        City city = cityRepository.findById(id).orElseThrow();
        return createCityResponse(city);
    }

    public CityRs updateCity(int id, CityRq cityRq){
        City city = cityRepository.findById(id).orElseThrow();
        city.setName(cityRq.getName());
        city.setDescription(cityRq.getDescription());
        city.setPopulation(cityRq.getPopulation());
        city.setCountry(countryRepository.findById(cityRq.getCountryId()).get());
        cityRepository.save(city);
        return createCityResponse(city);
    }

    public CityRs getCityLandmarks(int id){
        City city = cityRepository.findById(id).orElseThrow();
        List<Landmark> landmarkList = city.getLandmarkList();
        CityRs cityRs = new CityRs();
        cityRs.setLandmarkList(landmarkList);
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
