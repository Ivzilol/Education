package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.SellerSeedRootDto;
import softuni.exam.models.entity.Seller;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.SellerService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class SellerServiceImpl implements SellerService {


    private final SellerRepository sellerRepository;

    private static final String SELLER_PATH = "src/main/resources/files/xml/sellers.xml";

    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper mapper;

    public SellerServiceImpl(SellerRepository sellerRepository, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper mapper) {
        this.sellerRepository = sellerRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
    }

    @Override
    public boolean areImported() {
        return this.sellerRepository.count() > 0;
    }

    @Override
    public String readSellersFromFile() throws IOException {
        return Files
                .readString(Path.of(SELLER_PATH));
    }

    @Override
    public String importSellers() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();

        xmlParser.fromFile(SELLER_PATH, SellerSeedRootDto.class)
                .getSellerSeedDtoList()
                .stream()
                .filter(sellerSeedDto -> {
                    boolean isValid = validationUtil.isValid(sellerSeedDto);
                    Optional<Seller> seller = this.sellerRepository
                            .findByEmail(sellerSeedDto.getEmail());
                    if (seller.isPresent()) {
                        isValid = false;
                    }

                    sb.append(isValid
                            ?
                            String.format("Successfully import seller %s - %s",
                                    sellerSeedDto.getFirstName(), sellerSeedDto.getEmail())
                            :
                            "Invalid seller"
                    ).append(System.lineSeparator());
                    return isValid;
                }).map(sellerSeedDto -> mapper.map(sellerSeedDto, Seller.class))
                .forEach(this.sellerRepository::save);
        return sb.toString();
    }
}
