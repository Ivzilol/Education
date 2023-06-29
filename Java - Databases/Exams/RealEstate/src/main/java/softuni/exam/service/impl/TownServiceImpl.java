package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.TownsSeedDto;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.TownService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;

@Service
public class TownServiceImpl implements TownService {

    private final TownRepository townRepository;

    private static final String TOWNS_PATH = "src/main/resources/files/json/towns.json";
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper mapper;

    public TownServiceImpl(TownRepository townRepository, Gson gson, ValidationUtil validationUtil, ModelMapper mapper) {
        this.townRepository = townRepository;
        this.gson = gson;
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
                .readString(Path.of(TOWNS_PATH));
    }

    @Override
    public String importTowns() throws IOException {
        StringBuilder sb = new StringBuilder();

        Arrays.stream(gson
                        .fromJson(readTownsFileContent(), TownsSeedDto[].class))
                .filter(townsSeedDto -> {
                    boolean isValid = validationUtil.isValid(townsSeedDto);

                    Optional<Town> townByName =
                            townRepository.findTownByTownName(townsSeedDto.getTownName());

                    if (townByName.isPresent()) {
                        isValid = false;
                    }

                    sb.append(isValid
                                    ?
                                    String.format("Successfully imported town %s - %d", townsSeedDto.getTownName(),
                                            townsSeedDto.getPopulation())
                                    :
                                    "Invalid town")
                            .append(System.lineSeparator());
                    return isValid;
                })
                .map(townsSeedDto -> mapper.map(townsSeedDto, Town.class))
                .forEach(townRepository::save);
        return sb.toString();
    }
}
