package hiberspring.service.impl;

import com.google.gson.Gson;
import hiberspring.models.dtos.EmployeeCardSeedDto;
import hiberspring.models.entities.EmployeeCard;
import hiberspring.repository.EmployeeCardRepository;
import hiberspring.service.EmployeeCardService;
import hiberspring.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;

@Service
public class EmployeeCardServiceImpl implements EmployeeCardService {

    private final EmployeeCardRepository employeeCardRepository;
    private static final String CARD_PATH = "src/main/resources/files/employee-cards.json";
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper mapper;

    public EmployeeCardServiceImpl(EmployeeCardRepository employeeCardRepository, Gson gson, ValidationUtil validationUtil, ModelMapper mapper) {
        this.employeeCardRepository = employeeCardRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
    }


    @Override
    public Boolean employeeCardsAreImported() {
        return this.employeeCardRepository.count() > 0;
    }

    @Override
    public String readEmployeeCardsJsonFile() throws IOException {
        return Files
                .readString(Path.of(CARD_PATH));
    }

    @Override
    public String importEmployeeCards(String employeeCardsFileContent) throws IOException {
        StringBuilder sb = new StringBuilder();

        Arrays.stream(gson
                        .fromJson(readEmployeeCardsJsonFile(), EmployeeCardSeedDto[].class))
                .filter(employeeCardSeedDto -> {
                    boolean isValid = validationUtil.isValid(employeeCardSeedDto);

                    Optional<EmployeeCard> byNumber = this.employeeCardRepository
                            .findByNumber(employeeCardSeedDto.getNumber());
                    if (byNumber.isPresent()) {
                        isValid = false;
                    }

                    sb.append(isValid
                            ?
                            String.format("Successfully imported Employee Card %s",
                                    employeeCardSeedDto.getNumber())
                            :
                            "Error: Invalid data."
                    ).append(System.lineSeparator());
                    return isValid;
                })
                .map(employeeCardSeedDto -> mapper.map(employeeCardSeedDto, EmployeeCard.class))
                .forEach(this.employeeCardRepository::save);
        return sb.toString();
    }
}
