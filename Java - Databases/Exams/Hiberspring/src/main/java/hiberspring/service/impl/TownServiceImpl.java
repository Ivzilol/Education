package hiberspring.service.impl;

import com.google.gson.Gson;
import hiberspring.models.dtos.TownSeedDto;
import hiberspring.models.entities.Town;
import hiberspring.repository.TownRepository;
import hiberspring.service.TownService;
import hiberspring.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.security.sasl.SaslServer;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class TownServiceImpl implements TownService {

    private final TownRepository townRepository;

    private static final String TOWN_PATH = "src/main/resources/files/towns.json";

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
    public Boolean townsAreImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsJsonFile() throws IOException {
        return Files
                .readString(Path.of(TOWN_PATH));
    }

    @Override
    public String importTowns(String townsFileContent) throws IOException {
        StringBuilder sb = new StringBuilder();

        Arrays.stream(gson
                        .fromJson(readTownsJsonFile(), TownSeedDto[].class))
                .filter(townSeedDto -> {
                    boolean isValid = validationUtil.isValid(townSeedDto);

                    sb.append(isValid
                            ?
                            String.format("Successfully imported Town %s",
                                    townSeedDto.getName())
                            :
                            "Error: Invalid data."
                    ).append(System.lineSeparator());
                    return isValid;
                })
                .map(townSeedDto -> mapper.map(townSeedDto, Town.class))
                .forEach(this.townRepository::save);
        return sb.toString();
    }
}
