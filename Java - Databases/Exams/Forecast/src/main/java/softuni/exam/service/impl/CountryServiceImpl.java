package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CountrySeedDto;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CountryService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private static final String COUNTRIES_PATH = "src/main/resources/files/json/countries.json";
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper mapper;


    public CountryServiceImpl(CountryRepository countryRepository, Gson gson, ValidationUtil validationUtil, ModelMapper mapper) {
        this.countryRepository = countryRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
    }

    @Override
    public boolean areImported() {
        return countryRepository.count() > 0;
    }

    @Override
    public String readCountriesFromFile() throws IOException {
            return Files
                    .readString(Path.of(COUNTRIES_PATH));
    }

    @Override
    public String importCountries() throws IOException {
        StringBuilder sb = new StringBuilder();

        Arrays.stream(gson
                        .fromJson(readCountriesFromFile(), CountrySeedDto[].class))
                .filter(countrySeedDto -> {
                    boolean isValid = validationUtil.isValid(countrySeedDto);

                    Optional<Country> countryByName =
                            countryRepository.findCountryByCountryName(countrySeedDto.getCountryName());

                    if (countryByName.isPresent()) {
                        isValid = false;
                    }
                    sb.append(isValid
                                    ? String.format("Successfully imported country %s - %s", countrySeedDto.getCountryName(),
                                    countrySeedDto.getCurrency())
                                    : "Invalid country")
                            .append(System.lineSeparator());
                    return isValid;
                })
                .map(countrySeedDto -> mapper.map(countrySeedDto, Country.class))
                .forEach(countryRepository::save);
        return sb.toString();
    }
}
