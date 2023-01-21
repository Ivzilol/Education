package exam.service.impl;

import exam.model.dto.ImportTownDTO;
import exam.model.dto.ImportTownRootDTO;
import exam.model.entity.Town;
import exam.repository.TownRepository;
import exam.service.TownService;
import exam.util.UnmarshallerImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TownServiceImpl implements TownService {

    private static final String TOWN_FILE_PATH = "src/main/resources/files/xml/towns.xml";
    private final TownRepository townRepository;

    private final UnmarshallerImpl unmarshaller;

    private final ModelMapper modelMapper;

    private final Validator validator;

    public TownServiceImpl(TownRepository townRepository, UnmarshallerImpl unmarshaller, ModelMapper modelMapper, Validator validator) {
        this.townRepository = townRepository;
        this.unmarshaller = unmarshaller;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }

    @Override
    public boolean areImported() {
        return townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return Files.
                readString(Path.of(TOWN_FILE_PATH));
    }

    @Override
    public String importTowns() throws JAXBException, FileNotFoundException {
        ImportTownRootDTO importTownRootDTO =
                unmarshaller.fromFile(TOWN_FILE_PATH, ImportTownRootDTO.class);
        return importTownRootDTO
                .getTowns()
                .stream()
                .map(this::importTowns)
                .collect(Collectors.joining("\n"));

    }


    private String importTowns(ImportTownDTO dto) {
        Set<ConstraintViolation<ImportTownDTO>> errors =
                this.validator.validate(dto);
        if (!errors.isEmpty()) {
            return "Invalid town";
        } else {
            Optional<Town> optionalTown =
                    Optional.ofNullable(this.townRepository.findTownByName(dto.getName()));
            if (optionalTown.isPresent()) {
                return "Invalid town";
            }
        }

        Town town = this.modelMapper.map(dto, Town.class);
        this.townRepository.save(town);

        return String.format("Successfully imported Town %s", town.getName());
    }

    @Override
    public Town findTownByName(String townName) {
        Optional<Town> townByTownName = townRepository.getTownByName(townName);
        if (townByTownName.isEmpty()) {
            return null;
        } else {
            return townByTownName.get();
        }
    }

    @Override
    public Town findIdByTownName(String townName) {
        Optional<Town> townIdByTownName = Optional.ofNullable(townRepository.findIdByName(townName));
        if (townIdByTownName.isEmpty()) {
            return null;
        } else {
            return townIdByTownName.get();
        }
    }
}
