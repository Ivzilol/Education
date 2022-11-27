package softuni.exam.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportCountriesDTO;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CountryService;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CountryServiceImpl implements CountryService {


    private final CountryRepository countryRepository;
    private final Gson gson;
    private final Validator validator;
    private final ModelMapper modelMapper;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
        this.gson = new GsonBuilder().create();
        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();
        this.modelMapper = new ModelMapper();
    }

    @Override
    public boolean areImported() {
        return this.countryRepository.count() > 0;
    }

    @Override
    public String readCountriesFromFile() throws IOException {
        Path path = Path.of("src", "main", "resources", "files", "json", "countries.json");
        return String.join("\n", Files.readAllLines(path));
    }

    @Override
    public String importCountries() throws IOException {
        String json = readCountriesFromFile();
        ImportCountriesDTO[] importCountriesDTOs = this.gson.fromJson(json, ImportCountriesDTO[].class);

        List<String> result = new ArrayList<>();

        for (ImportCountriesDTO importCountriesDTO : importCountriesDTOs) {
            Set<ConstraintViolation<ImportCountriesDTO>> validate =
                    this.validator.validate(importCountriesDTO);
            if (validate.isEmpty()) {
                Optional<Country> optionalCountry =
                        this.countryRepository.findByCountryName(importCountriesDTO.getCountryName());
                if (optionalCountry.isEmpty()) {
                    Country country = this.modelMapper.map(importCountriesDTO, Country.class);
                    this.countryRepository.save(country);
                    String msg = String.format("Successfully imported country %s %s",
                            country.getCountryName(), country.getCurrency());
                    result.add(msg);

                } else {
                    result.add("Invalid Countries");
                }
            } else {
                result.add("Invalid Countries");
            }
        }
        return String.join("\n", result);
    }
}
