package landmarks.service;

import landmarks.model.City;
import landmarks.model.Country;
import landmarks.repository.CityRepository;
import landmarks.repository.CountryRepository;
import landmarks.request.CountryRq;
import landmarks.response.CountryRs;
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
public class CountryService {
    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;

    public CountryRs addCountry(CountryRq countryRq){
        CountryRs countryRs = new CountryRs();
        Country country = createCountry(countryRq);
        List<Country> countryList = countryRepository.findAll();
        if (countryList.isEmpty()){
            countryRepository.save(country);
            countryRs = createCountryResponse(country);
        }
        else{
            for (Country c : countryList){
                if(c.getName().equals(country.getName())){
                    countryRs.setDescription("Такая страна уже есть в справочнике");
                    break;
                }
            }
            if (!countryRs.getDescription().equals("Такая страна уже есть в справочнике")){
                countryRepository.save(country);
                countryRs = createCountryResponse(country);
            }
        }

        return countryRs;
    }

    public CountryRs deleteCountry(int id){
        Country country = countryRepository.findById(id).get();
        CountryRs countryRs = new CountryRs();
        if (country.equals(null)){
            countryRs.setDescription("Запись с таким Id не найдена");
        }
        else {
            countryRepository.deleteById(id);
            countryRs.setDescription("Запись удалена");
        }
        return countryRs;
    }

    public CountryRs updateCountry(int id, CountryRq countryRq){
        Country country = countryRepository.findById(id).orElseThrow();
        country.setName(countryRq.getName());
        country.setSquare(countryRq.getSquare());
        country.setPopulation(countryRq.getPopulation());
        country.setDescription(countryRq.getDescription());
        countryRepository.save(country);

        return createCountryResponse(country);

    }

    public List<CountryRs> getCountries(){
        List<Country> countryList = countryRepository.findAll();
        List<CountryRs> countryRsList = new ArrayList<>();
        for (Country country : countryList){
            countryRsList.add(createCountryResponse(country));
        }
        return countryRsList;
    }

    public CountryRs getCountry(int id){
        Country country = countryRepository.findById(id).orElseThrow();
        return createCountryResponse(country);
    }

    public CountryRs getCountryCities(int id){
        CountryRs countryRs = new CountryRs();
        Country country = countryRepository.findById(id).orElseThrow();
        List<City> cityList = country.getCityList();
        countryRs.setCityList(cityList);
        return countryRs;
    }

    private Country createCountry(CountryRq countryRq){
        Country country = new Country();
        country.setName(countryRq.getName());
        country.setSquare(countryRq.getSquare());
        country.setPopulation(countryRq.getPopulation());
        country.setDescription(countryRq.getDescription());
        return country;
    }

    private CountryRs createCountryResponse(Country country) {
        CountryRs countryRs = new CountryRs();
        countryRs.setName(country.getName());
        countryRs.setLanguage(country.getLanguage());
        countryRs.setPopulation(country.getPopulation());
        countryRs.setDescription(country.getDescription());
        countryRs.setSquare(country.getSquare());
        return countryRs;
    }
}
