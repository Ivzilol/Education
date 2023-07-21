package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.TicketSeedRootDto;
import softuni.exam.models.entity.Passenger;
import softuni.exam.models.entity.Plane;
import softuni.exam.models.entity.Ticket;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.PassengerRepository;
import softuni.exam.repository.PlaneRepository;
import softuni.exam.repository.TicketRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.TicketService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    private final TownRepository townRepository;

    private final PlaneRepository planeRepository;

    private final PassengerRepository passengerRepository;

    private static final String TICKET_PATH = "src/main/resources/files/xml/tickets.xml";

    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper mapper;

    public TicketServiceImpl(TicketRepository ticketRepository, TownRepository townRepository, PlaneRepository planeRepository, PassengerRepository passengerRepository, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper mapper) {
        this.ticketRepository = ticketRepository;
        this.townRepository = townRepository;
        this.planeRepository = planeRepository;
        this.passengerRepository = passengerRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
    }

    @Override
    public boolean areImported() {
        return this.ticketRepository.count() > 0;
    }

    @Override
    public String readTicketsFileContent() throws IOException {
        return Files
                .readString(Path.of(TICKET_PATH));
    }

    @Override
    public String importTickets() throws JAXBException {
        StringBuilder sb = new StringBuilder();

        xmlParser
                .fromFile(TICKET_PATH, TicketSeedRootDto.class)
                .getTicketSeedDtoList()
                .stream()
                .filter(ticketSeedDto -> {
                    boolean isValid = validationUtil.isValid(ticketSeedDto);

                    Optional<Ticket> bySerialNumber = this.ticketRepository
                            .findBySerialNumber(ticketSeedDto.getSerialNumber());
                    if (bySerialNumber.isPresent()) {
                        isValid = false;
                    }
                    sb.append(isValid
                            ?
                            String.format("Successfully imported Ticket %s - %s",
                                    ticketSeedDto.getFromTown().getName(),
                                    ticketSeedDto.getToTown().getName())
                            :
                            "Invalid Ticket"
                    ).append(System.lineSeparator());
                    return isValid;
                })
                .map(ticketSeedDto -> {
                    Ticket ticket = mapper.map(ticketSeedDto, Ticket.class);
                    Optional<Town> townByNameFromTown = this.townRepository
                            .findByName(ticketSeedDto.getFromTown().getName());
                    Optional<Town> townByNameToTown = this
                            .townRepository.findByName(ticketSeedDto.getToTown().getName());
                    Optional<Passenger> passengerByEmail = this.passengerRepository
                            .findByEmail(ticketSeedDto.getPassengerEmail().getEmail());
                    Optional<Plane> planeByRegisterNumber = this.planeRepository
                            .findByRegisterNumber(ticketSeedDto.getPlaneRegisterNumber().getRegisterNumber());
                    ticket.setFromTown(townByNameFromTown.get());
                    ticket.setToTown(townByNameToTown.get());
                    ticket.setPassenger(passengerByEmail.get());
                    ticket.setPlane(planeByRegisterNumber.get());
                    return ticket;
                })
                .forEach(this.ticketRepository::save);
        return sb.toString();
    }
}
