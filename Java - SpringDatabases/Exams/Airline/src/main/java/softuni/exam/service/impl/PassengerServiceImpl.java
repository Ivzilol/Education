package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ExportPassengerDto;
import softuni.exam.models.dto.PassengerSeedDto;
import softuni.exam.models.entity.Passenger;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.PassengerRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.PassengerService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository passengerRepository;
    private final TownRepository townRepository;
    private static final String PASSENGER_PATH = "src/main/resources/files/json/passengers.json";
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper mapper;

    public PassengerServiceImpl(PassengerRepository passengerRepository, TownRepository townRepository, Gson gson, ValidationUtil validationUtil, ModelMapper mapper) {
        this.passengerRepository = passengerRepository;
        this.townRepository = townRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
    }


    @Override
    public boolean areImported() {
        return this.passengerRepository.count() > 0;
    }

    @Override
    public String readPassengersFileContent() throws IOException {
        return Files
                .readString(Path.of(PASSENGER_PATH));
    }

    @Override
    public String importPassengers() throws IOException {
        StringBuilder sb = new StringBuilder();

        Arrays.stream(gson
                        .fromJson(readPassengersFileContent(), PassengerSeedDto[].class))
                .filter(passengerSeedDto -> {
                    boolean isValid = validationUtil.isValid(passengerSeedDto);

                    Optional<Passenger> byEmail =
                            this.passengerRepository.findByEmail(passengerSeedDto.getEmail());
                    if (byEmail.isPresent()) {
                        isValid = false;
                    }

                    sb.append(isValid
                            ?
                            String.format("Successfully imported Passenger %s - %s",
                                    passengerSeedDto.getLastName(), passengerSeedDto.getEmail())
                            :
                            "Invalid Passenger"
                    ).append(System.lineSeparator());
                    return isValid;
                })
                .map(passengerSeedDto -> {
                    Passenger passenger = mapper.map(passengerSeedDto, Passenger.class);
                    Optional<Town> findByName = this.townRepository.findByName(passengerSeedDto.getTown());
                    passenger.setTown(findByName.get());
                    return passenger;
                })
                .forEach(this.passengerRepository::save);
        return sb.toString();
    }

    @Override
    public String getPassengersOrderByTicketsCountDescendingThenByEmail() {
        StringBuilder sb = new StringBuilder();

        List<ExportPassengerDto> exportPassenger = this.passengerRepository.exportPassenger();

        exportPassenger
                .forEach(p -> sb.append(String.format("Passenger %s %s%n" +
                                "    Email - %s%n" +
                                "    Phone - %s%n" +
                                "    Number of tickets - %d",
                        p.getFirstName(), p.getLastName(),
                        p.getEmail(),
                        p.getPhoneNumber(),
                        p.getCountTickets()
                )).append(System.lineSeparator()).append(System.lineSeparator()));
        return sb.toString();
    }
}
