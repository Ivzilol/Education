package softuni.exam.service.impl;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportForecastDTO;
import softuni.exam.models.dto.ImportForecastRootDTO;
import softuni.exam.models.entity.City;
import softuni.exam.models.entity.DayOfWeek;
import softuni.exam.models.entity.Forecast;
import softuni.exam.repository.CityRepository;
import softuni.exam.repository.ForecastRepository;
import softuni.exam.service.ForecastService;

import javax.persistence.Convert;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ForecastServiceImpl implements ForecastService {
    private final Path path =
            Path.of("src", "main", "resources", "files", "xml", "forecasts.xml");
    private final ForecastRepository forecastRepository;
    private final Unmarshaller unmarshaller;
    private final Validator validator;
    private final ModelMapper modelMapper;
    private final CityRepository cityRepository;

    @Autowired
    public ForecastServiceImpl(ForecastRepository forecastRepository,
                               CityRepository cityRepository) throws JAXBException {
        this.forecastRepository = forecastRepository;
        this.cityRepository = cityRepository;
        JAXBContext context = JAXBContext.newInstance(ImportForecastRootDTO.class);
        this.unmarshaller = context.createUnmarshaller();
        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();
        this.modelMapper = new ModelMapper();
    }

    @Override
    public boolean areImported() {
        return forecastRepository.count() > 0;
    }

    @Override
    public String readForecastsFromFile() throws IOException {

        return String.join("\n", Files.readAllLines(path));
    }

    @Override
    public String importForecasts() throws IOException, JAXBException {
        ImportForecastRootDTO forecastDTOs = (ImportForecastRootDTO) this.unmarshaller.unmarshal(
                new FileReader(path.toAbsolutePath().toString()));
        return forecastDTOs
                .getForecasts()
                .stream()
                .map(this::importForecasts)
                .collect(Collectors.joining("\n"));

    }

    private String importForecasts(ImportForecastDTO dto) {
        Set<ConstraintViolation<ImportForecastDTO>> errors = this.validator.validate(dto);

        if (!errors.isEmpty()) {
            return "Invalid forecast";
        }

        Optional<Forecast> optForecast =
                this.forecastRepository
                        .findByDayOfWeekAndMaxTemperatureAndMinTemperatureAndSunriseAndSunset(
                                dto.getDayOfWeek(),
                                dto.getMaxTemperature(),
                                dto.getMinTemperature(),
                                dto.getSunrise(),
                                dto.getSunset()

                        );

        if (optForecast.isPresent()) {
            return "Invalid forecast";
        }

        Forecast forecast = this.modelMapper.map(dto, Forecast.class);
        Optional<City> city = this.cityRepository.findCityById(dto.getCity());
        forecast.setCities(city.get());
        this.forecastRepository.save(forecast);

        return "Successfully import forecast " + forecast;
    }

    @Override
    public String exportForecasts() {
        StringBuilder stringBuilder = new StringBuilder();
        Set<Forecast> allByDayOfWeek = this.forecastRepository.findAllByDayOfWeekAndCities_PopulationLessThanOrderByMaxTemperatureDescIdAsc(DayOfWeek.SUNDAY, 150000);
        allByDayOfWeek.forEach( f ->
                            stringBuilder.append(String.format("""
                                                    City: %s
                                                    -min temperature: %.2f
                                                    --max temperature: %.2f
                                                    ---sunrise: %s
                                                    -----sunset: %s""",
                                    f.getCities().getCityName(),
                                    f.getMinTemperature(),
                                    f.getMaxTemperature(),
                                    f.getSunrise(),
                                    f.getSunset()))
                                    .append(System.lineSeparator())
                );
        return stringBuilder.toString();
    }
}
