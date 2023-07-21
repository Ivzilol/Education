package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ExportForecastDto;
import softuni.exam.models.dto.ForecastSeedRootDto;
import softuni.exam.models.entity.City;
import softuni.exam.models.entity.Forecast;
import softuni.exam.repository.CityRepository;
import softuni.exam.repository.ForecastRepository;
import softuni.exam.service.ForecastService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Service
public class ForecastServiceImpl implements ForecastService {

    private final ForecastRepository forecastRepository;

    private final CityRepository cityRepository;

    private static final String FORECAST_PATH = "src/main/resources/files/xml/forecasts.xml";

    private final XmlParser xmlParser;

    private final ValidationUtil validationUtil;

    private final ModelMapper mapper;

    public ForecastServiceImpl(ForecastRepository forecastRepository, CityRepository cityRepository, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper mapper) {
        this.forecastRepository = forecastRepository;
        this.cityRepository = cityRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
    }


    @Override
    public boolean areImported() {
        return this.forecastRepository.count() > 0;
    }

    @Override
    public String readForecastsFromFile() throws IOException {
        return Files
                .readString(Path.of(FORECAST_PATH));
    }

    @Override
    public String importForecasts() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();

        xmlParser
                .fromFile(FORECAST_PATH, ForecastSeedRootDto.class)
                .getForecastSeedDto()
                .stream()
                .filter(forecastSeedDto -> {
                    boolean isValid = validationUtil.isValid(forecastSeedDto);

                    City city = this.cityRepository.findCityById(forecastSeedDto.getCity());

                    if (city == null) {
                        isValid = false;
                    }

                    Forecast forecast = this.forecastRepository.findAllByCityAndDayOfWeek(Optional.ofNullable(city), forecastSeedDto.getDayOfWeek());

                    if (forecast != null) {
                        isValid = false;
                    }

                    sb.append(isValid
                            ?
                            String.format("Successfully import forecast %s - %.2f",
                                    forecastSeedDto.getDayOfWeek(), forecastSeedDto.getMaxTemperature())
                            :
                            "Invalid forecast"
                    ).append(System.lineSeparator());
                    return isValid;
                })
                .map(forecastSeedDto -> {
                    Forecast forecast = mapper.map(forecastSeedDto, Forecast.class);
                    Optional<City> city = this.cityRepository.findById(forecastSeedDto.getCity());
                    forecast.setCity(city.get());
                    return forecast;
                })
                .forEach(this.forecastRepository::save);
        return sb.toString();
    }

    @Override
    public String exportForecasts() {
        StringBuilder sb = new StringBuilder();
        List<ExportForecastDto> exportForecast = this.forecastRepository
                .exportSundayForecastWithCitizens();

        exportForecast.forEach(f -> {
            sb.append(String.format("City: %s:%n" +
                            "    -min temperature: %.2f%n" +
                            "    --max temperature: %.2f%n" +
                            "    ---sunrise: %s%n" +
                            "    -----sunset: %s%n",
                    f.getCityName(),
                    f.getMinTemperature(),
                    f.getMaxTemperature(),
                    f.getSunrise(),
                    f.getSunset()
            ));
        });
        return sb.toString();
    }
}
