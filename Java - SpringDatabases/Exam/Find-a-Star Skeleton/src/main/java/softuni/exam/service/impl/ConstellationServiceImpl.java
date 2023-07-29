package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ConstellationSeedDto;
import softuni.exam.models.entity.Constellation;
import softuni.exam.repository.ConstellationRepository;
import softuni.exam.service.ConstellationService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;

@Service
public class ConstellationServiceImpl implements ConstellationService {

    private final ConstellationRepository constellationRepository;

    private static String CONSTELLATION_PATH = "src/main/resources/files/json/constellations.json";

    private final Gson gson;

    private final ValidationUtil validationUtil;

    private final ModelMapper mapper;

    public ConstellationServiceImpl(ConstellationRepository constellationRepository, Gson gson, ValidationUtil validationUtil, ModelMapper mapper) {
        this.constellationRepository = constellationRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
    }


    @Override
    public boolean areImported() {
        return this.constellationRepository.count() > 0;
    }

    @Override
    public String readConstellationsFromFile() throws IOException {
        return Files
                .readString(Path.of(CONSTELLATION_PATH));
    }

    @Override
    public String importConstellations() throws IOException {
        StringBuilder sb = new StringBuilder();

        Arrays.stream(gson
                        .fromJson(readConstellationsFromFile(), ConstellationSeedDto[].class))
                .filter(constellationSeedDto -> {
                    boolean isValid = validationUtil.isValid(constellationSeedDto);

                    Optional<Constellation> byName = this.constellationRepository
                            .findByName(constellationSeedDto.getName());

                    if (byName.isPresent()) {
                        isValid = false;
                    }

                    sb.append(isValid
                            ?
                            String.format("Successfully imported constellation %s - %s",
                                    constellationSeedDto.getName(), constellationSeedDto.getDescription())
                            :
                            "Invalid constellation"
                    ).append(System.lineSeparator());
                    return isValid;
                })
                .map(constellationSeedDto -> mapper.map(constellationSeedDto, Constellation.class))
                .forEach(this.constellationRepository::save);
        return sb.toString();
    }
}
