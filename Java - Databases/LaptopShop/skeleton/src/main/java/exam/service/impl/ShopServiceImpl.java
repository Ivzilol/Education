package exam.service.impl;

import exam.model.dto.ImportShopRootDto;
import exam.model.dto.ImportShopsDTO;
import exam.model.entity.Shop;
import exam.model.entity.Town;
import exam.repository.ShopRepository;
import exam.repository.TownRepository;
import exam.service.ShopService;
import exam.service.TownService;
import exam.util.UnmarshallerImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ShopServiceImpl implements ShopService {

    private static final String SHOP_FILE_PATH = "src/main/resources/files/xml/shops.xml";
    private final ShopRepository shopRepository;
    private final TownRepository townRepository;

    private final TownService townService;
    private final UnmarshallerImpl unmarshaller;
    private final Validator validator;
    private final ModelMapper modelMapper;

    public ShopServiceImpl(ShopRepository shopRepository, TownRepository townRepository, TownService townService, UnmarshallerImpl unmarshaller, Validator validator, ModelMapper modelMapper) {
        this.shopRepository = shopRepository;
        this.townRepository = townRepository;
        this.townService = townService;
        this.unmarshaller = unmarshaller;
        this.validator = validator;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return this.shopRepository.count() > 0;
    }

    @Override
    public String readShopsFileContent() throws IOException {
        return Files.
                readString(Path.of(SHOP_FILE_PATH));
    }

    @Override
    public String importShops() throws JAXBException, FileNotFoundException {
        ImportShopRootDto importShopRootDto =
                unmarshaller.fromFile(SHOP_FILE_PATH, ImportShopRootDto.class);

        return importShopRootDto
                .getShops()
                .stream()
                .map((this::importShops))
                .collect(Collectors.joining());

    }

    private String importShops (ImportShopsDTO dto) {
        Set<ConstraintViolation<ImportShopsDTO>> errors =
                this.validator.validate(dto);
        if (!errors.isEmpty()) {
            return "Invalid shop";
        } else {
            Optional<Shop> optionalShop =
                this.shopRepository.findShopByNameAndTown(
                        dto.getName(),
                        Optional.ofNullable(this.townService.findTownByName(dto.getTown().getName())));

            if (optionalShop.isPresent()) {
                return "Invalid shop";
            }

            Shop shop = this.modelMapper.map(dto, Shop.class);
            Town townByName = this.townService.findTownByName(dto.getTown().getName());
            shop.setTown(townByName);
            this.shopRepository.save(shop);

            return String.format("Successfully imported Shop %s - %.0f\n",
                    shop.getName(), shop.getIncome());
        }


    }
}
