package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ExportBestOffersDto;
import softuni.exam.models.dto.OfferSeedRootDto;
import softuni.exam.models.entity.Agent;
import softuni.exam.models.entity.Apartment;
import softuni.exam.models.entity.Offer;
import softuni.exam.repository.AgentRepository;
import softuni.exam.repository.ApartmentRepository;
import softuni.exam.repository.OfferRepository;
import softuni.exam.service.OfferService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;

    private final AgentRepository agentRepository;

    private final ApartmentRepository apartmentRepository;

    private static final String OFFER_PATH = "src/main/resources/files/xml/offers.xml";

    private final XmlParser xmlParser;

    private final ValidationUtil validationUtil;

    private final ModelMapper mapper;

    public OfferServiceImpl(OfferRepository offerRepository, AgentRepository agentRepository, ApartmentRepository apartmentRepository, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper mapper) {
        this.offerRepository = offerRepository;
        this.agentRepository = agentRepository;
        this.apartmentRepository = apartmentRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
    }

    @Override
    public boolean areImported() {
        return this.offerRepository.count() > 0;
    }

    @Override
    public String readOffersFileContent() throws IOException {
        return Files
                .readString(Path.of(OFFER_PATH));
    }

    @Override
    public String importOffers() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();

        xmlParser
                .fromFile(OFFER_PATH, OfferSeedRootDto.class)
                .getOfferSeedDtoList()
                .stream()
                .filter(offerSeedDto -> {
                    boolean isValid = validationUtil.isValid(offerSeedDto);

                    Optional<Agent> agentByName = this.agentRepository
                            .findByFirstName(offerSeedDto.getAgentName().getName());

                    if (agentByName.isEmpty()) {
                        isValid = false;
                    }

                    sb.append(isValid
                            ?
                            String.format("Successfully imported offer %.2f",
                                    offerSeedDto.getPrice())
                            :
                            "Invalid offer"
                    ).append(System.lineSeparator());
                    return isValid;
                })
                .map(offerSeedDto -> {
                    Offer offer = mapper.map(offerSeedDto, Offer.class);
                    Optional<Agent> agentByName = this.agentRepository
                            .findByFirstName(offerSeedDto.getAgentName().getName());
                    Optional<Apartment> apartmentById = this.apartmentRepository
                            .findById(offerSeedDto.getApartments().getId());
                    offer.setAgent(agentByName.get());
                    offer.setApartment(apartmentById.get());
                    return offer;
                })
                .forEach(this.offerRepository::save);
        return sb.toString();
    }

    @Override
    public String exportOffers() {
        StringBuilder sb = new StringBuilder();
        List<ExportBestOffersDto> bestOffers = this.agentRepository
                .findBestOffers();

        bestOffers.forEach(o -> {
            sb.append(String.format("Agent %s with offer № %d:%n" +
                            "        -Apartment area: %.2f%n" +
                            "        --Town: %s%n" +
                            "        ---Price: %.2f$%n",
                    o.getFullName(), o.getId(),
                    o.getArea(),
                    o.getTownName(),
                    o.getPrice()
            ));
        });
        return sb.toString();
    }
}
