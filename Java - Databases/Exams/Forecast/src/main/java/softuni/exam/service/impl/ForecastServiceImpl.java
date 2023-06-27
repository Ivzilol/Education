package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ForecastSeedRootDto;
import softuni.exam.models.entity.City;
import softuni.exam.models.entity.Forecast;
import softuni.exam.models.enyms.DaysOfWeek;
import softuni.exam.repository.CityRepository;
import softuni.exam.repository.ForecastRepository;
import softuni.exam.service.CityService;
import softuni.exam.service.ForecastService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import static org.modelmapper.Converters.Collection.map;

@Service
public class ForecastServiceImpl implements ForecastService {

    private static final String FORECAST_PATH = "src/main/resources/files/xml/forecasts.xml";
    private final ForecastRepository forecastRepository;

    private final CityService cityService;

    private final XmlParser xmlParser;

    private final ValidationUtil validationUtil;

    private final ModelMapper modelMapper;

    public ForecastServiceImpl(ForecastRepository forecastRepository, CityRepository cityRepository, CityService cityService, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.forecastRepository = forecastRepository;
        this.cityService = cityService;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
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
                .getForecastRootDto()
                .stream()
                .filter(forecastSeedDto -> {
                    boolean isValid = validationUtil.isValid(forecastSeedDto);

                    City city = this.cityService.findCityById(forecastSeedDto.getCity());

                    if (city == null) {
                        isValid = false;
                    }
                    Forecast forecast = forecastRepository.findAllByCityAndDays(Optional.ofNullable(city), forecastSeedDto.getDaysOfWeek());
                    if (forecast != null) {
                        isValid = false;
                    }
                    sb
                            .append(isValid
                                    ? String.format("Successfully import forecast %s - %.2f",
                                    forecastSeedDto.getDaysOfWeek().toString(), forecastSeedDto.getMaxTemperature())
                                    : "Invalid forecast")
                            .append(System.lineSeparator());

                    return isValid;
                })
                .map(forecastSeedDto -> {
                    Forecast forecast = modelMapper.map(forecastSeedDto, Forecast.class);
                    City city = cityService.findCityById(forecastSeedDto.getCity());
                    forecast.setCity(city);
                    return forecast;
                })
                .forEach(forecastRepository::save);
        return sb.toString();
    }

    @Override
    public String exportForecasts() {
        StringBuilder sb = new StringBuilder();

        Set<Forecast> allByDayOfWeekAndDay = this.forecastRepository.findAllByDaysAndCity_PopulationLessThanOrderByMaxTemperatureDescIdAsc(DaysOfWeek.SUNDAY, 150000);

        allByDayOfWeekAndDay
                .forEach(forecast -> {
                    sb.append(String.format("City: %s\n" +
                                            "-min temperature: %.2f\n" +
                                            "--max temperature: %.2f\n" +
                                            "---sunrise: %s\n" +
                                            "-----sunset: %s",
                                    forecast.getCity().getCityName(),
                                    forecast.getMinTemperature(),
                                    forecast.getMaxTemperature(),
                                    forecast.getSunrise(),
                                    forecast.getSunset()))
                            .append(System.lineSeparator());
                });
        return sb.toString();
    }
}
