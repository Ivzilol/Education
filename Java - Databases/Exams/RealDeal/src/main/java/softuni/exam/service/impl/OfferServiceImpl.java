package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.OfferSeedRootDto;
import softuni.exam.models.entity.Car;
import softuni.exam.models.entity.Offer;
import softuni.exam.models.entity.Seller;
import softuni.exam.repository.CarRepository;
import softuni.exam.repository.OfferRepository;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.OfferService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;

    private final CarRepository carRepository;

    private final SellerRepository sellerRepository;

    private static final String OFFER_PATH = "src/main/resources/files/xml/offers.xml";

    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper mapper;

    public OfferServiceImpl(OfferRepository offerRepository, CarRepository carRepository, SellerRepository sellerRepository, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper mapper) {
        this.offerRepository = offerRepository;
        this.carRepository = carRepository;
        this.sellerRepository = sellerRepository;
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
                    LocalDateTime parse = LocalDateTime.parse(offerSeedDto.getAddedOn(),
                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                    boolean ifExist = this.offerRepository.findOfferByDescriptionAndAddedOn(offerSeedDto.getDescription(),
                            parse).isEmpty();

                    if (!ifExist) {
                        isValid = false;
                    }

                    sb.append(isValid
                            ?
                            String.format("Successfully import offer %s - %s",
                                    offerSeedDto.getAddedOn(), offerSeedDto.isHasGoldStatus())
                            :
                            "Invalid offer"
                    ).append(System.lineSeparator());
                    return isValid;
                }).map(offerSeedDto -> {
                    Offer offer = mapper.map(offerSeedDto, Offer.class);
                    Optional<Car> car = this.carRepository
                            .findById(offerSeedDto.getCar().getCardId());
                    Optional<Seller> seller = this.sellerRepository
                            .findById(offerSeedDto.getSeller().getSellerId());
                    offer.setCar(car.get());
                    offer.setSeller(seller.get());
                    return offer;
                })
                .forEach(this.offerRepository::save);
        return sb.toString();
    }
}
