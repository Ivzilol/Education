package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.apache.tomcat.util.buf.UEncoder;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.TownSeedDto;
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
    private static final String TOWN_PATH = "src/main/resources/files/json/towns.json";
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
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return Files
                .readString(Path.of(TOWN_PATH));
    }

    @Override
    public String importTowns() throws IOException {
        StringBuilder sb = new StringBuilder();

        Arrays.stream(gson
                        .fromJson(readTownsFileContent(), TownSeedDto[].class))
                .filter(townSeedDto -> {
                    boolean isValid = validationUtil.isValid(townSeedDto);

                    Optional<Town> townByName = this.townRepository.findByName(townSeedDto.getName());

                    if (townByName.isPresent()) {
                        isValid = false;
                    }

                    sb.append(isValid
                            ?
                            String.format("Successfully imported Town %s - %d",
                                    townSeedDto.getName(), townSeedDto.getPopulation())
                            :
                            "Invalid Town"
                    ).append(System.lineSeparator());
                    return isValid;
                })
                .map(townSeedDto -> mapper.map(townSeedDto, Town.class))
                .forEach(this.townRepository::save);
        return sb.toString();
    }
}
