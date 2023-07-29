package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.StarExportDto;
import softuni.exam.models.dto.StarSeedDto;
import softuni.exam.models.entity.Constellation;
import softuni.exam.models.entity.Star;
import softuni.exam.repository.ConstellationRepository;
import softuni.exam.repository.StarRepository;
import softuni.exam.service.StarService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class StarServiceImpl implements StarService {

    private final StarRepository starRepository;

    private final ConstellationRepository constellationRepository;

    private static String STAR_PATH = "src/main/resources/files/json/stars.json";

    private final Gson gson;

    private final ValidationUtil validationUtil;

    private final ModelMapper mapper;

    public StarServiceImpl(StarRepository starRepository, ConstellationRepository constellationRepository, Gson gson, ValidationUtil validationUtil, ModelMapper mapper) {
        this.starRepository = starRepository;
        this.constellationRepository = constellationRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
    }


    @Override
    public boolean areImported() {
        return this.starRepository.count() > 0;
    }

    @Override
    public String readStarsFileContent() throws IOException {
        return Files
                .readString(Path.of(STAR_PATH));
    }

    @Override
    public String importStars() throws IOException {
        StringBuilder sb = new StringBuilder();

        Arrays.stream(gson
                        .fromJson(readStarsFileContent(), StarSeedDto[].class))
                .filter(starSeedDto -> {
                    boolean isValid = validationUtil.isValid(starSeedDto);

                    Optional<Star> starByName = this.starRepository
                            .findByName(starSeedDto.getName());

                    if (starByName.isPresent()) {
                        isValid = false;
                    }

                    sb.append(isValid
                            ?
                            String.format(Locale.US, "Successfully imported star %s - %.2f light years",
                                    starSeedDto.getName(), starSeedDto.getLightYears())
                            :
                            "Invalid star"
                    ).append(System.lineSeparator());
                    return isValid;
                })
                .map(starSeedDto -> {
                    Star star = mapper.map(starSeedDto, Star.class);
                    Optional<Constellation> byId = this.constellationRepository
                            .findById(starSeedDto.getConstellation());
                    star.setConstellation(byId.get());
                    return star;
                })
                .forEach(this.starRepository::save);
        return sb.toString();
    }

    @Override
    public String exportStars() {
        StringBuilder sb = new StringBuilder();

        List<StarExportDto> stars = this.starRepository
                .findStarRedGiant();

        stars.forEach(s -> {
            sb.append(String.format(Locale.US, "Star: %s%n" +
                            "   *Distance: %.2f light years%n" +
                            "   **Description: %s%n" +
                            "   ***Constellation: %s%n",
                    s.getName(),
                    s.getlYears(),
                    s.getDescription(),
                    s.getNameConst()
            ));
        });
        return sb.toString();
    }
}
