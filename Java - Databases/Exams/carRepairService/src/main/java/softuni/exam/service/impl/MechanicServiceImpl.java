package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.MechanicsSeedDto;
import softuni.exam.models.entity.Mechanic;
import softuni.exam.repository.MechanicRepository;
import softuni.exam.service.MechanicService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;

@Service
public class MechanicServiceImpl implements MechanicService {

    private static String MECHANIC_FILE_PATH = "src/main/resources/files/json/mechanics.json";

    private final MechanicRepository mechanicRepository;

    private final Gson gson;
    private final ValidationUtil validationUtil;

    private final ModelMapper mapper;

    public MechanicServiceImpl(MechanicRepository mechanicRepository, Gson gson, ValidationUtil validationUtil, ModelMapper mapper) {
        this.mechanicRepository = mechanicRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
    }

    @Override
    public boolean areImported() {
        return this.mechanicRepository.count() > 0;
    }

    @Override
    public String readMechanicsFromFile() throws IOException {
        return Files
                .readString(Path.of(MECHANIC_FILE_PATH));
    }

    @Override
    public String importMechanics() throws IOException {
        StringBuilder sb = new StringBuilder();


        Arrays.stream(gson
                        .fromJson(readMechanicsFromFile(), MechanicsSeedDto[].class))
                .filter(mechanicsSeedDto -> {
                    boolean isValid = validationUtil.isValid(mechanicsSeedDto);

                    Optional<Mechanic> byEmail = this.mechanicRepository
                            .findByEmail(mechanicsSeedDto.getEmail());
                    if (byEmail.isPresent()) {
                        isValid = false;
                    }

                    sb.append(isValid
                            ?
                            String.format("Successfully imported mechanic %s %s",
                                    mechanicsSeedDto.getFirstName(), mechanicsSeedDto.getLastName())
                            :
                            "Invalid mechanic"
                    ).append(System.lineSeparator());
                    return isValid;
                }).map(mechanicsSeedDto -> mapper.map(mechanicsSeedDto, Mechanic.class))
                .forEach(this.mechanicRepository::save);
        return sb.toString();
    }
}
