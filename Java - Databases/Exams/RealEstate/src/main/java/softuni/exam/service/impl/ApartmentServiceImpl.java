package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ApartmentSeedRootDto;
import softuni.exam.models.entity.Apartment;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.ApartmentRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.ApartmentService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class ApartmentServiceImpl implements ApartmentService {

    private final ApartmentRepository apartmentRepository;

    private final TownRepository townRepository;
    private static final String APARTMENT_PATH = "src/main/resources/files/xml/apartments.xml";
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;

    private final ModelMapper mapper;

    public ApartmentServiceImpl(ApartmentRepository apartmentRepository, TownRepository townRepository, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper mapper) {
        this.apartmentRepository = apartmentRepository;
        this.townRepository = townRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
    }

    @Override
    public boolean areImported() {
        return apartmentRepository.count() > 0;
    }

    @Override
    public String readApartmentsFromFile() throws IOException {
        return Files
                .readString(Path.of(APARTMENT_PATH));
    }

    @Override
    public String importApartments() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();

        xmlParser
                .fromFile(APARTMENT_PATH, ApartmentSeedRootDto.class)
                .getApartmentsSeedDtoList()
                .stream()
                .filter(apartmentsSeedDto -> {
                    boolean isValid = validationUtil.isValid(apartmentsSeedDto);

                    boolean isExist = this.apartmentRepository
                            .findApartmentByAreaAndTown(apartmentsSeedDto.getArea(),
                                    townRepository.findTownByTownName(apartmentsSeedDto.getTown()))
                            .isEmpty();
                    if (!isExist) {
                        isValid = false;
                    }
                    sb
                            .append(isValid
                                    ? String.format("Successfully import apartment %s - %.2f",
                                    apartmentsSeedDto.getApartmentType(), apartmentsSeedDto.getArea())
                                    : "Invalid apartment")
                            .append(System.lineSeparator());
                    return isValid;
                })
                .map(apartmentsSeedDto -> {
                    Apartment apartment = mapper.map(apartmentsSeedDto, Apartment.class);
                    Optional<Town> town = this.townRepository.findTownByTownName(apartmentsSeedDto.getTown());
                    apartment.setTown(town.get());
                    return apartment;
                })
                .forEach(apartmentRepository::save);
        return sb.toString();
    }
}
