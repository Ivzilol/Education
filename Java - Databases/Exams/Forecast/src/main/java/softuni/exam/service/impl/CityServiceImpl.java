package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CitySeedDto;
import softuni.exam.models.entity.City;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CityRepository;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CityService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {

    private static final String CITIES_PATH = "src/main/resources/files/json/cities.json";

    private final Gson gson;

    private final CityRepository cityRepository;

    private final CountryRepository countryRepository;

    private final ValidationUtil validationUtil;

    private final ModelMapper mapper;

    public CityServiceImpl(Gson gson, CityRepository cityRepository, CountryRepository countryRepository, ValidationUtil validationUtil, ModelMapper mapper) {
        this.gson = gson;
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
    }

    @Override
    public boolean areImported() {
        return cityRepository.count() > 0;
    }

    @Override
    public String readCitiesFileContent() throws IOException {
        return Files
                .readString(Path.of(CITIES_PATH));
    }

    @Override
    public String importCities() throws IOException {
        StringBuilder sb = new StringBuilder();

        Arrays.stream(gson
                        .fromJson(readCitiesFileContent(), CitySeedDto[].class))
                .filter(citySeedDto -> {
                    boolean isValid = validationUtil.isValid(citySeedDto);

                    Optional<City> cityByName = this.cityRepository.findByCityName(citySeedDto.getCityName());
                    if (cityByName.isPresent()) {
                        isValid = false;
                    }
                    sb.append(isValid
                                    ? String.format("Successfully imported city %s - %d", citySeedDto.getCityName(),
                                    citySeedDto.getPopulation())
                                    : "Invalid City")
                            .append(System.lineSeparator());
                    return isValid;

                })
                .map(citySeedDto -> {
                    City city = mapper.map(citySeedDto, City.class);
                    Optional<Country> countryById = this.countryRepository.findById(citySeedDto.getCountry());
                    if (countryById.isEmpty()) {
                        System.out.println("ERROR: " + citySeedDto.getCityName());
                        return city;
                    }
                    city.setCountry(countryById.get());
                    return city;
                })
                .forEach(cityRepository::save);
        return sb.toString();
    }

    @Override
    public City findCityById(Long city) {
        return cityRepository.findById(city).orElse(null);
    }
}
