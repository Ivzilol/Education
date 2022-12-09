package softuni.exam.service.impl;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportOfferRootDTO;
import softuni.exam.models.dto.ImportOffersDTO;
import softuni.exam.models.entity.Agent;
import softuni.exam.models.entity.Apartment;
import softuni.exam.models.entity.ApartmentType;
import softuni.exam.models.entity.Offer;
import softuni.exam.repository.AgentRepository;
import softuni.exam.repository.ApartmentRepository;
import softuni.exam.repository.OfferRepository;
import softuni.exam.service.OfferService;
import softuni.exam.util.UnmarshallerImpl;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {

    private static final String OFFERS_FILE_PATH = "src/main/resources/files/xml/offers.xml";
    private final OfferRepository offerRepository;

    private final ApartmentRepository apartmentRepository;

    private final AgentRepository agentRepository;
    private final UnmarshallerImpl unmarshaller;

    private final Validator validator;
    private final ModelMapper modelMapper;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository,
                            ApartmentRepository apartmentRepository, AgentRepository agentRepository,
                            UnmarshallerImpl unmarshaller,
                            ModelMapper modelMapper) {
        this.offerRepository = offerRepository;
        this.apartmentRepository = apartmentRepository;
        this.agentRepository = agentRepository;

        this.unmarshaller = unmarshaller;

        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();

        this.modelMapper = modelMapper;



    }

    @Override
    public boolean areImported() {
        return offerRepository.count() > 0;
    }

    @Override
    public String readOffersFileContent() throws IOException {
        return Files
                .readString(Path.of(OFFERS_FILE_PATH));
    }

    @Override
    public String importOffers() throws IOException, JAXBException {
        ImportOfferRootDTO offerDTOs =
                unmarshaller.fromFile(OFFERS_FILE_PATH, ImportOfferRootDTO.class);

        return offerDTOs
                .getOffers()
                .stream()
                .map(this::importOffer)
                .collect(Collectors.joining());

    }

    private String importOffer(ImportOffersDTO dto) {
        Set<ConstraintViolation<ImportOffersDTO>> errors =
                this.validator.validate(dto);
        if (!errors.isEmpty()) {
            return "Invalid offer";
        }
        Optional<Agent> optionalAgent =
                Optional.ofNullable(this.agentRepository.getAgentByFirstName(dto.getName().getName()));
        if (optionalAgent.isEmpty()) {
            return "Invalid offer";
        }
        Optional<Apartment> byId = this.apartmentRepository.findById(Math.toIntExact(dto.getApartment().getId()));
        Offer offer = modelMapper.map(dto, Offer.class);
        offer.setAgents(optionalAgent.get());
        offer.setApartments(byId.get());

        this.offerRepository.save(offer);

        return String.format("Successfully imported offer %.2f", offer.getPrice());

    }

    @Override
    public String exportOffers() {
        StringBuilder sb = new StringBuilder();
        List<Offer> offerList = offerRepository
                .findAllByApartments_ApartmentTypeOrderByApartments_AreaDescPriceAsc(ApartmentType.three_rooms);

        offerList
                .forEach(offer -> {
                    sb.append(String.format("Agent %s %s with offer №%d:%n" +
                                            "   -Apartment area: %.2f%n" +
                                            "   --Town: %s%n" +
                                            "   ---Price: %.2f$",
                                    offer.getAgents().getFirstName(),
                                    offer.getAgents().getLastName(),
                                    offer.getId(),
                                    offer.getApartments().getArea(),
                                    offer.getApartments().getTown().getTownName(),
                                    offer.getPrice()))
                            .append(System.lineSeparator());
                });

        return sb.toString();
    }
}
