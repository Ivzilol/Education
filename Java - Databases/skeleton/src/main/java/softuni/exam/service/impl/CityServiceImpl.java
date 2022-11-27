package softuni.exam.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportCityDTO;
import softuni.exam.models.entity.City;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CityRepository;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CityService;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class    CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final Gson gson;
    private final Validator validator;
    private final ModelMapper modelMapper;
    private final CountryRepository countryRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository, CountryRepository countryRepository) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
        this.gson = new GsonBuilder().create();
        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();
        this.modelMapper = new ModelMapper();
    }

    @Override
    public boolean areImported() {
        return cityRepository.count() > 0;
    }

    @Override
    public String readCitiesFileContent() throws IOException {
        Path path = Path.of("src", "main", "resources", "files", "json", "cities.json");
        return String.join("\n", Files.readAllLines(path));
    }

    @Override
    public String importCities() throws IOException {
        String json = readCitiesFileContent();
        ImportCityDTO[] importCityDTOs = this.gson.fromJson(json, ImportCityDTO[].class);

        List<String> result = new ArrayList<>();

        return Arrays.stream(importCityDTOs)
                .map(this::importCity)
                .collect(Collectors.joining("\n"));
    }

    private String importCity(ImportCityDTO dto) {
        Set<ConstraintViolation<ImportCityDTO>> validateErrors = this.validator.validate(dto);

        if (!validateErrors.isEmpty()) {
            return "Invalid City";
        }

        Optional<City> byCityName = this.cityRepository.findByCityName(dto.getCityName());

        if (byCityName.isPresent()) {
            return "Invalid Team";
        }

        City city = this.modelMapper.map(dto, City.class);
        Optional<Country> country = this.countryRepository.getCountryById(dto.getCountry());

        city.setCountry(country.get());
        this.cityRepository.save(city);

        return "Successfully imported city " + city;
    }
}
