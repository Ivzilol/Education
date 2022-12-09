package softuni.exam.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportTownDTO;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.TownService;
import softuni.exam.util.ValidatorUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Service
public class TownServiceImpl implements TownService {

    private static final String TOWNS_FILE_PATH = "src/main/resources/files/json/towns.json";

    private final TownRepository townRepository;
    private final Gson json;
    private final ValidatorUtil validator;
    private final ModelMapper modelMapper;


    @Autowired
    public TownServiceImpl(TownRepository townRepository, ValidatorUtil validator) {
        this.townRepository = townRepository;
        this.validator = validator;
        this.json = new GsonBuilder().create();
        this.modelMapper = new ModelMapper();
    }

    @Override
    public boolean areImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return Files
                .readString(Path.of(TOWNS_FILE_PATH));
    }

    @Override
    public String importTowns() throws IOException {
        StringBuilder sb = new StringBuilder();

        Arrays.stream(json
                        .fromJson(readTownsFileContent(), ImportTownDTO[].class))
                .filter(importTownDTO -> {
                    boolean isValid = validator.isValid(importTownDTO);
                    sb.append(isValid
                                    ? String.format("Successfully imported town %s - %s", importTownDTO.getTownName()
                                    , importTownDTO.getPopulation())
                                    : "Invalid town")
                            .append(System.lineSeparator());

                    return isValid;
                })
                .map(importTownDTO -> modelMapper.map(importTownDTO, Town.class))
                .forEach(townRepository::save);

        return sb.toString();
    }

    public Town findTownByName(String townName) {
        Optional<Town> townByTownName = townRepository.getTownByTownName(townName);
        if (townByTownName.isEmpty()) {
            return null;
        }
        return townByTownName.get();
    }
}
