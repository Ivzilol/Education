package exam.service.impl;

import exam.model.dto.ShopSeedRootDto;
import exam.model.entity.Shop;
import exam.model.entity.Town;
import exam.repository.ShopRepository;
import exam.repository.TownRepository;
import exam.service.ShopService;
import exam.util.ValidationUtil;
import exam.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;


@Service
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;

    private final TownRepository townRepository;

    private static final String SHOP_PATH = "src/main/resources/files/xml/shops.xml";

    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper mapper;


    public ShopServiceImpl(ShopRepository shopRepository, TownRepository townRepository, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper mapper) {
        this.shopRepository = shopRepository;
        this.townRepository = townRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
    }


    @Override
    public boolean areImported() {
        return shopRepository.count() > 0;
    }

    @Override
    public String readShopsFileContent() throws IOException {
        return Files
                .readString(Path.of(SHOP_PATH));
    }

    @Override
    public String importShops() throws JAXBException {
        StringBuilder sb = new StringBuilder();

        ShopSeedRootDto shopSeedRootDto = xmlParser
                .fromFile(SHOP_PATH, ShopSeedRootDto.class);

        shopSeedRootDto.getShopSeedDtoList()
                .stream()
                .filter(shopSeedDto -> {
                    boolean isValid = validationUtil.isValid(shopSeedDto);

                    Optional<Shop> shopByName = this.shopRepository.findByName(shopSeedDto.getName());

                    if (shopByName.isPresent()) {
                        isValid = false;
                    }
                    sb.append(isValid
                                    ?
                                    String.format("Successfully imported Shop %s - %.0f",
                                            shopSeedDto.getName(), shopSeedDto.getIncome())
                                    :
                                    "Invalid shop")
                            .append(System.lineSeparator());
                    return isValid;
                })
                .map(shopSeedDto -> {
                    Shop shop = mapper.map(shopSeedDto, Shop.class);
                    Optional<Town> townByName = this.townRepository.findByName(shopSeedDto.getTownName().getName());
                    shop.setTown(townByName.get());
                    return shop;
                })
                .forEach(this.shopRepository::save);
        return sb.toString();
    }
}
