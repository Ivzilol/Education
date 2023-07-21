package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.PlaneSeedRootDto;
import softuni.exam.models.entity.Plane;
import softuni.exam.repository.PlaneRepository;
import softuni.exam.service.PlaneService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class PlaneServiceImpl implements PlaneService {

    private final PlaneRepository planeRepository;
    private static final String PLANE_PATH = "src/main/resources/files/xml/planes.xml";
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper mapper;


    public PlaneServiceImpl(PlaneRepository planeRepository, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper mapper) {
        this.planeRepository = planeRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
    }

    @Override
    public boolean areImported() {
        return this.planeRepository.count() > 0;
    }

    @Override
    public String readPlanesFileContent() throws IOException {
        return Files
                .readString(Path.of(PLANE_PATH));
    }

    @Override
    public String importPlanes() throws JAXBException {
        StringBuilder sb = new StringBuilder();

        xmlParser
                .fromFile(PLANE_PATH, PlaneSeedRootDto.class)
                .getPlaneSeedDtoList()
                .stream()
                .filter(planeSeedDto -> {
                    boolean isValid = validationUtil.isValid(planeSeedDto);

                    Optional<Plane> plane = this.planeRepository
                            .findByRegisterNumber(planeSeedDto.getRegisterNumber());
                    if (plane.isPresent()) {
                        isValid = false;
                    }

                    sb.append(isValid
                            ?
                            String.format("Successfully imported Plane %s",
                                    planeSeedDto.getRegisterNumber())
                            :
                            "Invalid Plane"
                    ).append(System.lineSeparator());
                    return isValid;
                }).map(planeSeedDto -> mapper.map(planeSeedDto, Plane.class))
                .forEach(this.planeRepository::save);

        return sb.toString();
    }
}
