package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CarSeedRootDto;
import softuni.exam.models.entity.Car;
import softuni.exam.repository.CarRepository;
import softuni.exam.service.CarService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {
    private static String CARS_FILE_PATH = "src/main/resources/files/xml/cars.xml";
    private final CarRepository carRepository;

    private final ValidationUtil validationUtil;

    private final ModelMapper mapper;

    private final XmlParser xmlParser;

    public CarServiceImpl(CarRepository carRepository, ValidationUtil validationUtil, ModelMapper mapper, XmlParser xmlParser) {
        this.carRepository = carRepository;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
        this.xmlParser = xmlParser;
    }

    @Override
    public boolean areImported() {
        return this.carRepository.count() > 0;
    }

    @Override
    public String readCarsFromFile() throws IOException {
        return Files
                .readString(Path.of(CARS_FILE_PATH));
    }

    @Override
    public String importCars() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();

        xmlParser
                .fromFile(CARS_FILE_PATH, CarSeedRootDto.class)
                .getCarSeedDtoList()
                .stream()
                .filter(carSeedDto -> {
                    boolean isValid = validationUtil.isValid(carSeedDto);

                    Optional<Car> carByPlateNumber = this.carRepository
                            .findByPlateNumber(carSeedDto.getPlateNumber());
                    if (carByPlateNumber.isPresent()) {
                        isValid = false;
                    }

                    sb.append(isValid
                            ?
                            String.format("Successfully imported car %s - %s",
                                    carSeedDto.getCarMake(), carSeedDto.getCarModel())
                            :
                            "Invalid car"
                    ).append(System.lineSeparator());
                    return isValid;
                })
                .map(carSeedDto -> mapper.map(carSeedDto, Car.class))
                .forEach(this.carRepository::save);
        return sb.toString();
    }
}
