package exam.service.impl;

import com.google.gson.Gson;
import exam.model.dto.ExportLaptopsDTO;
import exam.model.dto.LaptopSeedDto;
import exam.model.entity.Laptop;
import exam.model.entity.Shop;
import exam.repository.LaptopRepository;
import exam.repository.ShopRepository;
import exam.service.LaptopService;
import exam.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class LaptopServiceImpl implements LaptopService {

    private final LaptopRepository laptopRepository;

    private final ShopRepository shopRepository;

    private static final String LAPTOP_PATH = "src/main/resources/files/json/laptops.json";

    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper mapper;

    public LaptopServiceImpl(LaptopRepository laptopRepository, ShopRepository shopRepository, Gson gson, ValidationUtil validationUtil, ModelMapper mapper) {
        this.laptopRepository = laptopRepository;
        this.shopRepository = shopRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
    }

    @Override
    public boolean areImported() {
        return this.laptopRepository.count() > 0;
    }

    @Override
    public String readLaptopsFileContent() throws IOException {
        return Files
                .readString(Path.of(LAPTOP_PATH));
    }

    @Override
    public String importLaptops() throws IOException {
        StringBuilder sb = new StringBuilder();

        Arrays.stream(gson
                        .fromJson(readLaptopsFileContent(), LaptopSeedDto[].class))
                .filter(laptopSeedDto -> {
                    boolean isValid = validationUtil.isValid(laptopSeedDto);
                    Optional<Laptop> byMacAddress = this.laptopRepository
                            .findByMacAddress(laptopSeedDto.getMacAddress());

                    if (byMacAddress.isPresent()) {
                        isValid = false;
                    }

                    sb.append(isValid
                            ?
                            String.format("Successfully imported Laptop %s - %.2f - %d - %d",
                                    laptopSeedDto.getMacAddress(), laptopSeedDto.getCpuSpeed(),
                                    laptopSeedDto.getRam(), laptopSeedDto.getStorage())
                            :
                            "Invalid Laptop"
                    ).append(System.lineSeparator());
                    return isValid;
                })
                .map(laptopSeedDto -> {
                    Laptop laptop = mapper.map(laptopSeedDto, Laptop.class);
                    Optional<Shop> shopByName = shopRepository.findByName(laptopSeedDto.getShop()
                            .getName());
                    laptop.setShop(shopByName.get());
                    return laptop;
                })
                .forEach(this.laptopRepository::save);
        return sb.toString();

    }

    @Override
    public String exportBestLaptops() {
        StringBuilder sb = new StringBuilder();

        List<ExportLaptopsDTO> bestLaptop = this.laptopRepository.findBestLaptops();

        bestLaptop
                .forEach(laptop -> {
                    sb.append(String.format("Laptop - %s%n" +
                                    "*Cpu speed - %.2f%n" +
                                    "**Ram - %d%n" +
                                    "***Storage - %d%n" +
                                    "****Price - %.2f%n" +
                                    "#Shop name - %s%n" +
                                    "##Town - %s",
                            laptop.getMacAddress(),
                            laptop.getCpuSpeed(),
                            laptop.getRam(),
                            laptop.getStorage(),
                            laptop.getPrice(),
                            laptop.getShopName(),
                            laptop.getTownName())
                    ).append(System.lineSeparator());
                });
        return sb.toString();
    }
}
