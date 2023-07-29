package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.AstronomerSeedRootDto;
import softuni.exam.models.entity.Astronomer;
import softuni.exam.models.entity.Star;
import softuni.exam.repository.AstronomerRepository;
import softuni.exam.repository.StarRepository;
import softuni.exam.service.AstronomerService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.Optional;

@Service
public class AstronomerServiceImpl implements AstronomerService {

    private final AstronomerRepository astronomerRepository;

    private final StarRepository starRepository;

    private static String ASTRONOMER_PATH = "src/main/resources/files/xml/astronomers.xml";

    private final XmlParser xmlParser;

    private final ValidationUtil validationUtil;

    private final ModelMapper mapper;

    public AstronomerServiceImpl(AstronomerRepository astronomerRepository, StarRepository starRepository, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper mapper) {
        this.astronomerRepository = astronomerRepository;
        this.starRepository = starRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
    }


    @Override
    public boolean areImported() {
        return this.astronomerRepository.count() > 0;
    }

    @Override
    public String readAstronomersFromFile() throws IOException {
        return Files
                .readString(Path.of(ASTRONOMER_PATH));
    }

    @Override
    public String importAstronomers() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();

        xmlParser
                .fromFile(ASTRONOMER_PATH, AstronomerSeedRootDto.class)
                .getAstronomerSeedDtoList()
                .stream()
                .filter(astronomerSeedDto -> {
                    boolean isValid = this.validationUtil.isValid(astronomerSeedDto);

                    Optional<Astronomer> byFullName = this.astronomerRepository
                            .findByFirstNameAndLastName(astronomerSeedDto.getFirstName(),
                                    astronomerSeedDto.getLastName());

                    if (byFullName.isPresent()) {
                        isValid = false;
                    }

                    Optional<Star> starById = this.starRepository
                            .findById(astronomerSeedDto.getObservingStarId());

                    if (starById.isEmpty()) {
                        isValid = false;
                    }

                    sb.append(isValid
                            ?
                            String.format(Locale.US, "Successfully imported astronomer %s %s - %.2f",
                                    astronomerSeedDto.getFirstName(), astronomerSeedDto.getLastName(),
                                    astronomerSeedDto.getAverageObservationHours())
                            :
                            "Invalid astronomer"
                    ).append(System.lineSeparator());
                    return isValid;
                })
                .map(astronomerSeedDto -> {
                    Astronomer astronomer = mapper.map(astronomerSeedDto, Astronomer.class);
                    Optional<Star> starById = this.starRepository
                            .findById(astronomerSeedDto.getObservingStarId());
                    astronomer.setObservingStar(starById.get());
                    return astronomer;
                })
                .forEach(this.astronomerRepository::save);
        return sb.toString();
    }
}
