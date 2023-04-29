package landmarks.service;

import landmarks.model.City;
import landmarks.model.Country;
import landmarks.repository.CityRepository;
import landmarks.repository.CountryRepository;
import landmarks.request.CountryRq;
import landmarks.response.CityRs;
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
        Country country = createCountry(countryRq);
        countryRepository.save(country);
        CountryRs countryRs = createCountryResponse(country);
        return countryRs;
    }

    public CountryRs deleteCountry(int id){
        Country country = countryRepository.findById(id).get();
        CountryRs countryRs = new CountryRs();
        if (country.equals(null)){
            countryRs.setDescription("Запись с таким Id не найдена");
        }
        else {
            countryRepository.delete(country);
            countryRs.setDescription("Запись удалена");
        }
        return countryRs;
    }

    public CountryRs updateCountry(int id, CountryRq countryRq){
        Country country = countryRepository.findById(id).get();
        if (countryRq.getName() != null){
            country.setName(countryRq.getName());
        }
        if (countryRq.getSquare() != null){
            country.setSquare(countryRq.getSquare());
        }
        if (countryRq.getPopulation() != null){
            country.setPopulation(countryRq.getPopulation());
        }
        if (countryRq.getDescription() != null){
            country.setDescription(countryRq.getDescription());
        }
        if (countryRq.getLanguage() != null){
            country.setLanguage(countryRq.getLanguage());
        }
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
        Country country = countryRepository.findById(id).get();
        return createCountryResponse(country);
    }

    public CountryRs getCountryCities(int id){
        Country country = countryRepository.findById(id).get();
        List<City> cityList = country.getCityList();
        List<CityRs> cityRsList = new ArrayList<>();
        for (City city: cityList){
            CityRs cityRs = new CityRs();
            cityRs.setName(city.getName());
            cityRs.setDescription(city.getDescription());
            cityRs.setPopulation(city.getPopulation());
            cityRs.setCountryId(city.getCountry().getId());
            cityRsList.add(cityRs);
        }
        CountryRs countryRs = createCountryResponse(country);
        countryRs.setCityRsList(cityRsList);
        return countryRs;
    }

    private Country createCountry(CountryRq countryRq){
        Country country = new Country();
        country.setName(countryRq.getName());
        country.setSquare(countryRq.getSquare());
        country.setLanguage(countryRq.getLanguage());
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
