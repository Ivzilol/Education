package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CarSeedDto;
import softuni.exam.models.dto.ExportCarsDto;
import softuni.exam.models.entity.Car;
import softuni.exam.repository.CarRepository;
import softuni.exam.service.CarService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    private static final String CAR_PATH = "src/main/resources/files/json/cars.json";

    private final Gson gson;

    private final ValidationUtil validationUtil;

    private final ModelMapper mapper;

    public CarServiceImpl(CarRepository carRepository, Gson gson, ValidationUtil validationUtil, ModelMapper mapper) {
        this.carRepository = carRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
    }

    @Override
    public boolean areImported() {
        return this.carRepository.count() > 0;
    }

    @Override
    public String readCarsFileContent() throws IOException {
        return Files
                .readString(Path.of(CAR_PATH));
    }

    @Override
    public String importCars() throws IOException {
        StringBuilder sb = new StringBuilder();

        Arrays.stream(gson
                        .fromJson(readCarsFileContent(), CarSeedDto[].class))
                .filter(carSeedDto -> {
                    boolean isValid = validationUtil.isValid(carSeedDto);

                    boolean ifExist = this.carRepository.findCarByMakeAndModelAndKilometers(carSeedDto.getMake(),
                            carSeedDto.getModel(), carSeedDto.getKilometers()).isEmpty();

                    if (!ifExist) {
                        isValid = false;
                    }

                    sb.append(isValid
                            ?
                            String.format("Successfully imported car - %s - %s",
                                    carSeedDto.getMake(), carSeedDto.getModel())
                            :
                            "Invalid car"
                    ).append(System.lineSeparator());
                    return isValid;
                }).map(carSeedDto -> mapper.map(carSeedDto, Car.class))
                .forEach(this.carRepository::save);
        return sb.toString();
    }

    @Override
    public String getCarsOrderByPicturesCountThenByMake() {
        StringBuilder sb = new StringBuilder();

        List<ExportCarsDto> exportCars = this.carRepository.exportCars();

        exportCars.forEach(p -> sb.append(String.format("Car make - %s, model - %s%n" +
                        "    Kilometers - %d%n" +
                        "    Registered on - %s%n" +
                        "    Number of Pictures - %d%n",
                p.getMake(), p.getModel(),
                p.getKilometers(),
                p.getRegisteredOn(),
                p.getCountPicture()
        )).append(System.lineSeparator()));
        return sb.toString();
    }
}
