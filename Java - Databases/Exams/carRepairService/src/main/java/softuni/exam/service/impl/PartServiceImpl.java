package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.PartSeedDto;
import softuni.exam.models.entity.Part;
import softuni.exam.repository.PartRepository;
import softuni.exam.service.PartService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;

@Service
public class PartServiceImpl implements PartService {

    private static String PART_FILE_PATH = "src/main/resources/files/json/parts.json";
    private final PartRepository partRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;

    private final ModelMapper mapper;

    public PartServiceImpl(PartRepository partRepository, Gson gson, ValidationUtil validationUtil, ModelMapper mapper) {
        this.partRepository = partRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
    }


    @Override
    public boolean areImported() {
        return this.partRepository.count() > 0;
    }

    @Override
    public String readPartsFileContent() throws IOException {
        return Files
                .readString(Path.of(PART_FILE_PATH));
    }

    @Override
    public String importParts() throws IOException {
        StringBuilder sb = new StringBuilder();

        Arrays.stream(gson
                        .fromJson(readPartsFileContent(), PartSeedDto[].class))
                .filter(partSeedDto -> {
                    boolean isValid = validationUtil.isValid(partSeedDto);

                    Optional<Part> byPartName = this.partRepository
                            .findByPartName(partSeedDto.getPartName());

                    if (byPartName.isPresent()) {
                        isValid = false;
                    }

                    sb.append(isValid
                            ?
                            String.format("Successfully imported part %s - %.2f",
                                    partSeedDto.getPartName(), partSeedDto.getPrice())
                            :
                            "Invalid part"
                    ).append(System.lineSeparator());
                    return isValid;
                })
                .map(partSeedDto -> mapper.map(partSeedDto, Part.class))
                .forEach(this.partRepository::save);
        return sb.toString();
    }
}
