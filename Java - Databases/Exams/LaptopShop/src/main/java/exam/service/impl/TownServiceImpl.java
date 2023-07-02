package exam.service.impl;

import exam.model.dto.TownSeedRootDto;
import exam.model.entity.Town;
import exam.repository.TownRepository;
import exam.service.TownService;
import exam.util.ValidationUtil;
import exam.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class TownServiceImpl implements TownService {

    private final TownRepository townRepository;
    private static final String PATH_TOWN = "src/main/resources/files/xml/towns.xml";
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper mapper;

    public TownServiceImpl(TownRepository townRepository, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper mapper) {
        this.townRepository = townRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
    }


    @Override
    public boolean areImported() {
        return townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return Files
                .readString(Path.of(PATH_TOWN));
    }

    @Override
    public String importTowns() throws JAXBException {
        StringBuilder sb = new StringBuilder();

        xmlParser
                .fromFile(PATH_TOWN, TownSeedRootDto.class)
                .getTownSeedDtoList()
                .stream()
                .filter(townSeedDto -> {
                    boolean isValid = validationUtil.isValid(townSeedDto);

                    sb.append(isValid
                                    ?
                                    String.format("Successfully imported Town %s", townSeedDto.getName())
                                    :
                                    "Invalid town")
                            .append(System.lineSeparator());
                    return isValid;
                })
                .map(townSeedDto -> mapper.map(townSeedDto, Town.class))
                .forEach(this.townRepository::save);
        return sb.toString();
    }
}
